package com.dietolog_server.spring.jpa.postgresql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dietolog_server.spring.jpa.postgresql.model.Info;

public interface InfoRepository  extends JpaRepository<Info, Long>{

  
  /* 
  @Query(value = "select _id, product, nutrient, cast(value as text) value, cast(perc1on100gr as text) perc1on100gr  \n" +
                 " from info i  where i.product = :productId", nativeQuery = true)
  List<Info> findInfoByProductId(@Param(value = "productId") Integer productId);
  */
  @Query(value = "select _id, product, nutrient, cast(value as text) value, cast(perc1on100gr as text) perc1on100gr  \n" +
  " from info i  where position(','||i.product||',' in :productList) > 0", nativeQuery = true)
  List<Info> findInfoByProductList(@Param(value = "productList") String productList);

  @Query(value = "select _id, product, nutrient, cast(value as text) value, cast(perc1on100gr as text) perc1on100gr  \n" +
  " from info i  ", nativeQuery = true)
  List<Info> findInfo();


  @Query(value = 
  "select _id, product, nutrient, value, perc1on100gr , rn from (  \n" +
  "    select _id, product, nutrient, cast(value as text) value, cast(perc1on100gr as text) perc1on100gr  \n" +
  "         , row_number() OVER (PARTITION BY i.nutrient ORDER BY i.perc1on100gr DESC ) rn  \n" +
  "    from info i  where position(','||i.nutrient||',' in :nutrientList) > 0  \n" +
  "                       and position(','||i.product||',' in :excludedProductstList) <= 0  \n" +
  ") ZZ where rn<=:topCountRecommendedProducts  \n"
  
  , nativeQuery = true)
  List<Info> findRecommendedProducts(@Param(value = "nutrientList") String nutrientList, @Param(value = "excludedProductstList") String excludedProductstList, @Param(value = "topCountRecommendedProducts") Integer topCountRecommendedProducts);

}
