package com.vijay.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;

import com.vijay.model.CategoryResponse;
import com.vijay.model.ProductRequest;
import com.vijay.model.ProductResponse;
import com.vijay.model.ProductResponses;

import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProductServiceWebClient {

	private final WebClient webClient;
	
	@Autowired
	private CategoryServiceWebClient categoryServiceWebClient;

	@Autowired
	public ProductServiceWebClient(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl("http://localhost:9092").build();
	}
	
	 public ProductResponse saveProduct(ProductRequest product) {
	        return webClient.post()
	                .uri("/product")
	                .contentType(MediaType.APPLICATION_JSON)
	                .body(Mono.just(product), ProductRequest.class)
	                .retrieve()
	                .bodyToMono(ProductResponse.class)
	                .block();
	             
	    }

	    public List<ProductResponse> getAllProducts() {
	        return webClient.get()
	                .uri("/product")
	                .retrieve()
	                .bodyToMono(new ParameterizedTypeReference<List<ProductResponse>>() {})
	                .block();
	    }
	    
	    public ProductResponses getProductById(long productId) {
	    	ProductResponses productResponse = webClient.get()
	                .uri("/product/{id}", productId)
	                .retrieve()
	                .bodyToMono(ProductResponses.class)
	                .block(); // Block until response is available

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
	        return webClient.put()
	                .uri("/product/{id}", productId)
	                .contentType(MediaType.APPLICATION_JSON)
	                .body(Mono.just(product), ProductRequest.class)
	                .retrieve()
	                .bodyToMono(ProductResponse.class)
	                .block();
	    }

	    public Void deleteProduct(long productId) {
	        return webClient.delete()
	                .uri("/product/{id}", productId)
	                .retrieve()
	                .bodyToMono(Void.class)
	                .block();
	    }

}



// if we use reactive 
/*
 * 
 * public Mono<ProductResponse> getProductById(long productId) {
        return webClient.get()
                .uri("/product/{id}", productId)
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .flatMap(productResponse -> {
                    return categoryServiceWebClient.getCategoryById(productResponse.getCategoryId())
                            .map(categoryResponse -> {
                                productResponse.setCategoryDetails(new ProductResponse.CategoryDetails(categoryResponse.getTitle(), categoryResponse.getDescription(), categoryResponse.getCategoryId()));
                                return productResponse;
                            });
                })
                .onErrorMap(e -> new ProductServiceException("Error occurred while retrieving product with ID " + productId, e));
    }
 */


/*


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProductServiceClient {

    private final WebClient webClient;

    @Autowired
    public ProductServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:9191").build();
    }

    public Mono<Product> saveNewProduct(Product product) {
        return webClient.post()
                .uri("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(product), Product.class)
                .retrieve()
                .onStatus(HttpStatus::isError, response -> handleErrorResponse(response))
                .bodyToMono(Product.class);
    }

    public Mono<List<Product>> getAllProducts() {
        return webClient.get()
                .uri("/products")
                .retrieve()
                .onStatus(HttpStatus::isError, response -> handleErrorResponse(response))
                .bodyToMono(new ParameterizedTypeReference<List<Product>>() {});
    }

    public Mono<Product> getProduct(int id) {
        return webClient.get()
                .uri("/products/find/{id}", id)
                .retrieve()
                .onStatus(HttpStatus::isError, response -> handleErrorResponse(response))
                .bodyToMono(Product.class);
    }

    public Mono<Product> updateProduct(int id, Product product) {
        return webClient.put()
                .uri("/products/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(product), Product.class)
                .retrieve()
                .onStatus(HttpStatus::isError, response -> handleErrorResponse(response))
                .bodyToMono(Product.class);
    }

    public Mono<Void> deleteProduct(int id) {
        return webClient.delete()
                .uri("/products/{id}", id)
                .retrieve()
                .onStatus(HttpStatus::isError, response -> handleErrorResponse(response))
                .bodyToMono(Void.class);
    }

    private Mono<? extends Throwable> handleErrorResponse(ClientResponse response) {
        // Implement error handling logic here
        return Mono.error(new ProductServiceException("Product Service Error: " + response.statusCode()));
    }
}


*/