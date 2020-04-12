package comjava.webbanhang.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import comjava.webbanhang.model.ProductDTO;
import comjava.webbanhang.model.ProductOrderDTO;
import comjava.webbanhang.model.TableOrderDTO;
import comjava.webbanhang.service.AccountService;
import comjava.webbanhang.service.CartService;
import comjava.webbanhang.service.ProductService;
import comjava.webbanhang.service.TableOrderService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private ProductService productService;

	@Autowired
	private TableOrderService tableOrderService;

	@Autowired
	private AccountService accountService;

	@Override
	public void addCart(int idProduct, int number) {
		ProductDTO productDTO = productService.getById(idProduct);

		TableOrderDTO tableOrderDTO = getTableOrderDTO();

		if (tableOrderDTO != null) {
			boolean flag = false;
			for (ProductOrderDTO productOrderDTO : tableOrderDTO.getProductOrderDTOs()) {
				if (productOrderDTO.getProductDTO().getId() == productDTO.getId()) {
					productOrderDTO.setNumber(productOrderDTO.getNumber() + number);
					flag = true;
				}
			}
			if (flag == false) {
				ProductOrderDTO productOrderDTO = new ProductOrderDTO();
				productOrderDTO.setNumber(number);
				productOrderDTO.setProductDTO(productDTO);
				tableOrderDTO.getProductOrderDTOs().add(productOrderDTO);
			}

		} else {
			tableOrderDTO = new TableOrderDTO();
			tableOrderDTO.setActive(false);
			tableOrderDTO.setName("nameorder");
			tableOrderDTO.setAccountDTO(
					accountService.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
			ProductOrderDTO productOrderDTO = new ProductOrderDTO();
			productOrderDTO.setNumber(number);
			productOrderDTO.setProductDTO(productDTO);

			List<ProductOrderDTO> productOrderDTOs = new ArrayList<ProductOrderDTO>();
			productOrderDTOs.add(productOrderDTO);

			tableOrderDTO.setProductOrderDTOs(productOrderDTOs);
		}
        tableOrderService.addOrUpdate(tableOrderDTO);
	}

	@Override
	public void deletedCart(int idProductOrder) {
		TableOrderDTO tableOrderDTO = getTableOrderDTO();
		Iterator<ProductOrderDTO> iterator = tableOrderDTO.getProductOrderDTOs().iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getId() == idProductOrder)
				iterator.remove();
		}
		tableOrderService.addOrUpdate(tableOrderDTO);

	}

	@Override
	public void reduceCart(int idProductOrder) {
		TableOrderDTO tableOrderDTO = getTableOrderDTO();
		for (ProductOrderDTO productOrderDTO : tableOrderDTO.getProductOrderDTOs()) {
			if (productOrderDTO.getId() == idProductOrder) {
				if (productOrderDTO.getNumber() > 1) {
					productOrderDTO.setNumber(productOrderDTO.getNumber() - 1);
					tableOrderService.addOrUpdate(tableOrderDTO);
				} else {
					deletedCart(idProductOrder);
				}

				return;
			}

		}
	}

	public TableOrderDTO getTableOrderDTO() {
		TableOrderDTO tableOrderDTO = tableOrderService
				.getByAccountUsernameAndActive(SecurityContextHolder.getContext().getAuthentication().getName(), false);
		return tableOrderDTO;
	}

}
