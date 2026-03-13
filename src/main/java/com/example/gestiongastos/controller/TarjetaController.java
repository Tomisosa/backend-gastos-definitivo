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
        // Revisamos si la tarjeta viene con un usuario asignado desde el frontend
        if (tarjeta.getUsuario() != null && tarjeta.getUsuario().getId() != null) {
            Usuario u = usuarioRepository.findById(tarjeta.getUsuario().getId()).orElse(null);
            if (u == null) {
                return ResponseEntity.badRequest().body("Usuario no encontrado en la base de datos.");
            }
            tarjeta.setUsuario(u); // Asignamos el usuario completo a la tarjeta
        } else {
             // Si el frontend no mandó el usuario correctamente formateado, avisamos.
             return ResponseEntity.badRequest().body("Falta enviar el ID del usuario.");
        }
        
        return ResponseEntity.ok(tarjetaRepository.save(tarjeta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTarjeta(@PathVariable Long id) {
        tarjetaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}