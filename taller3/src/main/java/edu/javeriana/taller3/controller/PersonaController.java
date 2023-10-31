package edu.javeriana.taller3.controller;

import edu.javeriana.taller3.model.Persona;
import edu.javeriana.taller3.services.PersonaService;
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
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/")
    public Flux<Persona> getAllPersona() {
        return personaService.getAll();
    }

    @PostMapping("/")
    public Mono<ResponseEntity<Map<String, Object>>> createPersona(@Valid @RequestBody Mono<Persona> user) {
        Map<String, Object> res = new HashMap<>();
        return user.flatMap(persona -> personaService.create(persona).map(c-> {
            res.put("cliente", c);
            res.put("Mensaje", "Cliente guardado");
            return ResponseEntity
                    .created(URI.create("/api/persona"))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(res);
        })).onErrorResume(t -> {
           return Mono.just(t).cast(WebExchangeBindException.class)
                   .flatMap(e -> Mono.just(e.getFieldErrors()))
                   .flatMapMany(Flux::fromIterable)
                   .map(fieldError -> "El campo: " + fieldError.getField() + " " + fieldError.getDefaultMessage())
                   .collectList()
                   .flatMap(list -> {
                       res.put("Errors", list);
                       res.put("Mensaje", "Ha ocurrido un error");
                       res.put("status", HttpStatus.BAD_REQUEST.value());
                       return Mono.just(ResponseEntity.badRequest().body(res));
                   });
        });
    }

}
