package br.ufpr.qrcdoor.specification;

import org.springframework.data.jpa.domain.Specification;

import br.ufpr.qrcdoor.entity.Pessoa;
import br.ufpr.qrcdoor.util.Util;

public final class PessoaSpecification {
	
	public PessoaSpecification() {
	}
	
	public static Specification<Pessoa> searchContainsIgnoreCase(String searchTerm) {
        return (root, query, cb) -> {
            String containsLikePattern = Util.getContainsLikePattern(searchTerm);
            return cb.or(
                    cb.like(cb.lower(root.get("nome")), containsLikePattern),
                    cb.like(cb.lower(root.get("email")), containsLikePattern),
                    cb.like(cb.lower(root.get("login")), containsLikePattern),
                    cb.like(cb.lower(root.get("documento")), containsLikePattern)
            );
        };
    }

}
