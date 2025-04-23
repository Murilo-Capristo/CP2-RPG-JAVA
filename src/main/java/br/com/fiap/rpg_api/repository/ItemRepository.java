package br.com.fiap.rpg_api.repository;

import br.com.fiap.rpg_api.model.Item;
import br.com.fiap.rpg_api.model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {

    List<Item> findByPersonagem(Personagem personagem);
}
