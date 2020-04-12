package comjava.webbanhang.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import comjava.webbanhang.model.AccountDTO;
import comjava.webbanhang.service.AccountService;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private AccountService accountService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		AccountDTO accountDTO = accountService.getByUsernameAndActive(username, true);
		
		if(accountDTO == null)
			throw new UsernameNotFoundException("Account khong ton tai");
	
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
				
		for (String role : accountDTO.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		
		User user = new User(accountDTO.getUsername(), accountDTO.getPassword(), authorities);
		
		return user;
	}
	
}
