<<<<<<< HEAD:src/main/java/br/ufpr/qrcdoor/repository/PessoaRepository.java
package br.ufpr.qrcdoor.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.ufpr.qrcdoor.entity.Pessoa;

@RepositoryRestResource(collectionResourceRel = "pessoa", path = "pessoa")
public interface PessoaRepository extends PagingAndSortingRepository<Pessoa, Long> {
	
	List<Pessoa> findByNome(@Param("name") String name);

}
=======
package br.ufpr.qrcdoor.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.ufpr.qrcdoor.entity.Pessoa;

@RepositoryRestResource(collectionResourceRel = "pessoa", path = "pessoa")
public interface PessoaRepository extends PagingAndSortingRepository<Pessoa, Long> {
	
	List<Pessoa> findByNome(@Param("name") String name);

}
>>>>>>> origin/master:src/main/java/br/ufpr/qrcdoor/repository/PessoaRepository.java
