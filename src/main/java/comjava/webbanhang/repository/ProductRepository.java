package comjava.webbanhang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import comjava.webbanhang.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	Product findById(int id);
	
	List<Product> findAllByNameLike(String name);
	
	List<Product> findAllByOrderByPriceAsc();
	
	List<Product> findAllByOrderByPriceDesc();

}
