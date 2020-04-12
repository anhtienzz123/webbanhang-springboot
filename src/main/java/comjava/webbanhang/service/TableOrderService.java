package comjava.webbanhang.service;

import java.util.List;

import comjava.webbanhang.model.TableOrderDTO;

public interface TableOrderService {

	
	
	public List<TableOrderDTO> getListByActive(boolean active);
	
	public TableOrderDTO getById(int id);
	
	public void addOrUpdate(TableOrderDTO tableOrderDTO);
	
	public TableOrderDTO getByAccountUsernameAndActive(String username, boolean active);
	
	public void delete(int id);
}
