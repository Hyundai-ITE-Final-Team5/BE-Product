
package com.mycompany.product.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.product.dto.Brand;
import com.mycompany.product.dto.Category;
import com.mycompany.product.service.NavBarService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/navbar")
public class NavBarController {

	@Resource
	private NavBarService navBarService;

	@RequestMapping("/categoryList")
	public Map<String, Object> categoryList(String depth1) {
		Map<String, Object> categoryMap = new HashMap();

		List<Category> depth1Category = navBarService.getCategoryByDepth1(depth1);

		Map<String, List<String>> depth2 = new HashMap();		

		for (Category cate : depth1Category) {
			List<String> list;

			if (depth2.containsKey(cate.getDepth2name())) {
				list = depth2.get(cate.getDepth2name());
			} else {
				list = new ArrayList();
			}
			list.add(cate.getDepth3name());
			depth2.put(cate.getDepth2name(), list);

		}
		
		categoryMap.put(depth1, depth2);

		return categoryMap;
	}

	@RequestMapping("/brandList")
	public List<Brand> brandList() {

		List<Brand> brands = navBarService.getBrands();

		return brands;
	}
}
