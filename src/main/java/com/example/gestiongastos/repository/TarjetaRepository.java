package com.example.gestiongastos.repository;

import com.example.gestiongastos.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
    
    // Función mágica para buscar solo las tarjetas de la persona que inició sesión
    List<Tarjeta> findByUsuarioId(Long usuarioId);
}