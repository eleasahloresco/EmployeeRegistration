package com.onb.yasah.test;

import static org.junit.Assert.*;

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
		
		assertTrue(employees.size() == 1);
	}
	
	@Test
	public void delete(){
		Employee employee = new Employee();
		employee.setFirstName("elyasah");
		employee.setMiddleName("frialde");
		employee.setLastName("loresco");
		employeeDAO.save(employee);
		List<Employee> employees = employeeDAO.listOfEmployees();
		assertTrue(employees.size() == 1);
		
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
		
		assertTrue(employees.size() == 1);
	}
	
	@Test
	public void findAnyMatches(){
		Employee employee = new Employee();
		employee.setFirstName("elyasah");
		employee.setMiddleName("Frialde");
		employee.setLastName("loresco");
		employeeDAO.save(employee);
		List<Employee> employees = employeeDAO.findAnyMatch(employee); 
		
		assertTrue(employees.size() == 1);
	}
	
	@Test
	public void findEmployeeWithSoftwareDeveloperType() throws Exception {
		List<Employee> employees =  employeeDAO.findEmployeeByType("SoftwareDeveloper");
		
		for (Employee employee : employees) {
			System.out.println(employee);
		}
	}
}
