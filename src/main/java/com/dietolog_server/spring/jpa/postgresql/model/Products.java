package com.dietolog_server.spring.jpa.postgresql.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long _id;

	@Column(name = "name")
	private String name;

	@Column(name = "lowercase")
	private String lowercase;
	
	private Double val;
	
	private String hint;
    
	private Integer rownumber;

	private Integer isrecommended;

	private Integer isnotrecommended;

	private Integer excluded;
}
