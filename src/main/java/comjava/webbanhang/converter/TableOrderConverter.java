package comjava.webbanhang.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import comjava.webbanhang.dao.AccountDao;
import comjava.webbanhang.entity.Account;
import comjava.webbanhang.entity.ProductOrder;
import comjava.webbanhang.entity.TableOrder;
import comjava.webbanhang.model.AccountDTO;
import comjava.webbanhang.model.ProductOrderDTO;
import comjava.webbanhang.model.TableOrderDTO;

@Component
public class TableOrderConverter {
	private ModelMapper modelMapper;
	@Autowired
	private ProductOrderConverter productOrderConverter;

	@Autowired
	private AccountConverter accountConverter;

	@Autowired
	private AccountDao accountDao;

	public TableOrderConverter() {
		modelMapper = new ModelMapper();
	}

	public TableOrder toEntity(TableOrderDTO tableOrderDTO) {
		TableOrder tableOrder = modelMapper.map(tableOrderDTO, TableOrder.class);

		Account account = accountDao.getById(tableOrderDTO.getAccountDTO().getId());
		tableOrder.setAccount(account);

		List<ProductOrderDTO> listProductOrderDTOs = tableOrderDTO.getProductOrderDTOs();
		List<ProductOrder> listProductOrders = new ArrayList<>();
		for (ProductOrderDTO productOrderDTO : listProductOrderDTOs) {
			ProductOrder productOrder = productOrderConverter.toEntity(productOrderDTO, tableOrder);
			listProductOrders.add(productOrder);
		}

		tableOrder.setProductOrders(listProductOrders);

		return tableOrder;
	}

	public TableOrderDTO toDTO(TableOrder tableOrder) {
		TableOrderDTO tableOrderDTO = modelMapper.map(tableOrder, TableOrderDTO.class);

		Account account = tableOrder.getAccount();
		AccountDTO accountDTO = accountConverter.toDTO(account);
		tableOrderDTO.setAccountDTO(accountDTO);

		List<ProductOrderDTO> productOrderDTOs = new ArrayList<ProductOrderDTO>();
		List<ProductOrder> productOrders = tableOrder.getProductOrders();
		for (ProductOrder productOrder : productOrders) {
			ProductOrderDTO productOrderDTO = productOrderConverter.toDTO(productOrder);
			productOrderDTOs.add(productOrderDTO);
		}
		tableOrderDTO.setProductOrderDTOs(productOrderDTOs);

		return tableOrderDTO;
	}

}
