package com.inventory.product.services;

import java.util.List;

import com.inventory.product.entities.Product;
import com.inventory.product.exception.ProductAlreadyExistsException;
import com.inventory.product.exception.ProductNotFoundException;

public interface ProductService {
	
	/**Fetch all the products*/
	public List<Product> getAllProducts();

	/**Fetch all the products of a given Product Category
	 * @param productCategory
	 * @return List<Product>
	 */
	public List<Product> getProductsOfCategory(String productCategory) throws ProductNotFoundException;
	
	/**Add a new product
	 * @param product 
	* @return Product
	*/
	public Product addProduct(Product product) throws ProductAlreadyExistsException;

	/**Update an existing product
	 * @param product 
	 * @return Product
	*/
	public Product updateProduct(Product product) throws ProductNotFoundException;

}
