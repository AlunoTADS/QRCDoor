package br.ufpr.qrcdoor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.ufpr.qrcdoor.entity.Funcao;

public interface FuncaoRepository extends JpaRepository<Funcao, Long>, JpaSpecificationExecutor<Funcao> {
	
}
