package com.coderscampus.tristanassignment13.web;

import java.util.Arrays;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.coderscampus.tristanassignment13.domain.Address;
import com.coderscampus.tristanassignment13.domain.User;
import com.coderscampus.tristanassignment13.service.AddressService;
import com.coderscampus.tristanassignment13.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/register")
	public String getCreateUser (ModelMap model) {
		
		model.put("user", new User());
		
		return "register";
	}
	
	@PostMapping("/register")
	public String postCreateUser (User user) {
		
		System.out.println(user);
		
		userService.saveUser(user);
		
		return "redirect:/register";
	}
	
	@GetMapping("/users")
	public String getUsers(ModelMap model) {
        
		Set<User> users = userService.findAll();
		
		model.put("users", users);
		
		return "users";
	}
	
	@GetMapping("/user")
	public String getAllUsers (ModelMap model) {
		
		Set<User> users = userService.findAll();
		
		model.put("users", users);
		if (users.size() == 1) {
			model.put("user", users.iterator().next());
		}
		
		return "user";
	}
	
	@GetMapping("/user/{userId}")
	public String getOneUser (ModelMap model, @PathVariable Long userId) {
		
		User userWithAccount = userService.findByIdWithAccounts(userId);
		
		addressService.createNewAddress(userWithAccount, userId);
		
		model.put("users", Arrays.asList(userWithAccount));
		
		model.put("user", userWithAccount);
		
		return "user";
	}
	
	
	@PostMapping("/user/{userId}")
	public String postOneUser (@PathVariable Long userId, User user) {
		
		
		Address address = addressService.save(user.getAddress());
		
		user.setAddress(address);
		
		User userWithAccount = userService.findByIdWithAccounts(userId);
		
		user.setAccounts(userWithAccount.getAccounts());
	
		userService.saveUser(user);
		
		return "redirect:/user/" + userId;
	}
	
	
	@PostMapping("/user/{userId}/delete")
	public String deleteOneUser (@PathVariable Long userId) {
		
		userService.delete(userId);
		
		return "redirect:/users";
	}
	
	
}
