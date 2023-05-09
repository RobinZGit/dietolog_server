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
  @Query(value = "select  row_number() over(order by COALESCE(ii.value,0) desc,p._id) rownumber, p._id _id, name, lowercase, 0 val  \n"+ 
                 //хинт тормозит..
                 ",p.name||', основные нутриенты:'||chr(13)||chr(13)||(select string_agg(name||' - '||cast(value as text)||' '||units||' на 100гр. ('||perc1on100gr||'% сут.нормы)',chr(13))  from (select n.name,i.value, i.perc1on100gr, n.units from info i left join nutrients n on n._id=i.nutrient where i.product=p._id order by i.value desc limit 20) ZZ) hint \n"+
                 " from products p \n"+
                 " left join info ii on ii.nutrient=:sorting and ii.product =p._id \n" +
                 " where p.name like :filter \n"+
                 " order by COALESCE(ii.value,0) desc,p._id \n" +
                 " \n" 
                 , nativeQuery = true)
  List<Products> findProductsByQuery(@Param(value = "filter") String filter, @Param(value = "sorting") Integer sorting);

  
  @Query(value = "select string_agg(name||' - '||cast(value as text)||' '||units||' на 100гр. ('||perc1on100gr||'% сут.нормы)',chr(13))  from (select n.name,i.value, i.perc1on100gr, n.units from info i left join nutrients n on n._id=i.nutrient where i.product=:productId order by i.value desc limit 20) ZZ  \n"
                 , nativeQuery = true)
  String getProductHint(@Param(value = "productId") Integer productId);


}
