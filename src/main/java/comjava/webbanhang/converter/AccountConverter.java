package comjava.webbanhang.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import comjava.webbanhang.dao.RoleDao;
import comjava.webbanhang.entity.Account;
import comjava.webbanhang.entity.Role;
import comjava.webbanhang.model.AccountDTO;

@Component
public class AccountConverter {

	private ModelMapper modelMapper;

	public AccountConverter() {
		modelMapper = new ModelMapper();
	}

	@Autowired
	private RoleDao roleDao;

	public AccountDTO toDTO(Account account) {
		if (account == null)
			return null;

		AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);

		List<String> roles = new ArrayList<>();

		for (Role role : account.getRoles()) {
			String roleString = role.getName();
			roles.add(roleString);
		}

		accountDTO.setRoles(roles);

		return accountDTO;
	}

	public Account toEntity(AccountDTO accountDTO) {
		if (accountDTO == null)
			return null;

		Account account = modelMapper.map(accountDTO, Account.class);

		List<Role> roles = new ArrayList<>();

		for (String roleString : accountDTO.getRoles()) {
			Role role = roleDao.getByName(roleString);
			roles.add(role);
		}

		account.setRoles(roles);

		return account;
	}

}
