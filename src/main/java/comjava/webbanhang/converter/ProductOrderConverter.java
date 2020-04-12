package comjava.webbanhang.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import comjava.webbanhang.dao.ProductDao;
import comjava.webbanhang.entity.Product;
import comjava.webbanhang.entity.ProductOrder;
import comjava.webbanhang.entity.TableOrder;
import comjava.webbanhang.model.ProductDTO;
import comjava.webbanhang.model.ProductOrderDTO;

@Component
public class ProductOrderConverter {
	private ModelMapper modelMapper;
	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductConverter productConverter;

	public ProductOrderConverter() {
		modelMapper = new ModelMapper();
	}

	public ProductOrder toEntity(ProductOrderDTO productOrderDTO, TableOrder tableOrder) {
		ProductOrder productOrder = modelMapper.map(productOrderDTO, ProductOrder.class);

		productOrder.setProduct(productDao.getById(productOrderDTO.getProductDTO().getId()));
		productOrder.setTableOrder(tableOrder);
		return productOrder;
	}

	public ProductOrderDTO toDTO(ProductOrder productOrder) {
		ProductOrderDTO productOrderDTO = modelMapper.map(productOrder, ProductOrderDTO.class);

		Product product = productOrder.getProduct();
		ProductDTO productDTO = productConverter.toDTO(product);
		productOrderDTO.setProductDTO(productDTO);

		return productOrderDTO;
	}

}
