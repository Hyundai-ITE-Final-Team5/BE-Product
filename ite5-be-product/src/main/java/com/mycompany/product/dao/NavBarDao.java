package com.mycompany.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.product.dto.Category;
import com.mycompany.product.vo.Brand;

@Mapper
public interface NavBarDao {
	public List<Category> getCategoryByDepth1(String depth1);
	public List<Brand> getBrands();
}
