package com.dietolog_server.spring.jpa.postgresql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dietolog_server.spring.jpa.postgresql.model.Info;

public interface InfoRepository  extends JpaRepository<Info, Long>{

  
  @Query(value = "select _id, product, nutrient, cast(value as text) value, cast(perc1on100gr as text) perc1on100gr  \n" +
                 " from info i  where i.product = :productId", nativeQuery = true)
  List<Info> findInfoByProductId(@Param(value = "productId") Integer productId);
}
