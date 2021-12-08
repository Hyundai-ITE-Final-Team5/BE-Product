package com.mycompany.product.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Product {
	String pid;
	String pname;
	String pnote;	
	String bname;
	int bno;
	int pstatus;
	boolean like;
	Date preleasedate;
	Object colorinfo;
}
