package com.mavenHibernate.HibernateDemo.HibernateLearning.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name="brand")
public class BrandEntity {
	@Id
private int bid;
private String price;
private String baname;
@ManyToOne
private CustomerEntity customer;

public CustomerEntity getCustomer() {
	return customer;
}
public void setCustomer(CustomerEntity customer) {
	this.customer = customer;
}
public int getBid() {
	return bid;
}
public void setBid(int bid) {
	this.bid = bid;
}
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}
public String getBaname() {
	return baname;
}
public void setBaname(String baname) {
	this.baname = baname;
}
@Override
public String toString() {
    return "BrandEntity [bid=" + bid + ", baname=" + baname + ", customer=" + customer + "]";
}


}
