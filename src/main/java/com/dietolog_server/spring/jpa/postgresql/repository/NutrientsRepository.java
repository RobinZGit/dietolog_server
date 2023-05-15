package com.dietolog_server.spring.jpa.postgresql.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dietolog_server.spring.jpa.postgresql.model.Nutrients;

public interface NutrientsRepository  extends JpaRepository<Nutrients, Long>{
  List<Nutrients> findByName(String name);

  List<Nutrients> findByNameContaining(String name);
  
  @Query(value = "select _id, name, units, dailyrate, (select max(i.value*100000/(0.00001+i.perc1on100gr_s)) from info i where i.nutrient = n._id) n_dailyrate \n"+
                 ", min_dailyrate, max_dailyrate, case n.units when 'г' then 1000 when 'мг' then 1 when 'мкг' then 0.001 when 'кКал' then 100000 else 1 end koeff_to_miligr, 0 val \n" +
                //хинт тормозит..
                 ",n.name||', основные продукты:'||chr(13)||chr(13)||(select string_agg(name||' - '||cast(value as text)||' '||units||' на 100гр. ('||perc1on100gr||'% сут.нормы)'   ,chr(13))   from (select p.name, i.value, i.perc1on100gr from info i left join products p on p._id=i.product where i.nutrient=n._id order by i.value desc limit 20) ZZ) hint  \n"+
                 " from nutrients n order by coalesce(n.min_dailyrate,-1)*(case n.units when 'г' then 1000 when 'мг' then 1 when 'мкг' then 0.001 when 'кКал' then 100000 else 1 end) desc,n._id", nativeQuery = true)
  List<Nutrients> findNutrientsByQuery();
}