package com.vijay.client;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

import com.vijay.model.ProductRequest;
import com.vijay.model.ProductResponse;
import com.vijay.model.ProductResponses;

@HttpExchange
public interface ProductClient {
	
    @PostExchange(value = "/product")
    ProductResponse saveProduct(@RequestBody ProductRequest product);

    @GetExchange("/product")
    List<ProductResponse> getAllProducts();

    @GetExchange("/product/{id}")
    ProductResponses getProductById(@PathVariable("id") long id);

    @PutExchange(value = "/product/{id}")
    ProductResponse updateProduct(@PathVariable("id") int id, @RequestBody ProductRequest product);

    @DeleteExchange("/product/{id}")
    void deleteProduct(@PathVariable("id") int id);

}
