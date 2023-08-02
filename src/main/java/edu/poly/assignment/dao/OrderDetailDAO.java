package edu.poly.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.poly.assignment.entity.Order;

public interface OrderDetailDAO extends JpaRepository<Order, Long> {

}
