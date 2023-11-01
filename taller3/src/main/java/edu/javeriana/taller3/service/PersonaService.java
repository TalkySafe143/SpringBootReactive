package edu.javeriana.taller3.service;

import edu.javeriana.taller3.model.Materia;
import edu.javeriana.taller3.model.Persona;
import edu.javeriana.taller3.repositorio.MateriaRepositorio;
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

    public Mono<Persona> getOne(Integer id) {
        return personaRepositorio.findById(id);
    }

    public Mono<Void> deleteOne(Integer id) {
        return personaRepositorio.deleteById(id);
    }

}
