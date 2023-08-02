package edu.poly.assignment.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import edu.poly.assignment.entity.Account;

public interface AccountService {
    List<Account> findAll(Example<Account> example, Sort sort);

    List<Account> findAll(Example<Account> example);

    void deleteAll();

    Account getReferenceById(String id);

    void deleteAll(List<Account> entities);

    void deleteAllById(List<String> ids);

    Account getById(String id);

    void delete(Account entity);

    Account getOne(String id);

    void deleteById(String id);

    void deleteAllInBatch();

    long count();

    <S extends Account, R> R findBy(Example<Account> example, Function<FetchableFluentQuery<Account>, R> queryFunction);

    void deleteAllByIdInBatch(List<String> ids);

    <S extends Account> boolean exists(Example<S> example);

    void deleteAllInBatch(List<Account> entities);

    <S extends Account> long count(Example<Account> example);

    boolean existsById(String id);

    void deleteInBatch(List<Account> entities);

    Optional<Account> findById(String id);

    Page<Account> findAll(Example<Account> example, Pageable pageable);

    List<Account> saveAllAndFlush(List<Account> entities);

    Account saveAndFlush(Account entity);

    void flush();

    List<Account> saveAll(List<Account> entities);

    List<Account> findAllById(List<String> ids);

    List<Account> findAll(Sort sort);

    Page<Account> findAll(Pageable pageable);

    List<Account> findAll();

    Optional<Account> findOne(Example<Account> example);

    Account save(Account entity);

}
