package edu.javeriana.taller3.controller;
import edu.javeriana.taller3.model.Materia;
import edu.javeriana.taller3.service.MateriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/materia")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping("/")
    public Flux<Materia> getAll() {
        return materiaService.getAllMaterias();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Materia>> getOneMateria(@PathVariable String id) {
        return materiaService.getOneMateria(Integer.valueOf(id)).map(m ->
                    ResponseEntity.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(m)
                ).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public Mono<ResponseEntity<Map<String, Object>>> createMateria(@Valid @RequestBody Mono<Materia> materia) {
        Map<String, Object> res = new HashMap<>();

        return materia.flatMap(m -> materiaService.crearMateria(m)
                .map(c -> {
                    res.put("Materia", c);
                    res.put("Mensaje", "Materia Guardada");
                    return ResponseEntity.created(URI.create("/api/materia"))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(res);
                })
        ).onErrorResume(t -> Mono.just(t).cast(WebExchangeBindException.class)
                .flatMap(e -> Mono.just(e.getFieldErrors()))
                .flatMapMany(Flux::fromIterable)
                .map(fieldError -> "El campo: " + fieldError.getField() + " " + fieldError.getDefaultMessage())
                .collectList()
                .flatMap(list -> {
                    res.put("Errors", list);
                    res.put("Mensaje", "Ha ocurrido un error");
                    res.put("status", HttpStatus.BAD_REQUEST.value());
                    return Mono.just(ResponseEntity.badRequest().body(res));
                }));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Materia>> updateMateria(@PathVariable String id,  @Valid  @RequestBody Materia materia) {
        return materiaService.getOneMateria(Integer.valueOf(id)).flatMap(m -> {
            m.setCreditos(materia.getCreditos());
            m.setNombre(materia.getNombre());
            return materiaService.crearMateria(m);
        }).map(p -> ResponseEntity.created(URI.create("/api/materia"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(p)
        ).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteMateria(@PathVariable String id) {
        return materiaService.deleteMateria(Integer.valueOf(id)).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
                .defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }
}
