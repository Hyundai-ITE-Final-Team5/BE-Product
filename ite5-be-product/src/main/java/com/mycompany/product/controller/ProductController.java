
package com.mycompany.product.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.product.dto.Category;
import com.mycompany.product.dto.Pager;
import com.mycompany.product.dto.Product;
import com.mycompany.product.dto.ProductColor;
import com.mycompany.product.security.JWTUtil;
import com.mycompany.product.service.LikeService;
import com.mycompany.product.service.ProductService;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/list")
public class ProductController {

	@Resource
	private ProductService productService;
	@Resource
	private LikeService likeService;

	

	@RequestMapping("/brand/{bno}")
	public List<Product> displayByBno(@PathVariable int bno, @RequestParam(defaultValue = "1") int pageNo,
			HttpServletRequest request) {
		log.info("실행");

		String mid = null;

		if (!request.getHeader("Authorization").equals("")) {
			String jwt = request.getHeader("Authorization").substring(7);
			Claims claims = JWTUtil.validateToken(jwt);
			mid = JWTUtil.getMid(claims);
		}

		int totalRows = productService.getTotalProductNum();
		Pager pager = new Pager(5, 5, totalRows, pageNo);

		List<Product> productList = productService.getProductByBno(bno, pager);

		if (mid != null) { // 로그인 되어 있으면
			for (Product pid : productList) {
				List<ProductColor> products = productService.getProductColorByPid(pid.getPid());
				pid.setColorinfo(products);

				// 좋아요 조회
				int like = likeService.getLikeProduct(mid, pid.getPid());
				if (like > 0) {
					pid.setLike(true);
				}
			}
		} else {
			for (Product pid : productList) {
				List<ProductColor> products = productService.getProductColorByPid(pid.getPid());
				pid.setColorinfo(products);
			}
		}

		return productList;
	}

	@RequestMapping("/category")
	public List<Product> displayByCategory(@RequestParam(defaultValue = "1") int pageNo, String depth1, String depth2,
			String depth3, HttpServletRequest request) {
		log.info("실행 pageNO: " + pageNo + " " + depth1 + depth2 + depth3);

		String mid = null;

		if (!request.getHeader("Authorization").equals("")) {
			String jwt = request.getHeader("Authorization").substring(7);
			Claims claims = JWTUtil.validateToken(jwt);
			mid = JWTUtil.getMid(claims);
		}

		int totalRows = productService.getTotalProductNum();
		Pager pager = new Pager(5, 5, totalRows, pageNo);

		Category category = new Category();
		category.setDepth1name(depth1);
		category.setDepth2name(depth2);
		category.setDepth3name(depth3);

		log.info(category.toString());

		List<Product> productList = productService.getProductByCategory(category, pager);

		if (mid != null) { // 로그인 되어 있으면
			for (Product pid : productList) {
				List<ProductColor> products = productService.getProductColorByPid(pid.getPid());
				pid.setColorinfo(products);

				int like = likeService.getLikeProduct(mid, pid.getPid());
				if (like > 0) {
					pid.setLike(true);
				}
			}
		} else {
			for (Product pid : productList) {
				List<ProductColor> products = productService.getProductColorByPid(pid.getPid());
				pid.setColorinfo(products);
			}
		}

		return productList;
	}

	@RequestMapping("/addlike/{pid}")
	public void addLike(@PathVariable String pid, HttpServletRequest request) {

		String mid = null;

		if (!request.getHeader("Authorization").equals("")) {
			String jwt = request.getHeader("Authorization").substring(7);
			Claims claims = JWTUtil.validateToken(jwt);
			mid = JWTUtil.getMid(claims);

			likeService.addLike(mid, pid);
		}else {
			throw new AuthorizationServiceException("로그인 정보가 없습니다.");
		}
	}

	@RequestMapping("/dellike/{pid}")
	public void delLike(@PathVariable String pid, HttpServletRequest request) {
		String mid = null;

		if (!request.getHeader("Authorization").equals("")) {
			String jwt = request.getHeader("Authorization").substring(7);
			Claims claims = JWTUtil.validateToken(jwt);
			mid = JWTUtil.getMid(claims);

			likeService.delLike(mid, pid);
		}else {
			throw new AuthorizationServiceException("로그인 정보가 없습니다.");
		}
	}

}
