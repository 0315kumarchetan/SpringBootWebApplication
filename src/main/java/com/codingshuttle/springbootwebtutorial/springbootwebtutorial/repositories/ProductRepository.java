package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    List<ProductEntity> getByTitle(String title);

    List<ProductEntity> findByTitleOrderByPrice(String title);

    Page<ProductEntity> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    ProductEntity findBySku(String sku);

    List<ProductEntity> findByOrderByQuantity();

    Page<ProductEntity> findBy(Pageable pageable);
}
