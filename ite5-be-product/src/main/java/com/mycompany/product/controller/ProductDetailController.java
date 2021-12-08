
package com.mycompany.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.product.dto.Product;
import com.mycompany.product.dto.ProductColor;
import com.mycompany.product.dto.ProductStock;
import com.mycompany.product.security.JWTUtil;
import com.mycompany.product.service.LikeService;
import com.mycompany.product.service.ProductDetailService;
import com.mycompany.product.service.ProductService;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductDetailController {

	@Resource
	private ProductDetailService productDetailService;
	@Resource
	private ProductService productService;
	@Resource
	private LikeService likeService;

	private static Map<String, Integer> visit = new HashMap();

	@RequestMapping("/detail/{pcid}")
	public Map<String, Object> displayProductDetail(@PathVariable String pcid, HttpServletRequest request) {
		log.info("실행");

		String mid = null;

		if (!request.getHeader("Authorization").equals("")) {
			String jwt = request.getHeader("Authorization").substring(7);
			Claims claims = JWTUtil.validateToken(jwt);
			mid = JWTUtil.getMid(claims);
		}

		String pid = pcid.split("_")[0];

		Product common = productDetailService.getProductCommonByPid(pid);

		if (mid != null) {
			int like = likeService.getLikeProduct(mid, common.getPid());
			if (like > 0) {
				common.setLike(true);
			}
		}

		List<ProductColor> colorinfo = productService.getProductColorByPid(pid);

		for (ProductColor ci : colorinfo) {
			List<ProductStock> ps = productDetailService.getProductStockByPcid(ci.getPcid());
			ci.setSizeinfo(ps);
		}

		if (visit.containsKey(pid)) { // 동시 접속자 수 증가
			visit.put(pid, visit.get(pid) + 1);
		} else {
			visit.put(pid, 1);
		}

		Map<String, Object> map = new HashMap();

		map.put("common", common);
		map.put("detail", colorinfo);
		map.put("visitor", visit.get(pid));
		
		log.info(map.toString());

		return map;
	}

	@RequestMapping("/exit/{pcid}")
	public void exitProductDetail(@PathVariable String pcid) {
		String pid = pcid.split("_")[0];

		if (visit.get(pid) == 1) { // 동시 접속자 수 감소
			visit.remove(pid);
		} else {
			visit.put(pid, visit.get(pid) - 1);
		}
	}
}
