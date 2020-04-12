package comjava.webbanhang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import comjava.webbanhang.entity.TableOrder;

public interface TableOrderRepository extends JpaRepository<TableOrder, Integer>{
	
	public TableOrder findByAccountUsernameAndActive(String username, boolean active);
	
	public List<TableOrder> findAllByActive(boolean active);

}
