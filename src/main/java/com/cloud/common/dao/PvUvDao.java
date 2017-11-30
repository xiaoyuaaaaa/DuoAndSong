package com.cloud.common.dao;

import java.util.Map;

/** 
 * @author tobber
 * @version 2017年11月29日
 */
public interface PvUvDao {
	/**
	 * 添加PV、UV
	 * @param parm
	 * @return
	 */
	public int addPvUvLogs(Map<String,Object> parm);
}
