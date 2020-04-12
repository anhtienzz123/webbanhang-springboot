package comjava.webbanhang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import comjava.webbanhang.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
	
	Account findByUsername(String username);
	
	Account findByEmail(String email);
	
	Account findByUsernameOrEmail(String username, String email);
	
	Account findByUsernameAndActive(String username, boolean active);
}
