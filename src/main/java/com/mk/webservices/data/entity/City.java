package com.mk.webservices.data.entity;

import javax.persistence.*;

@Entity
@Table(name="city")
public class City {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
//	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
//	@JoinColumn(name = "country_id")
//	private Country country;
	
	public City() {

	}
	
	public City(String name) {
		super();
		this.name = name;
		//this.country = country;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Country getCountry() {
//		return country;
//	}
//
//	public void setCountry(Country country) {
//		this.country = country;
//	}

	
	
}
