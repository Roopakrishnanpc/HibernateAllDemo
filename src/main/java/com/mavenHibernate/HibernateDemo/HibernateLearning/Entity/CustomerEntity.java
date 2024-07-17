package com.mavenHibernate.HibernateDemo.HibernateLearning.Entity;
import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name="customer")
public class CustomerEntity {
	
	@Id
	private int id;
	private String name;
	@OneToMany(mappedBy="customer", fetch=FetchType.EAGER)
	private Collection<BrandEntity> brand=new ArrayList<BrandEntity>();
	
	public Collection<BrandEntity> getBrand() {
		return brand;
	}
	public void setBrand(Collection<BrandEntity> brand) {
		this.brand = brand;
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
	
	@Override
	public String toString() {
	    return "CustomerEntity [id=" + id + ", name=" + name + ", numberOfBrands=" + (brand != null ? brand.size() : 0) + "]";
	}

}
