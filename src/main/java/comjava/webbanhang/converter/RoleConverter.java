package comjava.webbanhang.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import comjava.webbanhang.entity.Role;
import comjava.webbanhang.model.RoleDTO;

@Component
public class RoleConverter {
	private ModelMapper modelMapper;

	public RoleConverter() {
		modelMapper = new ModelMapper();
	}

	public RoleDTO toDTO(Role role) {
		RoleDTO roleDTO = modelMapper.map(role, RoleDTO.class);

		return roleDTO;
	}

	public Role toEntity(RoleDTO roleDTO) {
		Role role = modelMapper.map(roleDTO, Role.class);

		return role;
	}

}
