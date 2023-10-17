package com.app.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) throws Exception{
		request.logout();
		return "redirect:/login";
	}
	
	@GetMapping("/access-denied")
	public String accessDeniedError() {
		return "error/access-denied";
	}
}
