package edu.javeriana.taller3.controller;

import edu.javeriana.taller3.model.Persona;
import edu.javeriana.taller3.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/")
    Flux<Persona> getAllPersona() {
        return personaService.getAll();
    }

    @PostMapping("/")
    Mono<Persona> createPersona(@RequestBody Mono<Persona> user) {

        return personaService.create(user);
    }

}
