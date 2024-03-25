package com.vijay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
	
	
	private String categoryId;
    private String name;
    private long price;
    private long quantity;

}
