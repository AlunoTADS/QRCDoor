package br.ufpr.qrcdoor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpr.qrcdoor.entity.FuncaoPessoa;
import br.ufpr.qrcdoor.entity.Pessoa;

public interface FuncaoPessoaRepository extends JpaRepository<FuncaoPessoa, Long> {
	
	public List<FuncaoPessoa> findAllByPessoa(Pessoa pessoa);

}
