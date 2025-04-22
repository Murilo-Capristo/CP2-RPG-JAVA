package br.com.fiap.rpg_api.config;

import br.com.fiap.rpg_api.enums.Classe;
import br.com.fiap.rpg_api.enums.Raridade;
import br.com.fiap.rpg_api.enums.Tipo;
import br.com.fiap.rpg_api.model.Item;
import br.com.fiap.rpg_api.model.Personagem;
import br.com.fiap.rpg_api.repository.ItemRepository;
import br.com.fiap.rpg_api.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class DatabaseSeeder {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PersonagemRepository personagemRepository;

    @PostConstruct
    public void init() {
        // Criando personagens
        var personagens = List.of(
                Personagem.builder().nome("Aragorn").classe(Classe.GUERREIRO).nivel(10).moedas(100).build(),
                Personagem.builder().nome("Legolas").classe(Classe.ARQUEIRO).nivel(8).moedas(80).build(),
                Personagem.builder().nome("Gimli").classe(Classe.GUERREIRO).nivel(7).moedas(50).build(),
                Personagem.builder().nome("Gandalf").classe(Classe.MAGO).nivel(20).moedas(200).build()
        );

        personagemRepository.saveAll(personagens);

        // Criando itens (alguns com personagem, outros sem)
        var nomesItens = List.of(
                "Espada Longa",
                "Arco e Flecha",
                "Machado de Guerra",
                "Cajado do Mago",
                "Escudo de Mithril",
                "Poção de Cura",
                "Elmo do Dragão"
        );

        var tiposItens = List.of(Tipo.ARMA, Tipo.ARMADURA, Tipo.POCAO, Tipo.ACESSORIO);
        var raridades = List.of(Raridade.COMUM, Raridade.RARO, Raridade.EPICO, Raridade.LENDARIO);

        var itens = new ArrayList<Item>();
        for (int i = 0; i < 10; i++) {
            Personagem personagem = new Random().nextBoolean() ?
                    personagens.get(new Random().nextInt(personagens.size())) : null;

            itens.add(
                    Item.builder()
                            .nome(nomesItens.get(new Random().nextInt(nomesItens.size())))
                            .tipo(tiposItens.get(new Random().nextInt(tiposItens.size())))
                            .raridade(raridades.get(new Random().nextInt(raridades.size())))
                            .preco(10 + new Random().nextDouble() * 500)
                            .personagem(personagem) // Pode ser null
                            .build()
            );
        }

        itemRepository.saveAll(itens);
    }
}
