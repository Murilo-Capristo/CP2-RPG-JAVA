package br.com.fiap.rpg_api.model;

import br.com.fiap.rpg_api.enums.Classe;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private Classe classe;

    @NotNull
    @Min(1)
    @Max(99)
    private int nivel;

    @NotNull
    private int moedas;

    public void setId(Long id){
        this.id = id;
    }
}
