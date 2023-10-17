package com.app.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.security.entities.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {

	public AppUser findByUsername(String username);
}
