package geekbrains.ru.geekbrains.controller;

import geekbrains.ru.geekbrains.persist.User;
import geekbrains.ru.geekbrains.repository.UserRepository;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UserController implements Serializable {

    @EJB
    private UserRepository userRepository;

    private User user;

    private List<User> users;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        users = userRepository.findAll();
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String createUser() {
        this.user = new User();
        return "/user_form.xhtml?faces-redirect-true";
    }

    public List<User> getAllUsers() {
        return users;
    }

    public String editUser(User user) {
        this.user = user;
        return "/user_form.xhtml?faces-redirect-true";
    }

    public void deleteUser(User user) {
        userRepository.deleteById(user.getId());
    }

    public String saveUser() {
        userRepository.saveOrUpdate(user);
        return "/user.xhtml?faces-redirect-true";
    }
}
