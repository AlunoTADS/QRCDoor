package br.ufpr.qrcdoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.Ambiente;
import br.ufpr.qrcdoor.exception.ResourceNotFoundException;
import br.ufpr.qrcdoor.repository.AmbienteRepository;

@Service
public class AmbienteService {
	
	@Autowired
	AmbienteRepository ambienteRepository;
	
	public List<Ambiente> findAll() throws Exception {
		List<Ambiente> list = this.ambienteRepository.findAll();
		if (list == null || list.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return list;
	}
	
	public Ambiente findOne(Long id) throws Exception {
		Ambiente ambiente = this.ambienteRepository.findOne(id);
		if (ambiente == null) {
			throw new ResourceNotFoundException();
		}
		return ambiente;
	}
	
	public Ambiente save(Ambiente ambiente) throws Exception {
		return this.ambienteRepository.saveAndFlush(ambiente);
	}
	
	public void delete(Long id) throws Exception {
		this.ambienteRepository.delete(id);
	}

}
