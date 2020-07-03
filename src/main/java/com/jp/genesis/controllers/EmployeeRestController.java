package com.jp.genesis.controllers;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jp.genesis.beans.EmployeeResponse;
import com.jp.genesis.models.Employee;
import com.jp.genesis.services.EmployeeDataServices;


@RestController
@RequestMapping(value={"/jp/genesis"})
public class EmployeeRestController {

	public static final Logger LOG = LoggerFactory.getLogger(EmployeeRestController.class);

	@Autowired
	EmployeeDataServices employeeDataServices;


	@GetMapping(value="/healthcheck" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> healthCheck(){
		return new ResponseEntity<String>("applications is up", HttpStatus.OK);			

	}


	@GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllEmployeees() {
		LOG.debug("Inside /employees " );

		//		List<Employee> employeeList = new ArrayList<Employee>();
		//		Employee e1 = new Employee(1,"jitesh1", 1, 1000);
		//		employeeList.add(e1);
		//		Employee e2 = new Employee(2,"jitesh2", 1, 2000);
		//		employeeList.add(e2);
		//		Employee e3 = new Employee(3,"jitesh3", 2, 3000);
		//		employeeList.add(e3);
		//		return new ResponseEntity<>(null ,HttpStatus.OK );

		List<Employee> employeeList =  employeeDataServices.getAllEmployees();

		return new ResponseEntity<List<Employee>>(employeeList ,HttpStatus.OK );
	}


	@GetMapping(value = "/limit/employees" , produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getEmployeeesAsList(@RequestParam int offset, @RequestParam int limit, @RequestParam String outputformat){

		LOG.debug("offset : " + offset + " limit : " + limit + " outputformat : " + outputformat);


		EmployeeResponse employeeResponse = new EmployeeResponse();

		if (null != outputformat && outputformat.equalsIgnoreCase("json")) {
			List<Employee> employeeList =  employeeDataServices.getLimitedEmployees(limit, offset);
			employeeResponse.setEmployeeList(employeeList);
//			return new ResponseEntity<EmployeeResponse>(employeeResponse ,HttpStatus.OK );
		}
		else if (null != outputformat && outputformat.equalsIgnoreCase("list")) {
//			List<List<String>> listOfEmployeeList = new ArrayList<List<String>>();
//
//			listOfEmployeeList.add(getHeaderList());
//
//			List<String> empList = new ArrayList<String>(6);			
//			empList.add("10001");
//			empList.add("1953-09-02");
//			empList.add("Georgi");
//			empList.add("Facello");
//			empList.add("M");
//			empList.add("1986-06-26");
//			listOfEmployeeList.add(empList);
//
//			empList = new ArrayList<String>(6);
//			empList.add("10002");
//			empList.add("1964-06-02");
//			empList.add("Bezalel");
//			empList.add("Simmel");
//			empList.add("F");
//			empList.add("1985-11-21");
//			listOfEmployeeList.add(empList);
//			employeeResponse.setValueList(listOfEmployeeList);
			
			List<List<String>> listOfEmployeeList = employeeDataServices.getLimitedEmployeesJdbcTemplate(limit, offset);
			employeeResponse.setValueList(listOfEmployeeList);
						
		}

		return new ResponseEntity<EmployeeResponse>(employeeResponse ,HttpStatus.OK );

	}

	private List<String> getHeaderList(){
		List<String> headerNameList=new ArrayList<String>();

		headerNameList.add("emp_no");
		headerNameList.add("birth_date");
		headerNameList.add("first_name");
		headerNameList.add("last_name");
		headerNameList.add("gender");
		headerNameList.add("hire_date");

		return headerNameList;
	}






}
