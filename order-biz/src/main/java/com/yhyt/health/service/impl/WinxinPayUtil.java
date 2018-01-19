package com.yhyt.health.service.impl;

import com.yhyt.health.util.MD5Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class WinxinPayUtil {
	
	/**
	 * 微信统一下单接口
	 */
	public static String Pre_Order = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	/**
	 * 查单接口
	 */
	public static String Query_Order = "https://api.mch.weixin.qq.com/pay/orderquery";
	
	/**
	 * 微信统一退款接口
	 */
	public static String Pre_RefundOrder = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	
	/**
	 * APPID
	 */
	@Value("${weixinpay.APPID}")
//	public static String APPID = "wx0bc3237846552a5a";
	public static String APPID = "wxdb479b4baf251e33";

	/**
	 * 商户号id
	 */
	@Value("${weixinpay.MCHID}")
	public static String MCHID = "1490482872";

	/**
	 * 异步通知URL
	 */
	@Value("${weixinpay.NOTIFYURL}")
	public static String NOTIFYURL = "https://test.cis95132.com:8000/system/wxpay/notify";

	/**
	 * key为商户平台设置的密钥key
	 */
	@Value("${weixinpay.ENCODINGAESKEY}")
	public static String ENCODINGAESKEY = "466a358a57f75d0eb06b1c8da5920a16";

	/**
	 * 终端设备号(门店号或收银设备ID)，默认请传"WEB"
	 */
	public static String DEVICEINFO = "WEB";

	public static String SIGNTYPE = "MD5";


	/**
	 * 获取随机数
	 * 
	 * @return
	 */

	public static String getNonceStr() {
		String uuid = UUID.randomUUID().toString().replaceAll("\\-", "");
		return MD5Util.MD5(uuid).substring(0, 32);
	}

	public static String getSignContent(Map<String, String> params) {
		if (params == null) {
			return null;
		}
		params.remove("sign");
		StringBuffer content = new StringBuffer();
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key).trim();
			content.append((i == 0 ? "" : "&") + key + "=" + value);
		}
		return content.toString();
	}
	
	public static void main(String... args){
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", "wx0bc3237846552a5a");
		params.put("mch_id", "1480131802");
		params.put("nonce_str", "84D0D66993F9A3E346B74EBDBAEAEE34");
		params.put("out_trade_no", "2");
		params.put("device_info", "WEB");
		params.put("total_fee", "100");
		params.put("notify_url", "http://apitest.cis95132.com:8800/order/weixinpay/notify");
		params.put("trade_type", "APP");
		params.put("body", "咨询服务卡");
		String signStr = getSignContent(params) + "&key=mQmZ9XMkC1x0qDiwvXnStxDWoC99J5rB" ;
		System.out.println(signStr);
		System.out.println("appid=wx0bc3237846552a5a&body=咨询服务卡&device_info=WEB&mch_id=1480131802&nonce_str=84D0D66993F9A3E346B74EBDBAEAEE34&notify_url=http://apitest.cis95132.com:8800/order/weixinpay/notify&out_trade_no=2&total_fee=100&trade_type=APP&key=mQmZ9XMkC1x0qDiwvXnStxDWoC99J5rB");
		String sign = MD5Util.MD5(signStr,"UTF-8").toUpperCase();
		System.out.println(sign);
		sign = MD5Util.MD5(signStr,"UTF-8").toUpperCase();
		System.out.println(sign);
		signStr = "appid=wx0bc3237846552a5a&body=咨询服务卡&device_info=WEB&mch_id=1480131802&nonce_str=84D0D66993F9A3E346B74EBDBAEAEE34&notify_url=http://apitest.cis95132.com:8800/order/weixinpay/notify&out_trade_no=2&total_fee=100&trade_type=APP&key=mQmZ9XMkC1x0qDiwvXnStxDWoC99J5rB";
		sign = MD5Util.MD5(signStr,"UTF-8").toUpperCase();
		System.out.println(sign);
		System.out.println("192006250b4c09247ec02edce69f6a2d".length());
	}
}
