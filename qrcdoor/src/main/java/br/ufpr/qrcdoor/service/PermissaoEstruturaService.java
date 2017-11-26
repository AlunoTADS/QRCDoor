package br.ufpr.qrcdoor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.PermissaoEstrutura;
import br.ufpr.qrcdoor.entity.PermissaoEstrutura.PermissaoEstruturaPK;
import br.ufpr.qrcdoor.repository.PermissaoEstruturaRepository;

@Service
public class PermissaoEstruturaService extends GenericService<PermissaoEstrutura, PermissaoEstruturaPK> {

	@Autowired
	PermissaoEstruturaRepository permissaoEstruturaRepository;
	
	@Override
	public Page<PermissaoEstrutura> find(PermissaoEstrutura permissaoEstrutura, Pageable pageable) throws Exception {
		Long idEstrutura = null, idPermissao = null;
		if (permissaoEstrutura.getId().getEstrutura() != null && permissaoEstrutura.getId().getEstrutura().getId() != null) {
			idEstrutura = permissaoEstrutura.getId().getEstrutura().getId();
		} 
		if (permissaoEstrutura.getId().getPermissao() != null && permissaoEstrutura.getId().getPermissao().getId() != null) {
			idPermissao = permissaoEstrutura.getId().getPermissao().getId();
		}
		return this.permissaoEstruturaRepository.findAllById(idEstrutura, idPermissao, pageable);
	}
	
	public PermissaoEstrutura save(PermissaoEstrutura permissaoEstrutura) throws Exception {
		return this.permissaoEstruturaRepository.saveAndFlush(permissaoEstrutura);
	}
	
	@Override
	public JpaRepository<PermissaoEstrutura, PermissaoEstruturaPK> getRepository() {
		return this.permissaoEstruturaRepository;
	}

}
