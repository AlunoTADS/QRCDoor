package br.ufpr.qrcdoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.PermissaoPessoa;
import br.ufpr.qrcdoor.exception.ResourceNotFoundException;
import br.ufpr.qrcdoor.repository.PermissaoPessoaRepository;

@Service
public class PermissaoPessoaService {
	
	@Autowired
	PermissaoPessoaRepository permissaoPessoaRepository;
	
	public List<PermissaoPessoa> findAll() throws Exception {
		List<PermissaoPessoa> list = this.permissaoPessoaRepository.findAll();
		if (list == null || list.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return list;
	}
	
	public PermissaoPessoa findOne(Long id) throws Exception {
		PermissaoPessoa permissaoPessoa = this.permissaoPessoaRepository.findOne(id);
		if (permissaoPessoa == null) {
			throw new ResourceNotFoundException();
		}
		return permissaoPessoa;	
	}
	
	public PermissaoPessoa save(PermissaoPessoa permissaoPessoa) throws Exception {
		return this.permissaoPessoaRepository.saveAndFlush(permissaoPessoa);
	}
	
	public void delete(Long id) throws Exception {
		this.permissaoPessoaRepository.delete(id);
	}

}
