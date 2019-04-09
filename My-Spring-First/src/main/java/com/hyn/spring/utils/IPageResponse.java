package com.hyn.spring.utils;

import java.util.List;

import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * 
* @Title: IPageResponse.java
* @Package com.hyn.spring.utils
* @Description: TODO
* @author hyn  
* @date 2018年12月9日 下午2:48:16
* @version V1.0  
 */
public class IPageResponse<T> extends PageImpl<T> implements Page<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IPageResponse(List<T> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}

	/**
	* 重写当前页，将当前页加1返回前台，spring data jpa起始页0加1后返回前台
	* @return
	*/
	@Override
	public int getNumber() {
		return super.getNumber() + 1;
	}
	
	@Override
	public Pageable getPageable() {
		return null;
	}

	public static Page<?> getResponsePage(Page<?> page, AbstractPageRequest pageRequest) {
		if (page == null || page.getContent() ==null || page.getContent().size()==0) {
			return null;
		} else {
			return new IPageResponse<>(page.getContent(), pageRequest, page.getTotalElements());
		}
	}

}

