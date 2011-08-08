package com.onb.yasah.runner;
 
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
 
import com.onb.yasah.domain.Employee;
import com.onb.yasah.service.EmployeeService;
 
public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext appContext = 
    	  new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");
 
    	EmployeeService employeeService = (EmployeeService)appContext.getBean("employeeService");
 
    	Employee employee = new Employee();
		employee.setFirstName("Eleasah");
		employee.setMiddleName("Frialde");
		employee.setLastName("Loresco");	
		employeeService.save(employee);
		
    	System.out.println("Done");
    }
}