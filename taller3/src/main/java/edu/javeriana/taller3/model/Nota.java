package edu.javeriana.taller3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "nota")
public class Nota {
    @Id
    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Curso profesor_id;

    @Id
    @ManyToOne
    @JoinColumn(name = "materia_id")
    private Curso materia_id;

    @Id
    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Curso estudiante_id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String observacion;
    private Double valor;
    private Double procentaje;

    //@OneToMany(mappedBy = "nota")
   // private Set<Curso> cursos;
}
