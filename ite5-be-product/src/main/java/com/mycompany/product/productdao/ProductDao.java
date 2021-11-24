package com.mycompany.product.productdao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.product.dto.Product;
import com.mycompany.product.dto.ProductColor;

@Mapper
public interface ProductDao {
	public int totalProductCount();
	public List<Product> getProducts();
	public List<Product> getProductByBno(Map<String, Object> map);
	public List<ProductColor> getProductByPid(String pid);
	public List<Product> getProductByCategory(Map<String, Object> map);
}
