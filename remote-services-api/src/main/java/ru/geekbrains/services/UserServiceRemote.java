package ru.geekbrains.services;

import ru.geekbrains.dto.UserDto;

import java.util.List;

public interface UserServiceRemote {

    List<UserDto> findAll();

    UserDto findById(Long id);

    Long countAll();

}
