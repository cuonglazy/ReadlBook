package edu.poly.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.poly.assignment.entity.Account;

public interface AccountDAO extends JpaRepository<Account, String> {
    
}
