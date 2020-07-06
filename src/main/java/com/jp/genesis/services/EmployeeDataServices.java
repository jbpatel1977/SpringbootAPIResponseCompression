package com.jp.genesis.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jp.genesis.controllers.EmployeeRestController;
import com.jp.genesis.models.Employee;
import com.jp.genesis.repository.EmployeeRepository;

@Service
//@Transactional
public class EmployeeDataServices {
	public static final Logger LOG = LoggerFactory.getLogger(EmployeeRestController.class);


	@Autowired	
	EmployeeRepository employeeRepository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Employee> getAllEmployees(){

		List<Employee> employeeList = employeeRepository.findAll();
		return employeeList;
	}

	//	public List<Employee> getLimitedEmployees(){
	//		List<Employee> employeeList = employeeRepository.findLimitedEmployees();
	//		return employeeList;
	//		
	//	}

	public List<Employee> getLimitedEmployees(int limit, int offset){

		LOG.debug("*** getLimitedEmployees: START : " );
		long startTime = System.nanoTime();
		List<Employee> employeeList = employeeRepository.findLimitedEmployees(limit, offset);

		long endTime = System.nanoTime();
		LOG.debug("*** getLimitedEmployees: End : " + ( (endTime - startTime) / 1000000)  + " ms");
		return employeeList;

		/* validate Java stream and lambda operation timing - Filter data		
		LOG.debug("*** Java Filter: START : " );
		startTime = System.nanoTime();
		// filter result by gender = female and name start with
		List<Employee> femaleEmployeeList = employeeList
				.stream()
				.filter(emp -> emp.getGender() == 'F'&& emp.getFirst_name().startsWith("S"))
				.collect(Collectors.toList());
		endTime = System.nanoTime();
		LOG.debug("*** Java Filter: START : " + + ((endTime - startTime) / 1000000) + " ms");
		*/

		/* validate Java stream and lambda operation timing - filter data and count
		LOG.debug("*** Java Filter 2: START : " );
		startTime = System.nanoTime();
		long resultCount = employeeList
				.stream()
				.filter(emp -> emp.getGender() == 'F'&& emp.getFirst_name().startsWith("S"))
				.count();
		endTime = System.nanoTime();
		LOG.debug("*** Java Filter 2: START : " + + ((endTime - startTime) / 1000000) + " ms");
		LOG.debug("*** Result Count :  " + resultCount);
      */

//		return femaleEmployeeList;

		 
	}

	public List<List<String>>  getLimitedEmployeesJdbcTemplate(int limit, int offset){
		List<List<String>> listOfEmployeeList = new ArrayList<List<String>>();
		String query = "SELECT *  FROM employees LIMIT " + limit + " OFFSET " + offset ;

		List<Map<String, Object>> resultList =  jdbcTemplate.queryForList(query);

		List<String> rowList= null; 	

		for(Map<String,Object> rowMap : resultList){
			rowList=new ArrayList<String>();	
			rowList.add( rowMap.get("emp_no").toString());
			rowList.add( rowMap.get("birth_date").toString());
			rowList.add( rowMap.get("first_name").toString());
			rowList.add( rowMap.get("last_name").toString());
			rowList.add( rowMap.get("gender").toString());
			rowList.add( rowMap.get("hire_date").toString());
			listOfEmployeeList.add(rowList);

		}		

		return listOfEmployeeList;
	}


}
