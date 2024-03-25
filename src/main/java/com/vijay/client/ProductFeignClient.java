package com.vijay.client;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.vijay.model.ProductRequest;
import com.vijay.model.ProductResponse;
import com.vijay.model.ProductResponses;



@FeignClient(name = "PRODUCT-SERVICE", url = "http://localhost:9092")
public interface ProductFeignClient {
	

    @PostMapping(value = "/product", consumes = MediaType.APPLICATION_JSON_VALUE)
    ProductResponse saveProduct(@RequestBody ProductRequest product);

    @GetMapping("/product")
    List<ProductResponse> getAllProducts();

    @GetMapping("/product/{id}")
    ProductResponses getProduct(@PathVariable("id") int id);

    @PutMapping(value = "/product/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ProductResponse updateProduct(@PathVariable("id") int id, @RequestBody ProductRequest product);

    @DeleteMapping("/product/{id}")
    void deleteProduct(@PathVariable("id") int id);
	
	
	
	
	
	

//    @PostMapping(value = "/product", consumes = MediaType.APPLICATION_JSON_VALUE)
//    Mono<ProductResponse> saveProduct(@RequestBody ProductRequest product);
//
//    @GetMapping("/product")
//    Mono<List<ProductResponse>> getAllProducts();
//
//    @GetMapping("/product/{id}")
//    Mono<ProductResponse> getProduct(@PathVariable("id") int id);
//
//    @PutMapping(value = "/product/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    Mono<ProductResponse> updateProduct(@PathVariable("id") int id, @RequestBody ProductRequest product);
//
//    @DeleteMapping("/product/{id}")
//    Mono<Void> deleteProduct(@PathVariable("id") int id);
}
