package comjava.webbanhang.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import comjava.webbanhang.dao.RoleDao;
import comjava.webbanhang.entity.Role;
import comjava.webbanhang.repository.RoleRepository;

@Repository
public class RoleDaoImpl implements RoleDao{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public List<Role> getList() {
		List<Role> roles = roleRepository.findAll();
		return roles;
	}

	@Override
	public Role getById(int id) {
		Role role = roleRepository.findById(id).get();
		return role;
	}
	
	@Override
	public Role getByName(String name) {
		Role role = roleRepository.findByName(name);
		return role;
	}
	

	@Override
	public void addOrUpDate(Role role) {
		roleRepository.save(role);
		
	}

	@Override
	public void delete(int id) {
		roleRepository.delete(getById(id));
	}


	

}
