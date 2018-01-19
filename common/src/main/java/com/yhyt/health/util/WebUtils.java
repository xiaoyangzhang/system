
package com.yhyt.health.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


public final class WebUtils {
	
  /**
   * 获取当前用户名
   * 
   * @return 当前用户名
   */
  public static String getCurrentUserName() {
	  try {
		  ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();  
		  HttpServletRequest request = attributes.getRequest(); 
		  return request.getSession().getAttribute("userName").toString();
		} catch (Exception e) {
			
		}
	  return "anno";
  }
  
  	/**
	 * 获取当前用户名
	 * 
	 * @return 当前用户名
	 */
	public static String getCurrentUserId() {
		try {
			  ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();  
			  HttpServletRequest request = attributes.getRequest(); 
			  return request.getSession().getAttribute("userId").toString();
			} catch (Exception e) {

			}
		return "1";
	}

  	/**
	 * 获取当前用户名
	 * 
	 * @return 当前用户名
	 */
	public static String getCurrentRealUserName() {
		try {
			  ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();  
			  HttpServletRequest request = attributes.getRequest(); 
			  return request.getSession().getAttribute("realName").toString();
			} catch (Exception e) {
				
			}
		return "游客";
	}


  /**
   * private con
   */
  private WebUtils() {
  }
}
