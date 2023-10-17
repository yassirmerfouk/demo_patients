package com.app.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.security.entities.AppRole;

public interface RoleRepository extends JpaRepository<AppRole, Long>{

	public AppRole findByName(String name);
}
