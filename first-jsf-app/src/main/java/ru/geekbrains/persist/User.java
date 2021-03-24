package ru.geekbrains.persist;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.dto.UserDto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
@NamedQueries({
        @NamedQuery(name = "findAllUsers", query = "from User"),
        @NamedQuery(name = "findUserByName", query = "from User u where u.login = :name"),
        @NamedQuery(name = "deleteUserById", query = "delete from User u where u.id = :id"),
        @NamedQuery(name = "countAllUsers", query = "select count(*) from User")
})
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    //TODO
//    @ManyToMany(cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE
//    })
//    @JoinTable(name = "users_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
//    private Set<Role> roles;

    public User(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public User(UserDto userDto) {
        this(userDto.getId(), userDto.getLogin(), userDto.getPassword());
        //TODO
//        this.roles = new HashSet<>();
//        roles.addAll(userDto.getRoles().stream().map(Role::new).collect(Collectors.toList()));
    }

}
