package com.vijay.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.vijay.exception.ProductServiceException;
import com.vijay.model.CategoryResponse;
import com.vijay.model.ProductRequest;
import com.vijay.model.ProductResponse;
import com.vijay.model.ProductResponses;

@Service
public class ProductServiceRestClient {
	
	@Autowired
	private CategoryServiceWebClient categoryServiceWebClient;
	
	
	private final RestClient restClient;

    public ProductServiceRestClient() {
        restClient = RestClient.builder()
                .baseUrl("http://localhost:9092")
                .build();
    }
	
	public ProductResponse saveProduct(ProductRequest product) {
		try {
            return restClient.post()
                    .uri("/product")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(product)
                    .retrieve()
                    .body(ProductResponse.class);
        } catch (Exception e) {
            // Log or handle the error appropriately
            throw new ProductServiceException("Error occurred while saving new product", e);
        }
    }
	
	public List<ProductResponse> getAllProducts() {
        try {
            return restClient.get()
                    .uri("/product")
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<ProductResponse>>() {});
        } catch (Exception e) {
            // Log or handle the error appropriately
            throw new ProductServiceException("Error occurred while retrieving all products", e);
        }
    }

	public ProductResponses getProductById(long productId) {
       
		ProductResponses productResponse = restClient.get()
                    .uri("/product/{id}", productId)
                    .retrieve()
                    .body(ProductResponses.class);
            
            if (productResponse != null) {
	            CategoryResponse categoryResponse = categoryServiceWebClient.getCategoryById(productResponse.getCategoryId());
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

    public ProductResponse updateProduct(long productId, ProductRequest product) {
        try {
            return restClient.put()
                    .uri("/product/{id}", productId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(product)
                    .retrieve()
                    .body(ProductResponse.class);
        } catch (Exception e) {
            // Log or handle the error appropriately
            throw new ProductServiceException("Error occurred while updating product with ID " + productId, e);
        }
    }

    public String deleteProduct(long productId) {
        try {
            restClient.delete()
                    .uri("/product/{id}", productId)
                    .retrieve()
                    .toBodilessEntity();
            return "Product removed: " + productId;
        } catch (Exception e) {
            // Log or handle the error appropriately
            throw new ProductServiceException("Error occurred while deleting product with ID " + productId, e);
        }
    }
    
    
}




/*
 * import org.springframework.stereotype.Service;

@Service
public class ProductServiceClient {

    private final RestClient restClient;

    public ProductServiceClient() {
        restClient = RestClient.builder()
                .baseUrl("http://localhost:9191")
                .build();
    }

    public Product saveNewProduct(Product product) {
        return restClient.post()
                .uri("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .body(product)
                .retrieve()
                .body(Product.class);
    }

    public List<Product> getAllProducts(){
        return restClient.get()
                .uri("/products")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Product>>(){});
    }

    public Product getProduct(int id){
        return restClient.get()
                .uri("/products/find/{id}",id)
                .retrieve()
                .body(Product.class);
    }

    public Product updateProduct(int id,Product product){
        return restClient.put()
                .uri("/products/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(product)
                .retrieve()
                .body(Product.class);
    }

    public String deleteProduct(int id){
        restClient.delete()
                .uri("/products/{id}",id)
                .retrieve()
                .toBodilessEntity();
        return "product removed : "+id;
    }
}

 * 
 */
 

