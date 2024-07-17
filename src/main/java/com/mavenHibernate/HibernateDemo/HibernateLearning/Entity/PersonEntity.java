package com.mavenHibernate.HibernateDemo.HibernateLearning.Entity;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
//POJO Class
//@Entity(name="Employee")
@Entity
@Table(name="Employeecheck")
public class PersonEntity {
	@Id
	public int personID;
	@Column(name="empame")
	public EmployeeNameEntity name;
	//@Transient
	public String positions;
	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	public EmployeeNameEntity getName() {
		return name;
	}
	public void setName(EmployeeNameEntity name) {
		this.name = name;
	}
	public String getPositions() {
		return positions;
	}
	public void setPositions(String positions) {
		this.positions = positions;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
