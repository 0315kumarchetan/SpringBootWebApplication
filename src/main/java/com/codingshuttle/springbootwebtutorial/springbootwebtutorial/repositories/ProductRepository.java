package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    List<ProductEntity> getByTitle(String title);
}
