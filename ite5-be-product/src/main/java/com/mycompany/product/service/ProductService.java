package com.mycompany.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.product.dto.Category;
import com.mycompany.product.dto.Pager;
import com.mycompany.product.dto.Product;
import com.mycompany.product.dto.ProductColor;
import com.mycompany.product.orderdao.OrderDao;
import com.mycompany.product.productdao.ProductDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
	
	@Resource
	private ProductDao productDao;
	@Resource
	private OrderDao orderDao;
	
	public int getTotalProductNum(){
		return productDao.totalProductCount();
	}
	
	public List<Product> getProductByBno(int bno, Pager pager){
		Map<String, Object> map = new HashMap();
		map.put("bno", bno);
		map.put("pager", pager);
		return productDao.getProductByBno(map);
	}
	
	public List<ProductColor> getProductColorByPid(String pid){
		return productDao.getProductColorByPid(pid);
	}
	
	public List<Product> getProductByCategory(Category category, Pager pager){
		log.info("실행");
		Map<String, Object> map = new HashMap();
		map.put("category", category);
		map.put("pager", pager);
		return productDao.getProductByCategory(map);
	}
	
	public Product getProductByPid(String pid) {
		return productDao.getProductByPid(pid);
	}
	
	public List<String> getBestProduct(int dpNum){
		return orderDao.getBestProduct(dpNum);
	}
	
	public List<Product> getNewProduct(int dpNum){
		return productDao.getNewProduct(dpNum);
	}
}
