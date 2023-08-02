package edu.poly.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.poly.assignment.entity.CartItem;
import edu.poly.assignment.entity.Product;

public interface CartItemDAO extends JpaRepository<CartItem, Long> {
    @Query("SELECT ci FROM CartItem ci WHERE ci =:product")
    CartItem findByMasach(@Param("product") Product product);

}