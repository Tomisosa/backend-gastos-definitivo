package com.example.gestiongastos.controller;

import com.example.gestiongastos.model.Tarjeta;
import com.example.gestiongastos.repository.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarjetas")
@CrossOrigin(origins = "*") // Clave para que Vercel se pueda comunicar sin errores de CORS
public class TarjetaController {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    // 1. Obtener las tarjetas de un usuario específico
    @GetMapping("/usuario/{usuarioId}")
    public List<Tarjeta> obtenerTarjetas(@PathVariable Long usuarioId) {
        return tarjetaRepository.findByUsuarioId(usuarioId);
    }

    // 2. Crear una nueva tarjeta
    @PostMapping
    public Tarjeta crearTarjeta(@RequestBody Tarjeta tarjeta) {
        return tarjetaRepository.save(tarjeta);
    }

    // 3. Eliminar una tarjeta
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTarjeta(@PathVariable Long id) {
        tarjetaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}