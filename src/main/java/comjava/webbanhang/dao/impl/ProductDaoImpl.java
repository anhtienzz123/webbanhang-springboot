package comjava.webbanhang.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import comjava.webbanhang.dao.ProductDao;
import comjava.webbanhang.entity.Product;
import comjava.webbanhang.repository.ProductRepository;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getList() {

		return productRepository.findAll();
	}
	
	@Override
	public List<Product> getList(int page, int size) {
		Page<Product> pageProduct = productRepository.findAll(PageRequest.of(page, size));
		return pageProduct.getContent();
	}


	@Override
	public Product getById(int id) {
		
		return productRepository.findById(id);
	}

	@Override
	public void addOrUpDate(Product product) {
		productRepository.save(product);

	}


	@Override
	public void delete(int id) {
		productRepository.delete(getById(id));
		
	}

	@Override
	public List<Product> getListByNameLike(String name) {
		
		return productRepository.findAllByNameLike(name);
	}

	@Override
	public List<Product> getListOrderByPrice(int id) {
		
		if(id == 0) {
			return productRepository.findAllByOrderByPriceAsc();
		}
		
		return productRepository.findAllByOrderByPriceDesc();
	}

	
}
