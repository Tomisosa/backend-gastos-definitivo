package com.example.gestiongastos.repository;
import com.example.gestiongastos.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findByUsuarioId(Long usuarioId);
}