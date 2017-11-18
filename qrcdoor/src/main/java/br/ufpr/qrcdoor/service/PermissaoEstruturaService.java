package br.ufpr.qrcdoor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.PermissaoEstrutura;
import br.ufpr.qrcdoor.entity.PermissaoEstrutura.PermissaoEstruturaID;
import br.ufpr.qrcdoor.repository.PermissaoEstruturaRepository;

@Service
public class PermissaoEstruturaService extends GenericService<PermissaoEstrutura, PermissaoEstruturaID> {

	@Autowired
	PermissaoEstruturaRepository permissaoEstruturaRepository;
	
	public PermissaoEstrutura save(PermissaoEstrutura permissaoEstrutura) throws Exception {
		return this.permissaoEstruturaRepository.saveAndFlush(permissaoEstrutura);
	}
	
	@Override
	public JpaRepository<PermissaoEstrutura, PermissaoEstruturaID> getRepository() {
		return this.permissaoEstruturaRepository;
	}

}
