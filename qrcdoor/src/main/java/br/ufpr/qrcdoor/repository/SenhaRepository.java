package br.ufpr.qrcdoor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpr.qrcdoor.entity.Senha;

public interface SenhaRepository extends JpaRepository<Senha, Long> {

}
