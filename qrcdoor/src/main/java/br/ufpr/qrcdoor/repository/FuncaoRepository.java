package br.ufpr.qrcdoor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpr.qrcdoor.entity.Funcao;

public interface FuncaoRepository extends JpaRepository<Funcao, Long> {
	
}
