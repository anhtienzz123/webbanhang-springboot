package comjava.webbanhang.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import comjava.webbanhang.dao.TableOrderDao;
import comjava.webbanhang.entity.TableOrder;
import comjava.webbanhang.repository.TableOrderRepository;

@Repository
public class TableOrderDaoImpl implements TableOrderDao{

	@Autowired
	private TableOrderRepository tableOrderRepository;
	
	@Override
	public void addOrUpdate(TableOrder tableOrder) {
		tableOrderRepository.save(tableOrder);
	}

	@Override
	public TableOrder getByAccountUsernameAndActive(String username, boolean active) {
		
		return tableOrderRepository.findByAccountUsernameAndActive(username, active);
	}

	@Override
	public List<TableOrder> getListByActive(boolean active) {
		
		return tableOrderRepository.findAllByActive(active);
	}

	@Override
	public void delete(int id) {
		tableOrderRepository.delete(getById(id));
	}

	@Override
	public TableOrder getById(int id) {
		
		return tableOrderRepository.getOne(id);
	}
	
	
	
}
