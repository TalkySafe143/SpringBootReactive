package edu.javeriana.taller3.repositorio;

import edu.javeriana.taller3.model.Materia;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MateriaRepositorio extends ReactiveCrudRepository<Materia, Integer> {
}
