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

import edu.poly.assignment.dao.AccountDAO;
import edu.poly.assignment.entity.Account;

@SuppressWarnings("deprecation")
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDAO accountDAO;

    public Account save(Account entity) {
        return accountDAO.save(entity);
    }

    public List<Account> saveAll(List<Account> entities) {
        return accountDAO.saveAll(entities);
    }

    public Optional<Account> findOne(Example<Account> example) {
        return accountDAO.findOne(example);
    }

    public List<Account> findAll(Sort sort) {
        return accountDAO.findAll(sort);
    }

    public void flush() {
        accountDAO.flush();
    }

    public Page<Account> findAll(Pageable pageable) {
        return accountDAO.findAll(pageable);
    }

    public Account saveAndFlush(Account entity) {
        return accountDAO.saveAndFlush(entity);
    }

    public List<Account> saveAllAndFlush(List<Account> entities) {
        return accountDAO.saveAllAndFlush(entities);
    }

    public List<Account> findAll() {
        return accountDAO.findAll();
    }

    public List<Account> findAllById(List<String> ids) {
        return accountDAO.findAllById(ids);
    }

    public void deleteInBatch(List<Account> entities) {
        accountDAO.deleteInBatch(entities);
    }

    public Page<Account> findAll(Example<Account> example, Pageable pageable) {
        return accountDAO.findAll(example, pageable);
    }

    public Optional<Account> findById(String id) {
        return accountDAO.findById(id);
    }

    public void deleteAllInBatch(List<Account> entities) {
        accountDAO.deleteAllInBatch(entities);
    }

    public boolean existsById(String id) {
        return accountDAO.existsById(id);
    }

    public void deleteAllByIdInBatch(List<String> ids) {
        accountDAO.deleteAllByIdInBatch(ids);
    }

    public <S extends Account> long count(Example<Account> example) {
        return accountDAO.count(example);
    }

    public <S extends Account> boolean exists(Example<S> example) {
        return accountDAO.exists(example);
    }

    public void deleteAllInBatch() {
        accountDAO.deleteAllInBatch();
    }

    public Account getOne(String id) {
        return accountDAO.getOne(id);
    }

    public <S extends Account, R> R findBy(Example<Account> example,
            Function<FetchableFluentQuery<Account>, R> queryFunction) {
        return accountDAO.findBy(example, queryFunction);
    }

    public long count() {
        return accountDAO.count();
    }

    public void deleteById(String id) {
        accountDAO.deleteById(id);
    }

    public Account getById(String id) {
        return accountDAO.getById(id);
    }

    public void delete(Account entity) {
        accountDAO.delete(entity);
    }

    public Account getReferenceById(String id) {
        return accountDAO.getReferenceById(id);
    }

    public void deleteAllById(List<String> ids) {
        accountDAO.deleteAllById(ids);
    }

    public void deleteAll(List<Account> entities) {
        accountDAO.deleteAll(entities);
    }

    public List<Account> findAll(Example<Account> example) {
        return accountDAO.findAll(example);
    }

    public List<Account> findAll(Example<Account> example, Sort sort) {
        return accountDAO.findAll(example, sort);
    }

    public void deleteAll() {
        accountDAO.deleteAll();
    }

}
