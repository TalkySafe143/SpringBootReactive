/**package edu.javeriana.taller3.service;

import edu.javeriana.taller3.model.Curso;
import edu.javeriana.taller3.repositorio.CursoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CursoService {

    @Autowired
    private CursoRepositorio cursoRepositorio;

    public Flux<Curso> getAll() {
        return cursoRepositorio.findAll();
    }

    public Mono<Curso> getOne(String id) {
        return cursoRepositorio.findById(id);
    }

    public Mono<Curso> create(Curso curso) {
        return cursoRepositorio.save(curso);
    }

    public Mono<Void> deleteOne(String id) {
        return cursoRepositorio.deleteById(id);
    }

}
*/