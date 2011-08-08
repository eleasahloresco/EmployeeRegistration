package com.onb.yasah.service;

import java.util.List;

import com.onb.yasah.domain.Employee;

public interface EmployeeService {
	
	public void save(Employee employee);
	public void update(Employee employee);
	public void delete(Employee employee);
	public List<Employee> listOfEmployees();
	
}
