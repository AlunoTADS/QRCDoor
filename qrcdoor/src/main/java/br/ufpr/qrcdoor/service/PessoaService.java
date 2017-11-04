package br.ufpr.qrcdoor.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.Pessoa;
import br.ufpr.qrcdoor.exception.BusinessException;
import br.ufpr.qrcdoor.exception.ResourceNotFoundException;
import br.ufpr.qrcdoor.repository.PessoaRepository;
import br.ufpr.qrcdoor.util.Util;

@Service
public class PessoaService {
	
	@Autowired
	PessoaRepository pessoaRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<Pessoa> findAll() throws Exception {
		return this.pessoaRepository.findAll();
	}
	
	public Pessoa findOne(Long id) throws Exception {
		Pessoa pessoa = this.pessoaRepository.findOne(id);
		if (pessoa == null) {
			throw new ResourceNotFoundException();
		}
		return pessoa;
	}
	
	public Pessoa save(Pessoa pessoa) throws Exception {
		HashMap<String, List<String>> businessErrors = this.validateBusinessRules(pessoa);
		if (businessErrors.size() > 0) {
			throw new BusinessException("BusinessException", businessErrors);
		}
		pessoa.setSenha(passwordEncoder.encode(pessoa.getSenha()));
		return this.pessoaRepository.saveAndFlush(pessoa);
	}
	
	public void delete(Long id) throws Exception {
		this.pessoaRepository.delete(id);
	}
	
	public HashMap<String, List<String>> validateBusinessRules(Pessoa pessoa) {
		HashMap<String, List<String>> errors = new HashMap<String, List<String>>();
		
		// Valida se login é único
		if (this.pessoaRepository.findByLogin(pessoa.getLogin()) != null) {
			errors = Util.insertOrUpdateHashMap(errors, "login", "Login já existente no sistema, aplique outro e tente novamente.");
		}
		// Valida se documento é único
		if (this.pessoaRepository.findByDocumento(pessoa.getDocumento()) != null) {
			errors = Util.insertOrUpdateHashMap(errors, "documento", "Documento já existente no sistema, aplique outro e tente novamente.");
		}
		// Valida se email é único
		if (this.pessoaRepository.findByEmail(pessoa.getEmail()) != null) {
			errors = Util.insertOrUpdateHashMap(errors, "email", "Email já existente no sistema, aplique outro e tente novamente.");
		}
		// Valida se extensão da foto é permitida
		if (!",png,jpg,jpeg,".contains("," + pessoa.getFotoExtensao() + ",")) {
			errors = Util.insertOrUpdateHashMap(errors, "foto", "A extensão da foto não é permitida.");
		}
		
		return errors;
	}

}
