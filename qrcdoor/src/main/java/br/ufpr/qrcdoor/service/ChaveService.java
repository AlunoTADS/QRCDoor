package br.ufpr.qrcdoor.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.Chave;
import br.ufpr.qrcdoor.entity.Pessoa;
import br.ufpr.qrcdoor.repository.ChaveRepository;
import br.ufpr.qrcdoor.repository.PessoaRepository;

@Service
public class ChaveService extends GenericService<Chave, Long> {
	
	@Autowired
	ChaveRepository chaveRepository;
	@Autowired
	PessoaRepository pessoaRepository;
	
	public Chave register(String username) throws Exception {
		Pessoa pessoa = this.pessoaRepository.findByLogin(username);
		Chave chave = new Chave();
		chave.setPessoa(pessoa);
		chave.setSituacao("I");
		chave.setAssinatura(UUID.randomUUID().toString());
		return this.chaveRepository.saveAndFlush(chave);
	}
	
	public Chave save(Chave chave) throws Exception {
		return this.chaveRepository.saveAndFlush(chave);
	}

	@Override
	public JpaRepository<Chave, Long> getRepository() {
		return this.chaveRepository;
	}

}
