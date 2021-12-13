package com.mycompany.product.memberdao;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.product.dto.Like;

@Mapper
public interface MemberDao {
	public int getLikeProduct(String mid, String pid);
	public int addLike(Like like);
	public int delLike(String mid, String pid);
}
