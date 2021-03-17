package ru.geekbrains.persist;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.dto.UserDto;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@NamedQueries({
        @NamedQuery(name = "findAllUsers", query = "from User"),
        @NamedQuery(name = "deleteUserById", query = "delete from User u where u.id = :id"),
        @NamedQuery(name = "countAllUsers", query = "select count(*) from User")
})
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    public User(UserDto userDto) {
        this(userDto.getId(), userDto.getName(), userDto.getSurname());
    }

}
