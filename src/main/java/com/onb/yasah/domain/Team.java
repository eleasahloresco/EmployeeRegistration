package com.onb.yasah.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Team {

	@Id
	@GeneratedValue
	@Column(name = "TEAM_ID")
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private SoftwareDeveloper teamLead;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "TEAM_EMPLOYEE", 
			joinColumns = { @JoinColumn(name = "TEAM_ID") }, 
			inverseJoinColumns = { @JoinColumn(name = "EMPLOYEE_ID") })
	private Set<Employee> listOfMembers = new HashSet<Employee>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getTeamLead() {
		return teamLead;
	}

	public void setTeamLead(SoftwareDeveloper teamLead) {
		this.teamLead = teamLead;
	}

	public Set<Employee> getListOfMembers() {
		return listOfMembers;
	}

	public void setListOfMembers(Set<Employee> listOfMembers) {
		this.listOfMembers = listOfMembers;
	}
	
	
}
