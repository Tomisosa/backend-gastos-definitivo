package com.example.gestiongastos.repository;
import com.example.gestiongastos.model.Billetera;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BilleteraRepository extends JpaRepository<Billetera, Long> {
    List<Billetera> findByUsuarioId(Long usuarioId);
}