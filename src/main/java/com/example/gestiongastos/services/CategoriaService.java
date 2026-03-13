package com.example.gestiongastos.services;

import java.util.List;

import com.example.gestiongastos.dto.Request.CategoriaRequest;
import com.example.gestiongastos.dto.Response.CategoriaResponse;

public interface CategoriaService {

    CategoriaResponse create(CategoriaRequest request);

    List<CategoriaResponse> listByUsuario(Long usuarioId);

    CategoriaResponse getById(Long id);

    void delete(Long id);
}