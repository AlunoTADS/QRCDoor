package br.ufpr.qrcdoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.Pin;
import br.ufpr.qrcdoor.exception.ResourceNotFoundException;
import br.ufpr.qrcdoor.repository.PinRepository;

@Service
public class PinService {
	
	@Autowired
	PinRepository pinRepository;
	
	public List<Pin> findAll() throws Exception {
		List<Pin> list = this.pinRepository.findAll();
		if (list == null || list.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return list;
	}
	
	public Pin findOne(Long id) throws Exception {
		Pin pin = this.pinRepository.findOne(id);
		if (pin == null) {
			throw new ResourceNotFoundException();
		}
		return pin;
	}
	
	public Pin save(Pin pin) throws Exception {
		return this.pinRepository.saveAndFlush(pin);
	}
	
	public void delete(Long id) throws Exception {
		this.pinRepository.delete(id);
	}

}
