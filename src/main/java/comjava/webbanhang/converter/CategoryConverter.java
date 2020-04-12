package comjava.webbanhang.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import comjava.webbanhang.entity.Category;
import comjava.webbanhang.entity.Product;
import comjava.webbanhang.model.CategoryDTO;
import comjava.webbanhang.model.ProductDTO;

@Component
public class CategoryConverter {

	private ModelMapper modelMapper;

	@Autowired
	private ProductConverter productConverter;

	public CategoryConverter() {
		modelMapper = new ModelMapper();
	}

	public CategoryDTO toDTO(Category category) {
		CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);

		List<Product> products = category.getProducts();
		List<ProductDTO> productDTOs = new ArrayList<>();

		for (Product product : products) {
			ProductDTO productDTO = productConverter.toDTO(product);
			productDTOs.add(productDTO);
		}

		categoryDTO.setProductDTOs(productDTOs);

		return categoryDTO;
	}

	public Category toEntity(CategoryDTO categoryDTO) {
		Category category = modelMapper.map(categoryDTO, Category.class);

		return category;
	}

}
