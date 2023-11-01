package edu.javeriana.taller3.model;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "persona")
public class Persona {

    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String apellido;
    private String correo;
    private char rol;

    //@OneToMany(mappedBy = "persona")
    //private Set<Curso> cursos;
}
