package me.jeekhan.leyi.wxapi;

import java.io.File;
import java.util.HashMap;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.jeekhan.leyi.common.HttpUtils;

public class WXCardHandle {
	private static Logger log = LoggerFactory.getLogger(WXCardHandle.class);
	
	/**
	 * 上传图片
	 * 1.上传的图片限制文件大小限制1MB，像素为300*300，仅支持JPG、PNG格式。 
	 * 2.调用接口获取的logo_url仅支持在微信相关业务下使用
	 * @param file
	 * @return	{"url":"http://mmbiz.qpic.cn/mmbiz/iaL1LJM1mF9aRKPZJkmG8xXhiaHqkKSVMMWeN3hLut7X7hicFNjakmxibMLGWpXrEXB33367o7zHN0CwngnQY7zb7g/0"}
	 */
	public static Object uploadImg(File file){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=" + token ;
		log.info("上传图片（POST）：" + url);
		String ret = HttpUtils.uploadFileSSL(url, file,"buffer",new HashMap<String,String>());
		log.info("上传图片返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	/**
	 * 创建卡券
	 * @param cardInfo
	 * @return {"errcode":0,"errmsg":"ok","card_id":"p1Pj9jr90_SQRaVqYI239Ka1erkI"}
	 */
	public static Object createCard(JSONObject cardInfo){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/card/create?access_token=" + token ;
		log.info("创建卡券（POST）：" + url + "，参数：" + cardInfo);
		String ret = HttpUtils.doPostSSL(url, cardInfo);
		log.info("创建卡券返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	/**
	 * 设置卡券支持微信买单
	 * @param cardId	卡券ID
	 * @param isOpen	是否开启买单功能，填true/false 
	 * @return 00-成功，【其他】-失败信息
	 */
	public static String openWXPay(String cardId,boolean isOpen){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/card/create?access_token=" + token ;
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("card_id", cardId);
		jsonObj.put("is_open", isOpen);
		log.info("设置卡券支持微信买单（POST）：" + url + "，参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("设置卡券支持微信买单返回：" + ret );
		JSONObject retObj = new JSONObject(ret);
		if(retObj.has("errcode") && retObj.getInt("errcode") == 0){
			return "00";
		}else{
			if(retObj.has("errmsg")){
				return retObj.getString("errmsg");
			}else{
				return "设置卡券支持微信买单失败！";
			}
		}
	}
	
	/**
	 * 设置开通自助核销
	 * @param cardId	卡券ID
	 * @param isOpen	是否开启自助核销功能，填true/false 
	 * @return
	 */
	public static String openCheckSell(String cardId,boolean isOpen){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/card/selfconsumecell/set?access_token=" + token ;
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("card_id", cardId);
		jsonObj.put("is_open", isOpen);
		log.info("设置开通自助核销（POST）：" + url + "，参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("设置开通自助核销返回：" + ret );
		JSONObject retObj = new JSONObject(ret);
		if(retObj.has("errcode") && retObj.getInt("errcode") == 0){
			return "00";
		}else{
			if(retObj.has("errmsg")){
				return retObj.getString("errmsg");
			}else{
				return "设置开通自助核销失败！";
			}
		}
	}
}
