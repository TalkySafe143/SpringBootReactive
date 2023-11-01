/**package edu.javeriana.taller3.controller;

import edu.javeriana.taller3.model.Curso;
import edu.javeriana.taller3.service.CursoService;
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
@RequestMapping("/api/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping("/")
    public Flux<Curso> getAllPersona() {
        return cursoService.getAll();
    }

    @PostMapping("/")
    public Mono<ResponseEntity<Map<String, Object>>> createPersona(@Valid @RequestBody Mono<Curso> curso) {
        Map<String, Object> res = new HashMap<>();
        return curso.flatMap(l -> cursoService.create(l).map(c-> {
            res.put("Curso", c);
            res.put("Mensaje", "Curso guardado");
            return ResponseEntity
                    .created(URI.create("/api/persona"))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(res);
        })).onErrorResume(t -> Mono.just(t).cast(WebExchangeBindException.class)
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

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Curso>> getOnePersona(@PathVariable String id) {
        return cursoService.getOne(id).map(p -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(p)).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Curso>> updatePersona(@PathVariable String id, @RequestBody Curso data) {
        return cursoService.getOne(id).flatMap(p -> {
                    p.setEstudiante_id(data.getEstudiante_id());
                    p.setFecha_inicio(data.getFecha_inicio());
                    p.setFecha_final(data.getFecha_final());
                    p.setProfesor_id(data.getProfesor_id());
                    p.setMateria_id(data.getMateria_id());
                    return cursoService.create(p);
                }).map(p-> ResponseEntity.created(URI.create("/api/persona"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Map<String, Object>>> deletePersona(@PathVariable String id) {
        Map<String, Object> res = new HashMap<>();
        return cursoService.deleteOne(id).then(Mono.fromCallable(() -> {
            res.put("Mensaje", "Persona eliminada correctamente");
            return ResponseEntity.ok(res);
        })).defaultIfEmpty(new ResponseEntity<Map<String, Object>>(HttpStatus.NOT_FOUND));
    }

}
*/