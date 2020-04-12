package comjava.webbanhang.dao;

import java.util.List;

import comjava.webbanhang.entity.Category;

public interface CategoryDao {

	public List<Category> getList();
	
	public Category getById(int id);
	
	public Category getByName(String name);
	
	public void addOrUpDate(Category category);
	
	public void delete(int id);
}
