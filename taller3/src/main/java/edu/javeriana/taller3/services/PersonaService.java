package edu.javeriana.taller3.services;

import edu.javeriana.taller3.model.Persona;
import edu.javeriana.taller3.repositorio.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepositorio personaRepositorio;

    public Mono<Persona> create(Persona data) {
        return personaRepositorio.save(data);
    }

    public Flux<Persona> getAll() {
        return personaRepositorio.findAll();
    }

}
