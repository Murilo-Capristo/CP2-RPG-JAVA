package br.com.fiap.rpg_api.model;

import br.com.fiap.rpg_api.enums.Raridade;
import br.com.fiap.rpg_api.enums.Tipo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private Tipo tipo;

    @NotNull
    private Raridade raridade;

    @NotNull
    @Size(min = 0)
    private Double preco;

    private  Personagem personagem;

}
