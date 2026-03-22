package com.example.gestiongastos.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.gestiongastos.dto.Request.CategoriaRequest;
import com.example.gestiongastos.dto.Response.CategoriaResponse;
import com.example.gestiongastos.model.Categoria;
import com.example.gestiongastos.model.Gasto;
import com.example.gestiongastos.model.Ingreso;
import com.example.gestiongastos.model.Usuario;
import com.example.gestiongastos.repository.CategoriaRepository;
import com.example.gestiongastos.repository.GastoRepository;
import com.example.gestiongastos.repository.IngresoRepository;
import com.example.gestiongastos.repository.UsuarioRepository;
import com.example.gestiongastos.services.CategoriaService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaServiceImp implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;
    private final GastoRepository gastoRepository;
    private final IngresoRepository ingresoRepository;

    public CategoriaServiceImp(CategoriaRepository categoriaRepository, 
                              UsuarioRepository usuarioRepository,
                              GastoRepository gastoRepository,
                              IngresoRepository ingresoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
        this.gastoRepository = gastoRepository;
        this.ingresoRepository = ingresoRepository;
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
        delete(id, false);
    }

    public void delete(Long id, boolean force) {

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada para eliminar"));

        // Buscar gastos e ingresos asociados
        List<Gasto> gastos = gastoRepository.findByCategoriaId(id);
        List<Ingreso> ingresos = ingresoRepository.findByCategoriaId(id);

        boolean tieneMovimientos = (gastos != null && !gastos.isEmpty()) || 
                                   (ingresos != null && !ingresos.isEmpty());

        // Si tiene movimientos y no es forzado, lanzar excepción (403)
        if (tieneMovimientos && !force) {
            throw new IllegalArgumentException("Esta categoría tiene movimientos asociados");
        }

        // Si es forzado, eliminar los movimientos primero
        if (force && tieneMovimientos) {
            if (gastos != null && !gastos.isEmpty()) {
                gastoRepository.deleteAll(gastos);
            }
            if (ingresos != null && !ingresos.isEmpty()) {
                ingresoRepository.deleteAll(ingresos);
            }
        }

        // Finalmente eliminar la categoría
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