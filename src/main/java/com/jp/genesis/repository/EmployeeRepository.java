package com.jp.genesis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jp.genesis.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer >{
	
	
//	@Query(value="SELECT *  FROM employees LIMIT 100  OFFSET 0",nativeQuery=true)
//	List<Employee> findLimitedEmployees();
	
	@Query(value="SELECT *  FROM employees LIMIT :limit  OFFSET :offset ",nativeQuery=true)
	List<Employee> findLimitedEmployees(int limit, int offset);

}
