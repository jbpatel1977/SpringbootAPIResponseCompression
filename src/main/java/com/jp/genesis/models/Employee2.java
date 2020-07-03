package com.jp.genesis.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="employee")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee2 {
	
	private static final long serialVersionUID = 1L;
	
	
	
//	public Employee(int employee_id, String name, int department_id, int salary) {
//		super();
//		this.employee_id = employee_id;
//		this.name = name;
//		this.department_id = department_id;
//		this.salary = salary;
//	}
	
	@Id
	@Column(name="employee_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int employee_id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="department_id")
	private int department_id;
	
	@Column(name="salary")
	private int salary;
	
	
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	
	

}
