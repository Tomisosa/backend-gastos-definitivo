package com.example.gestiongastos.model;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "prestamos")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mesCuota;    // Ej: "2026-03"
    private String nombre;      // Ej: "Auto", "Heladera"
    private String perteneceA;  // Ej: "Mamá", "Papá", "Ambos"
    private Integer cuotaActual; // Ej: 1
    private Integer cuotaTotal;  // Ej: 36
    
    private Double montoTotal;   // Monto total de la cuota del mes
    private Double aporteBelen;  // Lo que pone Belén
    private Double aporteOtro;   // Lo que pone la otra persona

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Permite guardar pero no mostrar
    private Usuario usuario;

    // --- GETTERS Y SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getMesCuota() { return mesCuota; }
    public void setMesCuota(String mesCuota) { this.mesCuota = mesCuota; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getPerteneceA() { return perteneceA; }
    public void setPerteneceA(String perteneceA) { this.perteneceA = perteneceA; }
    
    public Integer getCuotaActual() { return cuotaActual; }
    public void setCuotaActual(Integer cuotaActual) { this.cuotaActual = cuotaActual; }
    
    public Integer getCuotaTotal() { return cuotaTotal; }
    public void setCuotaTotal(Integer cuotaTotal) { this.cuotaTotal = cuotaTotal; }
    
    public Double getMontoTotal() { return montoTotal; }
    public void setMontoTotal(Double montoTotal) { this.montoTotal = montoTotal; }
    
    public Double getAporteBelen() { return aporteBelen; }
    public void setAporteBelen(Double aporteBelen) { this.aporteBelen = aporteBelen; }
    
    public Double getAporteOtro() { return aporteOtro; }
    public void setAporteOtro(Double aporteOtro) { this.aporteOtro = aporteOtro; }
    
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}