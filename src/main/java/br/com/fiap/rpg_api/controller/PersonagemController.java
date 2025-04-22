package br.com.fiap.rpg_api.controller;

import br.com.fiap.rpg_api.model.Personagem;
import br.com.fiap.rpg_api.repository.PersonagemRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/personagem")

public class PersonagemController {

    @Autowired
    private PersonagemRepository repository;

    @GetMapping
    public List<Personagem> index(){
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Personagem create(@RequestBody @Valid Personagem personagem){
        return repository.save(personagem);
    }

    @GetMapping("{id}")
    public Personagem get(@PathVariable Long id){
        return getPersonagem(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id){
        repository.delete(getPersonagem(id));
    }

    @PutMapping("{id}")
    public Personagem update(@PathVariable Long id, @RequestBody Personagem personagem){
        getPersonagem(id);
        personagem.setId(id);
        return repository.save(personagem);
    }


    private Personagem getPersonagem(Long id){
        return repository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
    }
}
