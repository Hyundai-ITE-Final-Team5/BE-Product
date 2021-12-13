package com.mycompany.product.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.product.dto.Like;
import com.mycompany.product.memberdao.MemberDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LikeService {
	
	@Resource
	private MemberDao memberDao;
	
	public int getLikeProduct(String mid, String pid) {
		return memberDao.getLikeProduct(mid, pid);
	}
	
	public int addLike(Like like) {
		return memberDao.addLike(like);
	}
	
	public int delLike(String mid, String pid) {
		return memberDao.delLike(mid, pid);
	}
}
