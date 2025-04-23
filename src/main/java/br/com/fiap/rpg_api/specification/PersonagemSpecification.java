package br.com.fiap.rpg_api.specification;

import br.com.fiap.rpg_api.model.Personagem;
import br.com.fiap.rpg_api.model.PersonagemFilter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

public class PersonagemSpecification {

    /*
    ● buscar personagem por nome
    ● buscar personagem por classe
     */
    public static Specification<Personagem> withFilters(PersonagemFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(filter.nome() != null) {
                predicates.add(cb.like(
                        cb.lower(root.get("nome")), "%" + filter.nome().toLowerCase() + "%"
                ));
            }
            if (filter.classe() != null) {
                predicates.add(cb.equal(root.get("classe"), filter.classe()));
            }

            var arrayPredicades = predicates.toArray(new Predicate[0]);

            return cb.and(arrayPredicades);
        };
    }
}
