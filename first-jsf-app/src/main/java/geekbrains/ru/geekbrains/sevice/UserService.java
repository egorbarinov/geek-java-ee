package geekbrains.ru.geekbrains.sevice;

import geekbrains.ru.geekbrains.dto.UserDto;

import javax.ejb.Local;
import java.util.List;


@Local
public interface UserService {

    List<UserDto> findAll();

    UserDto findById(Long id);

    Long countAll();

    void saveOrUpdate(UserDto userDto);

    void deleteById(Long id);
}
