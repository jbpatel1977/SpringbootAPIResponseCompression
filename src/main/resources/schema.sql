
-- CREATE DATABASE IF NOT EXISTS genesis1;
-- USE genesis1;


-- -- MySQL  database query
CREATE TABLE genesis1.employee (
	employee_id INT(15) NOT NULL,
	name VARCHAR(50) NOT NULL,
	department_id INT(15) NOT NULL,
	salary INT(15) NOT NULL,
	
	PRIMARY KEY (employee_id)
	) ;
 