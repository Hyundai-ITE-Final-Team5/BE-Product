package com.mycompany.product.productdao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.product.dto.Product;
import com.mycompany.product.dto.ProductStock;

@Mapper
public interface ProductDetailDao {
	public Product getProductCommonByPid(String pid);
	public List<ProductStock> getProductStockByPcid(String pcid);
}
