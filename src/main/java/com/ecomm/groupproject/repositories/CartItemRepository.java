package com.ecomm.groupproject.repositories;

import com.ecomm.groupproject.models.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {

    List<CartItem> findAll();

    Optional<CartItem> findById(Long id);

    CartItem findByIdIs(Long id);

    CartItem findByProductId(Long id);


    List<CartItem> findByShoppingCartId(Long loggedInUserId);
}
