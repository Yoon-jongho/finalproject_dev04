package com.dev4.sunbbang.model;

import lombok.Data;

@Data
public class PageVO {

	private int pageNo = 0;
	private int pageSize = 5;
	private String condition = "";
	private String keyword = "";
}
