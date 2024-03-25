package com.vijay.client;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

import com.vijay.model.CategoryRequest;
import com.vijay.model.CategoryResponse;

@HttpExchange
public interface CategoryClient {
	
	@PostExchange("/categories")
	public CategoryResponse saveCategory(@RequestBody CategoryRequest categoryDto);
	
	@PutExchange("/categories/{categoryId}")
	public CategoryResponse updateCategory(@PathVariable String categoryId,
			@RequestBody CategoryRequest categoryDto);
	
	@GetExchange("/categories/{categoryId}")
	public CategoryResponse getCategoryById(@PathVariable String categoryId);
	
	@DeleteExchange("/categories/{categoryId}")
	public String deleteCategory(@PathVariable String categoryId);

}
