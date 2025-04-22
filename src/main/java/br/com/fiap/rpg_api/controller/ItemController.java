package br.com.fiap.rpg_api.controller;

import br.com.fiap.rpg_api.model.Item;
import br.com.fiap.rpg_api.model.Personagem;
import br.com.fiap.rpg_api.repository.ItemRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/items")

public class ItemController {

    @Autowired
    private ItemRepository repository;

    @GetMapping
    public List<Item> index(){
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item create(@RequestBody @Valid Item item){
        return repository.save(item);
    }

    @GetMapping
    public Item get(@PathVariable Long id){
        return getItem(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy (@PathVariable Long id){
        repository.delete(getItem(id));
    }

    @PutMapping
    public Item update(@PathVariable Long id, @RequestBody Item item){
        getItem(id);
        item.setId(id);
        return repository.save(item);
    }


    private Item getItem(Long id){
        return repository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
    }

}
