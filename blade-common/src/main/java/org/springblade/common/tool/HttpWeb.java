/**
 * Copyright (C), 2015-2020
 * FileName: HttpWeb
 * Author:   呵呵哒
 * Date:     2020/11/16 14:50
 * Description:
 */
package org.springblade.common.tool;

/**
 * @创建人 hyp
 * @创建时间 2020/11/16
 * @描述
 */

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;

public class HttpWeb extends XpError{
	private static String defencode = "utf-8";
	static boolean proxySet = false;
	static String proxyHost = "127.0.0.1";//post 代理地址
	static int proxyPort = 8080;


	/**
	 * 向指定URL发送GET方法的请求
	 *
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param){
		String encode=defencode;
		return sendGet( url,  param, encode);
	}
	public static String sendGet(String url, String param,String encode) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.3;SV1)");
			connection.setRequestProperty("charset", encode);
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 发起http请求获取返回结果
	 * @param req_url 请求地址
	 * @return
	 */
	public static String httpRequest(String req_url) {
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(req_url);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

			httpUrlConn.setDoOutput(false);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);

			httpUrlConn.setRequestMethod("GET");
			httpUrlConn.connect();

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		return buffer.toString();
	}


	/**
	 * 发送http请求取得返回的输入流
	 * @param requestUrl 请求地址
	 * @return InputStream
	 */
	public static InputStream httpRequestIO(String requestUrl) {
		InputStream inputStream = null;
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod("GET");
			httpUrlConn.connect();
			// 获得返回的输入流
			inputStream = httpUrlConn.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inputStream;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 *
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 *               是否使用代理模式
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		return sendPost(url,param);
	}
	public static String sendPost(String url, String param,boolean isproxy) {
		OutputStreamWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			HttpURLConnection conn = null;
			if(isproxy){//使用代理模式
				@SuppressWarnings("static-access")
				Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
				conn = (HttpURLConnection) realUrl.openConnection(proxy);
			}else{
				conn = (HttpURLConnection) realUrl.openConnection();
			}
			// 打开和URL之间的连接

			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");    // POST方法


			// 设置通用的请求属性

			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
				"Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.3;SV1)");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("charset", "utf-8");
			conn.connect();

			// 获取URLConnection对象对应的输出流
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			// 发送请求参数
			out.write(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
				new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！"+e);
			e.printStackTrace();
		}
		//使用finally块来关闭输出流、输入流
		finally{
			try{
				if(out!=null){
					out.close();
				}
				if(in!=null){
					in.close();
				}
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 发送Http post请求
	 *
	 * @param xmlInfo
	 *            json转化成的字符串
	 * @param URL
	 *            请求url
	 * @return 返回信息
	 */
	public static String doHttpPost(String URL,String xmlInfo) {
		//System.out.println("发起的数据:" + xmlInfo);
		byte[] xmlData = xmlInfo.getBytes();
		InputStream instr = null;
		java.io.ByteArrayOutputStream out = null;
		try {
			URL url = new URL(URL);
			URLConnection urlCon = url.openConnection();
			urlCon.setDoOutput(true);
			urlCon.setDoInput(true);
			urlCon.setUseCaches(false);
			urlCon.setRequestProperty("content-Type", "application/json");
			urlCon.setRequestProperty("charset", "utf-8");
			urlCon.setRequestProperty("Content-length",
				String.valueOf(xmlData.length));
			System.out.println(String.valueOf(xmlData.length));
			DataOutputStream printout = new DataOutputStream(
				urlCon.getOutputStream());
			printout.write(xmlData);
			printout.flush();
			printout.close();
			instr = urlCon.getInputStream();
			byte[] bis = IOUtils.toByteArray(instr);
			String ResponseString = new String(bis, "UTF-8");
			if ((ResponseString == null) || ("".equals(ResponseString.trim()))) {
				System.out.println("返回空");
			}
			System.out.println("返回数据为:" + ResponseString);
			return ResponseString;

		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		} finally {
			try {
				out.close();
				instr.close();

			} catch (Exception ex) {
				return "0";
			}
		}
	}
}
