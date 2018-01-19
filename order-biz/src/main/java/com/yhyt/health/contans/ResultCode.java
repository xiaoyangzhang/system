package com.yhyt.health.contans;

public enum ResultCode {  
//	验证码
	SMS("000","验证码：%s，有效期%s分钟!"),
    /** 成功 */  
    SUCCESS("200", "success"),  
    
    /** 没有登录 */  
    NOT_LOGIN("400", "没有登录"),  
      
    /** 发生异常 */  
    EXCEPTION("401", "发生异常"),  
      
    /** 系统错误 */  
    SYS_ERROR("402", "系统错误"),  
      
    /** 参数错误 */  
    PARAMS_ERROR("403", "参数错误 "), 
    
    /** 验证码错误 */  
    SECURITY_ERROR("405","验证码错误"),
      
    /** 不支持或已经废弃 */  
    NOT_SUPPORTED("410", "不支持或已经废弃"),  
      
    /** AuthCode错误 */  
    INVALID_AUTHCODE("444", "无效的AuthCode"),  
  
    /** 太频繁的调用 */  
    TOO_FREQUENT("445", "太频繁的调用"),  
    
    /** 用户名或密码错误 */
    UP_ERROR("10004","用户名或密码错误"),
    
    /** 用户名不存在*/
    NO_EXSTIS("10005","用户名不存在"),
    
    /** 已经注册过 */  
    REGIST_ALREADY("10006", "已经注册过了"), 
      
    /** 未知的错误 */  
    UNKNOWN_ERROR("499", "未知错误");  
	
	private String val;  
	private String msg;  
      
    private ResultCode(String value, String msg){  
        this.val = value;  
        this.msg = msg;  
    }  
      
    public String val() {  
        return val;  
    }  
  
    public String msg() {  
        return msg;  
    }  
      
   
} 
