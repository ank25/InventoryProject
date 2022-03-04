package com.inventory.product.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.product.entities.Product;

public interface ProductDao extends JpaRepository<Product, String>{
	
	List<Product> findByCategory(String category);
	
	@Transactional
	@Modifying
    @Query("UPDATE Product p SET p.category = :prodCategory, p.description = :prodDesc, "
    		+ "p.name = :prodName, p.units = :prodUnits "
    		+ "WHERE p.id = :prodId")
    int updateProduct(@Param("prodCategory") String prodcategory
    		, @Param("prodDesc") String prodDesc, @Param("prodName") String prodName,
    		@Param("prodUnits") long prodUnits, @Param("prodId") String prodId);
}
