package br.ufpr.qrcdoor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.Cofre;
import br.ufpr.qrcdoor.repository.CofreRepository;

@Service
public class CofreService extends GenericService<Cofre, Long> {
	
	@Autowired
	CofreRepository cofreRepository;
		
	public Cofre save(Cofre cofre) throws Exception {
		return this.cofreRepository.saveAndFlush(cofre);
	}

	@Override
	public JpaRepository<Cofre, Long> getRepository() {
		return this.cofreRepository;
	}
	
}
