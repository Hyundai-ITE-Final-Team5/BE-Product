package com.mycompany.product.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.product.dto.Product;
import com.mycompany.product.dto.ProductStock;
import com.mycompany.product.productdao.ProductDetailDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductDetailService {
	
	@Resource
	private ProductDetailDao productDetailDao;
	
	public Product getProductCommonByPid(String pid) {
		return productDetailDao.getProductCommonByPid(pid);
	}
	public List<ProductStock> getProductStockByPcid(String pcid) {
		return productDetailDao.getProductStockByPcid(pcid);
	}
	
}
