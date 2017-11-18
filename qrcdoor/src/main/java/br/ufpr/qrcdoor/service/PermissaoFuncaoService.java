package br.ufpr.qrcdoor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.PermissaoFuncao;
import br.ufpr.qrcdoor.repository.PermissaoFuncaoRepository;

@Service
public class PermissaoFuncaoService extends GenericService<PermissaoFuncao, Long> {

	@Autowired
	PermissaoFuncaoRepository permissaoFuncaoRepository;
	
	public PermissaoFuncao save(PermissaoFuncao permissaoFuncao) throws Exception {
		return this.permissaoFuncaoRepository.saveAndFlush(permissaoFuncao);
	}
	
	@Override
	public JpaRepository<PermissaoFuncao, Long> getRepository() {
		return this.permissaoFuncaoRepository;
	}
}
