package comjava.webbanhang.converter;

import comjava.webbanhang.entity.BaseEntity;
import comjava.webbanhang.model.BaseDTO;

public class BaseConverter {
	
	public static <D extends BaseDTO, E extends BaseEntity> void toDTO(D dto, E entity) {
		dto.setId(entity.getId());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		dto.setModifiedDate(entity.getModifiedDate());
	}

}
