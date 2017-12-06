package br.ufpr.qrcdoor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpr.qrcdoor.entity.PermissaoPessoa;
import br.ufpr.qrcdoor.entity.Pessoa;

public interface PermissaoPessoaRepository extends JpaRepository<PermissaoPessoa, Long> {
	
	public List<PermissaoPessoa> findAllByPessoa(Pessoa pessoa);

}
