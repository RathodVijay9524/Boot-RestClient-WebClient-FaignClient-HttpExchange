package com.vijay.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {


    private String categoryId;

 
    private String title;


    
    private String description;

    private String coverImage;

}
