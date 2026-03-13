package com.example.gestiongastos.model;
import jakarta.persistence.*;

@Entity
@Table(name = "prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mesCuota;
    private Double aporteMama;
    private Double aporteBelen;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getMesCuota() { return mesCuota; }
    public void setMesCuota(String mesCuota) { this.mesCuota = mesCuota; }
    
    public Double getAporteMama() { return aporteMama; }
    public void setAporteMama(Double aporteMama) { this.aporteMama = aporteMama; }
    
    public Double getAporteBelen() { return aporteBelen; }
    public void setAporteBelen(Double aporteBelen) { this.aporteBelen = aporteBelen; }
    
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}