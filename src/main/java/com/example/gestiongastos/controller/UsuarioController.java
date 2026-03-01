package com.example.gestiongastos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.gestiongastos.dto.Request.UsuarioLoginRequest;
import com.example.gestiongastos.dto.Request.UsuarioRegisterRequestDto;
import com.example.gestiongastos.dto.Response.UsuarioResponse;
import com.example.gestiongastos.model.Usuario;
import com.example.gestiongastos.security.JwtUtil;
import com.example.gestiongastos.services.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*") // <-- Permite que el celular se conecte
public class UsuarioController {
	
    private final JwtUtil jwtUtil;
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService, JwtUtil jwtUtil) {
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponse> register(@Valid @RequestBody UsuarioRegisterRequestDto req) {
        return ResponseEntity.ok(usuarioService.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponse> login(@Valid @RequestBody UsuarioLoginRequest req) {
        return ResponseEntity.ok(usuarioService.login(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }
    
    @GetMapping("/me")
    public ResponseEntity<UsuarioResponse> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String email = jwtUtil.extractEmail(token);
        Usuario usuario = usuarioService.findByEmail(email);

        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuario.getId());
        response.setNombre(usuario.getNombre());
        response.setEmail(usuario.getEmail());
        response.setToken(token);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestBody java.util.Map<String, String> req) {
        Long userId = Long.parseLong(req.get("userId"));
        String newPassword = req.get("newPassword");
        usuarioService.updatePassword(userId, newPassword);
        return ResponseEntity.ok("Contraseña actualizada con éxito");
    }
}