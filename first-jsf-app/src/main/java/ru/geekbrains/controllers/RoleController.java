package ru.geekbrains.controllers;

import ru.geekbrains.dto.RoleDto;
import ru.geekbrains.services.RoleService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class RoleController implements Serializable {

    @EJB
    private RoleService roleService;

    private RoleDto roleDto;

    private List<RoleDto> roles;

    @PostConstruct
    public void init() {
        roles = roleService.findAll();
    }

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        init();
    }

    public RoleDto getRole() {
        return roleDto;
    }

    public void setRole(RoleDto roleDto) {
        this.roleDto = roleDto;
    }

    public String createRole() {
        this.roleDto = new RoleDto();
        return "/admin/role_form.xhtml?faces-redirect=true";
    }

    public List<RoleDto> getAllRoles() {
        return roles;
    }

    public String editRole(RoleDto roleDto) {
        this.roleDto = roleDto;
        return "/admin/role_form.xhtml?faces-redirect=true";
    }

    public void deleteRole(RoleDto roleDto) {
        roleService.deleteById(roleDto.getId());
    }

    public String saveRole() {
        roleService.saveOrUpdate(roleDto);
        return "/admin/role.xhtml?faces-redirect=true";
    }
}
