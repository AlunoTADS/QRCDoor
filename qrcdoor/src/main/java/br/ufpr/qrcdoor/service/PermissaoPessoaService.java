package br.ufpr.qrcdoor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.PermissaoPessoa;
import br.ufpr.qrcdoor.repository.PermissaoPessoaRepository;

@Service
public class PermissaoPessoaService extends GenericService<PermissaoPessoa, Long> {
	
	@Autowired
	PermissaoPessoaRepository permissaoPessoaRepository;
	
	public PermissaoPessoa save(PermissaoPessoa permissaoPessoa) throws Exception {
		return this.permissaoPessoaRepository.saveAndFlush(permissaoPessoa);
	}
	
	@Override
	public JpaRepository<PermissaoPessoa, Long> getRepository() {
		return this.permissaoPessoaRepository;
	}

}
