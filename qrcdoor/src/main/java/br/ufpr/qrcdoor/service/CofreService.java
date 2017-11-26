package br.ufpr.qrcdoor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.ufpr.qrcdoor.entity.Cofre;
import br.ufpr.qrcdoor.repository.CofreRepository;

@Service
public class CofreService extends GenericService<Cofre, Long> {
	
	@Autowired
	CofreRepository cofreRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
		
	public Cofre save(Cofre cofre) throws Exception {
		return this.cofreRepository.saveAndFlush(this.changePassword(cofre));
	}
	
	public Cofre changePassword(Cofre cofre) {
		Cofre old = this.cofreRepository.findOne(cofre.getId());
		if (StringUtils.isEmpty(cofre.getSenha()) && cofre.getId() != null) {
			cofre.setSenha(old.getSenha());
		} else {
			cofre.setSenha(passwordEncoder.encode(cofre.getSenha()));
		}
		if (StringUtils.isEmpty(cofre.getPin()) && cofre.getId() != null) {
			cofre.setPin(old.getPin());
		} else {
			cofre.setPin(passwordEncoder.encode(cofre.getPin()));
		}
		return cofre;
	}

	@Override
	public JpaRepository<Cofre, Long> getRepository() {
		return this.cofreRepository;
	}
	
}
