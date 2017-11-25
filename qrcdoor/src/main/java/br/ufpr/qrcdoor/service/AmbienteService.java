package br.ufpr.qrcdoor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.Ambiente;
import br.ufpr.qrcdoor.repository.AmbienteRepository;

@Service
public class AmbienteService extends GenericService<Ambiente, Long> {
	
	@Autowired
	AmbienteRepository ambienteRepository;
	
	public Ambiente save(Ambiente ambiente) throws Exception {
		return this.ambienteRepository.saveAndFlush(ambiente);
	}
	
	@Override
	public JpaRepository<Ambiente, Long> getRepository() {
		return this.ambienteRepository;
	}

}
