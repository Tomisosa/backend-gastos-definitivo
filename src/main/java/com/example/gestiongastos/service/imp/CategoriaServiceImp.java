package com.example.gestiongastos.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.gestiongastos.dto.Request.CategoriaRequest;
import com.example.gestiongastos.dto.Response.CategoriaResponse;
import com.example.gestiongastos.model.Categoria;
import com.example.gestiongastos.model.Usuario;
import com.example.gestiongastos.repository.CategoriaRepository;
import com.example.gestiongastos.repository.UsuarioRepository;
import com.example.gestiongastos.services.CategoriaService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaServiceImp implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;

    public CategoriaServiceImp(CategoriaRepository categoriaRepository, UsuarioRepository usuarioRepository) {
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public CategoriaResponse create(CategoriaRequest request) {

        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        if (categoriaRepository.existsByNombreAndUsuarioId(request.getNombre(), request.getUsuarioId())) {
            throw new RuntimeException("La categoría ya existe");
        }

        Categoria categoria = new Categoria();
        categoria.setNombre(request.getNombre());
        categoria.setUsuario(usuario);

        Categoria guardada = categoriaRepository.save(categoria);

        return mapToResponse(guardada);
    }

    @Override
    public List<CategoriaResponse> listByUsuario(Long usuarioId) {

        return categoriaRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaResponse getById(Long id) {

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));

        return mapToResponse(categoria);
    }

    @Override
    public void delete(Long id) {

        if (!categoriaRepository.existsById(id)) {
            throw new EntityNotFoundException("Categoría no encontrada para eliminar");
        }

        categoriaRepository.deleteById(id);
    }

    private CategoriaResponse mapToResponse(Categoria c) {

        CategoriaResponse resp = new CategoriaResponse();
        resp.setId(c.getId());
        resp.setNombre(c.getNombre());

        if (c.getUsuario() != null) {
            resp.setUsuarioId(c.getUsuario().getId());
        }

        return resp;
    }
}