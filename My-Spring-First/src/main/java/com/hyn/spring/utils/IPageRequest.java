package com.hyn.spring.utils;

import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

/**
 * 
* @Title: IPageRequest.java
* @Package com.hyn.spring.utils
* @Description: TODO
* @author hyn  
* @date 2018年12月9日 下午3:19:09
* @version V1.0  
 */
public class IPageRequest {

	/**
	 * 排序方式
	 */
	String sort;

	/**
	 * 排序字段
	 */
	String sidx;
	/**
	 * 当前頁面
	 */
	Integer page=0;
	/**
	 * 页大小
	 */
	Integer size=15;

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public IPageRequest(String sort, String sidx, Integer page, Integer size) {
		super();
		this.sort = sort;
		this.sidx = sidx;
		this.page = page;
		this.size = size;
	}

	public IPageRequest() {
		super();
	}

	@Override
	public String toString() {
		return "IPageRequest [sort=" + sort + ", sidx=" + sidx + ", page=" + page + ", size=" + size + "]";
	}

	public AbstractPageRequest getRequestPage() {

		Sort mysort = null;
		if (StringUtils.isEmpty(this.sidx)) {
			this.sidx="moditime";
		}
		if (StringUtils.isEmpty(this.sort)) {
			mysort = new Sort(Sort.Direction.DESC, this.sidx);
		} else {
			if (Sort.Direction.DESC.name().equals(this.sort.toUpperCase())) {
				mysort = new Sort(Sort.Direction.DESC, this.sidx);
			} else {
				mysort = new Sort(Sort.Direction.ASC, this.sidx);
			}
		}
		
		int pagTmp = 0;
		int sizeTmp = 0;
		if (this.page == null || this.page < 1) {
			pagTmp = 0;
		} else {
			pagTmp = this.page - 1;
		}

		if (this.size==null || this.size <= 0) {
			sizeTmp = 15;
		}else
		{
			sizeTmp = this.size;
		}
		AbstractPageRequest pageable =null;
		if(mysort!=null)
		{
			pageable=PageRequest.of(pagTmp, sizeTmp, mysort);
		}
		return pageable;
	}
}
