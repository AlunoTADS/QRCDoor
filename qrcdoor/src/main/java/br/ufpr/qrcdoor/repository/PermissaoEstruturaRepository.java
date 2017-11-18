package br.ufpr.qrcdoor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpr.qrcdoor.entity.PermissaoEstrutura;
import br.ufpr.qrcdoor.entity.PermissaoEstrutura.PermissaoEstruturaID;

public interface PermissaoEstruturaRepository extends JpaRepository<PermissaoEstrutura, PermissaoEstruturaID> {

}
