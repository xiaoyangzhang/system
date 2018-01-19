package com.yhyt.health.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.client.http.HttpResponse;
import com.yhyt.health.HealthConstants;
import com.yhyt.health.OrderConstants;
import com.yhyt.health.dao.OrderMapper;
import com.yhyt.health.dao.PayDetailMapper;
import com.yhyt.health.model.Order;
import com.yhyt.health.model.PayDetail;
import com.yhyt.health.service.OrderService;
import com.yhyt.health.service.PayService;
import com.yhyt.health.service.impl.WinxinPayUtil;
import com.yhyt.health.util.BusinessException;
import com.yhyt.health.util.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "", description = "微信支付接口")
@RestController
@RequestMapping("/wxpay")
@ConfigurationProperties(prefix = "weixinpay")
@ControllerAdvice
public class WeixinPayController {

	private static Logger logger = LoggerFactory.getLogger(WeixinPayController.class);

	/**
	 * 微信统一下单接口
	 */
	private static String Pre_Order = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	/**
	 * 查单接口
	 */
	private static String Query_Order = "https://api.mch.weixin.qq.com/pay/orderquery";
	
	/**
	 * 微信统一退款接口
	 */
	public static String Pre_RefundOrder = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	
	/**
	 * APPID
	 */
//	private String APPID = "wxd930ea5d5a258f4f";
	private String APPID = "wxdb479b4baf251e33";

	/**
	 * 商户号id
	 */
	private String MCHID = "1490482872";

	/**
	 * 异步通知URL
	 */
//	private String NOTIFYURL = "http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php";
	private String NOTIFYURL = "http://apitest.cis95132.com:8800/system/wxpay/notify";

	/**
	 * key为商户平台设置的密钥key
	 */
//	private String ENCODINGAESKEY = "192006250b4c09247ec02edce69f6a2d";
//	private String ENCODINGAESKEY = "mQmZ9XMkC1x0qDiwvXnStxDWoC99J5rB";
	private String ENCODINGAESKEY = "466a358a57f75d0eb06b1c8da5920a16";

	/**
	 * 终端设备号(门店号或收银设备ID)，默认请传"WEB"
	 */
	private String DEVICEINFO = "WEB";

	private String SIGNTYPE = "MD5";

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private PayService payService;
	@Autowired
	private PayDetailMapper payDetailMapper;
	@Autowired
	private OrderService orderService;

	@ApiOperation(value="app下单接口", notes="支付")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "orderId", value = "订单主键id",paramType="path", required = true, dataType = "Long")
    })
	@GetMapping("/pay/{orderId}")
	public Map<String, String> sign(@PathVariable("orderId") Long id) {
		Order order = this.orderMapper.selectByPrimaryKey(id);
		String payOrderId = order.getOrderNo().replaceAll("\\.", "");
		if (order.getPayType()!=null && 2!=order.getPayType()){
			Map<String, Object> params = new HashMap<>();
			params.put("channel","alipay");
			params.put("orderId",payOrderId);
			payDetailMapper.deletePayDetail(params);
		}
		String nonceStr = WinxinPayUtil.getNonceStr();
		String xml = "<xml><appid>%1$s</appid><mch_id>%2$s</mch_id><device_info>%3$s</device_info><body>%4$s</body><nonce_str>%5$s</nonce_str>"
				+ "<out_trade_no>%6$s</out_trade_no><total_fee>%7$s</total_fee><notify_url>%8$s</notify_url><trade_type>%9$s</trade_type><sign>%10$s</sign></xml>";
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", APPID);
		params.put("mch_id", MCHID);
		params.put("device_info", DEVICEINFO);
		params.put("nonce_str", nonceStr);
		params.put("out_trade_no", payOrderId);
		//TODO 默认订单一分钱
		BigDecimal totalFee = order.getPrice().multiply(new BigDecimal(100)).setScale(0, java.math.BigDecimal.ROUND_HALF_EVEN);
//		BigDecimal totalFee = new BigDecimal(order.getPrice()*100).setScale(0, java.math.BigDecimal.ROUND_HALF_EVEN);
		params.put("total_fee", totalFee.toString());
		params.put("notify_url", NOTIFYURL);
		params.put("trade_type", "APP");
		params.put("body", "咨询服务卡");
		String signStr = WinxinPayUtil.getSignContent(params) + "&key=" + ENCODINGAESKEY;
		logger.info("微信下单签名串:"+signStr);
		String sign = MD5Util.MD5(signStr,"UTF-8").toUpperCase();
		logger.info("支付接口生成的 sign,sign:",sign);
		String requesetBody = String.format(xml, APPID, MCHID, DEVICEINFO, "咨询服务卡", nonceStr, payOrderId,
				totalFee.toString(), NOTIFYURL, "APP", sign);
		// 微信下单 日志记录
		PayDetail payDetail = null;
		String response = "";
		try {
			payDetail = new PayDetail();
			payDetail.setParams("支付前");
			payDetail.setOrderid(order.getOrderNo());
			payDetail.setPayorderid(payOrderId);
			payDetail.setChannel("weixinpay");
			payDetail.setMoney(totalFee.divide(new BigDecimal(100)));
			payDetail.setNotifyUrl(NOTIFYURL);
			payDetail.setState(1);//已发起
			payDetail.setSend(requesetBody);
			this.payService.edit(payDetail);
			response = this.payService.instaceXml(Pre_Order, requesetBody, String.class);
		} catch (Exception e) {
			logger.error("日志记录异常("+requesetBody+"):" ,e);
			throw new BusinessException(HealthConstants.EXCEPTION_OUTINTEFACE, e.getMessage());
		}
		Document document = null;
		Element root = null;
		try {
			document = DocumentHelper.parseText(response);
			root = document.getRootElement();
		} catch (DocumentException e) {
			logger.error("下单返回结果异常:" + e.getMessage());
			throw new BusinessException(OrderConstants.EXCEPTION_OUTINTEFACE, "微信支付下单接口异常");
		}
		String returnCode = root.element("return_code").getTextTrim();
		if ("SUCCESS".equals(returnCode)) {
			String result_code = root.element("result_code").getTextTrim();
			if ("SUCCESS".equals(result_code)) {
				String appid = root.element("appid").getTextTrim();
//				String device_info = root.element("device_info").getTextTrim();
				String mch_id = root.element("mch_id").getTextTrim();
				String nonce_str = root.element("nonce_str").getTextTrim();
				String returnSign = root.element("sign").getTextTrim();
				String prepay_id = root.element("prepay_id").getTextTrim();
				String trade_type = root.element("trade_type").getTextTrim();
				params.clear();
				params.put("appid", APPID);
				params.put("partnerid", MCHID);
				params.put("prepayid", prepay_id);
				params.put("noncestr", WinxinPayUtil.getNonceStr());
//				params.put("noncestr", nonce_str);
				params.put("package", "Sign=WXPay");
				params.put("timestamp", String.valueOf((new Date().getTime()) / 1000).substring(0, 10));
				signStr = WinxinPayUtil.getSignContent(params) + "&key=" + ENCODINGAESKEY;
				logger.info("统一下单签名串，signStr:{}",signStr);
				sign = MD5Util.MD5(signStr,"UTF-8").toUpperCase();
				params.put("sign", sign);
				try {
					// 微信支付app返回 日志记录
					payDetail = new PayDetail();
					payDetail.setParams(response);
					payDetail.setOrderid(order.getOrderNo());
					payDetail.setPayorderid(payOrderId);
					payDetail.setChannel("weixinpay");
					payDetail.setMoney(totalFee.divide(new BigDecimal(100)));
					payDetail.setNotifyUrl(NOTIFYURL);
					payDetail.setState(2);//已支付
					payDetail.setTranceid(prepay_id);
					payDetail.setSend(params.toString());
					this.payService.edit(payDetail);
					orderMapper.updateByOrderNo(order.getOrderNo(),(byte)2);
				} catch (Exception e) {
					logger.error("日志记录异常("+JSONObject.toJSONString(payDetail)+"):" + e.getMessage());
				}
				logger.info("调起支付接口参数，params:{}", JSON.toJSONString(params));
				return params;
			} else {
				String err_code = root.element("err_code").getTextTrim();
				String err_code_des = root.element("err_code_des").getTextTrim();
				throw new BusinessException(OrderConstants.EXCEPTION_PAY_RETURN, err_code_des);
			}
		} else {
			String returnMsg = root.element("return_msg").getTextTrim();
			throw new BusinessException(OrderConstants.EXCEPTION_PAY_CONNECT, returnMsg);
		}
	}

	@ApiOperation(value="支付结果校验", notes="支付")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "orderId", value = "订单主键id",paramType="path", required = true, dataType = "Long")
    })
	@GetMapping("/validate/{orderId}")
	public Order validate(@PathVariable("orderId") Long id){
		Order order = this.orderMapper.selectByPrimaryKey(id);
		if(null==order.getState()||order.getState()==1){
			Map<String,String> result = this.orderquery(order.getId());
			if("SUCCESS".equals(result.get("result"))){
				//TODO
//				orderService.updateOrderState(id,(byte)8);
				String out_trade_no = result.get("out_trade_no");
				PayDetail payDetail = new PayDetail();
				payDetail.setState(2);
				payDetail.setChannel("weixinpay");
				payDetail.setOrderid(out_trade_no.replaceAll("\\.",""));
				payDetail.setUpdateTime(new Date());
				payService.updatePayDetail(payDetail);

			}
		}
		return order;
	}
	
	@ApiOperation(value="查询订单", notes="支付")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "orderId", value = "订单主键id",paramType="path", required = true, dataType = "Long")

	})
	/**
	 * SUCCESS—支付成功
		REFUND—转入退款
		NOTPAY—未支付
		CLOSED—已关闭
		REVOKED—已撤销（刷卡支付）
		USERPAYING--用户支付中
		PAYERROR--支付失败(其他原因，如银行返回失败)
	 * @param id
	 */
	@GetMapping("/orderquery/{orderId}")
	public Map<String,String> orderquery(@PathVariable("orderId") Long id){
		Order order = this.orderMapper.selectByPrimaryKey(id);
		HashMap<String, Object> map = new HashMap<>();
		//订单查询参数
		HashMap<String, String> queryParams = new HashMap<>();
		map.put("orderId",order.getOrderNo());
		List<PayDetail> payDetailList = payDetailMapper.findPersistableList(map);
		logger.info("支付详细信息，info:{}",JSON.toJSONString(payDetailList));
		//签名生成参数
		Map<String, String> params = new HashMap<String, String>();
		queryParams.put("appid", APPID);
		queryParams.put("mch_id", MCHID);
		String nonceStr = WinxinPayUtil.getNonceStr();
		queryParams.put("nonce_str", nonceStr);
		queryParams.put("out_trade_no", order.getOrderNo().replaceAll("\\.", ""));
		params.putAll(queryParams);
//		BigDecimal money = new BigDecimal(order.getPrice()*100).setScale(0, java.math.BigDecimal.ROUND_HALF_EVEN);
		BigDecimal money = order.getPrice().multiply(new BigDecimal(100)).setScale(0, java.math.BigDecimal.ROUND_HALF_EVEN);
		String signStr = WinxinPayUtil.getSignContent(queryParams) + "&key=" + ENCODINGAESKEY;
		logger.info("订单查询签名串，signStr:{}",signStr);
		String sign2 = MD5Util.MD5(signStr,"UTF-8").toUpperCase();
		logger.info("查询订单生成的 sign,sign:",sign2);

		queryParams.put("sign", sign2);
		String xml = "<xml><appid>%1$s</appid><mch_id>%2$s</mch_id><nonce_str>%3$s</nonce_str><out_trade_no>%4$s</out_trade_no><sign>%5$s</sign></xml>";
		String requesetBody = String.format(xml, APPID, MCHID, nonceStr, order.getOrderNo().replaceAll("\\.", ""),
				 sign2);
		String response = this.payService.instaceXml(Query_Order, requesetBody, String.class);
		Document document = null;
		Element root = null;
		try {
			document = DocumentHelper.parseText(response);
			root = document.getRootElement();
		} catch (DocumentException e) {
			logger.info("下单返回结果异常:" , e);
			throw new BusinessException(OrderConstants.EXCEPTION_OUTINTEFACE, "微信支付下单接口异常");
		}
		String returnCode = root.element("return_code").getTextTrim();
		if ("SUCCESS".equals(returnCode)) {
			String result_code = root.element("result_code").getTextTrim();
			if ("SUCCESS".equals(result_code)) {
				//TODO 返回结校验
//				String appid = root.element("appid").getTextTrim();
//				String mch_id = root.element("mch_id").getTextTrim();
//				String nonce_str = root.element("nonce_str").getTextTrim();
//				String returnSign = root.element("sign").getTextTrim();
//				String prepay_id = root.element("prepay_id").getTextTrim();
//				String trade_type = root.element("trade_type").getTextTrim();
//				params.clear();
//				params.put("appid", APPID);
//				params.put("partnerid", MCHID);
//				params.put("prepayid", prepay_id);
//				params.put("noncestr", this.getNonceStr());
//				params.put("package", "Sign=WXPay");
//				params.put("timestamp", String.valueOf((new Date().getTime()) / 1000).substring(0, 10));
//				signStr = getSignContent(params) + "&key=" + ENCODINGAESKEY;
//				sign = MD5Util.MD5(signStr).toUpperCase();
//				params.put("sign", sign);
				String trade_state = root.element("trade_state").getTextTrim();
				String out_trade_no = root.element("out_trade_no").getTextTrim();
				String transaction_id = root.element("transaction_id").getTextTrim();
				String time_end = root.element("time_end").getTextTrim();
//				String trade_state_desc = root.element("trade_state_desc").getTextTrim();
				Map<String,String> result = new HashMap<String,String>();
				result.put("result", trade_state);
				result.put("tranceid", transaction_id);
				result.put("paytime", time_end);
				result.put("out_trade_no", out_trade_no);
//				result.put("desc", trade_state_desc);
				return result;
			} else {
				String err_code = root.element("err_code").getTextTrim();
				String err_code_des = root.element("err_code_des").getTextTrim();
				throw new BusinessException(OrderConstants.EXCEPTION_PAY_RETURN, err_code_des);
			}
		}else{
			logger.info("下单失败，error:{}",response);
			throw new BusinessException(OrderConstants.EXCEPTION_PAY_CONNECT, root.element("return_msg").getTextTrim());
		}
	}
	
	@ApiOperation(value="异步回调", notes="支付")
	@PostMapping("/notify")
	public void notify(@RequestBody String xml ,HttpResponse response){
		logger.info("微信支付回调，result:{}",JSON.toJSONString(xml));
        Document document = null;
        Element root = null;
        try {
            document = DocumentHelper.parseText(xml);
            root = document.getRootElement();
        } catch (DocumentException e) {
            logger.error("支付回调结果异常:" , e);
            throw new BusinessException(OrderConstants.EXCEPTION_OUTINTEFACE, "微信支付回调接口异常");
        }
		String returnCode = root.element("return_code").getTextTrim();
		Element out_refund_no = root.element("out_refund_no");//.getTextTrim();
		if (out_refund_no == null || StringUtils.isEmpty(out_refund_no.getTextTrim())){

			if ("SUCCESS".equals(returnCode)) {
				String result_code = root.element("result_code").getTextTrim();
				if ("SUCCESS".equals(result_code)) {
					String out_trade_no = root.element("out_trade_no").getTextTrim();
					PayDetail payDetail = new PayDetail();
					payDetail.setState(2);
					payDetail.setChannel("weixinpay");
					payDetail.setOrderid(out_trade_no.replaceAll("\\.",""));
					payService.updatePayDetail(payDetail);
				}
			}
		}
//		try {
//			((HttpServletResponse)response).getWriter().write("success");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	/*@ApiOperation(value = "取消支付",notes = "支付")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderId", value = "订单主键id",paramType="path", required = true, dataType = "Long")

	})
	*//**
	 * 选取微信支付，输入密码前修改为支付宝支付
	 *//*
	@PatchMapping("/cancel/{orderId}")
	public AppResult cancelPay(@PathVariable("orderId")Long orderId){
		AppResult appResult = new AppResult();
		orderService.cancelWXPay(orderId);
		appResult.getStatus().setCode("200");
		appResult.getStatus().setMessage("取消成功");
		return appResult;
	}*/
	
	public void refund(PayDetail payDetail){
		BigDecimal totalFee = payDetail.getMoney().multiply(new BigDecimal(100)).setScale(0, java.math.BigDecimal.ROUND_HALF_EVEN);
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", APPID);
		params.put("mch_id", MCHID);
		String nonceStr = WinxinPayUtil.getNonceStr();
		params.put("nonce_str", WinxinPayUtil.getNonceStr());
		params.put("out_trade_no", payDetail.getPayorderid());
		params.put("out_refund_no", payDetail.getPayorderid());
		params.put("refund_fee", totalFee.toString());
		params.put("total_fee", totalFee.toString());
		String signStr = WinxinPayUtil.getSignContent(params) + "&key=" + ENCODINGAESKEY;
		logger.info("微信下单签名串:"+signStr);
		String sign = MD5Util.MD5(signStr,"UTF-8").toUpperCase();
		params.put("sign", sign);
		String xml = "<xml><appid>%1$s</appid><mch_id>%2$s</mch_id><nonce_str>%3$s</nonce_str><out_refund_no>%4$s</out_refund_no><out_trade_no>%5$s</out_trade_no><refund_fee>%6$s</refund_fee><total_fee>%7$s</total_fee><sign>%8$s</sign></xml>";
		String requesetBody = String.format(xml, APPID, MCHID, nonceStr, payDetail.getPayorderid(),payDetail.getPayorderid(),totalFee.toString(),totalFee.toString(), sign);
		String response = this.payService.instaceXml(Pre_RefundOrder, requesetBody, String.class);
		Document document = null;
		Element root = null;
		try {
			document = DocumentHelper.parseText(response);
			root = document.getRootElement();
		} catch (DocumentException e) {
			logger.error("下单返回结果异常:" + e.getMessage());
			throw new BusinessException(OrderConstants.EXCEPTION_OUTINTEFACE, "微信支付下单接口异常");
		}
		String returnCode = root.element("return_code").getTextTrim();
		if ("SUCCESS".equals(returnCode)) {
			String result_code = root.element("result_code").getTextTrim();
			if ("SUCCESS".equals(result_code)) {
				String refund_id = root.element("refund_id").getTextTrim();
				try {
					// 微信支付app返回 日志记录
					payDetail = new PayDetail();
					payDetail.setParams(response);
					payDetail.setOrderid(payDetail.getOrderid());
					payDetail.setPayorderid(payDetail.getPayorderid());
					payDetail.setChannel("weixinpay");
					payDetail.setMoney(totalFee.divide(new BigDecimal(100)));
					payDetail.setNotifyUrl(NOTIFYURL);
					payDetail.setState(7);//申请退款
					payDetail.setTranceid(refund_id);
					payDetail.setSend(params.toString());
					this.payService.edit(payDetail);
				} catch (Exception e) {
					logger.error("日志记录异常("+JSONObject.toJSONString(payDetail)+"):" + e.getMessage());
				}
			} else {
				String err_code = root.element("err_code").getTextTrim();
				String err_code_des = root.element("err_code_des").getTextTrim();
				throw new BusinessException(OrderConstants.EXCEPTION_PAY_RETURN, err_code_des);
			}
		} else {
			String returnMsg = root.element("return_msg").getTextTrim();
			throw new BusinessException(OrderConstants.EXCEPTION_PAY_CONNECT, returnMsg);
		}
	}
	
	public String getAPPID() {
		return APPID;
	}

	public void setAPPID(String aPPID) {
		APPID = aPPID;
	}

	public String getMCHID() {
		return MCHID;
	}

	public void setMCHID(String mCHID) {
		MCHID = mCHID;
	}

	public String getNOTIFYURL() {
		return NOTIFYURL;
	}

	public void setNOTIFYURL(String nOTIFYURL) {
		NOTIFYURL = nOTIFYURL;
	}

	public String getENCODINGAESKEY() {
		return ENCODINGAESKEY;
	}

	public void setENCODINGAESKEY(String eNCODINGAESKEY) {
		ENCODINGAESKEY = eNCODINGAESKEY;
	}

	public String getDEVICEINFO() {
		return DEVICEINFO;
	}

	public void setDEVICEINFO(String dEVICEINFO) {
		DEVICEINFO = dEVICEINFO;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", "wxdb479b4baf251e33");
		params.put("mch_id", "1490482872");
		params.put("device_info", "WEB");
		params.put("nonce_str",  WinxinPayUtil.getNonceStr());
		params.put("out_trade_no", "00090067");
		//TODO 默认订单一分钱
		//BigDecimal totalFee = new BigDecimal(order.getPrice()*100).setScale(0, java.math.BigDecimal.ROUND_HALF_EVEN);
		BigDecimal totalFee = new BigDecimal(0.01*100).setScale(0, java.math.BigDecimal.ROUND_HALF_EVEN);
		params.put("total_fee", totalFee.toString());
		params.put("notify_url", "http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php");
		params.put("trade_type", "APP");
		params.put("body", "咨询服务卡");
		String signStr = WinxinPayUtil.getSignContent(params) + "&key=mQmZ9XMkC1x0qDiwvXnStxDWoC99J5rB";
		logger.info("微信下单签名串:"+signStr);
		String sign = MD5Util.MD5(signStr,"UTF-8").toUpperCase();
		System.out.println(sign);
		//查询订单
		Map<String, String> params2 = new HashMap<String, String>();
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("appid", "wxdb479b4baf251e33");
		queryParams.put("mch_id", "1490482872");
//		String nonceStr = WinxinPayUtil.getNonceStr();
		queryParams.put("nonce_str", WinxinPayUtil.getNonceStr());
		queryParams.put("out_trade_no", "00090067");
		params2.putAll(queryParams);
		params2.put("device_info", "WEB");
		BigDecimal money = new BigDecimal(0.01*100).setScale(0, java.math.BigDecimal.ROUND_HALF_EVEN);
//		if (!CollectionUtils.isEmpty(payDetailList)){
//			money = payDetailList.get(0).getMoney();
//			params.put("total_fee",money.setScale(0, java.math.BigDecimal.ROUND_HALF_EVEN).toString());
//		}//else {
		params.put("total_fee",money.toString());
//
//		}
		params2.put("notify_url","http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php");
		params2.put("trade_type","APP");
		params2.put("body","咨询服务卡");
		System.out.println(new String("咨询服务卡".getBytes(),"UTF-8"));
		String signStr2 = WinxinPayUtil.getSignContent(params) + "&key=mQmZ9XMkC1x0qDiwvXnStxDWoC99J5rB";
		logger.info("订单查询签名串，signStr:{}",signStr);
		String sign2 = MD5Util.MD5(signStr,"UTF-8").toUpperCase();
		queryParams.put("sign", sign2);
		System.out.println(sign2);
		System.out.println(sign.equals(sign2));
	}
	
}
