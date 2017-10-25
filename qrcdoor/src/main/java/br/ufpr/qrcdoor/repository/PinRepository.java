package br.ufpr.qrcdoor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpr.qrcdoor.entity.Pin;

public interface PinRepository extends JpaRepository<Pin, Long> {

}
