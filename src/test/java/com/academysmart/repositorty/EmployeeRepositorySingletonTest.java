package com.academysmart.repositorty;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import javax.servlet.ServletException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.academysmart.exception.IncorrectEmailException;
import com.academysmart.repository.EmployeeRepositorySingleton;

public class EmployeeRepositorySingletonTest {
	@BeforeClass
	public static void beforeClass() throws Exception {
		EmployeeRepositorySingleton.getRepository().addEmployee("Иван",
				"Иванов", "ivanov@mail.ru");
	}

	@Test
	public void testGetRepositoryReturnOneInstance() {
		Assert.assertSame(EmployeeRepositorySingleton.getRepository(),
				EmployeeRepositorySingleton.getRepository());
	}

	@Test(expected = IncorrectEmailException.class)
	public void testAddEmployeWithIncorrectEmail() throws ServletException,
			IncorrectEmailException, SQLException {
		EmployeeRepositorySingleton.getRepository().addEmployee("Иван",
				"Ианов", "ivanovICH@mail.ru");
		EmployeeRepositorySingleton.getRepository().addEmployee("Иван",
				"Иванов", "ivanovICH@mail.ru");
	}
}