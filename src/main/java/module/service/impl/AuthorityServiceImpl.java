package module.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import module.dao.AccountDAO;
import module.dao.AuthorityDAO;
import module.entity.Account;
import module.entity.Authority;
import module.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {
	@Autowired
	AuthorityDAO authorityDAO;

	@Autowired
	AccountDAO accountDAO;

	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		List<Account> accounts = accountDAO.getAdministrators();
		return authorityDAO.authoritiesOf(accounts);
	}

	@Override
	public List<Authority> findAll() {
		// TODO Auto-generated method stub
		return authorityDAO.findAll();
	}

	@Override
	public Authority create(Authority auth) {
		// TODO Auto-generated method stub
		return authorityDAO.save(auth);
	}

	@Override
	public void delete(Integer id) {
		authorityDAO.deleteById(id);
	}

}
