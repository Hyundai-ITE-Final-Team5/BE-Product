
package com.mycompany.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.product.dto.Product;
import com.mycompany.product.dto.ProductColor;
import com.mycompany.product.dto.ProductStock;
import com.mycompany.product.service.ProductDetailService;
import com.mycompany.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductDetailController {

	@Resource
	private ProductDetailService productDetailService;
	@Resource
	private ProductService productService;
	
	private static Map<String, Integer> visit = new HashMap();
	
	@RequestMapping("/{pcid}")
	public Map<String, Object> displayProductDetail(@PathVariable String pcid) {
		log.info("실행");

		String pid = pcid.split("_")[0];
		
		Product common = productDetailService.getProductCommonByPid(pid);
		
		List<ProductColor> colorinfo = productService.getProductByPid(pid);
		
		for(ProductColor ci : colorinfo) {
			List<ProductStock> ps = productDetailService.getProductStockByPcid(ci.getPcid());
			ci.setSizeinfo(ps);
		}
		
		if(visit.containsKey(pid)) { //동시 접속자 수 증가
			visit.put(pid, visit.get(pid) + 1);
		}else {
			visit.put(pid, 1);
		}
		
		Map<String, Object> map = new HashMap();
		
		map.put("common", common);
		map.put("detail", colorinfo);
		
		return map;	
	}
	

	@RequestMapping("/exit/{pcid}")
	public void exitProductDetail(@PathVariable String pcid) {
		String pid = pcid.split("_")[0];
		
		if(visit.get(pid) == 1) { //동시 접속자 수 감소
			visit.remove(pid);
		}else {
			visit.put(pid, visit.get(pid) - 1);
		}
	}
}
