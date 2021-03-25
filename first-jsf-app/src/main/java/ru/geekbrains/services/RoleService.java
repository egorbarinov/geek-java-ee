package ru.geekbrains.services;

import ru.geekbrains.dto.RoleDto;
import ru.geekbrains.persist.Role;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RoleService {

    List<RoleDto> findAll();

    RoleDto findById(Long id);

    void saveOrUpdate(RoleDto roleDto);

    void deleteById(Long id);

    RoleDto buildRoleDto(Role role);

}
