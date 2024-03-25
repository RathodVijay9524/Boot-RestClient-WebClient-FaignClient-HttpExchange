package com.vijay.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vijay.client.CategoryClient;

import com.vijay.client.ProductClient;

import com.vijay.model.CategoryResponse;
import com.vijay.model.ProductRequest;
import com.vijay.model.ProductResponse;
import com.vijay.model.ProductResponses;

@Service
public class ProductHttpExchangeServiceClient {

	@Autowired
	private ProductClient httpExchangeClient;
	@Autowired
	private CategoryClient catagepryHttpExchangeClient;

	public ProductResponse saveProduct(ProductRequest product) {
		return httpExchangeClient.saveProduct(product);
	}

	public List<ProductResponse> getAllProducts() {
		return httpExchangeClient.getAllProducts();
	}

	public ProductResponses getProductById(long id) {
		ProductResponses productResponse = httpExchangeClient.getProductById(id);

		if (productResponse != null) {
			CategoryResponse categoryResponse = catagepryHttpExchangeClient.getCategoryById(productResponse.getCategoryId());
			if (categoryResponse != null) {
				ProductResponses.CategoryDetails categoryDetails = new ProductResponses.CategoryDetails(
						categoryResponse.getTitle(), categoryResponse.getDescription(),
						categoryResponse.getCategoryId());
				productResponse.setCategoryDetails(categoryDetails);
			}
		}

		return productResponse;
	}

	public ProductResponse updateProduct(int id, ProductRequest product) {
		return httpExchangeClient.updateProduct(id, product);

	}

	public void deleteProduct(int id) {
		httpExchangeClient.deleteProduct(id);
	}

}
