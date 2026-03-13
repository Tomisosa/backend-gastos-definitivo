package com.example.gestiongastos.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private Usuario usuario;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    // ¡EL TRUCO ARREGLADO!
    // Le cambiamos el nombre al método a "getDuenoId" para que Spring Boot no explote.
    // Pero le ponemos @JsonProperty("usuarioId") para que a tu JavaScript le llegue perfecto.
    @JsonProperty("usuarioId")
    public Long getDuenoId() {
        if (this.usuario != null) {
            return this.usuario.getId();
        }
        return null;
    }
}