package edu.poly.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.poly.assignment.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, String>{

}
