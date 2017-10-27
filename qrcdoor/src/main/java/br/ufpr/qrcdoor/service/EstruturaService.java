package br.ufpr.qrcdoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.Estrutura;
import br.ufpr.qrcdoor.exception.ResourceNotFoundException;
import br.ufpr.qrcdoor.repository.EstruturaRepository;

@Service
public class EstruturaService {
	
	@Autowired
	EstruturaRepository estruturaRepository;
	
	public List<Estrutura> findAll() throws Exception {
		List<Estrutura> list = this.estruturaRepository.findAll();
		if (list == null || list.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return list;
	}
	
	public Estrutura findOne(Long id) throws Exception {
		Estrutura estrutura = this.estruturaRepository.findOne(id);
		if (estrutura == null) {
			throw new ResourceNotFoundException();
		}
		return estrutura;
	}
	
	public Estrutura save(Estrutura estrutura) throws Exception {
		return this.estruturaRepository.saveAndFlush(estrutura);
	}
	
	public void delete(Long id) throws Exception {
		this.estruturaRepository.delete(id);
	}

}
