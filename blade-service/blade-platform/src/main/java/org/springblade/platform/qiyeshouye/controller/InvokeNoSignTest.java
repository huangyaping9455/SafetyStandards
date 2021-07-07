///**
// * Copyright (C), 2015-2021
// * FileName: InvokeNoSignTest
// * Author:   呵呵哒
// * Date:     2021/4/23 21:16
// * Description:
// */
//package org.springblade.platform.qiyeshouye.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.sun.xml.internal.ws.developer.WSBindingProvider;
//import com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe;
//import org.apache.commons.io.FileUtils;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.entity.EntityBuilder;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpRequestBase;
//import org.apache.http.entity.ContentType;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @创建人 hyp
// * @创建时间 2021/4/23
// * @描述
// */
//public class InvokeNoSignTest {
//	private static final int account=267;
//	private static final String key="42e1586bcf5b404688817bf57c06816d";
//	private static final String url_domain="http://localhost:8092";
//
//
//	private void fillMethod(HttpRequestBase requestBase){
//		requestBase.addHeader("haccid",String.valueOf(account));//设置account值
//		requestBase.addHeader("hacckey",key); //设置key值
//		System.out.println(requestBase.getAllHeaders());
//	}
//	@Test
//	public void testForm(){
//		String uri = "/internal/test/form";
//		long timestamp = System.currentTimeMillis();
//		CloseableHttpClient client = HttpClientBuilder.create().build();
//		HttpPost post = new HttpPost(url_domain+uri);
//
//		BasicNameValuePair[] pairs = new BasicNameValuePair[]{new BasicNameValuePair("name","zhangsan"),new BasicNameValuePair("age","40")}; //构建表单数据
//		/**
//		 * 表单数据转为formData对象
//		 */
//		Map<String,String[]> formData = new HashMap<>();
//		for (int i = 0; i < pairs.length; i++) {
//			formData.put(pairs[i].getName(),new String[]{pairs[i].getValue()});
//		}
//		fillMethod(post);
//
//		HttpEntity entity = EntityBuilder.create().setParameters(pairs).build();
//		post.setEntity(entity);
//		try {
//			HttpResponse response = client.execute(post);
//			System.out.println("response is "+ EntityUtils.toString(response.getEntity()));
//		} catch (IOException e) {
//			e.printStackTrace();
//			fail();
//		}finally {
//			post.releaseConnection();
//		}
//	}
//
//	@Test
//	public void testJson()throws  Exception {
//		String uri = "/internal/test/json";
//		long timestamp = System.currentTimeMillis();
//		CloseableHttpClient client = HttpClientBuilder.create().build();
//		HttpPost post = new HttpPost(url_domain+uri);
//
//		JSONObject object = new JSONObject();
//		object.put("name","zhangsan");
//		object.put("age",40);
//		String data = object.toJSONString();
//		fillMethod(post);
//
//		HttpEntity entity = EntityBuilder.create().setContentType(ContentType.APPLICATION_JSON).setText(data).build();
//		post.setEntity(entity);
//		try {
//			HttpResponse response = client.execute(post);
//			System.out.println("response is "+EntityUtils.toString(response.getEntity()));
//		} catch (IOException e) {
//			e.printStackTrace();
//			fail();
//		}finally {
//			post.releaseConnection();
//		}
//	}
//
//	@Test
//	public void testGet()throws  Exception {
//		String uri = "/internal/restNeiHang/batchSearchNeiHangAuditState";
//		String query = "name=zhangsan&age=40";
//		uri = uri+"?"+query;
//		long timestamp = System.currentTimeMillis();
//		CloseableHttpClient client = HttpClientBuilder.create().build();
//		HttpGet post = new HttpGet(url_domain+uri);
//		post.removeHeaders("Content-Type");
//		fillMethod(post);
//
//		try {
//			HttpResponse response = client.execute(post);
//			System.out.println("response is "+EntityUtils.toString(response.getEntity()));
//		} catch (IOException e) {
//			e.printStackTrace();
//			fail();
//		}finally {
//			post.releaseConnection();
//		}
//	}
//
//	@Test
//	public void testSoapSign() throws IOException {
//		File tempFile  = new File("yxProPlan.wsdl");
//		if (!tempFile.exists()){
//			HttpGet httpGet = new HttpGet("http://testinterfacem.hbisscm.com/inf/internal/wulianb/ws/yxProPlan?wsdl");
//			fillMethod(httpGet);
//			CloseableHttpClient client = HttpClientBuilder.create().build();
//			HttpResponse response = client.execute(httpGet);
//			String wsdlContent = EntityUtils.toString(response.getEntity());
//			System.out.println(wsdlContent);
//			FileUtils.writeStringToFile(tempFile,wsdlContent,"UTF-8");
//		}
//		YxProPlanService proPlanService = new YxProPlanService(tempFile.toURI().toURL());
//		HttpTransportPipe.dump = true;
//		TransferProPlanRequest proPlanRequest = new TransferProPlanRequest();
//		Map<String, List<String>> userHeaders = new HashMap<>();
//
//		/**
//		 * 设置http头信息
//		 */
//		List<String> accountList = new ArrayList<>(1);
//		accountList.add("226");
//		userHeaders.put("haccid",accountList);
//
//		accountList = new ArrayList<>(1);
//		accountList.add("780b68fe518d4e5eb3e09078d67feedd");
//		userHeaders.put("hacckey",accountList);
//
//		YxProPlan proPlan = proPlanService.getYxProPlanSoap11();
//		((WSBindingProvider)proPlan).getRequestContext().put("javax.xml.ws.http.request.headers",userHeaders);
//		System.out.println(JSON.toJSONString(proPlan.transferProPlan(proPlanRequest),true));
//	}
//}
//
