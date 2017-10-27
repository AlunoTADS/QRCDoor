package br.ufpr.qrcdoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.Uf;
import br.ufpr.qrcdoor.exception.ResourceNotFoundException;
import br.ufpr.qrcdoor.repository.UfRepository;

@Service
public class UfService {
	
	@Autowired
	UfRepository ufRepository;
	
	public List<Uf> findAll() throws Exception {
		List<Uf> list = this.ufRepository.findAll();
		if (list == null || list.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return list;
	}
	
	public Uf findOne(Long id) throws Exception {
		Uf uf = this.ufRepository.findOne(id);
		if (uf == null) {
			throw new ResourceNotFoundException();
		}
		return uf;
	}
	
	public Uf save(Uf uf) throws Exception {
		return this.ufRepository.saveAndFlush(uf);
	}
	
	public void delete(Long id) throws Exception {
		this.ufRepository.delete(id);
	}

}
