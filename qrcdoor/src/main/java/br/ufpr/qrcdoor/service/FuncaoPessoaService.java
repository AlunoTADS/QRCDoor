package br.ufpr.qrcdoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.FuncaoPessoa;
import br.ufpr.qrcdoor.exception.ResourceNotFoundException;
import br.ufpr.qrcdoor.repository.FuncaoPessoaRepository;

@Service
public class FuncaoPessoaService {
	
	@Autowired
	FuncaoPessoaRepository funcaoPessoaRepository;
	
	public List<FuncaoPessoa> findAll() throws Exception {
		List<FuncaoPessoa> list = this.funcaoPessoaRepository.findAll();
		if (list == null || list.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return list;
	}
	
	public FuncaoPessoa findOne(Long id) throws Exception {
		FuncaoPessoa funcaoPessoa = this.funcaoPessoaRepository.findOne(id);
		if (funcaoPessoa == null) {
			throw new ResourceNotFoundException();
		}
		return funcaoPessoa;
	}
	
	public FuncaoPessoa save(FuncaoPessoa funcaoPessoa) throws Exception {
		return this.funcaoPessoaRepository.saveAndFlush(funcaoPessoa);
	}
	
	public void delete(Long id) throws Exception {
		this.funcaoPessoaRepository.delete(id);
	}

}
