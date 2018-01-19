package com.yhyt.health.service.impl;

import com.alibaba.fastjson.JSON;
import com.yhyt.health.dao.BaseDao;
import com.yhyt.health.model.Persistable;
import com.yhyt.health.service.BaseService;
import com.yhyt.health.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public abstract class BaseServiceImpl<T extends Persistable> implements BaseService<T> {

	private static Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);
	
	public abstract BaseDao<T> getBaseDao();
	
    private RestTemplate restTemplate;
    
    public BaseServiceImpl(){
    	super();
    	SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(3000);
        requestFactory.setReadTimeout(15000);
        this.restTemplate = new RestTemplate(requestFactory);
    }
    
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public <T> T post(String url,Map<String,Object> params,Class<T> returnClass){
		/*HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		HttpEntity httpEntity = new HttpEntity(params, null);
		Long start =  System.currentTimeMillis();
		T obj =  this.restTemplate.postForObject(url,httpEntity,returnClass);
		logger.info("interface request t("+url+"):"+(System.currentTimeMillis()-start)/1000+"s");
		return obj;*/
		Long start = System.currentTimeMillis();
		String param = "";
		int i=0;
		Object[] urlpamas = new Object[params.keySet().size()];
		for (Object key : params.keySet()) {
			param = param+"&"+key+"={"+key+"}";
			urlpamas[i] = params.get(key);
			i++;
		}
		if(param.length()>0){
			param = param.substring(1);
		}
	    ResponseEntity<T> obj = restTemplate.exchange(url+"?"+param, HttpMethod.POST, null, returnClass,  urlpamas);
	    logger.info("interface request("+url+"):"+(System.currentTimeMillis()-start)/1000+"s");
	    return obj.getBody();
	}
	
	public <T> T restGet(String url,Map<String,Object> params,Class<T> returnClass,Object... urlpamas){
		Long start = System.currentTimeMillis();
		String param = "";
		int i=0;
		if(urlpamas==null){
			urlpamas = new Object[params.keySet().size()];
		}else{
			Object[] temp = urlpamas;
			urlpamas =new Object[temp.length+params.keySet().size()];
			System.arraycopy(temp, 0, urlpamas, 0, 5);
		}
		for (Object key : params.keySet()) {
			param = param+"&"+key+"={"+key+"}";
			urlpamas[i] = params.get(key);
			i++;
		}
		if(param.length()>0){
			param = param.substring(1);
		}
	    ResponseEntity<T> obj = restTemplate.exchange(url+"?"+param, HttpMethod.GET, null, returnClass,  urlpamas);
	    logger.info("interface request("+url+"):"+(System.currentTimeMillis()-start)/1000+"s");
	    return obj.getBody();
	}
	
	public <T> T get(String url,Map params,Class<T> returnClass){
		Long start = System.currentTimeMillis();
		String param = "";
		List urlpamas= null;
		if(params!=null){
			urlpamas = new ArrayList<Object>();
			for (Object key : params.keySet()) {
				Object value =  params.get(key);
				if(value instanceof List){
					for (Object object : (List)value) {
						param = param+"&"+key+"={"+key+"}";
						urlpamas.add(object);
					}
				}else{
					param = param+"&"+key+"={"+key+"}";
					urlpamas.add(value);
				}
			}
			if(param.length()>0){
				param = param.substring(1);
			}
		}
//	    String jsonObj = JSON.toJSONString(params);
//	    HttpEntity<String> entity = new HttpEntity<String>(jsonObj,null);
	    ResponseEntity<T> obj = null;
	    if(urlpamas!=null){
	    	obj = restTemplate.exchange(url+"?"+param, HttpMethod.GET, null,returnClass,  urlpamas.toArray());
	    }else{
	    	obj = restTemplate.exchange(url+"?"+param, HttpMethod.GET, null,returnClass);
	    }
	    logger.info("interface request("+url+"):"+(System.currentTimeMillis()-start)/1000+"s");
	    return obj.getBody();
	}
	
	/*public <T> T instaceUrl(String url,Object params,Class<T> returnClass,String urlpamas){
//		HttpHeaders headers = new HttpHeaders();
//	    MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//	    headers.setContentType(type);
//	    headers.add("Accept", MediaType.APPLICATION_JSON.toString());
	    String jsonObj = JSON.toJSONString(params);
	    HttpEntity<String> entity = new HttpEntity<String>(jsonObj,null);
//	    return restTemplate.postForObject(url, entity, returnClass);
	    
	    return restTemplate.exchange(url, HttpMethod.GET, entity, returnClass,  urlpamas).getBody();
	}

	public <T> T instaceUrl(String url,Object params,Class<T> returnClass){
		HttpHeaders headers = new HttpHeaders();
	    MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
	    headers.setContentType(type);
	    headers.add("Accept", MediaType.APPLICATION_JSON.toString());
	    String jsonObj = JSON.toJSONString(params);
	    HttpEntity<String> entity = new HttpEntity<String>(jsonObj,headers);
	    return restTemplate.postForObject(url, entity, returnClass);
	}*/
	
	public <T> T instaceXml(String url,String xml,Class<T> returnClass){
		try {
			Long start = System.currentTimeMillis();
			HttpHeaders headers = new HttpHeaders();
			MediaType type = MediaType.parseMediaType("application/xml; charset=UTF-8");
			headers.setContentType(type);
			headers.add("Accept", MediaType.APPLICATION_XML.toString());
			HttpEntity<String> entity = new HttpEntity<String>(xml,headers);
			logger.info("微信支付请求参数，params:{}",JSON.toJSONString(entity));
			ResponseEntity<T> response =  restTemplate.exchange(url, HttpMethod.POST, entity, returnClass);
			logger.info("微信支付返回结果，result:{}", JSON.toJSONString(response));
			if(returnClass == String.class){
				String result = (String) response.getBody();
				try {
//					logger.info("编码后的 result,result:{}",JSON.toJSONString());
					return (T) new String(result.getBytes("iso8859-1"),"utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			logger.info("interface request("+url+"):"+(System.currentTimeMillis()-start)/1000+"s");
			logger.info("订单查询 body,body:{}",JSON.toJSONString(response.getBody()));
			return response.getBody();
//			return restTemplate.postForObject(url, entity, returnClass);
		} catch (RestClientException e) {
			e.printStackTrace();
			logger.info("微信支付异常，error:{}",e);
			throw e;
		}
	}
	
	// 使用POST方法发送XML数据
//		public String sendXMLDataByPost(String url, String xmlData) throws Exception {
//			if (client == null){
//				client = HttpClients.createDefault();
//			}
//			HttpPost post = new HttpPost(url);
//			List<BasicNameValuePair> parameters = new ArrayList<>();
//			parameters.add(new BasicNameValuePair("xml", xmlData));
//			post.setEntity(new UrlEncodedFormEntity(parameters,"UTF-8"));
//			HttpResponse response = client.execute(post);
//			System.out.println(response.toString());
//			HttpEntity entity = response.getEntity();
//			String result = EntityUtils.toString(entity, "UTF-8");
//			return result;
//		}
	
	private T newInstance(Class<? extends Persistable> cls){
		try {
			return (T) cls.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 保存前参数检查
	 * @param persistable
	 */
	void editBefore(T persistable){
		
	}
	
	/**
	 * 保存后处理
	 * @param persistable
	 */
	void editAfter(T persistable){
		
	}
	
	@Transactional
	@Override
	public T edit(T persistable){
		if(persistable!=null){
			T newPersistable = null;
			if(persistable.getId()!=null){
				newPersistable = this.getBaseDao().selectByPrimaryKey(persistable.getId());
			}else{
				newPersistable = this.newInstance(persistable.getClass());
			}
			BeanUtils.copyProperties(persistable, newPersistable);
			this.editBefore(newPersistable);
			if(persistable.getId()==null){
				this.getBaseDao().insertSelective(newPersistable);
				persistable.setId(newPersistable.getId());
			}else{
				this.getBaseDao().updateByPrimaryKeySelective(newPersistable);
			}
			this.editAfter(newPersistable);
		}
		return persistable;
	}
	
	/**
	 * 保存前参数检查
	 * @param persistable
	 */
	void deleteBefore(Long id){
		
	}
	
	@Transactional
	@Override
	public void delete(Long id){
		this.deleteBefore(id);
		this.getBaseDao().deleteByPrimaryKey(id);
	}
	
	void afterFindPersistable(final T t){
		
	}
	
	@Override
	public T findPersistable(Long id){
		T t = this.getBaseDao().selectByPrimaryKey(id);
		afterFindPersistable(t);
		return t;
	}
	
	
	/**
	 * 查询参数转换
	 */
	void paramsCharge(Map params,T persistable){
		
	}
	
	@Override
	public List<T> findPersistableList(T persistable){
		Map<String,Object> params = new HashMap<String,Object> ();
		this.paramsCharge(params, persistable);
		return this.getBaseDao().findPersistableList(params);
	}
	
	@Override
	public List<T> findPersistableList(Map<String,Object> params){
		return this.getBaseDao().findPersistableList(params);
	}
	
	@Override
	public Page<T> findPersistableList(T persistable,Integer pageNo,Integer pageSize){
		Map<String,Object> params = new HashMap<String,Object> ();
		this.paramsCharge(params, persistable);
		return this.findPersistableList(params, pageNo,pageSize);
	}
	
	@Override
	public Page<T> findPersistableList(Map<String,Object> params,Integer pageNo,Integer pageSize){
		Page page = new Page();
		if(pageNo==null||pageNo<=0){
			pageNo = 1;
		}
		page.setPageNo(pageNo);
		if(pageSize==null||pageSize<=0){
			pageSize = Page.DEFAULT_PAGESIZE;
		}
		page.setPageSize(pageSize);
		params.put("page",page);
		page.setResult( this.getBaseDao().findPersistableList(params));
		return page;
	}
	
	@Override
	public Page<T> findPersistableList(T persistable,Integer pageNo){
		return this.findPersistableList(persistable, pageNo,Page.DEFAULT_PAGESIZE);
	}
}
