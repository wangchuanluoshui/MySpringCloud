package com.hyn.spring.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hyn.spring.vo.SysStationVO;
import com.hyn.spring.vo.SysUserVO;

public interface ISysStationService {
	
	/**
	 * 增加
	 * @return
	 */
	String save(SysStationVO sysStationVO);
	
	/**
	 * 删除
	 * @return
	 */
	String delete(String id);
	
	/**
	 * 更新
	 * @return
	 */
	String update(SysStationVO sysStationVO);
	
	/**
	 * 分页查询
	 * @param userName
	 * @param pageable
	 * @return
	 */
	Page<SysStationVO> getSysStation(String pcCode,Pageable pageable);

}
