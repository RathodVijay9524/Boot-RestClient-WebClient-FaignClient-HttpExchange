package com.vijay.controller;

import java.util.List;

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


import com.vijay.client.ProductServiceRestClient;
import com.vijay.client.ProductServiceWebClient;

import com.vijay.model.ProductRequest;
import com.vijay.model.ProductResponse;
import com.vijay.model.ProductResponses;



@RestController
@RequestMapping("/client")
public class ProductClientController {

	@Autowired
	private ProductServiceRestClient restclient;
	
	@Autowired
	private ProductServiceWebClient webClient;

	@PostMapping
	public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest product) {
		ProductResponse saveNewProductWebClient = webClient.saveProduct(product);
		return new ResponseEntity<>(saveNewProductWebClient, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<ProductResponse>> getAllProduct() {
		List<ProductResponse> allProducts = restclient.getAllProducts();
		return new ResponseEntity<>(allProducts, HttpStatus.ACCEPTED);
	}
	@GetMapping("/{id}")
    public ResponseEntity<ProductResponses> getProductById(@PathVariable("id") long productId){
		  ProductResponses productById = restclient.getProductById(productId);
        return new ResponseEntity<>(productById, HttpStatus.OK);
    }
	@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id") long ProductId){
		restclient.deleteProduct(ProductId);
        return new ResponseEntity<>("Product Deleted Successfully",HttpStatus.OK);
    }
	
	@PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("productId") long productId, @RequestBody ProductRequest product){
		ProductResponse updateProduct = restclient.updateProduct(productId,product);
        return new ResponseEntity<>(updateProduct,HttpStatus.OK);
    }


}



