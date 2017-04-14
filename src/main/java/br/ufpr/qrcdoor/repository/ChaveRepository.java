package br.ufpr.qrcdoor.repository;

import br.ufpr.qrcdoor.entity.Chave;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "chave", path = "chave")
public interface ChaveRepository extends PagingAndSortingRepository<Chave, Long> {	
}