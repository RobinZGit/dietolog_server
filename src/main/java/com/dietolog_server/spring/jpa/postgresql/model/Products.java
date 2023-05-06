package com.dietolog_server.spring.jpa.postgresql.model;

import javax.persistence.*;

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


	public Products() {

	}

	public Products(String name, String lowercase) {
		this.name = name;
		this.lowercase = lowercase;
	}

	public long getId() {
		return _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLowercase() {
		return lowercase;
	}

	public void setLowercase(String lowercase) {
		this.lowercase = lowercase;
	}


	@Override
	public String toString() {
		return "Product [id=" + _id + ", name=" + name  + "]";
	}

}
