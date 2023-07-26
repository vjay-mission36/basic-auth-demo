package com.mission48.basicsuth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicAuthController {

    @GetMapping("/hello")
    public String sayHello(){
        return "hello basic auth...";
    }

    @GetMapping("/product")
    @PreAuthorize(value = "hasAuthority('ROLE_READ')")
    public String getProductInfo(){
        return "trying to get the secured product information!!!";
    }


    @GetMapping("/product/all")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public String getAllProducts(){
        return "Trying to get all the product information !!!!!";
    }
}
