package com.iharbor.common.util;

import java.util.UUID;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * 
 * @ClassName: EncryptionUtils 
 * @Description: 加密工具类
 * @author Fred
 * @version 1.0
 * @date 2012-1-9
 */
@Service
public class EncryptionUtils {
  
  /**
   *  使用简单的MD5加密方式   
   * @param password
   * @return String
   */
  public static String md5(String password){
    Md5PasswordEncoder md5 = new Md5PasswordEncoder();   
    md5.setEncodeHashAsBase64(false);
    String pwd=md5.encodePassword(password, null);
    return pwd;
  }
  
  /**
   * 使用MD5再加全局加密盐加密的方式加密    
   * @param password
   * @param salt
   * @return String
   */
  public static String md5_SystemWideSaltSource (String password,String salt){
    Md5PasswordEncoder md5 = new Md5PasswordEncoder();   
    md5.setEncodeHashAsBase64(false);    
    String pwd=md5.encodePassword(password, salt);
    return pwd;
  }
  
  /**
   * 使用SHA-256的哈希算法(SHA)加密   
   * @param password
   * @return String 
   */
  public static String sha_256(String password){
    ShaPasswordEncoder sha = new ShaPasswordEncoder(256);   
    sha.setEncodeHashAsBase64(false);    
    String pwd = sha.encodePassword(password, null);
    return pwd;
  }
  
 /**
  * 使用SHA-256的哈希算法(SHA)加密 加盐  
  * @param password
  * @param sh
  * @return String
  */
  public static String sha_SHA_256(String password,String sh){
    ShaPasswordEncoder sha = new ShaPasswordEncoder(256);   
    sha.setEncodeHashAsBase64(false);    
    String pwd = sha.encodePassword(password, sh);
    return pwd;
  }
  
  /**
   * 二进制转十六进制字符串
   * @param bytes
   * @return String
   */
  public static String byte2hex(byte[] bytes) {
      StringBuilder sign = new StringBuilder();
      for (int i = 0; i < bytes.length; i++) {
          String hex = Integer.toHexString(bytes[i] & 0xFF);
          if (hex.length() == 1) {
              sign.append("0");
          }
          sign.append(hex.toUpperCase());
      }
      return sign.toString();
  }
  
  /**
   * 获取一组36位的UUID
   * @return String
   */
  public static String getUUID() {
      UUID uuid = UUID.randomUUID();
      return uuid.toString().toUpperCase();
  }
  
  public static void main(String[] args){
	//  System.out.println(sha_256("测试"));
	//  System.out.println("30ce6eb647692a8a6ed30580021b8d772cfd128a9b664f965cdc17c0e6597543".length());
	// System.out.println(md5_SystemWideSaltSource("000000", "admin"));
	  System.out.println(sha_SHA_256("000000","aa"));
  }
}
