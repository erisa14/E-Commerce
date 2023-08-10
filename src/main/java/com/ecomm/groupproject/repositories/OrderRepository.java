package com.ecomm.groupproject.repositories;

import com.ecomm.groupproject.models.CartItem;
import com.ecomm.groupproject.models.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAll();

}
