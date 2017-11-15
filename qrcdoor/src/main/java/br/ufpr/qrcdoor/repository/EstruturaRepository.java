package br.ufpr.qrcdoor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpr.qrcdoor.entity.Estrutura;

public interface EstruturaRepository extends JpaRepository<Estrutura, Long> {
	
}
