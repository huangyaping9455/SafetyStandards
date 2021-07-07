///**
// * Copyright (C), 2015-2021
// * FileName: SignTest
// * Author:   呵呵哒
// * Date:     2021/4/25 16:18
// * Description:
// */
//package org.springblade.platform.qiyeshouye.controller;
//
///**
// * @创建人 hyp
// * @创建时间 2021/4/25
// * @描述
// */
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.hbis.infmgr.common.sign.RSAUtils;
//import com.hbis.infmgr.common.sign.SignUtil;
//import com.hbis.infmgr.common.sign.XmlSignUtil;
//import com.hbis.infmgr.redirect.test.webservice.client.TransferProPlanRequest;
//import com.hbis.infmgr.redirect.test.webservice.client.YxProPlan;
//import com.hbis.infmgr.redirect.test.webservice.client.YxProPlanService;
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
//import org.apache.xml.security.utils.XMLUtils;
//import org.junit.Test;
//import org.w3c.dom.Document;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//
//import javax.xml.namespace.QName;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.ws.handler.Handler;
//import javax.xml.ws.handler.MessageContext;
//import javax.xml.ws.handler.soap.SOAPHandler;
//import javax.xml.ws.handler.soap.SOAPMessageContext;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.*;
//
//import static org.junit.Assert.fail;
//
//public class SignTest {
//
//	private static final int account=227;
//	private static final String key="82402d7135444ea49c616100aaf8a9f8";
//	//    private static final String url_domain="http://localhost:8091";
//	private static final String url_domain="http://testinterfacem.hbisscm.com/inf";
//	public static final String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIzP/7dapt5TnSkktj2KjSe71TP7WFULIqgkoSlr4/oKeg7NVqVcpgl9I58CJAvy9QkLC/65KCH0NwJiYFgQ1k3Ml+7YnKKWFvvPwDrqEdGju6OyWVBnnJUu3wn0SrHgc8PrLer4F+QY7kpdbuSrc0kdtuz+4nJ2lTP9ByHK7w2TAgMBAAECgYAKpxmzZ1pEa5LyL+LRmQ/At8cTXap7FJKUoeDHX3Ap1G0TfZlWfUHaaest9l1/weujuYA4zw0oNG+M8KBVSUDNGF+xfmO9VmrS40He8ilotUFeyM3WUAExsL6uInqdANgdB2K8jI40hfpfMjFMikJ1JCWFD3a6ZZ6HMQ21EIfUUQJBAPH6kPkspauGrrhKC2WHfBV+RY2xBy0ttLTbfQlQMzkOA6zYKsucB78GH+pvF409fxJ/CxV2lPNgWbPCwR8ZlMUCQQCU+MPlkd+u+FPMwpfQ8KCYxNpC/GQwcQ+sSTXoJtK92iPRtbCDf9Uc7tFM3jOVBPmEAc+vmj1Tp9nKnWNYCa53AkB1eAU+4Xy/iw9wqYlkzWPgbz9GrMTmGsUZ81980PE85l/EXeBQnbto8Yk23p1ZVJxGl2QDFGD00TZavNXRyjJdAkBVAS4nDPnqSWDn8hHW87dv9MPmfUZeLeWXlZ7g/WY7VwIhY9946bSdcdiUWCZu3gvSu4Xa2dQsrBamevB5B+05AkEAh5y6alNq3IzkX+sf9bAnOjJ3BVVmrNfkkdm5MTJ9yRLBDGIakC4FHmnWBMEZm5EFjOOQiXGLnw8avI8L/R2owQ==";
//	public static final String public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCMz/+3WqbeU50pJLY9io0nu9Uz+1hVCyKoJKEpa+P6CnoOzValXKYJfSOfAiQL8vUJCwv+uSgh9DcCYmBYENZNzJfu2Jyilhb7z8A66hHRo7ujsllQZ5yVLt8J9Eqx4HPD6y3q+BfkGO5KXW7kq3NJHbbs/uJydpUz/Qchyu8NkwIDAQAB";
//
//
//	private void fillMethod(HttpRequestBase requestBase,long timestamp,String sign){
//		requestBase.addHeader("haccid",String.valueOf(account));//设置account值
//		requestBase.addHeader("hacckey",key); //设置key值
//		requestBase.addHeader("hacctimestamp",String.valueOf(timestamp)); //设置时间戳,nginx,underscores_in_headers on;放到http配置里，否则nginx会忽略包含"_"的头信息
//		requestBase.addHeader("haccsign",sign);
//		System.out.println(requestBase.getAllHeaders());
//	}
//
//	@Test
//	public void testFormSign(){
//		String uri = "/internal/test/sign/form";
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
//		/**
//		 * 根据私钥，生成签名，并将签名放到header头
//		 */
//		String sign = SignUtil.signFormData(formData,private_key,timestamp);
//		fillMethod(post,timestamp,sign);
//
//		/**
//		 * 发送带签名的数据。
//		 */
//		HttpEntity entity = EntityBuilder.create().setParameters(pairs).build();
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
//	public void testFormSign1() throws Exception {
//		long timestamp = System.currentTimeMillis();
//		BasicNameValuePair[] pairs = new BasicNameValuePair[]{new BasicNameValuePair("Key1","value1"),new BasicNameValuePair("Key3","value3"),new BasicNameValuePair("Key2","value2")};
//		Map<String,String[]> formData = new HashMap<>();
//		for (int i = 0; i < pairs.length; i++) {
//			formData.put(pairs[i].getName(),new String[]{pairs[i].getValue()});
//		}
//		Map keyMap = RSAUtils.genKeyPair();
//		String publicKey = RSAUtils.getPublicKey(keyMap);
//		String privateKey = RSAUtils.getPrivateKey(keyMap);
//		String sign = SignUtil.signFormData(formData,privateKey,timestamp);
//		System.out.println(timestamp);
//		System.out.println(sign);
//		System.out.println(SignUtil.validateFormData(formData,publicKey,sign,timestamp));
//	}
//
//	@Test
//	public void testJsonSign()throws  Exception {
//		String uri = "/internal/test/sign/json";
//		long timestamp = System.currentTimeMillis();
//		CloseableHttpClient client = HttpClientBuilder.create().build();
//		HttpPost post = new HttpPost(url_domain+uri);
//
//		JSONObject object = new JSONObject();
//		object.put("name","zhangsan");
//		object.put("age",40);
//		String data = object.toJSONString();
//		/**
//		 * 根据私钥，生成签名，并将签名放到header头
//		 */
//		String sign = SignUtil.sign(data,private_key,timestamp);
//		fillMethod(post,timestamp,sign);
//
//		/**
//		 * 发送带签名的数据。
//		 */
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
//	public void testGetSign()throws  Exception {
//		String uri = "/internal/restNeiHang/batchSearchNeiHangAuditState";
//		String query = "name=zhangsan&age=40";
//		uri = uri+"?"+query;
//		long timestamp = System.currentTimeMillis();
//		CloseableHttpClient client = HttpClientBuilder.create().build();
//		HttpGet post = new HttpGet(url_domain+uri);
//
//		/**
//		 * 根据私钥，生成签名，并将签名放到header头
//		 */
//		String sign = SignUtil.sign(query,private_key,timestamp);
//		fillMethod(post,timestamp,sign);
//
//		/**
//		 * 发送带签名的数据。
//		 */
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
//
//	@Test
//	public void testSoapSign() throws IOException {
//		File tempFile  = new File("yxProPlan.wsdl");
//		final String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJkquXlXlO9Uu2w99IwEPzQ0qnk/84T8z85b0dVYJRfp/PIm/88Endw7SkjPUSeiwH8sC6lS6Sddxkau9Zdt8qc6IQtloSvvhGmzUAkXoDsmhuB9lzHCS6rdUI9yyRTYI4nphxeHbB5l3jKbIsOKgIBVauGv1dYIwUVXTzRBH7PbAgMBAAECgYAatMNgL/AtBsYjKm5QV5E5phEvUQJB5t7I6WOd4l+dWtQa2MfzradktgXOyi8pTQxpYCGsZWN31Lb0rh7uR2JulpDTdaWu7pXSJaahDbJHV/58uXYE8cFfvKTEwkhFFUuLlCt662KYAY6vSb2OxmCQBIOZ+tipI1HsvqUFz29mOQJBANz+DYnLx+656eaWpbq49icxz6C+T4R0ELOhlOg4YfOHCc2AkVW2TN6Q+ya7uzzJIbleAta86pJMPTSNz4U/FZ0CQQCxbiGJRma1NWPrcZ2Dlgj5MpgFiXSsU7oFBU+mQToVO/2j69cSg34NH0V4MSn0V8RLobpX6CYMFf8YNXQjD7HXAkAm8K06BqpoKmJIu2rgiA73wRMw49fnIvx+ao5ujODVpInZqDnzvHe3EBzxKktH3rkOjL47UHaU6NIwHHzgockRAkEApfscrEqLsmDrxElYzMwCQPyvch6QYtVAY4oAZMsIBu1D7aCmZc9Uj/n9Bt8XHFkubIIyzCGD1jKNnJwEtPftqQJAYFP0abyBWXols8crG8OpBWGt3ipWbR8w6m0CpUqGI2vmISEYMqmex6g4a6rVs2dLj44FkQCGAFckFaMVzeCukQ==";
//
//		if (!tempFile.exists()){
//			HttpGet httpGet = new HttpGet("http://testinterfacem.hbisscm.com/inf/internal/wulianb/ws/yxProPlan?wsdl");
//			fillMethod(httpGet);
//			CloseableHttpClient client = HttpClientBuilder.create().build();
//			HttpResponse response = client.execute(httpGet);
//			String wsdlContent = EntityUtils.toString(response.getEntity());
//			System.out.println(wsdlContent);
//			FileUtils.writeStringToFile(tempFile,wsdlContent,"UTF-8");
//		}
//
//		YxProPlanService proPlanService = new YxProPlanService(tempFile.toURI().toURL());
//		List<Handler> handlerList = new ArrayList<>();
//		handlerList.add(new SOAPHandler() {
//			@Override
//			public Set<QName> getHeaders() {
//				return null;
//			}
//
//			@Override
//			public boolean handleMessage(MessageContext context) {
//				org.apache.xml.security.Init.init();
//				SOAPMessageContext soapMessageContext = (SOAPMessageContext) context;
//				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//				XMLUtils.outputDOM(soapMessageContext.getMessage().getSOAPPart(),outputStream);
//				System.out.println(new String(outputStream.toByteArray()));
//				try {
//					SignUtil.signSoap(soapMessageContext.getMessage().getSOAPPart().getDocumentElement(),private_key);
//				} catch (Exception e) {
//					e.printStackTrace();
//					return false;
//				}
//				return true;
//			}
//
//			@Override
//			public boolean handleFault(MessageContext context) {
//				return false;
//			}
//
//			@Override
//			public void close(MessageContext context) {
//			}
//		});
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
//
//	@Test
//	public void testSignXml() throws Exception {
//		org.apache.xml.security.Init.init();
//		File attachmentFile = new File("F:\\soap.xml");
//		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//		dbFactory.setNamespaceAware(true);//必须增加这条https://www.e-learn.cn/topic/2364093否则验签会报错
//		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//		Document doc = dBuilder.parse(attachmentFile);
//		SignUtil.signSoap(doc.getDocumentElement(),private_key);
//
//		String f = "D:\\test\\sign.xml";
//		FileOutputStream outputStream = new FileOutputStream(f);
//		XMLUtils.outputDOM(doc,outputStream);
//		outputStream.close();
//
//		Document filedoc = dBuilder.parse(new File(f));
//		System.out.println( XmlSignUtil.checkSign(doc,RSAUtils.getPublicKey(public_key)));
//		for (int i = 0; i <  filedoc.getDocumentElement().getChildNodes().getLength(); i++) {
//			Node child = filedoc.getDocumentElement().getChildNodes().item(i);
//			System.out.println(child.getNodeName());
//			if ("Signature".equals(child.getNodeName())){
//				NodeList children = child.getChildNodes();
//				for (int j = 0; j < children.getLength(); j++) {
//					Node sc = children.item(j);
//					System.out.println("s child "+sc.getNodeName());
//				}
//			}
//		}
//		System.out.println(XmlSignUtil.checkSign(filedoc,RSAUtils.getPublicKey(public_key)));
//	}
//
//	@Test
//	public void testSingleSign()  throws Exception {
//		org.apache.xml.security.Init.init();
//		String f = "D:\\test\\sign.xml";
//		String message = FileUtils.readFileToString(new File(f),"UTF-8");
//		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//		dbFactory.setNamespaceAware(true);//必须增加这条https://www.e-learn.cn/topic/2364093否则验签会报错
//		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//		Document filedoc = dBuilder.parse(new File(f));
//		System.out.println(XmlSignUtil.checkSign(message,RSAUtils.getPublicKey(public_key)));
//	}
//}
//
