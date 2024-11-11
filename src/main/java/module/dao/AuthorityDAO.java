package module.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import module.entity.Account;
import module.entity.Authority;

public interface AuthorityDAO extends JpaRepository<Authority, Integer> {

	@Query("SELECT DISTINCT a FROM Authority a WHERE a.account In ?1")
	List<Authority> authoritiesOf(List<Account> accounts);

}
