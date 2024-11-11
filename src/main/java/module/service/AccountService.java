package module.service;

import java.util.List;

import module.entity.Account;

public interface AccountService {
	Account findById(String name);

	List<Account> getAdministrators();

	List<Account> findAll();

	Account save(Account account);

	Account findByEmail(String email);

	Account update(Account account);

	void delete(String username);
}
