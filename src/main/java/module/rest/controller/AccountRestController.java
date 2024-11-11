package module.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import module.entity.Account;
import module.entity.Product;
import module.service.AccountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest")
public class AccountRestController {
	@Autowired
	AccountService accountService;

	@GetMapping("/accounts")
	public List<Account> getAccounts(@RequestParam("admin") Optional<Boolean> admin) {
		if (admin.orElse(false)) {
			return accountService.getAdministrators();
		}
		return accountService.findAll();
	}
	
	@PostMapping("/accounts")
	public Account create(@RequestBody Account account) {
		return accountService.save(account);
	}
	
	@PutMapping("/accounts/{username}")
	public Account update(@PathVariable("username") String username,@RequestBody Account account) {
		return accountService.update(account);
	}
	
	@DeleteMapping("/accounts/{username}")
	public void delete(@PathVariable("username") String username) {
		accountService.delete(username);
	}

	
}
