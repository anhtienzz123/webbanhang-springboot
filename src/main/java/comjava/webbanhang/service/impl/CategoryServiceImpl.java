package comjava.webbanhang.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comjava.webbanhang.converter.CategoryConverter;
import comjava.webbanhang.dao.CategoryDao;
import comjava.webbanhang.entity.Category;
import comjava.webbanhang.model.CategoryDTO;
import comjava.webbanhang.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private CategoryConverter categoryConverter;

	@Override
	public List<CategoryDTO> getList() {
		List<CategoryDTO> listCategoryDTOs = new ArrayList<>();
		List<Category> listCategories = categoryDao.getList();

		for (Category category : listCategories) {
			CategoryDTO categoryDTO = categoryConverter.toDTO(category);
			listCategoryDTOs.add(categoryDTO);
		}

		return listCategoryDTOs;
	}

	@Override
	public CategoryDTO getById(int id) {
		Category category = categoryDao.getById(id);
		CategoryDTO categoryDTO = categoryConverter.toDTO(category);
		return categoryDTO;
	}
	
	@Override
	public CategoryDTO getByName(String name) {
		Category category = categoryDao.getByName(name);
		CategoryDTO categoryDTO = categoryConverter.toDTO(category);
		return categoryDTO;
	}


	@Override
	public void addOrUpDate(CategoryDTO categoryDTO) {
		Category category = categoryConverter.toEntity(categoryDTO);
		categoryDao.addOrUpDate(category);

	}

	@Override
	public void delete(int id) {
		categoryDao.delete(id);

	}

}
