package comjava.webbanhang.service;

import java.util.List;

import comjava.webbanhang.model.ProductDTO;

public interface ProductService {

	public List<ProductDTO> getList();
	
	public List<ProductDTO> getList(int page, int size);
	
	 
    public List<ProductDTO> getListByNameLike(String name);
    
    public List<ProductDTO> getListOrderByPrice(int id);

	public ProductDTO getById(int id);

	public void addOrUpDate(ProductDTO productDTO);

	public void delete(int id);
}
