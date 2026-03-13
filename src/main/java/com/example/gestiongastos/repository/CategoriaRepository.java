package com.example.gestiongastos.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gestiongastos.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    boolean existsByNombreAndUsuarioId(String nombre, Long usuarioId);

    List<Categoria> findByUsuarioId(Long usuarioId);

}