package com.inventory.product.services;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.inventory.product.dao.ProductDao;
import com.inventory.product.entities.Product;
import com.inventory.product.exception.ProductAlreadyExistsException;
import com.inventory.product.exception.ProductNotFoundException;

@SpringBootTest
public class ProductServiceTest {
	
	@MockBean
	private ProductDao productDao;
	
	@Autowired
	private ProductService productService;

	@Test
	public void getAllProducts(){
		
		when(productDao.findAll())
			.thenReturn(Stream
					.of(new Product("P01", "Space", "Sentinel", "Satellite family", 2),
					new Product("P02", "Commercial", "A380", "Aircraft Family", 3),
					new Product("P03", "Space", "Sentinel", "Satellite Family", 5)).collect(Collectors.toList()));
		assertEquals(3, productService.getAllProducts().size());
		
	}
	
	@Test
	public void getProductsOfCategory() {
		String productCategory = "Space";
		when(productDao.findAll())
		.thenReturn(Stream
				.of(new Product("P01", "Space", "Sentinel", "Satellite family", 2),
				new Product("P02", "Commercial", "A380", "Aircraft Family", 3),
				new Product("P03", "Space", "Sentinel", "Satellite Family", 5)).collect(Collectors.toList()));
		//assertEquals(2, productService.getProductsOfCategory(productCategory).size());
		
	}
	
	@Test
	public void getProductsOfCategoryException() {
		
		when(productDao.findAll())
		.thenReturn(Stream
				.of(new Product("P01", "Space", "Sentinel", "Satellite family", 2),
				new Product("P02", "Commercial", "A380", "Aircraft Family", 3),
				new Product("P03", "Space", "Sentinel", "Satellite Family", 5)).collect(Collectors.toList()));
		
		String prodCategory = "Helicopter"; 
		Exception exception = assertThrows(ProductNotFoundException.class, () -> {
			productService.getProductsOfCategory(prodCategory);
		    });

		    String expectedMessage = "No product found for the Product Category: "+prodCategory;
		    String actualMessage = exception.getMessage();

		    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void addProduct() {
		when(productDao.findAll())
		.thenReturn(Stream
				.of(new Product("P01", "Space", "Sentinel", "Satellite family", 2),
				new Product("P02", "Commercial", "A380", "Aircraft Family", 3),
				new Product("P03", "Space", "Sentinel", "Satellite Family", 5)).collect(Collectors.toList()));
		
		Product product = new Product("P04", "Helicopter", "H135", "Light twin", 3);
		when(productDao.save(product)).thenReturn(product);
		assertEquals(product, productService.addProduct(product));
	}
	

	@Test
	public void addProductException() {
		when(productDao.findAll())
		.thenReturn(Stream
				.of(new Product("P01", "Space", "Sentinel", "Satellite family", 2),
				new Product("P02", "Commercial", "A380", "Aircraft Family", 3),
				new Product("P03", "Space", "Sentinel", "Satellite Family", 5)).collect(Collectors.toList()));
		
		Product product = new Product("P03", "Helicopter", "H135", "Light twin", 3);
		Exception exception = assertThrows(ProductAlreadyExistsException.class, () -> {
			when(productService.addProduct(product));
		    });

		    String expectedMessage = "Product already exists with the given Product ID";
		    String actualMessage = exception.getMessage();

		    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void updateProduct() {
		
		when(productDao.findAll())
		.thenReturn(Stream
				.of(new Product("P01", "Space", "Sentinel", "Satellite family", 2),
				new Product("P02", "Commercial", "A380", "Aircraft Family", 3),
				new Product("P03", "Space", "Sentinel", "Satellite Family", 5)).collect(Collectors.toList()));
		
		Product product = new Product("P02", "Helicopter", "H175", "Light twin", 6);
		when(productDao.save(product)).thenReturn(product);
		//assertEquals(product, productService.updateProduct(product));
	}
	
	@Test
	public void updateProductException() {
		when(productDao.findAll())
		.thenReturn(Stream
				.of(new Product("P01", "Space", "Sentinel", "Satellite family", 2),
				new Product("P02", "Commercial", "A380", "Aircraft Family", 3),
				new Product("P03", "Space", "Sentinel", "Satellite Family", 5)).collect(Collectors.toList()));
		
		Product product = new Product("P04", "Helicopter", "H135", "Light twin", 3);
		Exception exception = assertThrows(ProductNotFoundException.class, () -> {
			when(productService.updateProduct(product));
		    });

		    String expectedMessage = "No product found for the given Product ID.";
		    String actualMessage = exception.getMessage();

		    assertTrue(actualMessage.contains(expectedMessage));
	}

}
