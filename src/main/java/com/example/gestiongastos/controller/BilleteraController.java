package com.example.gestiongastos.controller;
import com.example.gestiongastos.model.Billetera;
import com.example.gestiongastos.model.Usuario;
import com.example.gestiongastos.repository.BilleteraRepository;
import com.example.gestiongastos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/billeteras")
@CrossOrigin(origins = "*")
public class BilleteraController {

    @Autowired
    private BilleteraRepository billeteraRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuario/{usuarioId}")
    public List<Billetera> getBilleterasUsuario(@PathVariable Long usuarioId) {
        return billeteraRepository.findByUsuarioId(usuarioId);
    }

    @PostMapping
    public ResponseEntity<?> crearBilletera(@RequestBody Billetera billetera) {
        Usuario u = usuarioRepository.findById(billetera.getUsuario().getId()).orElse(null);
        if (u == null) return ResponseEntity.badRequest().body("Usuario no encontrado");
        billetera.setUsuario(u);
        return ResponseEntity.ok(billeteraRepository.save(billetera));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarBilletera(@PathVariable Long id) {
        billeteraRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}