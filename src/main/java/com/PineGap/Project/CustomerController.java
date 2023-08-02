package com.PineGap.Project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

	@Autowired
	private UserService service;
	
	// HomePage, could be deleted
	@GetMapping("/customer")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		
		model.addAttribute("user", new User());	
		
		return "register";
	}
	
	@PostMapping("/processingRegistration")
	public String processRegister(User user) {
		
		service.registerDefaultUser(user);
		
		return "successfulRegistration";
	}
	
	@GetMapping("/login")
    public String viewLoginPage() {
         
        return "login";
    }
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		
		List<User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);	
		
		return "userList";
	}
	
	// Used for managing users, changing details, roles, and probably soon deleting
	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable("id") Long id, Model model) {
		
		User user = service.get(id);
		List<Role> listRoles = service.listRoles();
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		
		return "editUser";
	}
	
	@PostMapping("/users/save")
	public String saveUser(User user) {
		
		service.save(user);
		
		return "redirect:/users";
	}


	@GetMapping("/orderHistory")
	public String orderHistory(Model model) {
	
        model.addAttribute("shoppingCart", ShoppingCart.getShoppingCart());	
		model.addAttribute("orders", CheckoutController.getOrders());		
		return "/purchase_history.html";
	}
	
	

}
