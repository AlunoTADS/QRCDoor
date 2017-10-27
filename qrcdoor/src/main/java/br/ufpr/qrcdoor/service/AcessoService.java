package br.ufpr.qrcdoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.Acesso;
import br.ufpr.qrcdoor.exception.ResourceNotFoundException;
import br.ufpr.qrcdoor.repository.AcessoRepository;

@Service
public class AcessoService {
	
	@Autowired
	AcessoRepository acessoRepository;
	
	public List<Acesso> findAll() throws Exception {
		List<Acesso> list = this.acessoRepository.findAll();
		if (list == null || list.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return list;
	}
	
	public Acesso findOne(Long id) throws Exception {
		Acesso acesso = this.acessoRepository.findOne(id);
		if (acesso == null) {
			throw new ResourceNotFoundException();
		}
		return acesso;
	}
	
	public Acesso save(Acesso acesso) throws Exception {
		return this.acessoRepository.saveAndFlush(acesso);
	}
	
	public void delete(Long id) throws Exception {
		this.acessoRepository.delete(id);
	}

}
