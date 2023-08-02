package edu.poly.assignment.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import edu.poly.assignment.dao.ProductDAO;
import edu.poly.assignment.entity.Product;
import edu.poly.assignment.entity.Report;

@SuppressWarnings("deprecation")
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDAO productDAO;

    @Override
    public List<Report> getInventoryByCategory() {
        return productDAO.getInventoryByCategory();
    }

    @Override
    public Product save(Product entity) {
        return productDAO.save(entity);
    }

    @Override
    public List<Product> saveAll(List<Product> entities) {
        return productDAO.saveAll(entities);
    }

    @Override
    public Optional<Product> findOne(Example<Product> example) {
        return productDAO.findOne(example);
    }

    @Override
    public List<Product> findAll(Sort sort) {
        return productDAO.findAll(sort);
    }

    @Override
    public void flush() {
        productDAO.flush();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productDAO.findAll(pageable);
    }

    @Override
    public Product saveAndFlush(Product entity) {
        return productDAO.saveAndFlush(entity);
    }

    @Override
    public List<Product> saveAllAndFlush(List<Product> entities) {
        return productDAO.saveAllAndFlush(entities);
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public List<Product> findAllById(List<Integer> ids) {
        return productDAO.findAllById(ids);
    }

    @Override
    public void deleteInBatch(List<Product> entities) {
        productDAO.deleteInBatch(entities);
    }

    @Override
    public Page<Product> findAll(Example<Product> example, Pageable pageable) {
        return productDAO.findAll(example, pageable);
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return productDAO.findById(id);
    }

    @Override
    public void deleteAllInBatch(List<Product> entities) {
        productDAO.deleteAllInBatch(entities);
    }

    @Override
    public boolean existsById(Integer id) {
        return productDAO.existsById(id);
    }

    @Override
    public void deleteAllByIdInBatch(List<Integer> ids) {
        productDAO.deleteAllByIdInBatch(ids);
    }

    @Override
    public <S extends Product> long count(Example<Product> example) {
        return productDAO.count(example);
    }

    @Override
    public <S extends Product> boolean exists(Example<Product> example) {
        return productDAO.exists(example);
    }

    @Override
    public void deleteAllInBatch() {
        productDAO.deleteAllInBatch();
    }

    @Override
    public Product getOne(Integer id) {
        return productDAO.getOne(id);
    }

    @Override
    public <S extends Product, R> R findBy(Example<Product> example,
            Function<FetchableFluentQuery<Product>, R> queryFunction) {
        return productDAO.findBy(example, queryFunction);
    }

    @Override
    public long count() {
        return productDAO.count();
    }

    @Override
    public void deleteById(Integer id) {
        productDAO.deleteById(id);
    }

    @Override
    public Product getById(Integer id) {
        return productDAO.getById(id);
    }

    @Override
    public void delete(Product entity) {
        productDAO.delete(entity);
    }

    @Override
    public Product getReferenceById(Integer id) {
        return productDAO.getReferenceById(id);
    }

    @Override
    public void deleteAllById(List<Integer> ids) {
        productDAO.deleteAllById(ids);
    }

    @Override
    public void deleteAll(List<Product> entities) {
        productDAO.deleteAll(entities);
    }

    @Override
    public List<Product> findAll(Example<Product> example) {
        return productDAO.findAll(example);
    }

    @Override
    public List<Product> findAll(Example<Product> example, Sort sort) {
        return productDAO.findAll(example, sort);
    }

    @Override
    public void deleteAll() {
        productDAO.deleteAll();
    }

    @Override
    public Product findProductByID(int id) {

        for (Product product : productDAO.findAll()) {
            if (product.getMasach() == id) {
                return product;
            }
        }
        return null;
    }

}
