package br.com.fiap.rpg_api.model;

import br.com.fiap.rpg_api.enums.Raridade;
import br.com.fiap.rpg_api.enums.Tipo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
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
    @Min(0)
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "personagem_id", nullable = true)
    private  Personagem personagem;

    public void setId(Long id){
        this.id = id;
    }

}
