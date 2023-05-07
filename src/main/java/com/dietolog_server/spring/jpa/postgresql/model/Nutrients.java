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

    public Nutrients() {

	}

	public Nutrients(String name, String units, String dailyrate) {
		this.name = name;
		this.units = units;
        this.dailyrate = dailyrate;
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

    public void setDailyrates(String dailyrate) {
		this.dailyrate = dailyrate;
	}


	@Override
	public String toString() {
		return "Product [id=" + _id + ", name=" + name + ", units=" + units + ", dailyrate=" + dailyrate  + "]";
	}
    
}
