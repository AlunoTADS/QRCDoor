package br.ufpr.qrcdoor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpr.qrcdoor.entity.Funcao;

public interface FuncaoRepository extends JpaRepository<Funcao, Long> {
	
	public Page<Funcao> findByDescricaoContaining(String descricao, Pageable pageable);
	
}
