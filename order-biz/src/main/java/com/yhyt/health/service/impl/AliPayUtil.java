package com.yhyt.health.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AliPayUtil {

	@Value("${alipay.APPID}")
	public static String APPID = "2017101109249326";
	
	@Value("${alipay.PRIVATEKEY}")
	public static String  PRIVATEKEY="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANpRcW5dHbjzo+SunaAP/shR6AsOk5NI/VlRdQVYQgJNWsKYgpHdcvXq5ZnmvhvFL+RU7bLOHYD2lHDqZq+HtQplmM2Xz+8t7ZuNR+5SfoWWo/yFwWppP8u/h5g2k51zF82mk5M8fOzinE4H952LtAWJ0gUGRKHmiq4DhvgxDul1AgMBAAECgYEAhXLbH1cT4AYt3I9QEpM0bkaCzJ9wXctz8bD1EvtL/hRCskh/J6JxrxiSmGyPMHJsP+oaEf4j0rznJ85gTlNpuXuKgOZnpsSCmMCSSeTmidx8vwEBblG/UN7IfKS9iDPbwMp5z4z9syycaMIv4N5+NuoGg9JN77uAkMOlHCs3MgECQQD89ysYYJSFAz0n/ITEM+IL5P2YNqb4vCO4QSYfUbhZY0QkHWOo34rsnRlgthlMHsAsOxGdNfCJOe77FcdQjkaVAkEA3O/gTNHCT7JUWvK/raCcUHLm6hAY7EX8I295zXj5ab0yykyB5I+AamhH6QWhtK/YmWURdzUSy94gRKdH1Em/YQJAHqTGeK6Cb1aEGwrw/l+Wm4BC5CSCt5D69sAVoSeiIFnxEDrrLrLdvmoNf/SvPdRfzTvel1/IYqc89vedbHm/vQJABFopVfoP1NgTd1Mu2XG/goow6E124DikteH9SUHnz+BtPrTxHhqBkhcJy5pTzCZmoubtyCZgf2mZwyB0dscZIQJBAN+89uUq2ctgpZtiqqyRL+6z3ma3e/oUcqsVSUYnN7JOyj8zaO+51NzY6PsR6OStujHVZ6TQq5X6ScZhspxtY1k=";


	@Value("${alipay.PUBLICKEY}")
	public static String PUBLICKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDaUXFuXR2486Pkrp2gD/7IUegLDpOTSP1ZUXUFWEICTVrCmIKR3XL16uWZ5r4bxS/kVO2yzh2A9pRw6mavh7UKZZjNl8/vLe2bjUfuUn6FlqP8hcFqaT/Lv4eYNpOdcxfNppOTPHzs4pxOB/edi7QFidIFBkSh5oquA4b4MQ7pdQIDAQAB";

	@Value("${alipay.ALIPAY_PUBLICKEY}")
	public static String ALIPAY_PUBLICKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";

	@Value("${alipay.SIGNTYPE}")
	public static String SIGNTYPE="RSA";
	
	public static void main(String... args) throws AlipayApiException{
//		String content = "app_id=2017101109249326&biz_content={\"timeout_express\":\"30m\",\"seller_id\":\"\",\"product_code\":\"QUICK_MSECURITY_PAY\",\"total_amount\":\"0.01\",\"subject\":\"1\",\"body\":\"我是测试数据\",\"out_trade_no\":\"TUR8TD5YRVEEWNE\"}&charset=utf-8&method=alipay.trade.app.pay&sign_type=RSA&timestamp=2017-10-13 14:05:20&version=1.0";
//		String content = "{\"code\":\"10000\",\"msg\":\"Success\",\"app_id\":\"2017101109249326\",\"auth_app_id\":\"2017101109249326\",\"charset\":\"utf-8\",\"timestamp\":\"2017-10-13 20:12:25\",\"total_amount\":\"0.01\",\"trade_no\":\"2017101321001004080207725015\",\"seller_id\":\"2088821323908671\",\"out_trade_no\":\"00007560\"}";
//		String sign = "lMykRJkVUZMaB4UxQxboeppfNfmNmbeWx+VRUWSec+cLfw8G2NejbBd5oVmQzPwO02tbKu1XdDiIeCec+MaLw52FvriEUBOKCNVoJ4tizuBvng\\/OSXKmmYjSBZjvHlAa5hI8noRJyZ21BNp0d2SKZFuyKJYxJfd1UuqA\\/w4NiEs=";
//		String signType = "RSA";
//		String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANpRcW5dHbjzo+SunaAP/shR6AsOk5NI/VlRdQVYQgJNWsKYgpHdcvXq5ZnmvhvFL+RU7bLOHYD2lHDqZq+HtQplmM2Xz+8t7ZuNR+5SfoWWo/yFwWppP8u/h5g2k51zF82mk5M8fOzinE4H952LtAWJ0gUGRKHmiq4DhvgxDul1AgMBAAECgYEAhXLbH1cT4AYt3I9QEpM0bkaCzJ9wXctz8bD1EvtL/hRCskh/J6JxrxiSmGyPMHJsP+oaEf4j0rznJ85gTlNpuXuKgOZnpsSCmMCSSeTmidx8vwEBblG/UN7IfKS9iDPbwMp5z4z9syycaMIv4N5+NuoGg9JN77uAkMOlHCs3MgECQQD89ysYYJSFAz0n/ITEM+IL5P2YNqb4vCO4QSYfUbhZY0QkHWOo34rsnRlgthlMHsAsOxGdNfCJOe77FcdQjkaVAkEA3O/gTNHCT7JUWvK/raCcUHLm6hAY7EX8I295zXj5ab0yykyB5I+AamhH6QWhtK/YmWURdzUSy94gRKdH1Em/YQJAHqTGeK6Cb1aEGwrw/l+Wm4BC5CSCt5D69sAVoSeiIFnxEDrrLrLdvmoNf/SvPdRfzTvel1/IYqc89vedbHm/vQJABFopVfoP1NgTd1Mu2XG/goow6E124DikteH9SUHnz+BtPrTxHhqBkhcJy5pTzCZmoubtyCZgf2mZwyB0dscZIQJBAN+89uUq2ctgpZtiqqyRL+6z3ma3e/oUcqsVSUYnN7JOyj8zaO+51NzY6PsR6OStujHVZ6TQq5X6ScZhspxtY1k=";
//
//		String ss = AlipaySignature.rsaSign(content,privateKey, "utf-8");
//		System.out.println(ss);
//		System.out.println(AlipaySignature.rsaCheck(content,sign,PUBLICKEY,"utf-8","RSA"));
		Map<String, String> map = new HashMap<String, String>();
		map.put("app_id", APPID);
		map.put("method", "post");
		map.put("format", "json");
		map.put("charset", "utf-8");
		map.put("sign_type", "RSA");
		map.put("timestamp", "2017-10-13 20:12:25");
		map.put("version", "1.0");
		map.put("notify_url", "http://apitest.cis95132.com:8800/order/alipay/notify");
		Map<String, String> m = new HashMap<String, String>();
		//m.put("body", body);
		m.put("subject", "111111");
		m.put("out_trade_no", "2222222");
		m.put("timeout_express", "30m");
		m.put("total_amount", "0.01");
		m.put("seller_id", "2088821323908671");
		m.put("product_code", "QUICK_MSECURITY_PAY");

		String content = JSONObject.toJSONString(m);
		map.put("biz_content", content);
		List<String> keys = new ArrayList<String>(map.keySet());

		Collections.sort(keys);
		String prestr = "";

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = map.get(key);

			if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}
		System.out.println(prestr);
	}
}
