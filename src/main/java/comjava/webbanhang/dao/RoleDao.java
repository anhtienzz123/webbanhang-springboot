package comjava.webbanhang.dao;

import java.util.List;

import comjava.webbanhang.entity.Role;

public interface RoleDao {

	public List<Role> getList();

	public Role getById(int id);
	
	public Role getByName(String name);

	public void addOrUpDate(Role role);

	public void delete(int id);

}
