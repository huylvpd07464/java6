package module.service;

import java.util.List;

import module.entity.Account;
import module.entity.Authority;

public interface AuthorityService {

	List<Authority> findAuthoritiesOfAdministrators();

	List<Authority> findAll();

	Authority create(Authority auth);

	void delete(Integer id);

}
