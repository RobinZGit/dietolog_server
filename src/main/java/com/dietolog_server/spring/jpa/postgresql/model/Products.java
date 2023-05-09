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

	private Double val;

	private String hint;

	private Integer rownumber;


	public Products() {

	}

	public Products(String name, String lowercase, Double val,  String hint, Integer rownumber) {
		this.name = name;
		this.lowercase = lowercase;
		this.val = val;
		this.hint = hint;
		this.rownumber = rownumber;
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

	public Double getVal() {
		return val;
	}

	public void setVal(Double val) {
		this.val = val;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public Integer getRownumber() {
		return rownumber;
	}

	public void setRownumber(Integer rownumber) {
		this.rownumber = rownumber;
	}


	@Override
	public String toString() {
		return "Product [id=" + _id + ", name=" + name  + "]";
	}

}
