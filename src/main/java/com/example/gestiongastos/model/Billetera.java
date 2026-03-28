package com.example.gestiongastos.model;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty; // <-- Importamos la nueva herramienta

@Entity
@Table(name = "cuentas") 
public class Billetera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String color; 

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    // 👇 ESTA ES LA MAGIA: Permite guardar el usuario, pero lo protege al leer.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
    private Usuario usuario;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}