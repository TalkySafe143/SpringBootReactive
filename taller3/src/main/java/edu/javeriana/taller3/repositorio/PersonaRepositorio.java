package edu.javeriana.taller3.repositorio;

import edu.javeriana.taller3.model.Persona;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepositorio extends ReactiveCrudRepository<Persona, Integer> {
}
