package br.ufpr.qrcdoor.specification;

import org.springframework.data.jpa.domain.Specification;

import br.ufpr.qrcdoor.entity.Estrutura;
import br.ufpr.qrcdoor.util.Util;

public class EstruturaSpecification {
	
	public EstruturaSpecification() {
	}
	
	public static Specification<Estrutura> searchContainsIgnoreCase(String searchTerm) {
        return (root, query, cb) -> {
            String containsLikePattern = Util.getContainsLikePattern(searchTerm);
            return cb.or(
                    cb.like(cb.lower(root.get("descricao")), containsLikePattern),
                    cb.like(cb.lower(root.get("tempomaximoabertura")), containsLikePattern)
            );
        };
	}

}
