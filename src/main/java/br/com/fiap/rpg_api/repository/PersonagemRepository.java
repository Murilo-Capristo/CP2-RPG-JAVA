package br.com.fiap.rpg_api.repository;

import br.com.fiap.rpg_api.model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
}
