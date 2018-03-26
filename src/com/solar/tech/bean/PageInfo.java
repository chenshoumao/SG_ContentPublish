package com.solar.tech.bean;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.hibernate.dialect.Ingres10Dialect;

public class PageInfo {
	private final int perPage = 10;//每页显示数量
	private long pageSum;    //总页数
	private int currentPage;//当前页数
	private long sumOfResult;//数据总数

	
	
	
	public long getSumOfResult() {
		return sumOfResult;
	}

	public void setSumOfResult(long sumOfResult) {
		this.sumOfResult = sumOfResult;
		this.pageSum =  sumOfResult > 0 ?((sumOfResult - 1)/ this.perPage  + 1 ) : 0;
	}

	public long getPageSum() {
		return this.pageSum;
	}

	public void setPageSum(int pageSum) {
		this.pageSum = pageSum;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPerPage() {
		return perPage;
	}
	
 

}
