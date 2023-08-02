package edu.poly.assignment.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import edu.poly.assignment.entity.Product;
import edu.poly.assignment.entity.Report;

public interface ProductService {

	void deleteAll();

	List<Product> findAll(Example<Product> example, Sort sort);

	List<Product> findAll(Example<Product> example);

	void deleteAll(List<Product> entities);

	void deleteAllById(List<Integer> ids);

	Product getReferenceById(Integer id);

	void delete(Product entity);

	Product getById(Integer id);

	void deleteById(Integer id);

	long count();

	<S extends Product, R> R findBy(Example<Product> example, Function<FetchableFluentQuery<Product>, R> queryFunction);

	Product getOne(Integer id);

	void deleteAllInBatch();

	<S extends Product> boolean exists(Example<Product> example);

	<S extends Product> long count(Example<Product> example);

	void deleteAllByIdInBatch(List<Integer> ids);

	boolean existsById(Integer id);

	void deleteAllInBatch(List<Product> entities);

	Optional<Product> findById(Integer id);

	Page<Product> findAll(Example<Product> example, Pageable pageable);

	void deleteInBatch(List<Product> entities);

	List<Product> findAllById(List<Integer> ids);

	List<Product> findAll();

	List<Product> saveAllAndFlush(List<Product> entities);

	Product saveAndFlush(Product entity);

	Page<Product> findAll(Pageable pageable);

	void flush();

	List<Product> findAll(Sort sort);

	Optional<Product> findOne(Example<Product> example);

	List<Product> saveAll(List<Product> entities);

	Product save(Product entity);

	List<Report> getInventoryByCategory();

	public Product findProductByID(int id);
}
