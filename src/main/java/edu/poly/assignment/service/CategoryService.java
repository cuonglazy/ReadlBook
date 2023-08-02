package edu.poly.assignment.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import edu.poly.assignment.entity.Category;


public interface CategoryService {

	void deleteAll();

	List<Category> findAll(Example<Category> example, Sort sort);

	List<Category> findAll(Example<Category> example);

	void deleteAll(List<Category> entities);

	void deleteAllById(List<String> ids);

	Category getReferenceById(String id);

	void delete(Category entity);

	Category getById(String id);

	void deleteById(String id);

	long count();

	<S extends Category, R> R findBy(Example<Category> example,
			Function<FetchableFluentQuery<Category>, R> queryFunction);

	Category getOne(String id);

	void deleteAllInBatch();

	<S extends Category> boolean exists(Example<S> example);

	<S extends Category> long count(Example<Category> example);

	void deleteAllByIdInBatch(List<String> ids);

	boolean existsById(String id);

	void deleteAllInBatch(List<Category> entities);

	Optional<Category> findById(String id);

	Page<Category> findAll(Example<Category> example, Pageable pageable);

	void deleteInBatch(List<Category> entities);

	List<Category> findAllById(List<String> ids);

	List<Category> findAll();

	List<Category> saveAllAndFlush(List<Category> entities);

	Category saveAndFlush(Category entity);

	Page<Category> findAll(Pageable pageable);

	void flush();

	List<Category> findAll(Sort sort);

	Optional<Category> findOne(Example<Category> example);

	List<Category> saveAll(List<Category> entities);

	Category save(Category entity);

}
