package com.academysmart.repository;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import com.academysmart.exception.IncorrectEmailException;
import com.academysmart.model.Employee;

public class EmployeeRepositorySingleton {
	static EmployeeRepositorySingleton repository;
	List<Employee> employee = new ArrayList<Employee>();

	
	public static EmployeeRepositorySingleton getRepository() {
		// TODO implement method that returns repository instance
		if (repository == null) {
			repository = new EmployeeRepositorySingleton();
		}
		return repository;
	}

	int id = 1;

	public void addEmployee(String fname, String lname, String email)
			throws IncorrectEmailException, ServletException {
		// TODO implement method that adds an employee to repository
		// This method should check that email is not used by other employees
		Employee newEmployee = new Employee();
		if (fname.equals("") || lname.equals("") || email.equals("")) {
			throw new ServletException("Enter all field!");
		}
		newEmployee.setId(String.valueOf(id));
		newEmployee.setName(fname);
		newEmployee.setSurName(lname);
		newEmployee.setEmail(email);
		for (Employee i : employee) {
			if (i.getEmail().equals(newEmployee.getEmail())) {
				throw new IncorrectEmailException(
						"This email is not available!");
			}
		}
		employee.add(newEmployee);
		id++;
	}

	public List<Employee> getAllEmployees() {
		// TODO implement method that returns list of all employees
		return employee;
	}
}