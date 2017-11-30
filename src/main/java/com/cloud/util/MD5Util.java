package com.cloud.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7",
        "8", "9", "a", "b", "c", "d", "e", "f"};

	 /**
	  * 将源字符串使用MD5加密为字节数组
	  * @param source
	  * @return
	  */
	 public static byte[] encode2bytes(String source) {
		 byte[] result = null;
		 try {
			 MessageDigest md = MessageDigest.getInstance("MD5");
			 md.reset();
			 md.update(source.getBytes("UTF-8"));
			 result = md.digest();
		 } catch (NoSuchAlgorithmException e) {
			 e.printStackTrace();
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }

		 return result;
	 }

	 /**
	  * 将源字符串使用MD5加密为32位16进制数
	  * @param source
	  * @return
	  */
	 public static String encode2hex(String source) {
		 byte[] data = encode2bytes(source);
		 StringBuffer hexString = new StringBuffer();
		 for (int i = 0; i < data.length; i++) {
			 String hex = Integer.toHexString(0xff & data[i]);
			 if (hex.length() == 1) {
				 hexString.append('0');
		 }
			 hexString.append(hex);
		 }

	  return hexString.toString();
	 }

	 public static byte[] MD5(byte[] msg)
	  {
	    MessageDigest messageDigest = null;
	    try {
	      messageDigest = MessageDigest.getInstance("MD5");

	      messageDigest.reset();

	      messageDigest.update(msg);
	    } catch (NoSuchAlgorithmException e) {
	    	e.printStackTrace();
	    }
	    return messageDigest.digest();
	  }
	 
	 /**
	  * 验证字符串是否匹配
	  * @param unknown 待验证的字符串
	  * @param okHex 使用MD5加密过的16进制字符串
	  * @return 匹配返回true，不匹配返回false
	  */
	 public static boolean validate(String unknown , String okHex) {
		 return okHex.equals(encode2hex(unknown));
	 }
	 /**
     * MD5编码
     * @param origin 原始字符串
     * @return 经过MD5加密之后的结果
     */
    public static String MD5Encode(String origin) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }
    
    /**
     * 转换字节数组为16进制字串
     * @param b 字节数组
     * @return 16进制字串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }

    /**
     * 转换byte到16进制
     * @param b 要转换的byte
     * @return 16进制格式
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
    
	public static void main(String[] args) {
		//byte[] b  = encode2bytes("tobber_guo@sina.com");
		/*String sfddsfString = encode2hex("123");
		System.out.println(sfddsfString);
		
		System.out.println(validate("tobber_guo@sina.com",sfddsfString));
		
//		byte[] a  = encode2bytes("tobber_guo@sina.com");
		String ss= encode2hex("tobber_guo@sina.com");
		System.out.println(Base64.encodeBase64(ss.getBytes()));*/
		System.out.println(encode2hex("timeStamp=148471537526&token=1b4c62afe8b155fd48a0ea61bfd67f8d&accountId=9865232"));
		
	}
		

}
