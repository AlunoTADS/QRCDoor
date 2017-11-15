package br.ufpr.qrcdoor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.Estrutura;
import br.ufpr.qrcdoor.repository.EstruturaRepository;

@Service
public class EstruturaService extends GenericService<Estrutura, Long> {
	
	@Autowired
	EstruturaRepository estruturaRepository;
	
	public Estrutura save(Estrutura estrutura) throws Exception {
		return this.estruturaRepository.saveAndFlush(estrutura);
	}
	
	@Override
	public JpaRepository<Estrutura, Long> getRepository() {
		return this.estruturaRepository;
	}

}
