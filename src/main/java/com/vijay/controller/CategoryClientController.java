package com.vijay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.client.CategoryClient;
import com.vijay.client.CategoryFeignClient;
import com.vijay.client.CategoryServiceRestClient;
import com.vijay.client.CategoryServiceWebClient;

import com.vijay.model.CategoryRequest;
import com.vijay.model.CategoryResponse;

@RestController
@RequestMapping("/client/category")
public class CategoryClientController {

	@Autowired
	private CategoryServiceRestClient restClient;
	
	@Autowired
	private CategoryServiceWebClient webClient;
	
	@Autowired
	private CategoryFeignClient categoryFeignClient;
	
	@Autowired
	private CategoryClient httpExchangeClient;

	@PostMapping
	public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryDto) {
		CategoryResponse saveCategory = restClient.saveCategory(categoryDto);
		return new ResponseEntity<>(saveCategory, HttpStatus.CREATED);
	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryResponse> updateCategory(@PathVariable String categoryId,
			@RequestBody CategoryRequest categoryDto) {
		CategoryResponse updateCategory = httpExchangeClient.updateCategory(categoryId, categoryDto);
		return new ResponseEntity<>(updateCategory, HttpStatus.OK);
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryResponse> getSingle(@PathVariable String categoryId) {
		CategoryResponse categoryDto = categoryFeignClient.getCategoryById(categoryId);
		return ResponseEntity.ok(categoryDto);
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable String categoryId) {
		webClient.deleteCategory(categoryId);
		return new ResponseEntity<>("Category Deleted Successfully..!!", HttpStatus.OK);
	}

}
