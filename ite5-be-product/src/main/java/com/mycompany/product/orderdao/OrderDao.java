package com.mycompany.product.orderdao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.product.dto.Pager;
import com.mycompany.product.dto.Product;

@Mapper
public interface OrderDao {
	public List<String> getBestProduct(int dpNum);
}
