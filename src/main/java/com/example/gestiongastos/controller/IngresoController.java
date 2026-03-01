package com.example.gestiongastos.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.gestiongastos.dto.Request.IngresoRequest;
import com.example.gestiongastos.dto.Response.IngresoResponse;
import com.example.gestiongastos.services.IngresoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ingresos")
@CrossOrigin(origins = "*") // <-- Habilita conexión desde el celular
public class IngresoController {

    private final IngresoService ingresoService;

    public IngresoController(IngresoService ingresoService) {
        this.ingresoService = ingresoService;
    }

    @PostMapping
    public ResponseEntity<IngresoResponse> create(@Valid @RequestBody IngresoRequest req) {
        return ResponseEntity.ok(ingresoService.create(req));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<IngresoResponse>> listByUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(ingresoService.listByUsuario(usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ingresoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
