package ru.geekbrains.controllers;

import ru.geekbrains.dto.UserDto;
import ru.geekbrains.services.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UserController implements Serializable {

    @EJB
    private UserService userService;

    private UserDto user;

    private List<UserDto> users;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        users = userService.findAll();
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto userDto) {
        this.user = userDto;
    }

    public String createUser() {
        this.user = new UserDto();
        return "/user_form.xhtml?faces-redirect=true";
    }

    public List<UserDto> getAllUsers() {
        return users;
    }

    public String editUser(UserDto userDto) {
        this.user = userDto;
        return "/user_form.xhtml?faces-redirect=true";
    }

    public void deleteUser(UserDto userDto) {
        userService.deleteById(userDto.getId());
    }

    public String saveUser() {
        userService.saveOrUpdate(user);
        return "/user.xhtml?faces-redirect=true";
    }
}
