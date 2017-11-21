package br.ufpr.qrcdoor.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.ufpr.qrcdoor.entity.Pessoa;
import br.ufpr.qrcdoor.exception.BusinessException;
import br.ufpr.qrcdoor.repository.PessoaRepository;
import br.ufpr.qrcdoor.util.Util;

@Service
public class PessoaService extends GenericService<Pessoa, Long> {

	@Autowired
	PessoaRepository pessoaRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public Pessoa insert(Pessoa pessoa) throws Exception {
		HashMap<String, List<String>> businessErrors = this.validateInsertBusinessRules(pessoa);
		if (businessErrors.size() > 0) {
			throw new BusinessException("BusinessException", businessErrors);
		}
		return this.pessoaRepository.saveAndFlush(this.changePassword(pessoa));
	}

	public Pessoa update(Pessoa pessoa) throws Exception {
		HashMap<String, List<String>> businessErrors = this.validateUpdateBusinessRules(pessoa);
		if (businessErrors.size() > 0) {
			throw new BusinessException("BusinessException", businessErrors);
		}
		return this.pessoaRepository.saveAndFlush(this.changePassword(pessoa));
	}

	public HashMap<String, List<String>> validateInsertBusinessRules(Pessoa pessoa) {
		HashMap<String, List<String>> errors = new HashMap<String, List<String>>();

		// Valida se login é único
		if (this.pessoaRepository.findByLogin(pessoa.getLogin()) != null) {
			errors = Util.insertOrUpdateHashMap(errors, "login",
					"Login já existente no sistema, aplique outro e tente novamente.");
		}
		// Valida se documento é único
		if (this.pessoaRepository.findByDocumento(pessoa.getDocumento()) != null) {
			errors = Util.insertOrUpdateHashMap(errors, "documento",
					"Documento já existente no sistema, aplique outro e tente novamente.");
		}
		// Valida se email é único
		if (this.pessoaRepository.findByEmail(pessoa.getEmail()) != null) {
			errors = Util.insertOrUpdateHashMap(errors, "email",
					"Email já existente no sistema, aplique outro e tente novamente.");
		}
//		// Valida se extensão da foto é permitida
//		if (!",png,jpg,jpeg,".contains("," + pessoa.getFotoExtensao() + ",")) {
//			errors = Util.insertOrUpdateHashMap(errors, "foto", "A extensão da foto não é permitida.");
//		}

		return errors;
	}

	public HashMap<String, List<String>> validateUpdateBusinessRules(Pessoa pessoa) {
		HashMap<String, List<String>> errors = new HashMap<String, List<String>>();
		Pessoa oldPessoa = this.pessoaRepository.findOne(pessoa.getId());

		// Valida se login é único
		if (!pessoa.getLogin().equals(oldPessoa.getLogin())) {
			if (this.pessoaRepository.findByLogin(pessoa.getLogin()) != null) {
				errors = Util.insertOrUpdateHashMap(errors, "login",
						"Login já existente no sistema, aplique outro e tente novamente.");
			}
		}
		// Valida se documento é único
		if (!pessoa.getDocumento().equals(oldPessoa.getDocumento())) {
			if (this.pessoaRepository.findByDocumento(pessoa.getDocumento()) != null) {
				errors = Util.insertOrUpdateHashMap(errors, "documento",
						"Documento já existente no sistema, aplique outro e tente novamente.");
			}
		}
		// Valida se email é único
		if (!pessoa.getEmail().equals(oldPessoa.getEmail())) {
			if (this.pessoaRepository.findByEmail(pessoa.getEmail()) != null) {
				errors = Util.insertOrUpdateHashMap(errors, "email",
						"Email já existente no sistema, aplique outro e tente novamente.");
			}
		}
//		// Valida se extensão da foto é permitida
//		if (!",png,jpg,jpeg,".contains("," + pessoa.getFotoExtensao() + ",")) {
//			errors = Util.insertOrUpdateHashMap(errors, "foto", "A extensão da foto não é permitida.");
//		}

		return errors;
	}

	public Pessoa changePassword(Pessoa pessoa) {
		if (StringUtils.isEmpty(pessoa.getSenha()) && pessoa.getId() != null) {
			pessoa.setSenha(this.pessoaRepository.findOne(pessoa.getId()).getSenha());
		} else {
			pessoa.setSenha(passwordEncoder.encode(pessoa.getSenha()));
		}
		return pessoa;
	}

	@Override
	public JpaRepository<Pessoa, Long> getRepository() {
		return this.pessoaRepository;
	}

}
