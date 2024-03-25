package com.vijay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.vijay.client.service.ProductFeignServiceClient;
import com.vijay.client.service.ProductHttpExchangeServiceClient;
import com.vijay.model.ProductRequest;
import com.vijay.model.ProductResponse;
import com.vijay.model.ProductResponses;

@RestController
@RequestMapping("/client/products")
public class FeignClientProductController {

	@Autowired
	private ProductHttpExchangeServiceClient httpExchangeClient;

	@Autowired
	private ProductFeignServiceClient productFeignService;

	@PostMapping
	public ProductResponse saveProduct(@RequestBody ProductRequest product) {
		return productFeignService.saveProduct(product);
	}

	@GetMapping
	public List<ProductResponse> getAllProducts() {
		return productFeignService.getAllProducts();
	}

	@GetMapping("/{id}")
	public ProductResponses getProduct(@PathVariable int id) {
		return httpExchangeClient.getProduct(id);
	}

	@PutMapping("/{id}")
	public ProductResponse updateProduct(@PathVariable int id, @RequestBody ProductRequest product) {
		return productFeignService.updateProduct(id, product);
	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable int id) {
		httpExchangeClient.deleteProduct(id);
	}
}
