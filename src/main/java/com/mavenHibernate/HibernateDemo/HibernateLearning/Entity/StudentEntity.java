package com.mavenHibernate.HibernateDemo.HibernateLearning.Entity;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name="Student")
public class StudentEntity {
@Id
private int rollno;
private String sname;
private int marks;
//@OneToOne
//private LaptiopEntity lapto;
//
//public LaptiopEntity getLapto() {
//	return lapto;
//}
//public void setLapto(LaptiopEntity lapto) {
//	this.lapto = lapto;}

//This means one student ropa ca have many table
//@OneToMany(mappedBy="stud")
//private List<LaptiopEntity> laptop=new ArrayList<LaptiopEntity>();
//
//
//public List<LaptiopEntity> getLaptop() {
//	return laptop;
//}
//public void setLaptop(List<LaptiopEntity> laptop) {
//	this.laptop = laptop;
//}


//Many Studet can have one lapto or one laptop can have many students
@ManyToMany(mappedBy="student")
private List<LaptiopEntity> lap=new ArrayList<LaptiopEntity>();

public List<LaptiopEntity> getLap() {
	return lap;
}
public void setLap(List<LaptiopEntity> lap) {
	this.lap = lap;
}
public int getRollno() {
	return rollno;
}
public void setRollno(int rollno) {
	this.rollno = rollno;
}
public String getSname() {
	return sname;
}
public void setSname(String sname) {
	this.sname = sname;
}
public int getMarks() {
	return marks;
}
public void setMarks(int marks) {
	this.marks = marks;
}



}
