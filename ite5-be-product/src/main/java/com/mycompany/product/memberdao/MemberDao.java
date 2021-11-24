package com.mycompany.product.memberdao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
	public int getLikeProduct(String mid, String pid);
	public int addLike(String mid, String pid);
	public int delLike(String mid, String pid);
}
