package com.mavenHibernate.HibernateDemo.HibernateLearning.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name="Laptop")
public class LaptiopEntity {
@Id
private int id;
private String lame;
//First run without givig this that will also work. which means one student has may laptop. 
//tO DO MAY LAPTOP TO BE TAGGED ONE STUDENT WE USE MANYTOOE.without also it will work but we don't eed to create a new table with this
//Many laptop begin to oee student
//@ManyToOne
//private StudentEntity stud;
//public StudentEntity getStud() {
//return stud;
//}
//public void setStud(StudentEntity stud) {
//this.stud = stud;
//}

@OneToMany
private List<StudentEntity> student=new ArrayList<StudentEntity>();

public List<StudentEntity> getStudent() {
	return student;
}
public void setStudent(List<StudentEntity> student) {
	this.student = student;
}


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getLame() {
	return lame;
}
public void setLame(String lame) {
	this.lame = lame;
}
@Override
public String toString() {
	// TODO Auto-generated method stub
	return super.toString();
}

}
