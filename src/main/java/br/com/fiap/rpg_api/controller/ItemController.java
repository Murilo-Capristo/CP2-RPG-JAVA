package br.com.fiap.rpg_api.controller;

import br.com.fiap.rpg_api.model.Item;
import br.com.fiap.rpg_api.model.ItemFilter;
import br.com.fiap.rpg_api.model.Personagem;
import br.com.fiap.rpg_api.repository.ItemRepository;
import br.com.fiap.rpg_api.specification.ItemSpecification;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/itens")

public class ItemController {

    @Autowired
    private ItemRepository repository;

    @GetMapping
    public Page<Item> index(ItemFilter filter, @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return repository.findAll(ItemSpecification.withFilters(filter), pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item create(@RequestBody @Valid Item item){
        return repository.save(item);
    }

    @GetMapping("{id}")
    public Item get(@PathVariable Long id){
        return getItem(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy (@PathVariable Long id){
        repository.delete(getItem(id));
    }

    @PutMapping("{id}")
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
