package com.mycompany.product.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.product.dto.Brand;
import com.mycompany.product.dto.Category;
import com.mycompany.product.productdao.NavBarDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NavBarService {
	
	@Resource
	private NavBarDao navBarDao;
	
	public List<Category> getCategoryByDepth1(String depth1){
		return navBarDao.getCategoryByDepth1(depth1);
	}
	
	public List<Brand> getBrands(){
		return navBarDao.getBrands();
	}
	
//	public List<Product> getProducts(Pager pager){
//		return productDao.getProducts(pager);
//	}
}
