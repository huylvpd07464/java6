package module.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import module.dao.AccountDAO;
import module.entity.Account;
import module.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountDAO accountDAO;

	@Override
	public Account findById(String name) {
		// TODO Auto-generated method stub
		return accountDAO.findById(name).get();
	}

	@Override
	public List<Account> getAdministrators() {
		// TODO Auto-generated method stub
		return accountDAO.getAdministrators();
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return accountDAO.findAll();
	}

	@Override
	public Account save(Account account) {
		return accountDAO.save(account);
	}

	@Override
	public Account findByEmail(String email) {
		// TODO Auto-generated method stub
		return accountDAO.findByEmail(email);
	}

	@Override
	public Account update(Account account) {
		// TODO Auto-generated method stub
		return accountDAO.save(account);
	}

	@Override
	public void delete(String username) {
		accountDAO.deleteById(username);
	}
}
