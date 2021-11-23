
package com.mycompany.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.product.dto.Category;
import com.mycompany.product.dto.Pager;
import com.mycompany.product.dto.Product;
import com.mycompany.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/list")
public class ProductController {
	
	@Resource
	private ProductService productService;
	
	@RequestMapping("/brand/{bno}")
	public Map<String, Object> displayByBno(@PathVariable int bno, @RequestParam(defaultValue = "1") int pageNo) {
		log.info("실행");
		Map<String, Object> productList = new HashMap();
		
		int totalRows = productService.getTotalProductNum();
		Pager pager = new Pager(5, 5, totalRows, pageNo);
		
		List<Product> products = productService.getProductByBno(bno, pager);
		
		productList.put("products", products);
		
		return productList;
	}
	
	@RequestMapping("/category/{category}")
	public Map<String, Object> displayByCategory(@RequestParam(defaultValue = "1") int pageNo) {
		log.info("실행");
		Map<String, Object> productList = new HashMap();
		
		return productList;
	}
	
	
}




