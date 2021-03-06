package com.yhyt.health.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.yhyt.health.HealthConstants;
import com.yhyt.health.OrderConstants;
import com.yhyt.health.dao.OrderMapper;
import com.yhyt.health.dao.PayDetailMapper;
import com.yhyt.health.model.Item;
import com.yhyt.health.model.Order;
import com.yhyt.health.model.PayDetail;
import com.yhyt.health.service.ItemService;
import com.yhyt.health.service.OrderService;
import com.yhyt.health.service.PayService;
import com.yhyt.health.util.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;


@Api(value="",description ="支付宝接口")
@RestController
@RequestMapping("/alipay")
@ConfigurationProperties(prefix="alipay")
@ControllerAdvice
public class AliPayController {

	private static Logger logger = LoggerFactory.getLogger(AliPayController.class);
	
	private String APPID;
	
	private String PRIVATEKEY;
	
	private String PUBLICKEY;
	
	private String SIGNTYPE="";
	
	private String NOTIFYURL="";
	
	private String PARTNER="";
	
	private String PAYMETHOD = "alipay.trade.app.pay";
	private String QUERYMETHOD = "alipay.trade.query";
	private String ALIPAYURL="https://openapi.alipay.com/gateway.do";
	private String ALIPAY_PUBLICKEY;

	public String getALIPAY_PUBLICKEY() {
		return ALIPAY_PUBLICKEY;
	}

	public void setALIPAY_PUBLICKEY(String ALIPAY_PUBLICKEY) {
		this.ALIPAY_PUBLICKEY = ALIPAY_PUBLICKEY;
	}

	public String getAPPID() {
		return APPID;
	}

	public void setAPPID(String aPPID) {
		APPID = aPPID;
	}

	public String getPRIVATEKEY() {
		return PRIVATEKEY;
	}

	public void setPRIVATEKEY(String pRIVATEKEY) {
		PRIVATEKEY = pRIVATEKEY;
	}

	public String getPUBLICKEY() {
		return PUBLICKEY;
	}

	public void setPUBLICKEY(String pUBLICKEY) {
		PUBLICKEY = pUBLICKEY;
	}

	public String getSIGNTYPE() {
		return SIGNTYPE;
	}

	public void setSIGNTYPE(String sIGNTYPE) {
		SIGNTYPE = sIGNTYPE;
	}

	public String getNOTIFYURL() {
		return NOTIFYURL;
	}

	public void setNOTIFYURL(String nOTIFYURL) {
		NOTIFYURL = nOTIFYURL;
	}

	public String getPARTNER() {
		return PARTNER;
	}

	public void setPARTNER(String pARTNER) {
		PARTNER = pARTNER;
	}

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private PayService payService;

	@Autowired
	private OrderService orderService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private PayDetailMapper payDetailMapper;
	/**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    private String createLinkString(Map<String, String> params) {
 
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
 
        String prestr = "";
 
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
 
            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
 
        return prestr;
    }
	
	private String signPay(Order order) throws UnsupportedEncodingException, AlipayApiException{
		
		String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		BigDecimal money = order.getPrice().setScale(2, java.math.BigDecimal.ROUND_HALF_EVEN);
		Map<String, String> map = new HashMap<String, String>();
        map.put("app_id", APPID);
        map.put("method", PAYMETHOD);
        map.put("format", "json");
        map.put("charset", "utf-8");
        map.put("sign_type", SIGNTYPE);
        map.put("timestamp", date);
        map.put("version", "1.0");
        map.put("notify_url", NOTIFYURL);
        Map<String, String> m = new HashMap<String, String>();
        //m.put("body", body);
        m.put("subject", order.getOrderNo());
        m.put("out_trade_no", order.getOrderNo().replaceAll("\\.", ""));
        m.put("timeout_express", "30m");
        m.put("total_amount", money.toString());
        m.put("seller_id", PARTNER);
        m.put("product_code", "QUICK_MSECURITY_PAY");
        //服务包
//        if (order.getItemType()==2){
//
//		}

        String content = JSONObject.toJSONString(m);
        map.put("biz_content", content);
       //对未签名原始字符串进行签名       
       String rsaSign = AlipaySignature.rsaSign(map, PRIVATEKEY, "utf-8");

        Map<String, String> map4 = new HashMap<String, String>();

        map4.put("app_id", APPID);
        map4.put("method", PAYMETHOD);
        map4.put("format", "json");
        map4.put("charset", "utf-8");
        map4.put("sign_type", SIGNTYPE);
        map4.put("timestamp", URLEncoder.encode(date,"UTF-8").replace("+","%20"));
        map4.put("version", "1.0");
        map4.put("notify_url",  URLEncoder.encode(NOTIFYURL,"UTF-8"));
        map4.put("biz_content", URLEncoder.encode(content, "UTF-8"));

//        Map par = AlipayCore.paraFilter(map4); //除去数组中的空值和签名参数
        String json4 = createLinkString(map4);   //拼接后的字符串

        json4=json4 + "&sign=" + URLEncoder.encode(rsaSign, "UTF-8");
//        map.put("sign", rsaSign);
//        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhEGqyd5pCndhq4IRfIm1pZtTA5l+YGEYDgq1FGtOUxZscxMnsavaFVQyiKyuR2s6srCs0re6Oo7VJtXsh+3HWpbPx0ItRcJY3TjxCgXJq+GTyxCy5s2p2Pk6sA8ymGx05DnR6PUvIaY54GJfA4YSwAcMqlT+CAmM+dEC1q2jD9rxCzmSkaD3Uft0fwRaeCIkQGc+czXdulaZd8q53QcW88OXzsc2lnNUN1cAXe/uzv6K288vkaXm2CiaBQT9K2RpNz10UMmSLrZ+c5c2clPD2hXhzRZJ0tio/+/c/C832EROWSPfxUlNAX7vumHruBQjszBZv4ACAUFjU8YZBzHH5QIDAQAB";
//        boolean istrue = AlipaySignature.rsaCheckV2( map,   publicKey,"utf8",SIGNTYPE);
//        System.out.println(istrue+"  : "+json4.toString());

//        AliPayMsg apm = new AliPayMsg();
//        apm.setCode("1");
//        apm.setMsg("支付成功");
//        apm.setData(json4.toString());  
//
//        JSONObject json = JSONObject.fromObject(apm);
//
//
//        System.out.println(json.toString());

        return json4;     
	}

	private String signQuery(Order order,String tradeNo) throws UnsupportedEncodingException, AlipayApiException{

		String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		BigDecimal money = order.getPrice().setScale(2, java.math.BigDecimal.ROUND_HALF_EVEN);
		Map<String, String> map = new HashMap<String, String>();
        map.put("app_id", APPID);
        map.put("method", QUERYMETHOD);
        map.put("format", "json");
        map.put("charset", "utf-8");
        map.put("sign_type", SIGNTYPE);
        map.put("timestamp", date);
        map.put("version", "1.0");
//        map.put("notify_url", NOTIFYURL);
        Map<String, String> m = new HashMap<String, String>();
        //m.put("body", body);
//        m.put("subject", order.getOrderNo());
        m.put("out_trade_no", order.getOrderNo().replaceAll("\\.", ""));
        m.put("trade_no", tradeNo.replaceAll("\\.", ""));
//        m.put("timeout_express", "30m");
//        m.put("total_amount", money.toString());
//        m.put("seller_id", PARTNER);
//        m.put("product_code", "QUICK_MSECURITY_PAY");
        //服务包
//        if (order.getItemType()==2){
//
//		}

        String content = JSONObject.toJSONString(m);
        map.put("biz_content", content);
       //对未签名原始字符串进行签名
       String rsaSign = AlipaySignature.rsaSign(map, PRIVATEKEY, "utf-8");

        Map<String, String> map4 = new HashMap<String, String>();

        map4.put("app_id", APPID);
        map4.put("method", QUERYMETHOD);
        map4.put("format", "json");
        map4.put("charset", "utf-8");
        map4.put("sign_type", SIGNTYPE);
        map4.put("timestamp", URLEncoder.encode(date,"UTF-8").replace("+","%20"));
        map4.put("version", "1.0");
//        map4.put("notify_url",  URLEncoder.encode(NOTIFYURL,"UTF-8"));
        map4.put("biz_content", URLEncoder.encode(content, "UTF-8"));

//        Map par = AlipayCore.paraFilter(map4); //除去数组中的空值和签名参数
        String json4 = createLinkString(map4);   //拼接后的字符串
//		map4.put("sign",URLEncoder.encode(rsaSign, "UTF-8"));
        json4=json4 + "&sign=" + URLEncoder.encode(rsaSign, "UTF-8");
//        map.put("sign", rsaSign);
//        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhEGqyd5pCndhq4IRfIm1pZtTA5l+YGEYDgq1FGtOUxZscxMnsavaFVQyiKyuR2s6srCs0re6Oo7VJtXsh+3HWpbPx0ItRcJY3TjxCgXJq+GTyxCy5s2p2Pk6sA8ymGx05DnR6PUvIaY54GJfA4YSwAcMqlT+CAmM+dEC1q2jD9rxCzmSkaD3Uft0fwRaeCIkQGc+czXdulaZd8q53QcW88OXzsc2lnNUN1cAXe/uzv6K288vkaXm2CiaBQT9K2RpNz10UMmSLrZ+c5c2clPD2hXhzRZJ0tio/+/c/C832EROWSPfxUlNAX7vumHruBQjszBZv4ACAUFjU8YZBzHH5QIDAQAB";
//        boolean istrue = AlipaySignature.rsaCheckV2( map,   publicKey,"utf8",SIGNTYPE);
//        System.out.println(istrue+"  : "+json4.toString());

//        AliPayMsg apm = new AliPayMsg();
//        apm.setCode("1");
//        apm.setMsg("支付成功");
//        apm.setData(json4.toString());
//
//        JSONObject json = JSONObject.fromObject(apm);
//
//
//        System.out.println(json.toString());

        return json4;
	}
	
	@ApiOperation(value="app下单接口", notes="支付")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "orderId", value = "订单主键id",paramType="path", required = true, dataType = "Long")
    })
	@GetMapping("/pay/{orderId}")
	public String sign(@PathVariable("orderId") Long id) throws UnsupportedEncodingException{
		try {
			Order order = this.orderMapper.selectByPrimaryKey(id);
			if(order==null){
				throw new BusinessException(HealthConstants.EXCEPTION_PARAMERROR,"参数错误,未找到订单信息");
			}

			if (order.getItemType()!= null && order.getItemType()==2) {//服务包
				Item item = itemService.selectByOrderId(id);
				if (item==null){
                    throw new BusinessException(HealthConstants.EXCEPTION_PARAMERROR,"参数错误,未找到订单中的商品");
                }
                else if(item.getState()==4){//已下架
                    throw new BusinessException(HealthConstants.EXCEPTION_PARAMERROR,"该商品已下架，不可购买");
                }
			}
			if (order.getPayType()!=null && order.getPayType()!=1){
				orderService.cancelWXPay(id);

			}
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("state",1);
			params.put("orderId",id);
			List<PayDetail> payDetailList = payService.findPersistableList(params);
			if (!CollectionUtils.isEmpty(payDetailList)){

			}
			//TODO 默认金额 一分
			String result = this.signPay(order);
			PayDetail payDetail = new PayDetail();
			payDetail.setOrderid(order.getOrderNo());
			payDetail.setPayorderid(order.getOrderNo().replaceAll("\\.", ""));
			payDetail.setChannel("alipay");
			payDetail.setMoney( order.getPrice().setScale(2, java.math.BigDecimal.ROUND_HALF_EVEN));
			payDetail.setNotifyUrl(NOTIFYURL);
			payDetail.setState(1);
			payDetail.setSend(result);
			this.payService.edit(payDetail);
			//支付待确认

			/*order.setPayType((byte) 1);
			order.setState((byte) 8);//待确认
			this.orderMapper.updateByPrimaryKeySelective(order);*/
			logger.info("支付宝下单参数，params:{}",JSON.toJSONString(result));
			return result;

		} catch (AlipayApiException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new BusinessException(HealthConstants.EXCEPTION_PARAMERROR,"参数加密错误");
		}
	}
	
	/*@ApiOperation(value="app下单接口", notes="支付")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "orderId", value = "订单主键id",paramType="path", required = true, dataType = "Long")
    })
	@GetMapping("/pay/{orderId}")
	public String sign(@PathVariable("orderId") Long id) throws UnsupportedEncodingException{
		Order order = this.orderMapper.selectByPrimaryKey(id);
		BigDecimal money = new BigDecimal(order.getPrice()).setScale(2, java.math.BigDecimal.ROUND_HALF_EVEN);
		String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		String content = "app_id="+APPID+"&biz_content={\"product_code\":\"QUICK_MSECURITY_PAY\",\"total_amount\":\""+money.toString()+
				"\",\"out_trade_no\":\""+order.getOrderNo()+"\"}&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url="+
				NOTIFYURL+"&sign_type="+SIGNTYPE+"&timestamp="+date+"&version=1.0";
		try {
			PayDetail payDetail = new PayDetail();
			String sign =  AlipaySignature.rsaSign(content,PRIVATEKEY, "utf-8",SIGNTYPE);
			date = URLEncoder.encode(date,"UTF-8").replace("+","%20");
			String result = "app_id="+APPID+"&biz_content="+URLEncoder.encode("{\"product_code\":\"QUICK_MSECURITY_PAY\",\"total_amount\":\""+money.toString()+
				"\",\"out_trade_no\":\""+order.getOrderNo()+"\"}","UTF-8")+"&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url="+
				URLEncoder.encode(NOTIFYURL,"UTF-8")+"&sign_type="+SIGNTYPE+"&timestamp="+date+"&version=1.0"+"&sign="+sign;
			payDetail.setOrderid(order.getOrderNo());
			payDetail.setPayorderid(order.getOrderNo());
			payDetail.setChannel("alipay");
			payDetail.setMoney(money);
			payDetail.setNotifyUrl(NOTIFYURL);
			payDetail.setState(1);
			this.payService.edit(payDetail);
			return result;
		} catch (AlipayApiException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new BusinessException(HealthConstants.EXCEPTION_PARAMERROR,"参数加密错误");
		}
	}
	*/
	@SuppressWarnings("all")
	@ApiOperation(value="结果验证(state 1-未支付 2-已支付 3-失效 4退款成功 6退款失败 7 申请退款8支付待确认)", notes="支付")
    /**
     * 交易状态：WAIT_BUYER_PAY（交易创建，等待买家付款）、
     * TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、
     * TRADE_SUCCESS（交易支付成功）、
     * TRADE_FINISHED（交易结束，不可退款）
     */
	@PostMapping("/validate")
	public Order returnUrl(String result) {
		try {
			logger.info("支付宝支付结果，result:{}", JSON.toJSONString(result));
			JSONObject obj = JSONObject.parseObject(result);
			String code1 = obj.getString("resultStatus");
			JSONObject body = obj.getJSONObject("result");
			JSONObject resp = body.getJSONObject("alipay_trade_app_pay_response");

			//"{\"code\":\"10000\",\"msg\":\"Success\",\"app_id\":\"2017060107399098\",\"auth_app_id\":\"2017060107399098\",\"charset\":\"utf-8\",\"timestamp\":\"2017-09-15 18:56:19\",\"total_amount\":\"0.01\",\"trade_no\":\"2017091521001004880293323349\",\"seller_id\":\"2088511511698621\",\"out_trade_no\":\"No00039663\"}";
            //{"orderId":"571","resultStatus":"9000","result":{"alipay_trade_app_pay_response":{"code":"10000","msg":"Success","app_id":"2017101109249326","auth_app_id":"2017101109249326","charset":"utf-8","timestamp":"2017-10-13 20:12:25","total_amount":"0.01","trade_no":"2017101321001004080207725015","seller_id":"2088821323908671","out_trade_no":"00007560"},"sign":"lMykRJkVUZMaB4UxQxboeppfNfmNmbeWx+VRUWSec+cLfw8G2NejbBd5oVmQzPwO02tbKu1XdDiIeCec+MaLw52FvriEUBOKCNVoJ4tizuBvng\/OSXKmmYjSBZjvHlAa5hI8noRJyZ21BNp0d2SKZFuyKJYxJfd1UuqA\/w4NiEs=","sign_type":"RSA"},"memo":""}
            LinkedHashMap<String, String> linkedMap = new LinkedHashMap<String,String>();
	        linkedMap.put("trade_no",resp.getString("trade_no") );
	        linkedMap.put("out_trade_no",resp.getString("out_trade_no") );
	        String orderNo = resp.getString("out_trade_no");
			if(orderNo.startsWith("No")&&(!orderNo.startsWith("No."))){
				orderNo = "No."+orderNo.substring(2, orderNo.length());
			}
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("orderNo", orderNo);
			List<Order> orderList = this.orderMapper.getOrder(params);
			if(orderList==null||orderList.size()==0){
				throw new BusinessException(OrderConstants.EXCEPTION_PAY_VALIDATE,"无效的订单号");
			}
			Order order = orderList.get(0);
			String requestBody = signQuery(order,resp.getString("trade_no"));
			if(!"9000".equals(code1)){
				logger.info("alipay支付结果判断，result:{}",result);
				if ("8000".equals(code1) || "6004".equals(code1)){
					AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do?"+requestBody,APPID,PRIVATEKEY,"json","UTF-8",ALIPAY_PUBLICKEY,SIGNTYPE);
					AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
//					request.setBizContent("{" +
//							"\"out_trade_no\":\"20150320010101001\"," +
//							"\"trade_no\":\"2014112611001004680073956707\"" +
//							"}");
//					request.set
					request.setBizContent(JSON.toJSONString(linkedMap));
					AlipayTradeQueryResponse response = alipayClient.execute(request);
					if (response!=null  && response.isSuccess()){
						if("10000".equals(response.getCode())){
								//支付失败
							if (!"TRADE_SUCCESS".equalsIgnoreCase(response.getTradeStatus())){
								/*PayDetail payDetail = new PayDetail();
								payDetail.setParams(JSONObject.toJSONString(result));
								payDetail.setOrderid(orderNo);
								payDetail.setPayorderid(resp.getString("out_trade_no"));
								payDetail.setChannel("alipay");
								payDetail.setMoney(new BigDecimal(resp.getString("total_amount")));
								payDetail.setState(2);//支付成功
								payDetail.setTranceid(resp.getString("trade_no"));
								this.payService.edit(payDetail);
								//TODO
								order.setPayTime(new Date());
								order.setPayType((byte) 1);
								order.setState((byte) 2);
								this.orderMapper.updateByPrimaryKeySelective(order);
								return order;*/
								payDetailMapper.deleteByOrderIdAndState(orderNo,"1");
								order.setPayTime(new Date());
								order.setPayType((byte) 1);
								order.setState((byte) 1);
								this.orderMapper.updateByPrimaryKeySelective(order);
							}
						}else {
							//查询支付宝订单接口调用失败
						}
					}
				}else {
					payDetailMapper.deleteByOrderIdAndState(orderNo,"1");
					order.setPayTime(null);
					order.setPayType((byte) 1);
					order.setState((byte) 1);
					this.orderMapper.updateByPrimaryKeySelective(order);
					throw new BusinessException(HealthConstants.EXCEPTION_OUTINTEFACE, "支付失败");
				}
			}
	        linkedMap.put("app_id",resp.getString("app_id") );
	        linkedMap.put("seller_id",resp.getString("seller_id") );
	        linkedMap.put("auth_app_id",resp.getString("auth_app_id") );
	        linkedMap.put("charset",resp.getString("charset") );
	        linkedMap.put("timestamp",resp.getString("timestamp") );
	        linkedMap.put("total_amount",resp.getString("total_amount") );
	        linkedMap.put("code",resp.getString("code") );
	        linkedMap.put("msg",resp.getString("msg") );
			if(order.getState()!=1){
				return order;
			}
			boolean istrue = AlipaySignature.rsaCheck(JSONObject.toJSONString(linkedMap), body.get("sign")+"",  PUBLICKEY,"utf8",SIGNTYPE);
//			if(istrue){
				if(APPID.equals(resp.getString("app_id"))){
//					if(payDetail.getMoney().compareTo(new BigDecimal(order.getPrice()))!=0){
//						throw new BusinessException(OrderConstants.EXCEPTION_PAY_VALIDATE,"无效的订单号,支付金额有误");
//					}
					PayDetail payDetail = new PayDetail();
					payDetail.setParams(JSONObject.toJSONString(result));
					payDetail.setOrderid(orderNo);
					payDetail.setPayorderid(resp.getString("out_trade_no"));
					payDetail.setChannel("alipay");
					payDetail.setMoney(new BigDecimal(resp.getString("total_amount")));
					payDetail.setState(2);//支付成功
					payDetail.setTranceid(resp.getString("trade_no"));
					this.payService.edit(payDetail);
					//TODO
					order.setPayTime(new Date());
					order.setPayType((byte) 1);
					order.setState((byte) 2);
					this.orderMapper.updateByPrimaryKeySelective(order);
//					OrderDetail od=new OrderDetail();
//					od.setType((byte) 1);
//					od.setOderId(order.getId());
//					this.orderMapper.updateDetailByPrimaryKeySelective(od);
					return order;
				}else{
					throw new BusinessException(OrderConstants.EXCEPTION_PAY_VALIDATE,"返回参数信息错误APPID");
				}
//			}else{
//				throw new BusinessException(OrderConstants.EXCEPTION_PAY_VALIDATE,"返回结果校验失败");
//			}
		}catch(BusinessException e){
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(HealthConstants.EXCEPTION_OUTINTEFACE,e.getMessage());
		}
	}
	
	@ApiOperation(value="异步回调", notes="支付")
	@PostMapping("/notify")
	public void notify(HttpRequest request,HttpResponse response){
		logger.info("支付宝支付结果通知,notify:{}",JSON.toJSONString(request.getQueryParams()));
		try {
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getQueryParams();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			    String name = (String) iter.next();
			    String[] values = (String[]) requestParams.get(name);
			    String valueStr = "";
			    for (int i = 0; i < values.length; i++) {
			        valueStr = (i == values.length - 1) ? valueStr + values[i]
			                    : valueStr + values[i] + ",";
			  	}
			    //乱码解决，这段代码在出现乱码时使用。
				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			//切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
			//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
			boolean flag = AlipaySignature.rsaCheckV1(params, PUBLICKEY, "utf-8",SIGNTYPE);
			if(flag){
				String orderNo = params.get("out_trade_no");
				if(orderNo.startsWith("No")&&(!orderNo.startsWith("No."))){
					orderNo = "No."+orderNo.substring(2, orderNo.length());
				}
				if(APPID.equals(params.get("app_id"))){
					Map<String,Object> paramss = new HashMap<String,Object>();
					paramss.put("orderNo", orderNo);
					List<Order> orderList = this.orderMapper.getOrder(paramss);
					if(orderList==null||orderList.size()==0){
						throw new BusinessException(OrderConstants.EXCEPTION_PAY_VALIDATE,"无效的订单号");
					}
					Order order = orderList.get(0);
				
					if(new BigDecimal(params.get("total_amount")).compareTo(order.getPrice())!=0){
						throw new BusinessException(OrderConstants.EXCEPTION_PAY_VALIDATE,"支付金额有误");
					}
					//TODO 添加支付订单号和第三方支付流水号
					PayDetail payDetail = new PayDetail();
					payDetail.setParams(JSONObject.toJSONString(params));
					payDetail.setOrderid(orderNo);
					payDetail.setPayorderid(params.get("out_trade_no"));
					payDetail.setChannel("alipay");
					payDetail.setMoney(new BigDecimal(params.get("total_amount")));
					payDetail.setState(2);
					payDetail.setTranceid(params.get("trade_no"));
					this.payService.edit(payDetail);
					
					order.setPayTime(new Date());
					order.setPayType((byte) 1);
					order.setState((byte) 2);
					this.orderMapper.updateByPrimaryKeySelective(order);
					((HttpServletResponse)response).getWriter().write("success");
				}else{
					throw new BusinessException(OrderConstants.EXCEPTION_PAY_VALIDATE,"返回参数信息错误APPID");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

   /* @ApiOperation(value = "取消支付",notes = "支付")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单主键id",paramType="path", required = true, dataType = "Long")

    })
    *//**
     * 选取支付宝支付，输入密码前修改为微信支付
     *//*
    @PatchMapping("/cancel/{orderId}")
    public AppResult cancelPay(@PathVariable("orderId")Long orderId){
        AppResult appResult = new AppResult();
        Order order = orderMapper.selectByPrimaryKey(orderId);
        Map<String, Object> params = new HashMap<>();
        params.put("channel","alipay");
        params.put("orderId",order.getOrderNo());
        payDetailMapper.deletePayDetail(params);
        appResult.getStatus().setCode("200");
        appResult.getStatus().setMessage("取消成功");
        return appResult;
    }*/
	/**
	 * {code=10000, msg=Success, app_id=2017060107399098, auth_app_id=2017060107399098, charset=utf-8, 
timestamp=2017-09-15 16:49:01, total_amount=0.01, trade_no=2017091521001004080262343708, seller_id=2088511511698621, out_trade_no=No00039648
}, sign=A+M123nt9Ms3m57qUtUj794zQuQqBWFj6noxAu68HvOqvPh83rdYtYe7Rbyi1jFkElA10SK7r5lAr9i8krg7Spi5tG6uaGmW1X7v5beM/DmIeSwzojLNUs7XGy+XzJZBwL94kwb8WcsOFVwAznLDncAkUFloylbs85ljdhbtPTI=,


String content = "{\"code\":\""+code+"\",\"msg\":\""+msg+"\",\"total_amount\":\""+total_amount+"\",
\"app_id\":\""+app_id+"\",\"trade_no\":\""+trade_no+"\",\"seller_id\":\""+seller_id+"\",\"out_trade_no\":\""+out_trade_no+"\"}";
	 * @param strings
	 * @throws AlipayApiException 
	 * 
	 * 
	 * {
    memo："";
    orderId = 242;
    result = "{\"alipay_trade_app_pay_response\":{\"code\":\"10000\",\"msg\":\"Success\",\"app_id\":\"2017060107399098\",\"auth_app_id\":\"2017060107399098\",\"charset\":\"utf-8\",\"timestamp\":\"2017-09-15 18:56:19\",\"total_amount\":\"0.01\",\"trade_no\":\"2017091521001004880293323349\",\"seller_id\":\"2088511511698621\",\"out_trade_no\":\"No00039663\"},\"sign\":\"ZNoR5j+onbLfKc1rc+Ygke3w7903U+ANo5pa6KfNW1VyYgX3oiPb03ONCUwPzr4+fru/e7ki02z46M6wOkTI/HcxtjHrQWTvadw9wvGwKQaYHkGdQcL7q/pMJ/bg8qOI6GDzKNDVVrhEkQDlZCi6Idh+kWPtmXMOVdhPI6l0U3s=\",\"sign_type\":\"RSA\"}";
    resultStatus = 9000;
}
	 */
	/*
	* {"orderId":"571","resultStatus":"9000",
	* "result":{"alipay_trade_app_pay_response":{"code":"10000","msg":"Success","app_id":"2017101109249326",
	* "auth_app_id":"2017101109249326","charset":"utf-8","timestamp":"2017-10-13 20:12:25","total_amount":"0.01",
	* "trade_no":"2017101321001004080207725015","seller_id":"2088821323908671","out_trade_no":"00007560"},
	* "sign":"lMykRJkVUZMaB4UxQxboeppfNfmNmbeWx+VRUWSec+cLfw8G2NejbBd5oVmQzPwO02tbKu1XdDiIeCec+MaLw52FvriEUBOKCNVoJ4tizuBvng\/OSXKmmYjSBZjvHlAa5hI8noRJyZ21BNp0d2SKZFuyKJYxJfd1UuqA\/w4NiEs=","sign_type":"RSA"},"memo":""}
	* */
	public static void main(String...strings) throws AlipayApiException{
//		String publickey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAysMBPVBqwHRWpS/x9zgFxmsjNbhv5dfJO3aCjuPyZCBWMLVh4IiKdM5NI2Mo3/MVJOHsBtUHL07i9s+8DqkWl0hOUhOT/kbrR07sTY+K+RoP7OULg1voEMwuK1NRFfS9g2fjJI4lI0tIZTakvHkc57jggATZINHVH9PwC/g2zDyQrpjwAi9Sxs7yJSY/d9bUgjnN7WUjiozqnDN5sfqv+Hz7oVxRAvAOghREb10YcCfmjXAoNVpHQUIwvrxMEnHJ5ap/9JrI7C83CHq8DJ31sEpMwGawQZbhEnGqWhHJb/wayX1iSUVxZpqoAQRHbiu4hYfHy3ipxGE717zT9GgatQIDAQAB";
		String publickey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDaUXFuXR2486Pkrp2gD/7IUegLDpOTSP1ZUXUFWEICTVrCmIKR3XL16uWZ5r4bxS/kVO2yzh2A9pRw6mavh7UKZZjNl8/vLe2bjUfuUn6FlqP8hcFqaT/Lv4eYNpOdcxfNppOTPHzs4pxOB/edi7QFidIFBkSh5oquA4b4MQ7pdQIDAQAB";
		String privatekey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANpRcW5dHbjzo+SunaAP/shR6AsOk5NI/VlRdQVYQgJNWsKYgpHdcvXq5ZnmvhvFL+RU7bLOHYD2lHDqZq+HtQplmM2Xz+8t7ZuNR+5SfoWWo/yFwWppP8u/h5g2k51zF82mk5M8fOzinE4H952LtAWJ0gUGRKHmiq4DhvgxDul1AgMBAAECgYEAhXLbH1cT4AYt3I9QEpM0bkaCzJ9wXctz8bD1EvtL/hRCskh/J6JxrxiSmGyPMHJsP+oaEf4j0rznJ85gTlNpuXuKgOZnpsSCmMCSSeTmidx8vwEBblG/UN7IfKS9iDPbwMp5z4z9syycaMIv4N5+NuoGg9JN77uAkMOlHCs3MgECQQD89ysYYJSFAz0n/ITEM+IL5P2YNqb4vCO4QSYfUbhZY0QkHWOo34rsnRlgthlMHsAsOxGdNfCJOe77FcdQjkaVAkEA3O/gTNHCT7JUWvK/raCcUHLm6hAY7EX8I295zXj5ab0yykyB5I+AamhH6QWhtK/YmWURdzUSy94gRKdH1Em/YQJAHqTGeK6Cb1aEGwrw/l+Wm4BC5CSCt5D69sAVoSeiIFnxEDrrLrLdvmoNf/SvPdRfzTvel1/IYqc89vedbHm/vQJABFopVfoP1NgTd1Mu2XG/goow6E124DikteH9SUHnz+BtPrTxHhqBkhcJy5pTzCZmoubtyCZgf2mZwyB0dscZIQJBAN+89uUq2ctgpZtiqqyRL+6z3ma3e/oUcqsVSUYnN7JOyj8zaO+51NzY6PsR6OStujHVZ6TQq5X6ScZhspxtY1k=";
		Map<String,String> params = new HashMap<String,String>();
		String result = "{\"alipay_trade_app_pay_response\":{\"code\":\"10000\",\"msg\":\"Success\",\"app_id\":\"2017101109249326\",\"auth_app_id\":\"2017101109249326\",\"charset\":\"utf-8\",\"timestamp\":\"2017-10-13 20:12:25\",\"total_amount\":\"0.01\",\"trade_no\":\"2017101321001004080207725015\",\"seller_id\":\"2088821323908671\",\"out_trade_no\":\"00007560\"},\"sign\":\"lMykRJkVUZMaB4UxQxboeppfNfmNmbeWx+VRUWSec+cLfw8G2NejbBd5oVmQzPwO02tbKu1XdDiIeCec+MaLw52FvriEUBOKCNVoJ4tizuBvng\\/OSXKmmYjSBZjvHlAa5hI8noRJyZ21BNp0d2SKZFuyKJYxJfd1UuqA\\/w4NiEs=\",\"sign_type\":\"RSA\"}";
		/*map.put("app_id", APPID);
		map.put("method", PAYMETHOD);
		map.put("format", "json");
		map.put("charset", "utf-8");
		map.put("sign_type", SIGNTYPE);
		map.put("timestamp", date);
		map.put("version", "1.0");
		map.put("notify_url", NOTIFYURL);编码
		Map<String, String> m = new HashMap<String, String>();
		//m.put("body", body);
		m.put("subject", order.getOrderNo());
		m.put("out_trade_no", order.getOrderNo().replaceAll("\\.", ""));
		m.put("timeout_express", "30m");
		m.put("total_amount", money.toString());
		m.put("seller_id", PARTNER);
		m.put("product_code", "QUICK_MSECURITY_PAY");

		String content = JSONObject.toJSONString(m);
		map.put("biz_content", content);编码*/ //返回给客户端
		String content = "app_id=2017101109249326&biz_content={\"subject\":\"00022545\",\"out_trade_no\":\"00022545\",\"timeout_express\":\"30m\",\"total_amount\":\"0.01\",\"seller_id\":\"2088821323908671\",\"product_code\":\"QUICK_MSECURITY_PAY\"}&charset=utf-8&method=alipay.trade.app.pay&sign_type=RSA&timestamp=2017-10-16 10:37:42&version=1.0";

		String sign="CI3QGqOw6hJJ4JqMGUp4+TQheRWbnmbTeND7KixlQ7Q9D+FwyCneuY18K4U+T8oOVS9zSUeMhjDR2QVQ+CNUSHKrraaHhbsno1K77B+zzLgqTKKjSPMH2KqEHBwQ3ZcwrVHpkeCzdF08EkVeClU88IS15AEN/C02P2L/seAWfLI=";
		JSONObject obj = JSONObject.parseObject(result);
		for (String string : obj.keySet()) {
			params.put(string, obj.getString(string));
		}
		params.remove("alipay_trade_app_pay_response");
		params.remove("sign_type");
		/*linkedMap.put("code",resp.getString("code") );
		linkedMap.put("msg",resp.getString("msg") );
		linkedMap.put("app_id",resp.getString("app_id") );
		linkedMap.put("auth_app_id",resp.getString("auth_app_id") );
		linkedMap.put("charset",resp.getString("charset") );
		linkedMap.put("timestamp",resp.getString("timestamp") );
		linkedMap.put("total_amount",resp.getString("total_amount") );
		linkedMap.put("trade_no",resp.getString("trade_no") );
		linkedMap.put("seller_id",resp.getString("seller_id") );
		linkedMap.put("out_trade_no",resp.getString("out_trade_no") );*/ //服务端对接受到的参数验签
//		result = "{\"code\":\"10000\",\"msg\":\"Success\",\"app_id\":\"2017101109249326\",\"auth_app_id\":\"2017101109249326\",\"charset\":\"utf-8\",\"timestamp\":\"2017-10-13 20:12:25\",\"total_amount\":\"0.01\",\"trade_no\":\"2017101321001004080207725015\",\"seller_id\":\"2088821323908671\",\"out_trade_no\":\"00007560\"}";
//		System.out.println(AlipaySignature.rsaCheck(result, params.get("sign"), publickey, "utf-8", "RSA"));
		LinkedHashMap<String, String> linkedMap = new LinkedHashMap<String,String>();
		linkedMap.put("code","10000");
		linkedMap.put("msg","Success");
		linkedMap.put("app_id","2017101109249326");
		linkedMap.put("auth_app_id","2017101109249326");
		linkedMap.put("charset","utf-8");
		linkedMap.put("timestamp","2017-10-16 10:37:42");
		linkedMap.put("total_amount","0.01");
		linkedMap.put("trade_no","2017101621001004080213684074");
		linkedMap.put("seller_id","2088821323908671");
		linkedMap.put("out_trade_no","00022545");
		boolean istrue = AlipaySignature.rsaCheck(JSONObject.toJSONString(linkedMap), sign,  publickey,"utf8","RSA");
		System.out.println(istrue);

		/*obj = JSONObject.parseObject(result);
		for (String string : obj.keySet()) {
			params.put(string, obj.getString(string).trim());
		}
		
		
		boolean istrue = AlipaySignature.rsaCheckV1( params,  publickey,"utf-8","RSA");
		System.out.println(istrue);*/
	}
	
}

