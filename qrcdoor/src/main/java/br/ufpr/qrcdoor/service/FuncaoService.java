package br.ufpr.qrcdoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.Funcao;
import br.ufpr.qrcdoor.exception.ResourceNotFoundException;
import br.ufpr.qrcdoor.repository.FuncaoRepository;

@Service
public class FuncaoService {
	
	@Autowired
	FuncaoRepository funcaoRepository;
	
	public List<Funcao> findAll() throws Exception {
		List<Funcao> list = this.funcaoRepository.findAll();
		if (list == null || list.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return list;
	}
	
	public Funcao findOne(Long id) throws Exception {
		Funcao funcao = this.funcaoRepository.findOne(id);
		if (funcao == null) {
			throw new ResourceNotFoundException();
		}
		return funcao;
	}
	
	public Funcao save(Funcao funcao) throws Exception {
		return this.funcaoRepository.saveAndFlush(funcao);
	}
	
	public void delete(Long id) throws Exception {
		this.funcaoRepository.delete(id);
	}

}
