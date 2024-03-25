package com.vijay.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.vijay.client.CategoryClient;
import com.vijay.client.ProductClient;

@Configuration
public class WebClientConfig {
	
	@Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;


	@Bean
	public WebClient categoryWebClient() {
		return WebClient.builder().baseUrl("http://CATEGORY-SERVICE")
				.filter(filterFunction)
				.build();
	}

	@Bean
	public WebClient productWebClient() {
		return WebClient.builder().baseUrl("http://PRODUCT-SERVICE")
				.filter(filterFunction) 
				.build();
	}

	@Bean
	public CategoryClient employeeClient() {
		HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
				.builder(WebClientAdapter.forClient(categoryWebClient())).build();
		return httpServiceProxyFactory.createClient(CategoryClient.class);
	}

	@Bean
	public ProductClient inventoryClient() {
		return HttpServiceProxyFactory.builder(WebClientAdapter.forClient(productWebClient())).build()
				.createClient(ProductClient.class);
	}

}
