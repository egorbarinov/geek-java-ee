package ru.geekbrains.services;

import ru.geekbrains.dto.UserDto;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserServiceRemote {

    List<UserDto> findAll();

    UserDto findById(Long id);

    Long countAll();

}
