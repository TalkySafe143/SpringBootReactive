package edu.javeriana.taller3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "curso")
public class Curso {

    @ManyToOne
    @JoinColumn(name = "id")
    private Persona profesor_id;

    @ManyToOne
    @JoinColumn(name = "id")
    private Materia materia_id;

    @Id
    @org.springframework.data.annotation.Id
    private String numero;

    @ManyToOne
    @JoinColumn(name = "id")
    private Persona estudiante_id;

    private Date fecha_inicio;

    private Date fecha_final;
}
