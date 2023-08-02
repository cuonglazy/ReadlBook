package edu.poly.assignment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.poly.assignment.entity.Product;
import edu.poly.assignment.entity.Report;

public interface ProductDAO extends JpaRepository<Product, Integer> {
    @Query(name="getInventoryByCategory")
    public List<Report> getInventoryByCategory(); 
    @Query("SELECT p FROM Product p WHERE p.name LIKE :name")
    public List<Product> findByName(@Param("name") String name);
}
