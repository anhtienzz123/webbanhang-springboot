package comjava.webbanhang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import comjava.webbanhang.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	Category findByName(String name);
}
