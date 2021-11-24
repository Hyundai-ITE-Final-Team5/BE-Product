
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
import com.mycompany.product.dto.ProductColor;
import com.mycompany.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/list")
public class ProductController {

	@Resource
	private ProductService productService;

	@RequestMapping("/brand/{bno}")
	public List<Product> displayByBno(@PathVariable int bno, @RequestParam(defaultValue = "1") int pageNo) {
		log.info("실행");

		int totalRows = productService.getTotalProductNum();
		Pager pager = new Pager(5, 5, totalRows, pageNo);

		List<Product> productList = productService.getProductByBno(bno, pager);

		for (Product pid : productList) {
			List<ProductColor> products = productService.getProductByPid(pid.getPid());
			pid.setColorinfo(products);
		}

		return productList;
	}

	@RequestMapping("/category")
	public List<Product> displayByCategory(@RequestParam(defaultValue = "1") int pageNo, String depth1, String depth2,
			String depth3) {
		log.info("실행");

		int totalRows = productService.getTotalProductNum();
		Pager pager = new Pager(5, 5, totalRows, pageNo);

		Category category = new Category();
		category.setDepth1name(depth1);
		category.setDepth2name(depth2);
		category.setDepth3name(depth3);

		log.info(category.toString());

		List<Product> productList = productService.getProductByCategory(category, pager);

		for (Product pid : productList) {
			List<ProductColor> products = productService.getProductByPid(pid.getPid());
			pid.setColorinfo(products);
		}

		return productList;
	}

//	@PostMapping("/like")
//	public Map<String, String> searchlike(HttpServletRequest request) {
//		String jwt = request.getHeader("Authorization").substring(7);
//		Claims claims = JWTUtil.validateToken(jwt);
//		String mid = JWTUtil.getMid(claims);
//
//		List<Likes> likeList = memberService.getLikeList(mid);
//
//		Map<String, String> map = new HashMap<>();
//		return map;
//	}

}
