package com.ecomm.groupproject.repositories;

import com.ecomm.groupproject.models.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

    List<ShoppingCart> findAll();

    Optional<ShoppingCart> findById(Long id);

    ShoppingCart findByIdIs(Long id);


    ShoppingCart findByUserId(Long userId);



}
