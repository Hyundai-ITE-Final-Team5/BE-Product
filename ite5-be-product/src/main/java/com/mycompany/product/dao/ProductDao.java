package com.mycompany.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.product.dto.Product;

@Mapper
public interface ProductDao {
	public int totalProductCount();
	public List<Product> getProducts();
	public List<Product> getProductByBno(Map<String, Object> map);
}
