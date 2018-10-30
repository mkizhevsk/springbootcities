package com.mk.webservices.data.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="country")
public class Country {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(fetch= FetchType.EAGER, cascade= CascadeType.ALL)
	@JoinColumn(name="country_id")
	private List<City> cities;

	public Country() {
	}

	public void addCity(City tempCity) {
		
		if(cities == null) {
			cities = new ArrayList<>();
		}
		
		cities.add(tempCity);

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
	
	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + "]";
	}

}
