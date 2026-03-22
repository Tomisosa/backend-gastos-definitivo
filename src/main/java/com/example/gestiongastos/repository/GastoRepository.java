package com.example.gestiongastos.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.gestiongastos.model.Gasto;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {
    
    List<Gasto> findByUsuarioId(Long usuarioId);
    
    // ✅ NUEVO MÉTODO
    List<Gasto> findByCategoriaId(Long categoriaId);
}