package br.ufpr.qrcdoor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpr.qrcdoor.entity.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

}
