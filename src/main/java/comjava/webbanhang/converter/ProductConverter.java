package comjava.webbanhang.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import comjava.webbanhang.entity.Category;
import comjava.webbanhang.entity.Product;
import comjava.webbanhang.model.ProductDTO;
import comjava.webbanhang.repository.CategoryRepository;

@Component
public class ProductConverter {

	private ModelMapper modelMapper;
	@Autowired
	private CategoryRepository categoryRepository;

	public ProductConverter() {
		modelMapper = new ModelMapper();
	}

	public ProductDTO toDTO(Product product) {
		ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);

		productDTO.setCategory(product.getCategory().getName());

		return productDTO;
	}

	public Product toEntity(ProductDTO productDTO) {
		Product product = modelMapper.map(productDTO, Product.class);

		Category category = categoryRepository.findByName(productDTO.getCategory());

		product.setCategory(category);

		return product;

	}
}
