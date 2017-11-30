package com.cloud.resume.util;

import com.cloud.util.CheckUtil;
import com.cloud.util.rsa.RSAUtil;

/** 
 * @author tobber
 * @version 2017年11月9日
 */
public class DetailUtil {
	
	/**
	 * 判断是否可以正常获取简历详情和联系方式
	 * @param resumeId
	 * @param searchId
	 * @param token
	 * @param r_t
	 * @return
	 */
	public static boolean isGetDetail(Integer resumeId,Integer searchId, String token,String r_t){
		if(CheckUtil.matcheVerify(resumeId+"", "[0-9]{1,10}") &&
				CheckUtil.matcheVerify(searchId+"", "[0-9]{1,10}") &&
				CheckUtil.matcheVerify(token+"", "[0-9]{1,50}") && r_t!=null && RSAUtil.decryptByPrivateKey(r_t).equals(resumeId+token)
		){
			return true;
		}else{
			return false;
		}
	}
}
