package comjava.webbanhang.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import comjava.webbanhang.dao.CategoryDao;
import comjava.webbanhang.entity.Category;
import comjava.webbanhang.repository.CategoryRepository;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getList() {

		return categoryRepository.findAll();
	}

	@Override
	public Category getById(int id) {

		return categoryRepository.findById(id).get();
	}
	
	@Override
	public Category getByName(String name) {
		
		return categoryRepository.findByName(name);
	}

	@Override
	public void addOrUpDate(Category category) {
		categoryRepository.save(category);

	}

	@Override
	public void delete(int id) {
		categoryRepository.delete(getById(id));
	}


}
