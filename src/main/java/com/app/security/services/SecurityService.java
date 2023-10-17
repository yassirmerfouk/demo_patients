package com.app.security.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.security.entities.AppRole;
import com.app.security.entities.AppUser;
import com.app.security.repositories.RoleRepository;
import com.app.security.repositories.UserRepository;

@Service
public class SecurityService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	public AppUser loadUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public AppUser saveUser(String username, String password, String rePassword) {
		if(!password.equals(rePassword)) throw new RuntimeException("Passwords not nutch");
		if(userRepository.findByUsername(username) != null) throw new RuntimeException("User already exists");
		AppUser user = new AppUser();
		user.setUsername(username);
		user.setPassword(passwordEncoder().encode(password));
		userRepository.save(user);
		return user;
	}
	
	public AppRole saveRole(String name, String description) {
		if(roleRepository.findByName(name) != null) throw new RuntimeException("Role already exists");
		AppRole role = new AppRole();
		role.setName(name);
		role.setDescription(description);
		roleRepository.save(role);
		return role;
	}
	
	public AppUser addRoleToUser(String username, String roleName) {
		AppUser user = userRepository.findByUsername(username);
		AppRole role = roleRepository.findByName(roleName);
		if(user == null) throw new RuntimeException("User does not exists");
		if(role == null) throw new RuntimeException("Role does not exists");
		if(user.getRoles() == null) user.setRoles(new ArrayList<>());
		user.getRoles().add(role);
		userRepository.save(user);
		return user;
	}
	
	public AppUser deleteRoleFromUser(String username, String roleName) {
		AppUser user = userRepository.findByUsername(username);
		AppRole role = roleRepository.findByName(roleName);
		if(user == null) throw new RuntimeException("User does not exists");
		if(role == null) throw new RuntimeException("Role does not exists");
		user.getRoles().remove(role);
		userRepository.save(user);
		return user;
	}
	
	
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
