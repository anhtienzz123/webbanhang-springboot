package comjava.webbanhang.dao;

import java.util.List;

import comjava.webbanhang.entity.Account;

public interface AccountDao {
	
    public List<Account> getList();
	
	public Account getById(int id);
	
	public Account getByUsername(String username);
	
	public Account getByEmail(String email);
	
	public Account getByUsernameOrEmail(String username, String email);
	
	public Account getByUsernameAndActive(String username, boolean active);
	
	public void addOrUpdate(Account account);
	
	public void delete(int id);
	
}
