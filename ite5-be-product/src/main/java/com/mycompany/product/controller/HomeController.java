package com.mycompany.product.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.product.dto.Product;
import com.mycompany.product.dto.ProductColor;
import com.mycompany.product.security.JWTUtil;
import com.mycompany.product.service.LikeService;
import com.mycompany.product.service.ProductService;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/")
public class HomeController {
	@RequestMapping("/")
	public String home() {
		log.info("실행");
		return "home";
	}

	@Resource
	private ProductService productService;
	@Resource
	private LikeService likeService;

	@RequestMapping("/bestproduct")
	public List<Product> bestProduct(HttpServletRequest request) {

		String mid = null;

		if (request.getHeader("Authorization") != null && !request.getHeader("Authorization").equals("")) {
			String jwt = request.getHeader("Authorization").substring(7);
			Claims claims = JWTUtil.validateToken(jwt);
			mid = JWTUtil.getMid(claims);
		}

		List<String> pidList = productService.getBestProduct(10);// 베스트상품 가져오는 갯수
		List<Product> productList = new ArrayList();

		if (mid != null) { // 로그인 되어 있으면
			for (String pid : pidList) {
				Product product = productService.getProductByPid(pid);

				int like = likeService.getLikeProduct(mid, pid);
				if (like > 0) {
					product.setLike(true);
				}

				List<ProductColor> products = productService.getProductColorByPid(pid);
				product.setColorinfo(products);

				productList.add(product);
			}
		} else {
			for (String pid : pidList) {
				Product product = productService.getProductByPid(pid);

				List<ProductColor> products = productService.getProductColorByPid(pid);
				product.setColorinfo(products);

				productList.add(product);
			}
		}

		return productList;
	}
	
	@RequestMapping("/newproduct")
	public List<Product> newProduct(HttpServletRequest request) {

		String mid = null;

		if (request.getHeader("Authorization") != null && !request.getHeader("Authorization").equals("")) {
			String jwt = request.getHeader("Authorization").substring(7);
			Claims claims = JWTUtil.validateToken(jwt);
			mid = JWTUtil.getMid(claims);
		}

		List<Product> productList = productService.getNewProduct(10);
		
		if(mid != null) {
			for(Product pd: productList) {
				pd.setColorinfo(productService.getProductColorByPid(pd.getPid()));
				int like = likeService.getLikeProduct(mid, pd.getPid());
				if (like > 0) {
					pd.setLike(true);
				}
			}
		}else {
			for(Product pd: productList) {
				pd.setColorinfo(productService.getProductColorByPid(pd.getPid()));
			}
		}
		

		return productList;
	}
}
