package br.ufpr.qrcdoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.Senha;
import br.ufpr.qrcdoor.exception.ResourceNotFoundException;
import br.ufpr.qrcdoor.repository.SenhaRepository;

@Service
public class SenhaService {
	
	@Autowired
	SenhaRepository senhaRepository;
	
	public List<Senha> findAll() throws Exception {
		List<Senha> list = this.senhaRepository.findAll();
		if (list == null || list.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return list;
	}
	
	public Senha findOne(Long id) throws Exception {
		Senha senha = this.senhaRepository.findOne(id);
		if (senha == null) {
			throw new ResourceNotFoundException();
		}
		return senha;
	}
	
	public Senha save(Senha senha) throws Exception {
		return this.senhaRepository.saveAndFlush(senha);
	}
	
	public void delete(Long id) throws Exception {
		this.senhaRepository.delete(id);
	}

}
