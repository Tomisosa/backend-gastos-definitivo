package com.example.gestiongastos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gestiongastos.dto.Request.CategoriaRequest;
import com.example.gestiongastos.dto.Response.CategoriaResponse;
import com.example.gestiongastos.services.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> create(@Valid @RequestBody CategoriaRequest req) {
        return ResponseEntity.ok(categoriaService.create(req));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<CategoriaResponse>> listByUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(categoriaService.listByUsuario(usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestParam(required = false, defaultValue = "false") boolean force) {
        categoriaService.delete(id, force);
        return ResponseEntity.noContent().build();
    }
}