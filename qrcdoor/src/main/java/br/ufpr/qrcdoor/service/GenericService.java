package br.ufpr.qrcdoor.service;

import java.io.Serializable;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpr.qrcdoor.exception.ResourceNotFoundException;

public abstract class GenericService<T, ID extends Serializable> {

	public Page<T> find(T t, Pageable pageable) throws Exception {
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnoreCase()
				.withIgnoreNullValues()
				.withStringMatcher(StringMatcher.CONTAINING);
		return this.getRepository().findAll(Example.of(t, matcher), pageable);
	}
	
	public T findOne(ID id) throws Exception {
		T t = this.getRepository().findOne(id);
		if (t == null) {
			throw new ResourceNotFoundException();
		}
		return t;
	}
	
	public void delete(ID id) throws Exception {
		this.getRepository().delete(id);
	}
	
	public abstract JpaRepository<T, ID> getRepository();

}
