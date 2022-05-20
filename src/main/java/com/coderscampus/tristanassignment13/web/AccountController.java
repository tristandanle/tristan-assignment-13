package com.coderscampus.tristanassignment13.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.coderscampus.tristanassignment13.domain.Account;
import com.coderscampus.tristanassignment13.domain.User;
import com.coderscampus.tristanassignment13.service.AccountService;
import com.coderscampus.tristanassignment13.service.UserService;



@Controller
public class AccountController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/user/{userId}/accounts")
	public String createAccount (@PathVariable Long userId) {
		
		Account account = new Account();
		
		User user = userService.findById(userId);
		
		account.getUsers().add(user);
		
		user.getAccounts().add(account);
		
		String accountName = "Account #" + user.getAccounts().size();
			
		account.setAccountName(accountName);
		
		account = accountService.save(account);
		
		return "redirect:/user/"+userId+"/accounts/"+account.getAccountId();
	}
	
	
	
	@PostMapping("/user/{userId}/accounts/{accountId}")
	public String saveAccount(Account account, @PathVariable Long userId) {
		
		account = accountService.save(account);
		
		return "redirect:/user/"+userId+"/accounts/"+account.getAccountId();
	}
	
	
	
	@GetMapping("/user/{userId}/accounts/{accountId}")
	public String getAccount(ModelMap model, @PathVariable Long userId, @PathVariable Long accountId) {
		
		Account account = accountService.findById(accountId);
		
		User user = userService.findById(userId);
		
		model.put("account", account);
		
		model.put("user", user);
		
		return "account";
		
	}
	
}
