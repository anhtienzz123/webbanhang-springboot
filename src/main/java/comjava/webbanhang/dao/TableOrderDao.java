package comjava.webbanhang.dao;

import java.util.List;

import comjava.webbanhang.entity.TableOrder;

public interface TableOrderDao {
	
	public TableOrder getById(int id);
	
	public List<TableOrder> getListByActive(boolean active);
	
	public void addOrUpdate(TableOrder tableOrder);
	
	public TableOrder getByAccountUsernameAndActive(String username, boolean active);

	public void delete(int id);
}
