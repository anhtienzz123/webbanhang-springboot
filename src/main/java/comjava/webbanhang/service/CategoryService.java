package comjava.webbanhang.service;

import java.util.List;

import comjava.webbanhang.model.CategoryDTO;

public interface CategoryService {

	public List<CategoryDTO> getList();

	public CategoryDTO getById(int id);

	public CategoryDTO getByName(String name);

	public void addOrUpDate(CategoryDTO categoryDTO);

	public void delete(int id);
}
