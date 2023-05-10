package com.dietolog_server.spring.jpa.postgresql.model;


import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Info {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long _id;

    @Column(name = "value")
	private String value;
	
    @Column(name = "perc1on100gr")
	private String perc1on100gr;
    
	@Column(name = "product")
    private Integer product;

    @Column(name = "nutrient")
    private Integer nutrient;

}
