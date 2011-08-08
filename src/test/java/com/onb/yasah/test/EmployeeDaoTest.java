package com.onb.yasah.test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.onb.yasah.dao.EmployeeDAO;
import com.onb.yasah.domain.Employee;
import com.onb.yasah.domain.SoftwareDeveloper;


public class EmployeeDaoTest {
	
	ApplicationContext appContext = 
	  	  new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

	EmployeeDAO employeeDAO = (EmployeeDAO)appContext.getBean("employeeDAO");

	@Test
	public void save(){
		Employee employee = new Employee();
		employee.setFirstName("Eleasah");
		employee.setMiddleName("Frialde");
		employee.setLastName("Loresco");	
		employeeDAO.save(employee);

		assertNotNull(employee.getId());
	}
	
	@Test
	public void update(){
		Employee employee = new Employee();
		employee.setFirstName("Eleasah");
		employee.setMiddleName("Frialde");
		employee.setLastName("Loresco");
		employeeDAO.save(employee);
		List<Employee> employees = employeeDAO.findExactMatches(employee);
		Employee retrieveEmployee = employees.get(0);
		retrieveEmployee.setFirstName("Test");
		employeeDAO.update(retrieveEmployee);
		
		assertTrue(employeeDAO.findExactMatches(retrieveEmployee).isEmpty() == false);
	}
	
	@Test
	public void listOfEmployees(){
		Employee employee = new Employee();
		employee.setFirstName("elyasah");
		employee.setMiddleName("frialde");
		employee.setLastName("loresco");
		employeeDAO.save(employee);
		List<Employee> employees = employeeDAO.listOfEmployees();
		
		assertTrue(employees.contains(employee));
	}
	
	@Test
	public void delete(){
		Employee employee = new Employee();
		employee.setFirstName("elyasah");
		employee.setMiddleName("frialde");
		employee.setLastName("loresco");
		employeeDAO.save(employee);
		List<Employee> employees = employeeDAO.listOfEmployees();
		assertTrue(employees.size() > 0);
		
		Employee employee1 = employees.get(0);
		employeeDAO.delete(employee1);
		
		assertNull(employeeDAO.find(employee1.getId()));
	}
	
	
	@Test
	public void findMatches(){
		Employee employee = new Employee();
		employee.setFirstName("elyasah");
		employee.setMiddleName("frialde");
		employee.setLastName("loresco");
		employeeDAO.save(employee);
		List<Employee> employees = employeeDAO.findExactMatches(employee);
		
		assertTrue(employees.contains(employee));
	}
	
	@Test
	public void findAnyMatches(){
		Employee employee = new Employee();
		employee.setFirstName("elyasah");
		employee.setMiddleName("Frialde");
		employee.setLastName("loresco");
		employeeDAO.save(employee);
		List<Employee> employees = employeeDAO.findAnyMatch(employee); 
		
		assertTrue(employees.contains(employee));
	}
	
	@Test
	public void findEmployeeByType() throws Exception {
		SoftwareDeveloper softwareDeveloper = new SoftwareDeveloper();
		softwareDeveloper.setFirstName("Eleasah");
		softwareDeveloper.setMiddleName("Frialde");
		softwareDeveloper.setLastName("Loresco");
		softwareDeveloper.getLanguages().add("Java");
		softwareDeveloper.getLanguages().add("Groovy");
		softwareDeveloper.getLanguages().add("HQL");
		employeeDAO.save(softwareDeveloper);
		
		assertNotNull(softwareDeveloper.getId());
		assertEquals(3, softwareDeveloper.getLanguages().size());		
	}
}
