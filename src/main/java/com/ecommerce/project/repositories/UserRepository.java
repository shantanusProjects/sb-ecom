package com.ecommerce.project.repositories;

import com.ecommerce.project.model.Product;
import com.ecommerce.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Product,Long> {

    Optional<User> findByUserName(String username);
}
