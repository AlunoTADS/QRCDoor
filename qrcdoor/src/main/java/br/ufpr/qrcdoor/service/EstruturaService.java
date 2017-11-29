package br.ufpr.qrcdoor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.ufpr.qrcdoor.entity.Estrutura;
import br.ufpr.qrcdoor.repository.EstruturaRepository;

@Service
public class EstruturaService extends GenericService<Estrutura, Long> {
	
	@Autowired
	EstruturaRepository estruturaRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Estrutura save(Estrutura estrutura) throws Exception {
		return this.estruturaRepository.saveAndFlush(this.changePassword(estrutura));
	}
	
	public Estrutura changePassword(Estrutura estrutura) {
		if (StringUtils.isEmpty(estrutura.getSenha()) && estrutura.getId() != null) {
			estrutura.setSenha(this.estruturaRepository.findOne(estrutura.getId()).getSenha());
		} else {
			estrutura.setSenha(passwordEncoder.encode(estrutura.getSenha()));
		}
		return estrutura;
	}
	
	@Override
	public JpaRepository<Estrutura, Long> getRepository() {
		return this.estruturaRepository;
	}

}
