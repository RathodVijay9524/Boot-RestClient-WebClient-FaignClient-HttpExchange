package com.vijay.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.vijay.exception.ProductServiceException;
import com.vijay.model.CategoryRequest;
import com.vijay.model.CategoryResponse;


@Service
public class CategoryServiceRestClient {
	
	private final RestClient restClient;

    public CategoryServiceRestClient() {
        restClient = RestClient.builder()
                .baseUrl("http://localhost:9091")
                .build();
    }
    
    public CategoryResponse saveCategory(CategoryRequest category) {
		try {
            return restClient.post()
                    .uri("/categories")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(category)
                    .retrieve()
                    .body(CategoryResponse.class);
        } catch (Exception e) {
            // Log or handle the error appropriately
            throw new ProductServiceException("Error occurred while saving new product", e);
        }
    }
    public String deleteCategory(String categoryId ) {
        try {
            restClient.delete()
                    .uri("/categories/{id}", categoryId)
                    .retrieve()
                    .toBodilessEntity();
            return "categories removed: " + categoryId;
        } catch (Exception e) {
            // Log or handle the error appropriately
            throw new ProductServiceException("Error occurred while deleting product with ID " + categoryId, e);
        }
    }
    
    public CategoryResponse updateCategory(String categoryId, CategoryRequest product) {
        try {
            return restClient.put()
                    .uri("/categories/{id}", categoryId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(product)
                    .retrieve()
                    .body(CategoryResponse.class);
        } catch (Exception e) {
            // Log or handle the error appropriately
            throw new ProductServiceException("Error occurred while updating product with ID " + categoryId, e);
        }
    }
    public CategoryResponse getCategoryById(String categoryId) {
        try {
            return restClient.get()
                    .uri("/categories/{id}", categoryId)
                    .retrieve()
                    .body(CategoryResponse.class);
        } catch (Exception e) {
            // Log or handle the error appropriately
            throw new ProductServiceException("Error occurred while retrieving product with ID " + categoryId, e);
        }
    }

}
