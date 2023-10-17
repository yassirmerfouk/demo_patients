package com.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.dao.PatientRepository;
import com.app.entities.Patient;

@Controller
public class PatientController {

	@Autowired
	PatientRepository patientRepository;

	@GetMapping(path = { "/", "/patients" })
	public String getPatientsList(Model model, @RequestParam(name="name", defaultValue = "") String name,@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size) {
		Page<Patient> pagePatients = patientRepository.findByFirstName("%"+name+"%", PageRequest.of(page, size));
		model.addAttribute("patients", pagePatients.getContent());
		model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("currentSize", size);
		model.addAttribute("searchName", name);
		return "patients";
	}
	
	@PostMapping("/delete")
	public String deletePatient(@ModelAttribute Patient patient, @RequestParam(name="name", defaultValue = "") String name,@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size) {
		patientRepository.delete(patient);
		return "redirect:/patients?name="+name+"&page="+page+"&size="+size;
	}
	
	@GetMapping("/add")
	public String getPatientAddForm(@ModelAttribute Patient patient) {
		return "addpatient";
	}
	
	@PostMapping("/add")
	public String addPatient(@Valid @ModelAttribute Patient patient, BindingResult result, RedirectAttributes redirectAttributes) {
		if(result.hasErrors())
		return "addPatient";
		else {
			patientRepository.save(patient);
			redirectAttributes.addFlashAttribute("alert", "patient : "+patient.getFirstName() + " " + patient.getLastName()+ " has been added");
			return "redirect:/patients";
		}
	}
	
	@GetMapping("/update/{id}")
	public String getPatientUpdateForm(@PathVariable("id") Long id, Model model) {
		Patient patient = patientRepository.findById(id).get();
		model.addAttribute(patient);
		return "updatepatient";
	}
	
	@PostMapping("/update")
	public String updatePatient(@Valid @ModelAttribute Patient patient, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors())
			return "updatepatient";
		patientRepository.save(patient);
		redirectAttributes.addFlashAttribute("alert", "patient : "+patient.getId()+ " has been updated");
		return "redirect:/patients";
	}
}
