package com.cloud.util.rsa;
/** 
 * @author tobber
 * @version 2017年10月26日
 */
public class RSAUtil {
	
	/**
	 * RSA加密 公钥
	 */
	private static String RSA_pulicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDHAVGSYsHoRCaiXVwbILAl7hb2zu3ICKXYPMTwx3pMy1639prxF+OG7vK6K6O0G5msxmkV3eyMlnfaiNtNKlJgRSCSXhcD2WaVdncqTwsRhugTr9eGaMZD9KGcI/1ldZ7NIqzp8z2IT7v7CViotBlGKbaoJtYOmdNXAasnn8e63wIDAQAB";
	
	/**
	 * RSA加密 私钥
	 */
	private static String RSA_privateKey="MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBAMcBUZJiwehEJqJdXBsgsCXuFvbO7cgIpdg8xPDHekzLXrf2mvEX44bu8roro7QbmazGaRXd7IyWd9qI200qUmBFIJJeFwPZZpV2dypPCxGG6BOv14ZoxkP0oZwj/WV1ns0irOnzPYhPu/sJWKi0GUYptqgm1g6Z01cBqyefx7rfAgMBAAECgYEAwLgzMet306IkM/rrls3aKHFhRdTxi1VsnnODWZYkhzgv20HznOXAj0zaPZbDa0mLUjm3TNjlGuUg3r7IefsxV5oTy6ZV415KCQpKDFHPOZQp6GSkwDzmZFfngAwOdEUBq56LIYmjp/VSoqYVuq7u1Sv/lev6e+kdzdM05hKIzBkCQQDsi1lU4WAnMZ40ZeqC4CbWNq8yjtxzqo36X1VzDz86Bq2nV6RVL5nR5gHoF9cWRyEZVxlgpKtcn+cw2IESpGaTAkEA11+Mg8GGjoYsPv5RLclUm1M1vvM8pw05qTuTl+ZZ4Y5/DYcSAi3zrtA3o70GsiXp/A+i7YK/66eqO/O/xpGeBQJBALT23r7bNU6G3TvFBKZfihaz+1C4jttGjhwPtzWxYnOoDE5L9ILfSdSVHLxNENYQULgsNFQFZrwT8TEjUYvLjlUCQQCQ4ecNz0csY5gqPDzx9ykiYCScstZPbncmoDxWiBBYytoviHiV3MG8DLgRPxzdBOJVvVMWQcnHxn+fWwbclqLdAkEA34DrCpwpoozBuHLaHjl0AOfePfz0ShRS1EeNf6oqwdBflL0c8Naykv9UMfwiBb9fWopo5k3f6U3+IydNdxAa7w==";
	

	/**
	 * 数据进行RSA加密再base64双重加密 默认publicKey
	 * @param date
	 * @throws Exception 
	 */
	public static String encryptByPublicKey(String data){
		try {
			return RSAcode.encryptBASE64(RSAcode.encryptBASE64(RSAcode.encryptByPublicKey(data.getBytes(), RSA_pulicKey)).getBytes()).replaceAll("\\s*|\t|\r|\n", "");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 *base64双重解密在RSA解密 默认privateKey
	 * @param date 解密字符串
	 * @throws Exception 
	 */
	public static String decryptByPrivateKey(String data){
		try {
			return new String(RSAcode.decryptByPrivateKey(RSAcode.decryptBASE64(new String(RSAcode.decryptBASE64(data))),RSA_privateKey)).replaceAll("\\s*|\t|\r|\n", "");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
