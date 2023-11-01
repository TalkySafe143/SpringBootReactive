package edu.javeriana.taller3.service;

import edu.javeriana.taller3.model.Materia;
import edu.javeriana.taller3.repositorio.MateriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MateriaService {


    //@Autowired
    private MateriaRepositorio materiaRepositorio;


    public Mono<Materia> getOneMateria(Integer id) {
        return materiaRepositorio.findById(id);
    }

    public Flux<Materia> getAllMaterias() {
        return materiaRepositorio.findAll();
    }

    public Mono<Materia> crearMateria(Materia data) {
        return materiaRepositorio.save(data);
    }

    public Mono<Void> deleteMateria(Integer id) {
        return materiaRepositorio.deleteById(id);
    }
}
