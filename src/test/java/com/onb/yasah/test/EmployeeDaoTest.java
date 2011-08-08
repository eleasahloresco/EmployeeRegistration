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
		employee.setId(Long.parseLong("32768"));
		employee.setFirstName("elyasah");
		employee.setMiddleName("frialde");
		employee.setLastName("loresco");
		employeeDAO.update(employee);
		
		assertEquals("elyasah", employee.getFirstName());
	}
	
	@Test
	public void listOfEmployees(){
		Employee employee = new Employee();
		employee.setFirstName("elyasah");
		employee.setMiddleName("frialde");
		employee.setLastName("loresco");
		List<Employee> employees = employeeDAO.listOfEmployees();
		
		assertTrue(employees.contains(employee));
	}
	
	@Test
	public void delete(){
		List<Employee> employees = employeeDAO.listOfEmployees();
		Employee employee = employees.get(0);
		employeeDAO.delete(employee);
		
		assertNull(employeeDAO.find(employee.getId()));
	}
	
	
	@Test
	public void findMatches(){
		Employee employee = new Employee();
		employee.setFirstName("elyasah");
		employee.setMiddleName("frialde");
		employee.setLastName("loresco");
		
		List<Employee> employees = employeeDAO.findExactMatches(employee);
		
		assertTrue(employees.contains(employee));
	}
	
	@Test
	public void findAnyMatches(){
		Employee employee = new Employee();
		employee.setFirstName("elyasah");
		employee.setMiddleName("Frialde");
		employee.setLastName("loresco");
		
		List<Employee> employees = employeeDAO.findAnyMatch(employee); 
		
		Employee employee2 = new Employee();
		employee2.setFirstName("elyasah");
		employee2.setMiddleName("frialde");
		employee2.setLastName("loresco");
		
		assertTrue(employees.contains(employee2));
	}
	
	@Test
	public void findEmployeeWithSoftwareDeveloperType() throws Exception {
		List<Employee> employees =  employeeDAO.findEmployeeByType("SoftwareDeveloper");
		
		for (Employee employee : employees) {
			System.out.println(employee);
		}
	}
}
