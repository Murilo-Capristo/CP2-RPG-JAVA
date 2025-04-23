package br.com.fiap.rpg_api.model;

import br.com.fiap.rpg_api.enums.Raridade;
import br.com.fiap.rpg_api.enums.Tipo;

public record ItemFilter (String nome, Tipo tipo, Raridade raridade, Double preco, PersonagemFilter personagemFilter){}
