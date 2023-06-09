package com.dietolog_server.spring.jpa.postgresql.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "nutrients")
public class Nutrients {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long _id;

	@Column(name = "name")
	private String name;

	@Column(name = "units")
	private String units;

    @Column(name = "dailyrate")
	private String dailyrate;

	@Column(name = "min_dailyrate")
	private Double min_dailyrate;

	@Column(name = "max_dailyrate")
	private Double max_dailyrate;

	private Double koeff_to_miligr; //на сколько умножить val чтобы получить милигрвммы

	private Double val;

	private String hint;

	private Integer excluded;

    //TODO   если удалить этот геттер - падает при сортировке Resolved [org.springframework.web.method.annotation.MethodArgumentTypeMismatchException: Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'; nested exception is java.lang.NumberFormatException: For input string: "undefined"]
	public long getId() {
		return _id;
	}

}
