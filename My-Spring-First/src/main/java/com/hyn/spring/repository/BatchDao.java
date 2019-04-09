package com.hyn.spring.repository;

import java.util.List;

public interface BatchDao<T> {
	public void batchInsert(List<T> list);
}
