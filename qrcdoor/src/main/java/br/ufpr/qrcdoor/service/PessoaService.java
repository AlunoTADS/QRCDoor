package br.ufpr.qrcdoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.Pessoa;
import br.ufpr.qrcdoor.exception.ResourceNotFoundException;
import br.ufpr.qrcdoor.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	public List<Pessoa> findAll() throws Exception {
		return this.pessoaRepository.findAll();
	}
	
	public Pessoa findOne(Long id) throws Exception {
		Pessoa pessoa = this.pessoaRepository.findOne(id);
		if (pessoa == null) {
			throw new ResourceNotFoundException();
		}
		return pessoa;
	}
	
	public Pessoa save(Pessoa pessoa) throws Exception {
		return this.pessoaRepository.saveAndFlush(pessoa);
	}
	
	public void delete(Long id) throws Exception {
		this.pessoaRepository.delete(id);
	}

}
