package br.ufpr.qrcdoor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpr.qrcdoor.entity.Ambiente;

public interface AmbienteRepository extends JpaRepository<Ambiente, Long> {

}
