package br.ufpr.qrcdoor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.ufpr.qrcdoor.entity.Estrutura;

public interface EstruturaRepository extends JpaRepository<Estrutura, Long>, JpaSpecificationExecutor<Estrutura> {
	
}
