package br.ufpr.qrcdoor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.ufpr.qrcdoor.entity.Ambiente;
import br.ufpr.qrcdoor.repository.AmbienteRepository;

@Service
public class AmbienteService extends GenericService<Ambiente, Long> {
	
	@Autowired
	AmbienteRepository ambienteRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Ambiente save(Ambiente ambiente) throws Exception {
		return this.ambienteRepository.saveAndFlush(this.changePassword(ambiente));
	}
	
	public Ambiente changePassword(Ambiente ambiente) {
		if (StringUtils.isEmpty(ambiente.getSenha()) && ambiente.getId() != null) {
			ambiente.setSenha(this.ambienteRepository.findOne(ambiente.getId()).getSenha());
		} else {
			ambiente.setSenha(passwordEncoder.encode(ambiente.getSenha()));
		}
		return ambiente;
	}
	
	@Override
	public JpaRepository<Ambiente, Long> getRepository() {
		return this.ambienteRepository;
	}

}
