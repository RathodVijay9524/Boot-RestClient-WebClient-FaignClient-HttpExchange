package com.vijay.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vijay.client.CategoryFeignClient;
import com.vijay.client.ProductFeignClient;
import com.vijay.model.CategoryResponse;
import com.vijay.model.ProductRequest;
import com.vijay.model.ProductResponse;
import com.vijay.model.ProductResponses;

import java.util.List;

@Service
public class ProductFeignServiceClient {

	 private final ProductFeignClient productFeignClient;
	 
	 @Autowired
	 private CategoryFeignClient feignClient;

	    @Autowired
	    public ProductFeignServiceClient(ProductFeignClient productFeignClient) {
	        this.productFeignClient = productFeignClient;
	    }

	    public ProductResponse saveProduct(ProductRequest product) {
	        return productFeignClient.saveProduct(product);
	    }

	    public List<ProductResponse> getAllProducts() {
	        return productFeignClient.getAllProducts();
	    }

	    public ProductResponses getProduct(int id) {
	    	ProductResponses productResponse = productFeignClient.getProduct(id);
	        
	    	 if (productResponse != null) {
		            CategoryResponse categoryResponse = feignClient.getCategoryById(productResponse.getCategoryId());
		            if (categoryResponse != null) {
		            	ProductResponses.CategoryDetails categoryDetails = new ProductResponses.CategoryDetails(
		                        categoryResponse.getTitle(), 
		                        categoryResponse.getDescription(), 
		                        categoryResponse.getCategoryId());
		                productResponse.setCategoryDetails(categoryDetails);
		            }
		        }

		        return productResponse;
	    }

	    public ProductResponse updateProduct(int id, ProductRequest product) {	
	        return productFeignClient.updateProduct(id, product);
	        
	    }

	    public void deleteProduct(int id) {
	        productFeignClient.deleteProduct(id);
	    }

	
}
