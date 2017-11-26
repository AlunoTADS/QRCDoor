package br.ufpr.qrcdoor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufpr.qrcdoor.entity.Estrutura;
import br.ufpr.qrcdoor.entity.Permissao;
import br.ufpr.qrcdoor.entity.PermissaoEstrutura;
import br.ufpr.qrcdoor.entity.PermissaoEstrutura.PermissaoEstruturaPK;

public interface PermissaoEstruturaRepository extends JpaRepository<PermissaoEstrutura, PermissaoEstruturaPK> {
	
	public Page<PermissaoEstrutura> findByIdEstrutura(Estrutura estrutura, Pageable pageable);
	public Page<PermissaoEstrutura> findByIdPermissao(Permissao permissao, Pageable pageable);
	
	@Query("select pe from PermissaoEstrutura pe where pe.id.estrutura.id = ?1 or pe.id.permissao.id = ?2")
	public Page<PermissaoEstrutura> findAllById(Long idEstrutura, Long idPermissao, Pageable pageable);

}
