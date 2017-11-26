package br.ufpr.qrcdoor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.Chave;
import br.ufpr.qrcdoor.repository.ChaveRepository;

@Service
public class ChaveService extends GenericService<Chave, Long> {
	
	@Autowired
	ChaveRepository chaveRepository;
	
	public Chave save(Chave chave) throws Exception {
		return this.chaveRepository.saveAndFlush(chave);
	}

	@Override
	public JpaRepository<Chave, Long> getRepository() {
		return this.chaveRepository;
	}

}
