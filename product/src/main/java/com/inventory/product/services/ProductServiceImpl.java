package com.inventory.product.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.product.dao.ProductDao;
import com.inventory.product.entities.Product;
import com.inventory.product.exception.ProductAlreadyExistsException;
import com.inventory.product.exception.ProductNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	@Override
	public List<Product> getAllProducts(){		
		return productDao.findAll();
	}

	@Override
	public List<Product> getProductsOfCategory(String productCategory) throws ProductNotFoundException{
		
		List<Product> productList = new ArrayList<Product>();
		productList = productDao.findByCategory(productCategory);
		if(productList.isEmpty()) {
			throw new ProductNotFoundException("No product found for the Product Category: "+productCategory);
		}
		return productList;
	}

	@Override
	public Product addProduct(Product product) throws ProductAlreadyExistsException {
		
		for(Product productIterate : productDao.findAll()) {
			if(productIterate!=null && product!=null) {
				if(productIterate.getId().equals(product.getId())) {
					throw new ProductAlreadyExistsException("Product already exists with the given Product ID");
				}
			}
		}
		productDao.save(product);
		return product;
	}

	@Override
	public Product updateProduct(Product product) throws ProductNotFoundException {
		
		int rowsAffected = productDao.updateProduct(product.getCategory(),product.getDescription(), product.getName(),
				product.getUnits(), product.getId());

		if(rowsAffected == 0) {
			throw new ProductNotFoundException("No product found for the given Product ID."); 
		}
		return product;		
	}
	

}
