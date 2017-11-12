package br.ufpr.qrcdoor.specification;

import org.springframework.data.jpa.domain.Specification;

import br.ufpr.qrcdoor.entity.Funcao;
import br.ufpr.qrcdoor.util.Util;

public class FuncaoSpecification {

	public FuncaoSpecification() {
	}
	
	public static Specification<Funcao> searchContainsIgnoreCase(String searchTerm) {
        return (root, query, cb) -> {
            String containsLikePattern = Util.getContainsLikePattern(searchTerm);
            return cb.or(
                    cb.like(cb.lower(root.get("descricao")), containsLikePattern)
            );
        };
	}
}
