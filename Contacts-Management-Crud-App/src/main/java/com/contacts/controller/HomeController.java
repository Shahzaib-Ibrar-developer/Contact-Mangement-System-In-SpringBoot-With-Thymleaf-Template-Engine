package com.contacts.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.contacts.entity.Contacts;
import com.contacts.repository.ContactsRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	private ContactsRepository contactRepo;
	@GetMapping("/")
	public String home(Model m) {
		
		
		List<Contacts> list = contactRepo.findAll();
			
		m.addAttribute("all_contacts",list);
		return "index";
	}

	@GetMapping("/load_form")
	public String loadForm() {
		return "add";
	}

	@GetMapping("/edit_form/{id}")
	public String editForm(@PathVariable(value = "id") long id,Model m) {
		Optional<Contacts> contact =  contactRepo.findById(id);
		Contacts pro = contact.get();
		m.addAttribute("contact",pro);
		return "edit";
	}
	@PostMapping("/save_contacts")
	public String saveContacts(@ModelAttribute Contacts contacts,HttpSession session) {
		contactRepo.save(contacts);
		session.setAttribute("msg", "Contact Added Successfully...");
		return "redirect:/load_form";
	}
	
	@PostMapping("/update_contacts")
	public String updateProducts(@ModelAttribute Contacts contacts,HttpSession session) {
		contactRepo.save(contacts);
		session.setAttribute("msg", "Contact update Successfully...");
		return "redirect:/";
	}
	@GetMapping("/delete/{id}")
	public String deleteProducts(@PathVariable(value = "id") long id, HttpSession session){
		contactRepo.deleteById(id);
		
		session.setAttribute("msg", "Contact delete Successfully...");
		
		return "redirect:/";
	}
}
