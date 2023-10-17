package com.app.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.security.entities.AppRole;
import com.app.security.entities.AppUser;
import com.app.security.repositories.RoleRepository;
import com.app.security.repositories.UserRepository;
import com.app.security.services.SecurityService;

@Controller
public class UserController {

	@Autowired
	SecurityService securityService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	
	@GetMapping("/user/add")
	public String getUserAddForm(@ModelAttribute(name = "user") AppUser user, Model model) {
		model.addAttribute("roles", roleRepository.findAll());
		return "adduser";
	}
	
	@PostMapping("/user/add")
	public String addUser(@Valid @ModelAttribute("user") AppUser user, BindingResult result, @RequestParam("rePassword") String rePassword) {
		securityService.saveUser(user.getUsername(), user.getPassword(), rePassword);
		for(AppRole role : user.getRoles()) {
			securityService.addRoleToUser(user.getUsername(), role.getName());
		}
		return "redirect:/";
	}
}
