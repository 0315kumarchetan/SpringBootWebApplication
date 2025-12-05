package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.controllers;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.ProductEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductRepository productRepository;

    private final int PAGE_SIZE=5;

    public ProductController(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    @GetMapping("/getBySku")
    public ProductEntity getProductEntityBySku(@RequestParam String sku){
        return productRepository.findBySku(sku);
    }

    @GetMapping("/getByTitle")
    public List<ProductEntity> getProductEntityByTitle(@RequestParam String title){
        return productRepository.findByTitleOrderByPrice(title);
    }

    @GetMapping("/getAllDataSortByAnything")
    public Page<ProductEntity> getAllDataSortByAnything(@RequestParam(defaultValue = "id") String sortBy){
        return productRepository.findBy(PageRequest.of(0,20,Sort.by(Sort.Direction.ASC,sortBy)));
    }

    @GetMapping("/findByTitleContainingIgnoreCase")
    public Page<ProductEntity> findByTitleContainingIgnoreCase(@RequestParam(defaultValue = "") String title,
                                                               @RequestParam(defaultValue = "0") int pageNumber,
                                                               @RequestParam(defaultValue = "id") String sortBy){
        PageRequest pageRequest = PageRequest.of(pageNumber,PAGE_SIZE,Sort.by(Sort.Direction.DESC,sortBy));
        return productRepository.findByTitleContainingIgnoreCase(title,pageRequest);
    }
}
