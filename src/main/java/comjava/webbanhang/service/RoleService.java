package comjava.webbanhang.service;

import java.util.List;

import comjava.webbanhang.model.RoleDTO;

public interface RoleService {
	public List<RoleDTO> getList();

	public RoleDTO getById(int id);

	public RoleDTO getByName(String name);

	public void addOrUpdate(RoleDTO roleDTO);

	public void delete(int id);
}
