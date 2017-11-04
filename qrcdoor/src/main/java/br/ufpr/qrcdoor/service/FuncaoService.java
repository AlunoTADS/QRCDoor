package br.ufpr.qrcdoor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.Funcao;
import br.ufpr.qrcdoor.exception.ResourceNotFoundException;
import br.ufpr.qrcdoor.repository.FuncaoRepository;

@Service
public class FuncaoService {
	
	@Autowired
	FuncaoRepository funcaoRepository;
	
	public Page<Funcao> find(String descricao, Pageable pageable) throws Exception {
		descricao = (descricao == null) ? "" : descricao;
		return this.funcaoRepository.findByDescricaoContaining(descricao, pageable);
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
