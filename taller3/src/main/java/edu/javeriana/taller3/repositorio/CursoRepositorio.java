package edu.javeriana.taller3.repositorio;

import edu.javeriana.taller3.model.Curso;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepositorio extends ReactiveCrudRepository<Curso, String> {
}
