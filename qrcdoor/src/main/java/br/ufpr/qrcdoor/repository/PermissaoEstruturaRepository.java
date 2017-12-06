package br.ufpr.qrcdoor.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufpr.qrcdoor.entity.PermissaoEstrutura;
import br.ufpr.qrcdoor.entity.PermissaoEstrutura.PermissaoEstruturaPK;

public interface PermissaoEstruturaRepository extends JpaRepository<PermissaoEstrutura, PermissaoEstruturaPK> {
	
	@Query("select pe from PermissaoEstrutura pe where pe.id.estrutura.id = ?1 or pe.id.permissao.id = ?2")
	public Page<PermissaoEstrutura> findAllById(Long idEstrutura, Long idPermissao, Pageable pageable);
	
	@Query("select pe from PermissaoEstrutura pe where pe.id.permissao.id = ?1")
	public List<PermissaoEstrutura> findAllByIdPermissao(Long idPermissao);

}
