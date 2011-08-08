package com.onb.yasah.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onb.yasah.dao.EmployeeDAO;
import com.onb.yasah.domain.Employee;
import com.onb.yasah.service.EmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;
	
	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	public void save(Employee employee) {
		employeeDAO.save(employee);
	}

	public void update(Employee employee) {
		employeeDAO.update(employee);		
	}

	public void delete(Employee employee) {
		employeeDAO.delete(employee);		
	}

	public List<Employee> listOfEmployees() {
		return employeeDAO.listOfEmployees();
	}

}
