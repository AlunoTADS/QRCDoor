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
	
	public Chave register(Chave chave, String username) throws Exception {
		Pessoa pessoa = this.pessoaRepository.findByLogin(username);
		if (chave != null && chave.getId() != null && chave.getAssinatura() != null) {
			Chave oldChave = this.chaveRepository.findOne(chave.getId());
			if (oldChave != null && oldChave.getAssinatura().equals(chave.getAssinatura())) {
				return oldChave;
			}
		}
		Chave newChave = new Chave();
		newChave.setPessoa(pessoa);
		newChave.setSituacao("I");
		newChave.setAssinatura(UUID.randomUUID().toString());
		return this.chaveRepository.saveAndFlush(newChave);
	}
	
	public Chave save(Chave chave) throws Exception {
		return this.chaveRepository.saveAndFlush(chave);
	}

	@Override
	public JpaRepository<Chave, Long> getRepository() {
		return this.chaveRepository;
	}

}
