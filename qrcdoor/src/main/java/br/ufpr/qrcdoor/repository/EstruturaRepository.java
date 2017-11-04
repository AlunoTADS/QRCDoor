package br.ufpr.qrcdoor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpr.qrcdoor.entity.Estrutura;

public interface EstruturaRepository extends JpaRepository<Estrutura, Long> {
	
	public Page<Estrutura> findByDescricaoContaining(String descricao, Pageable pageable);

}
