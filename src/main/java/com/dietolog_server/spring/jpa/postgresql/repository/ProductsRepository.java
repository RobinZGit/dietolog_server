package com.dietolog_server.spring.jpa.postgresql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dietolog_server.spring.jpa.postgresql.model.Products;

public interface ProductsRepository extends JpaRepository<Products, Long> {
  //List<Products> findByName(String name);

  List<Products> findByNameContaining(String name);
  //
  @Query(value = "select _id, name, lowercase, 0 val  \n"+ 
                 //хинт тормозит..
                 ",(select string_agg(name||' - '||cast(perc1on100gr as text)||'%???',chr(13)||chr(13))  from (select n.name,i.perc1on100gr from info i left join nutrients n on n._id=i.nutrient where i.product=p._id order by i.perc1on100gr desc limit 10) ZZ) hint \n"+
                 " from products p where p.name like :filter", nativeQuery = true)
  List<Products> findProductsByQuery(@Param(value = "filter") String filter);

  
  @Query(value = "select string_agg(name||' - '||cast(perc1on100gr as text)||'%???',chr(13)||chr(13))  from (select n.name,i.perc1on100gr from info i left join nutrients n on n._id=i.nutrient where i.product=:productId order by i.perc1on100gr desc limit 10) ZZ  \n"
                 , nativeQuery = true)
  String getProductHint(@Param(value = "productId") Integer productId);


}
