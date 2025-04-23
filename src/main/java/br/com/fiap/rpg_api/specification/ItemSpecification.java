package br.com.fiap.rpg_api.specification;

import br.com.fiap.rpg_api.model.Item;
import br.com.fiap.rpg_api.model.ItemFilter;
import org.springframework.data.jpa.domain.Specification;
import br.com.fiap.rpg_api.model.Personagem;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;
/*

● buscar itens por nome parcial
● buscar itens por tipo
● buscar itens por preço mínimo e máximo
● buscar itens por raridade


 */
public class ItemSpecification {
    public static Specification<Item> withFilters(ItemFilter filter){
        return (root, query, cb) -> {
            List<Predicate> predicades = new ArrayList<>();

            if (filter.nome() != null) {
                predicades.add(cb.like(
                        cb.lower(root.get("nome")), "%" + filter.nome().toLowerCase() + "%"));

            }
            if (filter.tipo() != null) {
                predicades.add(cb.equal(root.get("tipo"), filter.tipo()));
            }

            if (filter.raridade() != null) {
                predicades.add(cb.equal(root.get("raridade"), filter.raridade()));
            }



            if(filter.precoMinimo() != null &&filter.precoMaximo() != null) {
                predicades.add(cb.between(root.get("preco"), filter.precoMinimo(), filter.precoMaximo()));
            } else if(filter.precoMinimo() != null) {
                predicades.add(cb.greaterThanOrEqualTo(root.get("preco"), filter.precoMinimo()));
            } else if(filter.precoMaximo() != null) {
                predicades.add(cb.lessThanOrEqualTo(root.get("preco"), filter.precoMaximo()));
            }


            var arrayPredicades = predicades.toArray(new Predicate[0]);

            return cb.and(arrayPredicades);

        };
    }
}
