package me.jeekhan.leyi.wxapi;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.apache.http.client.HttpClient;

import me.jeekhan.leyi.common.HttpUtils;

/**
 * 调用微信通用、基础API
 * @author Jee Khan
 *
 */
public class CommonAPI {
	private static String ACCESS_TOKEN = "";
	private static long lastUpdTime = System.currentTimeMillis();
	private static String prop_file = "props/WXParam";	//微信常量配置文件
	private static  ResourceBundle BUNDLE = ResourceBundle.getBundle(prop_file);
	
	/**
	 * 根据指定key获取对应的值
	 * @param key
	 * @param params
	 * @return
	 */
	public static String getForamtValue(String key,Object[] params) {
		String keyValue = null;
		String msg = null;
		try{
			keyValue = BUNDLE.getString(key);
			MessageFormat mf = new MessageFormat(keyValue); 
			msg = mf.format(params);  
		}catch(Exception e){
			return null;
		}
		return msg;
	}
	//获取参数
	public static String getParam(String key) {
		return BUNDLE.getString(key);
	}
	/**
	 * 获取微信接口访问凭证：ACCESS_TOKEN
	 */
	public static void getAccessToken(){
		String appId = getParam("APPID");
		String appSecret = getParam("APPSECRET");
		String url = getParam("ACCESS_TOKEN_URL");
		url.replaceAll("APPID", appId).replaceAll("APPSECRET", appSecret);
		String result = null;
		try {
			result = HttpUtils.doGet(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(result);
	}
	
	
	
	

}
