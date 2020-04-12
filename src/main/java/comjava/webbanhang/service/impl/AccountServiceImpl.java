package comjava.webbanhang.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comjava.webbanhang.converter.AccountConverter;
import comjava.webbanhang.dao.AccountDao;
import comjava.webbanhang.entity.Account;
import comjava.webbanhang.model.AccountDTO;
import comjava.webbanhang.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private AccountConverter accountConverter;

	@Override
	public List<AccountDTO> getList() {
		List<AccountDTO> accountDTOs = new ArrayList<>();

		for (Account account : accountDao.getList()) {
			AccountDTO accountDTO = accountConverter.toDTO(account);
			accountDTOs.add(accountDTO);
		}

		return accountDTOs;
	}

	@Override
	public AccountDTO getById(int id) {
		Account account = accountDao.getById(id);
		AccountDTO accountDTO = accountConverter.toDTO(account);
		return accountDTO;
	}

	@Override
	public AccountDTO getByUsername(String username) {
		Account account = accountDao.getByUsername(username);
		if (account != null) {
			AccountDTO accountDTO = accountConverter.toDTO(account);
			return accountDTO;
		}
		return null;
	}

	@Override
	public AccountDTO getByEmail(String email) {
		Account account = accountDao.getByEmail(email);
		AccountDTO accountDTO = accountConverter.toDTO(account);
		return accountDTO;
	}

	@Override
	public AccountDTO getByUsernameOrEmail(String username, String email) {
		Account account = accountDao.getByUsernameOrEmail(username, email);
		AccountDTO accountDTO = accountConverter.toDTO(account);
		return accountDTO;
	}

	@Override
	public AccountDTO getByUsernameAndActive(String username, boolean active) {
		Account account = accountDao.getByUsernameAndActive(username, active);
		AccountDTO accountDTO = accountConverter.toDTO(account);
		return accountDTO;
	}
	

	@Override
	public void addOrUpdate(AccountDTO accountDTO) {
		Account account = accountConverter.toEntity(accountDTO);
		accountDao.addOrUpdate(account);
	}

	@Override
	public void delete(int id) {
		Account account = accountDao.getById(id);
		account.getRoles().clear();
		accountDao.addOrUpdate(account);

		accountDao.delete(id);
	}

}
