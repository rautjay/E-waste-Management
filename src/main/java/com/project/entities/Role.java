package com.project.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
@Id
private Long roleId;

private String name;
 
@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "role")
 private Set<User_Role> userroles = new HashSet<>();

public Long getRoleId() {
	return roleId;
}

public void setRoleId(Long roleId) {
	this.roleId = roleId;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Set<User_Role> getUserroles() {
	return userroles;
}

public void setUserroles(Set<User_Role> userroles) {
	this.userroles = userroles;
}

public Role(Long roleId, String name, Set<User_Role> userroles) {
	super();
	this.roleId = roleId;
	this.name = name;
	this.userroles = userroles;
}

public Role() {
	super();
}
 
 




}
