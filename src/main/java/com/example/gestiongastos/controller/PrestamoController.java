package com.example.gestiongastos.controller;
import com.example.gestiongastos.model.Prestamo;
import com.example.gestiongastos.model.Usuario;
import com.example.gestiongastos.repository.PrestamoRepository;
import com.example.gestiongastos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
@CrossOrigin(origins = "*")
public class PrestamoController {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuario/{usuarioId}")
    public List<Prestamo> getPrestamosUsuario(@PathVariable Long usuarioId) {
        return prestamoRepository.findByUsuarioId(usuarioId);
    }

    @PostMapping
    public ResponseEntity<?> crearPrestamo(@RequestBody Prestamo prestamo) {
        Usuario u = usuarioRepository.findById(prestamo.getUsuario().getId()).orElse(null);
        if (u == null) return ResponseEntity.badRequest().body("Usuario no encontrado");
        prestamo.setUsuario(u);
        Prestamo guardado = prestamoRepository.save(prestamo);
        return ResponseEntity.ok(guardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPrestamo(@PathVariable Long id) {
        prestamoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}