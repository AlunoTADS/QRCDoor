package br.ufpr.qrcdoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.Cofre;
import br.ufpr.qrcdoor.exception.ResourceNotFoundException;
import br.ufpr.qrcdoor.repository.CofreRepository;

@Service
public class CofreService {
	
	@Autowired
	CofreRepository cofreRepository;
	
	public List<Cofre> findAll() throws Exception {
		List<Cofre> list = this.cofreRepository.findAll();
		if (list == null || list.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return list;
	}
	
	public Cofre findOne(Long id) throws Exception {
		Cofre cofre = this.cofreRepository.findOne(id);
		if (cofre == null) {
			throw new ResourceNotFoundException();
		}
		return cofre;
	}
	
	public Cofre save(Cofre cofre) throws Exception {
		return this.cofreRepository.saveAndFlush(cofre);
	}
	
	public void delete(Long id) throws Exception {
		this.cofreRepository.delete(id);
	}

}
