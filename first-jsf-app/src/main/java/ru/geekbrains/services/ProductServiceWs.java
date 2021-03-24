package ru.geekbrains.services;

import ru.geekbrains.dto.ProductDto;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ProductServiceWs {

    @WebMethod
    List<ProductDto> findAll();

    @WebMethod
    ProductDto findById(Long id);

}
