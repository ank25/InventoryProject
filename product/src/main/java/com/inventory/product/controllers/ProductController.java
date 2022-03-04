package com.inventory.product.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.inventory.product.entities.Product;
import com.inventory.product.exception.ProductAlreadyExistsException;
import com.inventory.product.exception.ProductNotFoundException;
import com.inventory.product.services.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	public ProductService productService;
	
	@GetMapping("/")
	public String welcome() {
		return "index";
	}
	
	@PostMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/add")
	public String add() {
		System.out.println("in add");
		return "add";
	}
	
	@GetMapping("/update")
	public String update() {
		return "update";
	}
	
	/**Fetch all the products and return a Response Entity [JSON]*/
	@GetMapping("/api/products")
	@ResponseBody
	public ResponseEntity<List<Product>> getProducts(){
		
		List<Product> getAllProducts = this.productService.getAllProducts();
		return new ResponseEntity<List<Product>>(getAllProducts,HttpStatus.OK);
	
	}
	
	/**Fetch all the products of a given Product Category 
		and return a Response Entity [JSON]
		@param productCategory
	*/
	@GetMapping("/api/products/{productCategory}")
	@ResponseBody
	public ResponseEntity<List<Product>> getProductsOfCategory(@PathVariable String productCategory) throws ProductNotFoundException{		
		
		List<Product> productListofCategory = this.productService.getProductsOfCategory(productCategory);		
			return new ResponseEntity<List<Product>>(productListofCategory,HttpStatus.OK);
		
	}
	
	/**Add a product and return the same as a Response Entity [JSON]*/
	@PostMapping("/api/products")
	@ResponseBody
	public ResponseEntity<Product> addProduct(@RequestBody Product product) throws ProductAlreadyExistsException {
		
		Product addedProduct = this.productService.addProduct(product);
	    return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
	}
	
	/**Update an existing product and 
	 * return the same as a Response Entity [JSON]*/
	@PutMapping("/api/products/{productId}")
	@ResponseBody
	public ResponseEntity<Product> updateEmployee(@RequestBody Product product) throws ProductNotFoundException{
		
		Product updatedProduct = this.productService.updateProduct(product);
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);   
	}
		
	@ExceptionHandler(value = ProductAlreadyExistsException.class)
    public ResponseEntity<String> ProductAlreadyExistsException(ProductAlreadyExistsException productAlreadyExistsException) {
		//ResponseEntity<String> o = new ResponseEntity<String>(productAlreadyExistsException.getMessage(), HttpStatus.CONFLICT);
        //System.out.println("ResponseEntity: "+o.toString());
		//return o;
		return new ResponseEntity<String>(productAlreadyExistsException.getMessage(), HttpStatus.CONFLICT);
    }
	
	@ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<String> ProductNotFoundException(ProductNotFoundException productNotFoundException) {
		//ResponseEntity<String> o = new ResponseEntity<String>(productNotFoundException.getMessage(), HttpStatus.CONFLICT);
        //System.out.println("ResponseEntity: "+o.toString());
		//return o;
		
		return new ResponseEntity<String>(productNotFoundException.getMessage(), HttpStatus.CONFLICT);
    }
}
