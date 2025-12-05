package com.codingshuttle.springbootwebtutorial.springbootwebtutorial;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.ProductEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class SpringBootWebTutorialApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void createProductTest(){
		ProductEntity productEntity = ProductEntity.builder()
				.sku("Tea")
				.title("Bru Tea")
				.price(BigDecimal.valueOf(124.45))
				.quantity(1)
				.build();

		ProductEntity savedProductEntity = productRepository.save(productEntity);
		System.out.println(savedProductEntity);
	}

	@Test
	void getAllProduct(){
		List<ProductEntity> productEntities = productRepository.getByTitle("Bru Tea");
		System.out.println(productEntities);
	}

	@ParameterizedTest
	@ValueSource(strings = {"Coffie"})
	void getProductBySku(String input){
		ProductEntity productEntity = productRepository.findBySku(input);
		System.out.println(productEntity);
	}

}
