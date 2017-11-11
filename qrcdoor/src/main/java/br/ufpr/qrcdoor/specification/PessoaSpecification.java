package br.ufpr.qrcdoor.specification;

import org.springframework.data.jpa.domain.Specification;

import br.ufpr.qrcdoor.entity.Pessoa;

public final class PessoaSpecification {
	
	private PessoaSpecification() {
	}
	
	public static Specification<Pessoa> searchContainsIgnoreCase(String searchTerm) {
        return (root, query, cb) -> {
            String containsLikePattern = getContainsLikePattern(searchTerm);
            return cb.or(
                    cb.like(cb.lower(root.get("nome")), containsLikePattern),
                    cb.like(cb.lower(root.get("email")), containsLikePattern),
                    cb.like(cb.lower(root.get("login")), containsLikePattern),
                    cb.like(cb.lower(root.get("documento")), containsLikePattern)
            );
        };
    }
 
    private static String getContainsLikePattern(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return "%";
        }
        else {
            return "%" + searchTerm.toLowerCase() + "%";
        }
    }

}
