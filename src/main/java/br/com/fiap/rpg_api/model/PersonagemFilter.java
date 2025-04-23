package br.com.fiap.rpg_api.model;

import br.com.fiap.rpg_api.enums.Classe;

public record PersonagemFilter (String nome, Classe classe, int nivel){
}
