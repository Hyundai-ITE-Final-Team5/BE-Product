package com.mycompany.product.dto;

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
	Object colorinfo;
}
