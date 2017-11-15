package br.ufpr.qrcdoor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.Funcao;
import br.ufpr.qrcdoor.repository.FuncaoRepository;

@Service
public class FuncaoService extends GenericService<Funcao, Long> {
	
	@Autowired
	FuncaoRepository funcaoRepository;
	
	public Funcao save(Funcao funcao) throws Exception {
		return this.funcaoRepository.saveAndFlush(funcao);
	}
	
	@Override
	public JpaRepository<Funcao, Long> getRepository() {
		return this.funcaoRepository;
	}

}
