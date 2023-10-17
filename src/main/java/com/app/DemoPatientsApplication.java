package com.app;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.app.dao.PatientRepository;
import com.app.entities.Patient;
import com.app.security.entities.AppRole;
import com.app.security.entities.AppUser;
import com.app.security.services.SecurityService;

@SpringBootApplication
public class DemoPatientsApplication implements CommandLineRunner{

	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	SecurityService securityService;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoPatientsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		patientRepository.save(new Patient(null, "patient1", "patient1", LocalDate.now(), false));
//		patientRepository.save(new Patient(null, "patient2", "patient2", LocalDate.now(), true));
//		patientRepository.save(new Patient(null, "patient3", "patient3", LocalDate.now(), false));
//		patientRepository.save(new Patient(null, "patient4", "patient4", LocalDate.now(), false));
//		patientRepository.save(new Patient(null, "patient5", "patient5", LocalDate.now(), false));
//		patientRepository.save(new Patient(null, "patient6", "patient6", LocalDate.now(), true));
		
//		AppUser user1 = new AppUser(null, "admin", "123456" , null);
//		AppUser user2 = new AppUser(null, "user1", "123456", null);
//		
//		AppRole role1 = new AppRole(null, "ADMIN", "");
//		AppRole role2 = new AppRole(null, "USER", "");
		
//		securityService.saveUser(user1.getUsername(), user1.getPassword(), user1.getPassword());
//		securityService.saveUser(user2.getUsername(), user2.getPassword(), user2.getPassword());
//		securityService.saveRole(role1.getName(), role1.getDescription());
//		securityService.saveRole(role2.getName(), role2.getDescription());
		
//		securityService.addRoleToUser(user1.getUsername(), role1.getName());
//		securityService.addRoleToUser(user1.getUsername(), role2.getName());
//		securityService.addRoleToUser(user2.getUsername(), role2.getName());
	}

}
