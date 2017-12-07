package br.ufpr.qrcdoor.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.ufpr.qrcdoor.entity.Chave;
import br.ufpr.qrcdoor.entity.Estrutura;
import br.ufpr.qrcdoor.entity.Pessoa;
import br.ufpr.qrcdoor.repository.EstruturaRepository;
import br.ufpr.qrcdoor.repository.PessoaRepository;

@Service
public class EstruturaService extends GenericService<Estrutura, Long> {
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	EstruturaRepository estruturaRepository;
	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public boolean hasPermission(Chave chave, Estrutura estrutura) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("now", new Date());
		parameters.put("idchave", chave.getId());
		parameters.put("assinatura", chave.getAssinatura() + "%");
		parameters.put("idestrutura", estrutura.getId());
		
		String sql = "SELECT 1 "
				+"FROM "
				+"	CHAVE C "
				+"	INNER JOIN PESSOA P ON P.IDPESSOA = C.IDPESSOA "
				+"	INNER JOIN PERMISSAOPESSOA PP ON PP.IDPESSOA = P.IDPESSOA "
				+"	INNER JOIN PERMISSAO PPP ON PPP.IDPERMISSAO = PP.IDPERMISSAO "
				+"	INNER JOIN PERMISSAOESTRUTURA PE ON PE.IDPERMISSAO = PPP.IDPERMISSAO "
				+"WHERE "
				+"	C.IDCHAVE = :idchave "
				+"	AND C.ASSINATURA LIKE :assinatura"
				+"	AND PE.IDESTRUTURA = :idestrutura "
				+"	AND PPP.ABRE = 'T' "
				+"	AND CURRENT_DATE BETWEEN COALESCE(PPP.DATAINICIO, CURRENT_DATE) AND COALESCE(PPP.DATAFIM, CURRENT_DATE) "
				+"	AND SUBSTR(PPP.MESES, CAST(TO_CHAR(CURRENT_DATE, 'MM') AS INTEGER), 1) = '1' "
				+"	AND SUBSTR(PPP.DIASSEMANA, CAST(TO_CHAR(CURRENT_DATE, 'D') AS INTEGER), 1) = '1' "
				+"	AND :now BETWEEN COALESCE(PPP.HORAINICIO, '00:00') AND COALESCE(PPP.HORAFIM, '23:59') "
				+"UNION "
				+"SELECT 1 "
				+"FROM "
				+"	CHAVE C "
				+"	INNER JOIN PESSOA P ON P.IDPESSOA = C.IDPESSOA "
				+"	INNER JOIN FUNCAOPESSOA FP ON FP.IDPESSOA = P.IDPESSOA "
				+"	INNER JOIN PERMISSAOFUNCAO PF ON PF.IDFUNCAO = FP.IDFUNCAO "
				+"	INNER JOIN PERMISSAO PPP ON PPP.IDPERMISSAO = PF.IDPERMISSAO "
				+"	INNER JOIN PERMISSAOESTRUTURA PE ON PE.IDPERMISSAO = PPP.IDPERMISSAO "
				+"WHERE "
				+"	C.IDCHAVE = :idchave "
				+"	AND C.ASSINATURA LIKE :assinatura"
				+"	AND PE.IDESTRUTURA = :idestrutura "
				+"	AND PPP.ABRE = 'T' "
				+"	AND CURRENT_DATE BETWEEN COALESCE(PPP.DATAINICIO, CURRENT_DATE) AND COALESCE(PPP.DATAFIM, CURRENT_DATE) "
				+"	AND SUBSTR(PPP.MESES, CAST(TO_CHAR(CURRENT_DATE, 'MM') AS INTEGER), 1) = '1' "
				+"	AND SUBSTR(PPP.DIASSEMANA, CAST(TO_CHAR(CURRENT_DATE, 'D') AS INTEGER), 1) = '1' "
				+"	AND :now BETWEEN COALESCE(PPP.HORAINICIO, '00:00') AND COALESCE(PPP.HORAFIM, '23:59')";
		
		List<Integer> result;
		try {
			result = (List<Integer>) namedParameterJdbcTemplate.queryForList(sql, parameters, Integer.class);
		} catch(EmptyResultDataAccessException e) {
			result = null;
		}
		
		return result != null && result.size() > 0;
	}
	
	public List<Estrutura> list(String username) throws Exception {
		Pessoa pessoa = this.pessoaRepository.findByLogin(username);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idpessoa", pessoa.getId());
		
		String sql = "SELECT E.* "
				+"FROM "
				+"	CHAVE C "
				+"	INNER JOIN PESSOA P ON P.IDPESSOA = C.IDPESSOA "
				+"	INNER JOIN PERMISSAOPESSOA PP ON PP.IDPESSOA = P.IDPESSOA "
				+"	INNER JOIN PERMISSAO PPP ON PPP.IDPERMISSAO = PP.IDPERMISSAO "
				+"	INNER JOIN PERMISSAOESTRUTURA PE ON PE.IDPERMISSAO = PPP.IDPERMISSAO "
				+"	INNER JOIN ESTRUTURA E ON E.IDESTRUTURA = PE.IDESTRUTURA "
				+"WHERE "
				+"	PPP.ABRE = 'T' "
				+"	AND P.IDPESSOA = :idpessoa "
				+"UNION "
				+"SELECT E.* "
				+"FROM "
				+"	CHAVE C "
				+"	INNER JOIN PESSOA P ON P.IDPESSOA = C.IDPESSOA "
				+"	INNER JOIN FUNCAOPESSOA FP ON FP.IDPESSOA = P.IDPESSOA "
				+"	INNER JOIN PERMISSAOFUNCAO PF ON PF.IDFUNCAO = FP.IDFUNCAO "
				+"	INNER JOIN PERMISSAO PPP ON PPP.IDPERMISSAO = PF.IDPERMISSAO "
				+"	INNER JOIN PERMISSAOESTRUTURA PE ON PE.IDPERMISSAO = PPP.IDPERMISSAO "
				+"	INNER JOIN ESTRUTURA E ON E.IDESTRUTURA = PE.IDESTRUTURA "
				+"WHERE "
				+"	PPP.ABRE = 'T' "
				+"	AND P.IDPESSOA = :idpessoa";
		List<Estrutura> result = new ArrayList<>();
		List<Map<String, Object>> rows;
		try {
			rows = (List<Map<String, Object>>) namedParameterJdbcTemplate.queryForList(sql, parameters);
			for (Map<String, Object> row: rows) {
				Estrutura estrutura = new Estrutura();
				estrutura.setId((Long)row.get("idestrutura"));
				estrutura.setDescricao((String)row.get("descricao"));
				result.add(estrutura);
			}
		} catch(EmptyResultDataAccessException e) {
		}
		
		return result;
	}
	
	public Estrutura save(Estrutura estrutura) throws Exception {
		return this.estruturaRepository.saveAndFlush(this.changeFoto(this.changePassword(estrutura)));
	}
	
	public Estrutura changePassword(Estrutura estrutura) {
		if (StringUtils.isEmpty(estrutura.getSenha()) && estrutura.getId() != null) {
			estrutura.setSenha(this.estruturaRepository.findOne(estrutura.getId()).getSenha());
		} else {
			estrutura.setSenha(passwordEncoder.encode(estrutura.getSenha()));
		}
		return estrutura;
	}
	
	public Estrutura changeFoto(Estrutura estrutura) {
		if (estrutura.getFoto() == null && estrutura.getId() != null) {
			estrutura.setFoto(this.estruturaRepository.findOne(estrutura.getId()).getFoto());
			estrutura.setFotoExtensao(this.estruturaRepository.findOne(estrutura.getId()).getFotoExtensao());
		}
		return estrutura;
	}
	
	@Override
	public JpaRepository<Estrutura, Long> getRepository() {
		return this.estruturaRepository;
	}

}
