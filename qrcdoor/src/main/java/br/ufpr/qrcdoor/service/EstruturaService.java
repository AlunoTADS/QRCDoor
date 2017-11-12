package br.ufpr.qrcdoor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.Estrutura;
import br.ufpr.qrcdoor.exception.ResourceNotFoundException;
import br.ufpr.qrcdoor.repository.EstruturaRepository;
import br.ufpr.qrcdoor.specification.EstruturaSpecification;

@Service
public class EstruturaService {
	
	@Autowired
	EstruturaRepository estruturaRepository;
	
	public Page<Estrutura> find(String query, Pageable pageable) throws Exception {
		Specification<Estrutura> searchSpec = EstruturaSpecification.searchContainsIgnoreCase(query);
		return this.estruturaRepository.findAll(searchSpec, pageable);
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
