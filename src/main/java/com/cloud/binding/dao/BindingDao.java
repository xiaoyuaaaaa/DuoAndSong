package com.cloud.binding.dao;

import java.util.List;
import java.util.Map;

public interface BindingDao {
	
	/**
	 * 检查账户是否被绑定
	 * @param parm
	 * @return
	 */
	public int checkBingdingStatus(Map<String,Object> parm);
	
	/**
	 * 账户绑定
	 * @param parm
	 * @return
	 */
	public String accountBinding(Map<String,Object> parm);
	
	/**
	 * 获取用户绑定状态 
	 * @param compUserId
	 * @return
	 */
	public List<Map<String,Object>> getUserBingdingStatus(String compUserId);
}
