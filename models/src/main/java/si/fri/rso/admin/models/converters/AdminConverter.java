package si.fri.rso.admin.models.converters;

import si.fri.rso.admin.lib.Admin;
import si.fri.rso.admin.models.entities.AdminEntity;

public class AdminConverter {

    public static Admin toDto(AdminEntity entity) {

        Admin dto = new Admin();
        dto.setAdminId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setRoles(entity.getRoles());


        return dto;

    }

    public static AdminEntity toEntity(Admin dto) {

        AdminEntity entity = new AdminEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setRoles(dto.getRoles());

        return entity;

    }

}
