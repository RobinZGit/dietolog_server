package com.dietolog_server.spring.jpa.postgresql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dietolog_server.spring.jpa.postgresql.model.Products;

public interface ProductsRepository extends JpaRepository<Products, Long> {
  //List<Products> findByName(String name);

  List<Products> findByNameContaining(String name);
  
  @Query(value = "select _id, name, lowercase from products limit 1000000", nativeQuery = true)
  List<Products> findProductsByQuery();
}
