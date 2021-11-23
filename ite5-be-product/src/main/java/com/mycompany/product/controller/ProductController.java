
package com.mycompany.product.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/list")
public class ProductController {
	
	@Resource
	private ProductService productService;
	
	@RequestMapping("/{category}")
	public Map<String, String> displayByCategory() {
		log.info("실행");
		Map<String, String> productList = new HashMap<String, String>();
		
		
		return productList;
	}	
}




