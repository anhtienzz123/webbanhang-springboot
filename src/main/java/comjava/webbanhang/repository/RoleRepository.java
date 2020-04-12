package comjava.webbanhang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import comjava.webbanhang.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	Role findByName(String name);

}
