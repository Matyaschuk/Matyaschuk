package com.academysmart.repository;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import com.academysmart.exception.IncorrectEmailException;
import com.academysmart.model.Employee;
import java.sql.*;

public class EmployeeRepositorySingleton {
	static EmployeeRepositorySingleton repository;
	static List<Employee> employees = new ArrayList<Employee>();
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:@localhost:1521/xe";
	static final String USER = "Matyaschuk";
	static final String PASS = "26022008";

	public static EmployeeRepositorySingleton getRepository() {
		// TODO implement method that returns repository instance
		if (repository == null) {
			repository = new EmployeeRepositorySingleton();
		}
		return repository;
	}

	public static void getDataBase() {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql = "SELECT * FROM Employees";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
		
				Employee newEmployee = new Employee();
				newEmployee.setId(rs.getString("id"));
				newEmployee.setName(rs.getString("name"));
				newEmployee.setSurName(rs.getString("surname"));
				newEmployee.setEmail(rs.getString("email"));
				employees.add(newEmployee);
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	//int id = 1;

	public void addEmployee(String fname, String lname, String email)
			throws IncorrectEmailException, ServletException, SQLException {
		// TODO implement method that adds an employee to repository
		// This method should check that email is not used by other employees
		int id = 1;
		if (fname.equals("") || lname.equals("") || email.equals("")) {
			throw new ServletException("Enter all field!");
		}
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement prStatement = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = (Statement) conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Employees ORDER BY \"id\"");
			while (rs.next()) {
				 id = 1 + Integer.parseInt(rs.getString("id"));
			}
//			rs = stmt.executeQuery("SELECT email FROM Employees");
			prStatement = conn
					.prepareStatement("insert into Employees "
							+ "values(?, ?, ?, ?)");
			prStatement.setInt(1, id);
			prStatement.setString(2, fname);
			prStatement.setString(3, lname);
			prStatement.setString(4, email);
			prStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Employee newEmployee = new Employee();
		newEmployee.setId(String.valueOf(id));
		newEmployee.setName(fname);
		newEmployee.setSurName(lname);
		newEmployee.setEmail(email);
		for (Employee i : employees) {
			if (i.getEmail().equals(newEmployee.getEmail())) {
				throw new IncorrectEmailException(
						"This email is not available!");
			}
			
		}
	
		employees.add(newEmployee);
		id++;
	}

	public List<Employee> getAllEmployees() {
		// TODO implement method that returns list of all employees
		return employees;
	}
}