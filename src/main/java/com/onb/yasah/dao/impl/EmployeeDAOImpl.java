package com.onb.yasah.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.onb.yasah.util.CustomHibernateDaoSupport;
import com.onb.yasah.dao.EmployeeDAO;
import com.onb.yasah.domain.Employee;

@Repository("employeeDAO")
public class EmployeeDAOImpl extends CustomHibernateDaoSupport implements EmployeeDAO {

	public void save(Employee employee) {
		getHibernateTemplate().save(employee);
	}

	public void update(Employee employee) {
		getHibernateTemplate().update(employee);		
	}

	public void delete(Employee employee) {
		getHibernateTemplate().delete(employee);		
	}

	@SuppressWarnings("unchecked")
	public List<Employee> listOfEmployees() {
		String SQL_QUERY =" from Employee ";
		return getHibernateTemplate().find(SQL_QUERY);
	}
	
	public Employee find(Long id) {
		return (Employee) getHibernateTemplate().get(Employee.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> findExactMatches(Employee employee){
		DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
		addRestrictions(employee, criteria, Restrictions.conjunction());
		return getHibernateTemplate().findByCriteria(criteria);
	}

	private void addRestrictions(Employee employee, DetachedCriteria criteria, Junction junction) {
		criteria.add(junction
			.add(Restrictions.eq("firstName", employee.getFirstName()))
			.add(Restrictions.eq("middleName", employee.getMiddleName()))
			.add(Restrictions.eq("lastName", employee.getLastName())));
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> findAnyMatch(Employee employee){
		DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
		addRestrictions(employee, criteria, Restrictions.disjunction());
		return getHibernateTemplate().findByCriteria(criteria);
	}

	
	@SuppressWarnings("unchecked")
	public List<Employee> findEmployeeByType(String type){
		String SQL_QUERY =" select employee from Employee employee, " +
			type + " typeName " +
			"where employee.id = typeName.id";
		
		return getHibernateTemplate().find(SQL_QUERY);			
	}
}
