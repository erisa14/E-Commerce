package com.ecomm.groupproject.repositories;

import com.ecomm.groupproject.models.Category;
import com.ecomm.groupproject.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByName(String email);
}
