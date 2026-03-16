package com.example.gestiongastos.model;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty; // <-- Asegurate de importar esto

@Entity
@Table(name = "billeteras")
public class Billetera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    // ¡REEMPLAZAMOS EL @JsonIgnore POR LA MAGIA!
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Usuario usuario;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}