package com.example.gestiongastos.dto.Request;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IngresoRequest {
    private String descripcion;
    private BigDecimal monto;
    private LocalDate fecha;
    private Long usuarioId;
    private Long categoriaId;
    private String medioPago;
    
    // AGREGAMOS EL CAMPO ACÁ:
    private String mesImpacto;

    // Getters y Setters
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public Long getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }
    
    public String getMedioPago() { return medioPago; }
    public void setMedioPago(String medioPago) { this.medioPago = medioPago; }

    // GETTER Y SETTER DE MES IMPACTO:
    public String getMesImpacto() { return mesImpacto; }
    public void setMesImpacto(String mesImpacto) { this.mesImpacto = mesImpacto; }
}