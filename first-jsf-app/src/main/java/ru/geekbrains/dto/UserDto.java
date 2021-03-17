package ru.geekbrains.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.persist.User;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserDto implements Serializable {

    private Long id;
    private String name;
    private String surname;

    public UserDto(User user) {
        id = user.getId();
        name = user.getName();
        surname = user.getSurname();
    }
}
