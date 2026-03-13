package com.example.gestiongastos.controller;

import com.example.gestiongastos.model.Tarjeta;
import com.example.gestiongastos.model.Usuario;
import com.example.gestiongastos.repository.TarjetaRepository;
import com.example.gestiongastos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarjetas")
@CrossOrigin(origins = "*")
public class TarjetaController {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuario/{usuarioId}")
    public List<Tarjeta> getTarjetasUsuario(@PathVariable Long usuarioId) {
        return tarjetaRepository.findByUsuarioId(usuarioId);
    }

    @PostMapping
    public ResponseEntity<?> crearTarjeta(@RequestBody Tarjeta tarjeta) {
        // Le enseñamos a Java a vincular la tarjeta con tu usuario
        if (tarjeta.getUsuario() != null && tarjeta.getUsuario().getId() != null) {
            Usuario u = usuarioRepository.findById(tarjeta.getUsuario().getId()).orElse(null);
            if (u == null) {
                return ResponseEntity.badRequest().body("Usuario no encontrado");
            }
            tarjeta.setUsuario(u);
        }
        return ResponseEntity.ok(tarjetaRepository.save(tarjeta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTarjeta(@PathVariable Long id) {
        tarjetaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}