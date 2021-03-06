package com.mycompany.product.productdao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.product.dto.Brand;
import com.mycompany.product.dto.Category;

@Mapper
public interface NavBarDao {
	public List<Category> getCategoryByDepth1(String depth1);
	public List<Brand> getBrands();
}