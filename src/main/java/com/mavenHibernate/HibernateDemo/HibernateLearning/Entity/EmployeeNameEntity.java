package com.mavenHibernate.HibernateDemo.HibernateLearning.Entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class EmployeeNameEntity {
private String firstName;
private String lastName;
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
@Override
public String toString() {
	return "EmployeeNameEntity [firstName=" + firstName + ", lastName=" + lastName + "]";
}

}
