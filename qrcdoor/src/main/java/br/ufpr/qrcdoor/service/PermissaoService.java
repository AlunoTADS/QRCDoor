package br.ufpr.qrcdoor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.Permissao;
import br.ufpr.qrcdoor.repository.PermissaoRepository;

@Service
public class PermissaoService extends GenericService<Permissao, Long> {
	
	@Autowired
	PermissaoRepository permissaoRepository;
	
	public Permissao save(Permissao permissao) throws Exception {
		return this.permissaoRepository.saveAndFlush(permissao);
	}
	
	@Override
	public JpaRepository<Permissao, Long> getRepository() {
		return this.permissaoRepository;
	}

}
