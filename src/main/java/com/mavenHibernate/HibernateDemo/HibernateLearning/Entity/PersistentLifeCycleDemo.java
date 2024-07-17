package com.mavenHibernate.HibernateDemo.HibernateLearning.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity(name="persistent_entity")
public class PersistentLifeCycleDemo {
	@Id
	private int rollno;

	private String stud_name;
	private int marks;
	public int getRollno() {
		return rollno;
	}
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	public String getStud_name() {
		return stud_name;
	}
	public void setStud_name(String stud_name) {
		this.stud_name = stud_name;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	@Override
	public String toString() {
		return "Persistent [rollno=" + rollno + ", stud_name=" + stud_name + ", marks=" + marks + "]";
	}
	
}
