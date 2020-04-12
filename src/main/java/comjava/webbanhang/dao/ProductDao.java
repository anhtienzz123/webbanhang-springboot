package comjava.webbanhang.dao;

import java.util.List;

import comjava.webbanhang.entity.Product;

public interface ProductDao {
	
    public List<Product> getList();
    
    public List<Product> getList(int page, int size);
    
    public List<Product> getListByNameLike(String name);
    
    public List<Product> getListOrderByPrice(int id);
	
    
	public Product getById(int id);
	
	public void addOrUpDate(Product product);
	
	public void delete(int id);
}
