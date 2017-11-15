package br.ufpr.qrcdoor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufpr.qrcdoor.entity.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	@Query("select new Pessoa(p.login, p.senha, p.role) from Pessoa p where p.login = :login")
	public Pessoa loadProfile(@Param("login") String login);
	
	public Pessoa findByLogin(String login);
	
	public Pessoa findByDocumento(String documento);
	
	public Pessoa findByEmail(String email);
	
}
