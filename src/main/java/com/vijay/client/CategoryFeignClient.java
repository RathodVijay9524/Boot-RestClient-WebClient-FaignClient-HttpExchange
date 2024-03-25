package com.vijay.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.vijay.model.CategoryRequest;
import com.vijay.model.CategoryResponse;

@FeignClient(name = "CATEGORY-SERVICE", url = "http://localhost:9091")
public interface CategoryFeignClient {
	
	@PostMapping("/categories")
	public CategoryResponse saveCategory(@RequestBody CategoryRequest categoryDto);
	
	@PutMapping("/categories/{categoryId}")
	public CategoryResponse updateCategory(@PathVariable String categoryId,
			@RequestBody CategoryRequest categoryDto);
	
	@GetMapping("/categories/{categoryId}")
	public CategoryResponse getCategoryById(@PathVariable String categoryId);
	
	@DeleteMapping("/categories/{categoryId}")
	public String deleteCategory(@PathVariable String categoryId);

}
