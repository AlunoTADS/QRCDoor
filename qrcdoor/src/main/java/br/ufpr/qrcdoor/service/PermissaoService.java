package br.ufpr.qrcdoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.Permissao;
import br.ufpr.qrcdoor.exception.ResourceNotFoundException;
import br.ufpr.qrcdoor.repository.PermissaoRepository;

@Service
public class PermissaoService {
	
	@Autowired
	PermissaoRepository permissaoRepository;
	
	public List<Permissao> findAll() throws Exception {
		return this.permissaoRepository.findAll();
	}
	
	public Permissao findOne(Long id) throws Exception {
		Permissao permissao = this.permissaoRepository.findOne(id);
		if (permissao == null) {
			throw new ResourceNotFoundException();
		}
		return permissao;
	}
	
	public Permissao save(Permissao permissao) throws Exception {
		return this.permissaoRepository.saveAndFlush(permissao);
	}
	
	public void delete(Long id) throws Exception {
		this.permissaoRepository.delete(id);
	}

}
