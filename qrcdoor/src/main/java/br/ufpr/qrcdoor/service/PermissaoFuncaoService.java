package br.ufpr.qrcdoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.PermissaoFuncao;
import br.ufpr.qrcdoor.exception.ResourceNotFoundException;
import br.ufpr.qrcdoor.repository.PermissaoFuncaoRepository;

@Service
public class PermissaoFuncaoService {

	@Autowired
	PermissaoFuncaoRepository permissaoFuncaoRepository;
	
	public List<PermissaoFuncao> findAll() throws Exception {
		List<PermissaoFuncao> list = this.permissaoFuncaoRepository.findAll();
		if (list == null || list.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return list;
	}
	
	public PermissaoFuncao findOne(Long id) throws Exception {
		PermissaoFuncao permissaoFuncao = this.permissaoFuncaoRepository.findOne(id);
		if (permissaoFuncao == null) {
			throw new ResourceNotFoundException();
		}
		return permissaoFuncao;
	}
	
	public PermissaoFuncao save(PermissaoFuncao permissaoFuncao) throws Exception {
		return this.permissaoFuncaoRepository.saveAndFlush(permissaoFuncao);
	}
	
	public void delete(Long id) throws Exception {
		this.permissaoFuncaoRepository.delete(id);
	}
}
