package me.jeekhan.leyi.wx;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.jeekhan.leyi.common.HttpUtils;

/**
 * 调用微信通用、基础API
 * @author Jee Khan
 *
 */
public class AccessToken {
	private static Logger log = LoggerFactory.getLogger(AccessToken.class);
	private static String ACCESS_TOKEN = "";	//访问凭证
	private static long LASTUPDTIME = 0L;		//上次获取时间
	private static long EXPIRESIN = 0L;			//有效时间
	
	/**
	 * 获取微信接口访问凭证：ACCESS_TOKEN
	 */
	public static String getAccessToken(){
		long cutTime = System.currentTimeMillis();
		long needTime = LASTUPDTIME + EXPIRESIN*1000 + 10000;//提前10更新
		if(ACCESS_TOKEN == null || ACCESS_TOKEN.length()<1 || cutTime >= needTime){
			String appId = WXSysParam.getParam("APPID");
			String appSecret = WXSysParam.getParam("APPSECRET");
			String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
			url.replaceAll("APPID", appId).replaceAll("APPSECRET", appSecret);
			String result = null;
			JSONObject json = null;
			//返回格式：{"access_token":"ACCESS_TOKEN","expires_in":7200}
			//		   {"errcode":40013,"errmsg":"invalid appid"}
			result = HttpUtils.doGet(url);
			json = new JSONObject(result);
			if(json != null){
				if(json.has("access_token")){
					ACCESS_TOKEN = json.getString("access_token");
					EXPIRESIN = json.getLong("expires_in");
					LASTUPDTIME = System.currentTimeMillis();
					log.info("ACCESS_TOKEN获取返回成功：" + result);
					return ACCESS_TOKEN;
				}else if(json.has("errcode")){
					log.info("ACCESS_TOKEN获取返回失败，失败信息：" + json.getString("errmsg"));
				}
			}
		}
		return null;
	}
	
	
	
	

}
