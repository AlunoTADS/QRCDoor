package br.ufpr.qrcdoor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.Acesso;
import br.ufpr.qrcdoor.repository.AcessoRepository;
import br.ufpr.qrcdoor.repository.PermissaoFuncaoRepository;
import br.ufpr.qrcdoor.repository.PermissaoPessoaRepository;
import br.ufpr.qrcdoor.repository.PessoaRepository;

@Service
public class AcessoService extends GenericService<Acesso, Long> {
	
	@Autowired
	AcessoRepository acessoRepository;
	@Autowired
	PermissaoFuncaoRepository permissaoFuncaoRepository;
	@Autowired
	PermissaoPessoaRepository permissaoPessoaRepository;
	@Autowired
	PessoaRepository pessoaRepository;
	
	public Acesso save(Acesso acesso) throws Exception {
		
		return this.acessoRepository.saveAndFlush(acesso);
	}

	@Override
	public JpaRepository<Acesso, Long> getRepository() {
		return this.acessoRepository;
	}
	
}
