package br.ufpr.qrcdoor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpr.qrcdoor.entity.Funcao;
import br.ufpr.qrcdoor.entity.PermissaoFuncao;

public interface PermissaoFuncaoRepository extends JpaRepository<PermissaoFuncao, Long> {
	
	public List<PermissaoFuncao> findAllByFuncao(Funcao funcao);

}
