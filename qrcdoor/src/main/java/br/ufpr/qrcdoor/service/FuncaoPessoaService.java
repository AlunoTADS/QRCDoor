package br.ufpr.qrcdoor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.FuncaoPessoa;
import br.ufpr.qrcdoor.repository.FuncaoPessoaRepository;

@Service
public class FuncaoPessoaService extends GenericService<FuncaoPessoa, Long> {
	
	@Autowired
	FuncaoPessoaRepository funcaoPessoaRepository;
	
	public FuncaoPessoa save(FuncaoPessoa funcaoPessoa) throws Exception {
		return this.funcaoPessoaRepository.saveAndFlush(funcaoPessoa);
	}
	
	@Override
	public JpaRepository<FuncaoPessoa, Long> getRepository() {
		return funcaoPessoaRepository;
	}

}
