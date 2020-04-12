package comjava.webbanhang.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comjava.webbanhang.converter.TableOrderConverter;
import comjava.webbanhang.dao.TableOrderDao;
import comjava.webbanhang.entity.TableOrder;
import comjava.webbanhang.model.TableOrderDTO;
import comjava.webbanhang.service.TableOrderService;

@Service
public class TableOrderServiceImpl implements TableOrderService {

	@Autowired
	private TableOrderDao tableOrderDao;

	@Autowired
	private TableOrderConverter tableOrderConverter;

	@Override
	public void addOrUpdate(TableOrderDTO tableOrderDTO) {

		TableOrder tableOrder = tableOrderConverter.toEntity(tableOrderDTO);

		tableOrderDao.addOrUpdate(tableOrder);
	}

	@Override
	public TableOrderDTO getByAccountUsernameAndActive(String username, boolean active) {
		TableOrder tableOrder = tableOrderDao.getByAccountUsernameAndActive(username, active);

		if (tableOrder != null) {
			TableOrderDTO tableOrderDTO = tableOrderConverter.toDTO(tableOrder);
			return tableOrderDTO;
		}

		return null;
	}

	@Override
	public List<TableOrderDTO> getListByActive(boolean active) {
		List<TableOrderDTO> tableOrderDTOs = new ArrayList<TableOrderDTO>();
		
		List<TableOrder> tableOrders = tableOrderDao.getListByActive(active);		
		for (TableOrder tableOrder : tableOrders) {
			TableOrderDTO tableOrderDTO = tableOrderConverter.toDTO(tableOrder);
			tableOrderDTOs.add(tableOrderDTO);
		}
		
		return tableOrderDTOs;
	}

	@Override
	public TableOrderDTO getById(int id) {
		TableOrder tableOrder = tableOrderDao.getById(id);
		
		TableOrderDTO tableOrderDTO = tableOrderConverter.toDTO(tableOrder);
		
		return tableOrderDTO;
	}

	@Override
	public void delete(int id) {
		tableOrderDao.delete(id);
		
	}

}
