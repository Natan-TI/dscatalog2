package com.natanti.dscatalog.tests;

import java.time.Instant;

import com.natanti.dscatalog.dto.ProductDTO;
import com.natanti.dscatalog.entities.Category;
import com.natanti.dscatalog.entities.Product;

public class Factory {
	
	public static Product createProduct() {
		Product product = new Product(26L, "Phone", "Good Phone", 800.0, "https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/26-big.jpg", Instant.parse("2025-06-22T03:00:00Z"));
		product.getCategories().add(createCategory());
		return product;
	}
	
	public static ProductDTO createProductDTO() {
		Product product = createProduct();
		return new ProductDTO(product, product.getCategories());
	}
	
	public static Category createCategory() {
		return new Category(2L, "Eletrônicos", Instant.parse("2025-06-22T03:00:00Z"), null);
	}
}
