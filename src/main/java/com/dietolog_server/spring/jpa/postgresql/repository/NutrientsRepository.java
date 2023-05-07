package com.dietolog_server.spring.jpa.postgresql.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dietolog_server.spring.jpa.postgresql.model.Nutrients;

public interface NutrientsRepository  extends JpaRepository<Nutrients, Long>{
  List<Nutrients> findByName(String name);

  List<Nutrients> findByNameContaining(String name);
  
  @Query(value = "select _id, name, units, dailyrate from nutrients ", nativeQuery = true)
  List<Nutrients> findNutrientsByQuery();//{return null;};
}
