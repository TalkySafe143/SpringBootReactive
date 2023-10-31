package edu.javeriana.taller3.repositorio;

import edu.javeriana.taller3.model.Nota;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepositorio extends ReactiveCrudRepository<Nota, Integer> {
}
