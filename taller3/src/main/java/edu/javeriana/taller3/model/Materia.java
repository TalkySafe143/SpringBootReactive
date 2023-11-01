package edu.javeriana.taller3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "materia")
public class Materia {
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private Integer creditos;

    //@OneToMany(mappedBy = "materia")
    //private Set<Curso> cursos;
}
