package br.ufpr.qrcdoor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpr.qrcdoor.entity.Chave;

public interface ChaveRepository extends JpaRepository<Chave, Long> {

}
