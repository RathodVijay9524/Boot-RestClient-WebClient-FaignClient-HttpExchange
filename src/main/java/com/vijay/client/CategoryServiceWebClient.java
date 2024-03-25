package com.vijay.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.vijay.model.CategoryRequest;
import com.vijay.model.CategoryResponse;


import reactor.core.publisher.Mono;

@Service
public class CategoryServiceWebClient {
	
	private final WebClient webClient;

	@Autowired
	public CategoryServiceWebClient(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl("http://localhost:9091").build();
	}
	
	 public CategoryResponse saveCategory(CategoryRequest product) {
	        return webClient.post()
	                .uri("/categories")
	                .contentType(MediaType.APPLICATION_JSON)
	                .body(Mono.just(product), CategoryRequest.class)
	                .retrieve()
	                .bodyToMono(CategoryResponse.class)
	                .block();
	             
	    }
	  public CategoryResponse updateCategory(String categoryId, CategoryRequest product) {
	        return webClient.put()
	                .uri("/categories/{id}", categoryId)
	                .contentType(MediaType.APPLICATION_JSON)
	                .body(Mono.just(product), CategoryRequest.class)
	                .retrieve()
	                .bodyToMono(CategoryResponse.class)
	                .block();
	    }
	  
	  public CategoryResponse getCategoryById(String categoryId) {
	        return webClient.get()
	                .uri("/categories/{id}", categoryId)
	                .retrieve()
	                .bodyToMono(CategoryResponse.class)
	                .block();
	    }
	  
	  public Void deleteCategory(String categoryId) {
	        return webClient.delete()
	                .uri("/categories/{id}", categoryId)
	                .retrieve()
	                .bodyToMono(Void.class)
	                .block();
	    }

	


}
