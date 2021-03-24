package ru.geekbrains.services;

import ru.geekbrains.dto.RoleDto;
import ru.geekbrains.persist.Role;
import ru.geekbrains.persist.RoleRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class RoleServiceImpl implements RoleService {

    @EJB
    private RoleRepository roleRepository;

    @TransactionAttribute
    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream()
                .map(this::buildRoleDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDto findById(Long id) {
        Role role = roleRepository.findById(id);
        if (role == null) return null;
        return buildRoleDto(role);
    }

    @Override
    public void saveOrUpdate(RoleDto roleDto) {
        roleRepository.saveOrUpdate(new Role(roleDto));
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    public RoleDto buildRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;
    }
}
