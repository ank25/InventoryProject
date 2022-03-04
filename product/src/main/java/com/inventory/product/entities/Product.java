package com.inventory.product.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
	
	@Id
	private String id;	
	private String category;
	private String name;
	private String description;
	private long units;
	
	public Product() {
		super();
	}

	public Product(String id, String category, String name, String description, long units) {
		super();
		this.id = id;
		this.category = category;
		this.name = name;
		this.description = description;
		this.units = units;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getUnits() {
		return units;
	}

	public void setUnits(long units) {
		this.units = units;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", category=" + category + ", name=" + name + ", description=" + description
				+ ", units=" + units + "]";
	}
	
	

	
}
