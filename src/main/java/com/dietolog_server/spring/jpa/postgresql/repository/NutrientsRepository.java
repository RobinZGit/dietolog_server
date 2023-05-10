package com.dietolog_server.spring.jpa.postgresql.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dietolog_server.spring.jpa.postgresql.model.Nutrients;

public interface NutrientsRepository  extends JpaRepository<Nutrients, Long>{
  List<Nutrients> findByName(String name);

  List<Nutrients> findByNameContaining(String name);
  
  @Query(value = "select _id, name, units, dailyrate, 0 val \n" +
                //хинт тормозит..
                 ",n.name||', основные продукты:'||chr(13)||chr(13)||(select string_agg(name||' - '||cast(value as text)||' '||units||' на 100гр. ('||perc1on100gr||'% сут.нормы)'   ,chr(13))   from (select p.name, i.value, i.perc1on100gr from info i left join products p on p._id=i.product where i.nutrient=n._id order by i.value desc limit 20) ZZ) hint  \n"+
                 " from nutrients n", nativeQuery = true)
  List<Nutrients> findNutrientsByQuery();
}