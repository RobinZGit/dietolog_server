package com.dietolog_server.spring.jpa.postgresql.model;
import javax.persistence.*;

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

	private Double val;

	private String hint;

    public Nutrients() {

	}

	public Nutrients(String name, String units, String dailyrate, Double val,  String hint) {
		this.name = name;
		this.units = units;
        this.dailyrate = dailyrate;
		this.val = val;
		this.hint = hint;
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

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

    public void setDailyrate(String dailyrate) {
		this.dailyrate = dailyrate;
	}

	public String getDailyrate(String dailyrate) {
		return dailyrate;
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


	@Override
	public String toString() {
		return "Product [id=" + _id + ", name=" + name + ", units=" + units + ", dailyrate=" + dailyrate  + "]";
	}
    
}
