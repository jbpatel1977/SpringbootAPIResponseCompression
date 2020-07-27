package com.jp.genesis.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="employees")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee {
	
	private static final long serialVersionUID = 1L;
	
	public Employee() {
	}
	
	public Employee( Date birth_date, String first_name, String last_name, char gender, Date hire_date) {
		super();
		this.birth_date = birth_date;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.hire_date = hire_date;
	}
	
	public Employee(int emp_no,  Date birth_date, String first_name, String last_name, char gender, Date hire_date) {
		super();
		this.emp_no = emp_no;
		this.birth_date = birth_date;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.hire_date = hire_date;
	}
	
	@Id
	@Column(name="emp_no")
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int emp_no;
	
	@Column(name="birth_date")
    private Date birth_date;
	


	@Column(name="first_name")
    private String first_name;
	
	@Column(name="last_name")
    private String last_name;
	
	@Column(name="gender")
    private char gender;
	
	@Column(name="hire_date")
    private Date hire_date;
	

	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public Date getHire_date() {
		return hire_date;
	}

	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}
	


}
