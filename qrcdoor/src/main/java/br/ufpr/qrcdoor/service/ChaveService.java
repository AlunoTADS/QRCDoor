package br.ufpr.qrcdoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.Chave;
import br.ufpr.qrcdoor.exception.ResourceNotFoundException;
import br.ufpr.qrcdoor.repository.ChaveRepository;

@Service
public class ChaveService {
	
	@Autowired
	ChaveRepository chaveRepository;
	
	public List<Chave> findAll() throws Exception {
		List<Chave> list = this.chaveRepository.findAll();
		if (list == null || list.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return list;
	}
	
	public Chave findOne(Long id) throws Exception {
		Chave chave = this.chaveRepository.findOne(id);
		if (chave == null) {
			throw new ResourceNotFoundException();
		}
		return chave;
	}
	
	public Chave save(Chave chave) throws Exception {
		return this.chaveRepository.saveAndFlush(chave);
	}
	
	public void delete(Long id) throws Exception {
		this.chaveRepository.delete(id);
	}

}
