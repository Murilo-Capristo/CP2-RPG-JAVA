package br.com.fiap.rpg_api.repository;

import br.com.fiap.rpg_api.model.Item;
import br.com.fiap.rpg_api.model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByPersonagem(Personagem personagem);
    List<Item> findByPrecoBetween(Double precoMinimo, Double precoMaximo);
}
