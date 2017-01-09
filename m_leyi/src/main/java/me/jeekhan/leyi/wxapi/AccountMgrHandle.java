package me.jeekhan.leyi.wxapi;

import java.io.File;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.jeekhan.leyi.common.HttpUtils;

/**
 * 公众账号管理
 * @author Jee Khan
 *
 */
public class AccountMgrHandle {
	private  Logger log = LoggerFactory.getLogger(AccountMgrHandle.class);
	/**
	 * 创建二维码ticket
	 * @param sence_id		场景值ID，临时二维码时为32位非0整型
	 * @param expire_seconds	该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒
	 * @return {"ticket":"gQH47joAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2taZ2Z3TVRtNzJXV1Brb3ZhYmJJAAIEZ23sUwMEmm3sUw==","expire_seconds":60,"url":"http:\/\/weixin.qq.com\/q\/kZgfwMTm72WWPkovabbI"}
	 * 	ticket：获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码
	 * 	expire_seconds：该二维码有效时间，以秒为单位。 最大不超过2592000（即30天）。
	 * 	url：二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
	 */
	public Object createTempTicket(int sence_id,int expire_seconds ){
		String token = AccessToken.getAccessToken();
		String url = " https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + token ;
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("expire_seconds", expire_seconds);
		jsonObj.put("action_name", "QR_SCENE");
		JSONObject action_info = new JSONObject();
		action_info.put("scene_id",sence_id);
		jsonObj.put("action_info", action_info);
		log.info("创建临时二维码ticket（POST）" + url + "，参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("创建临时二维码ticket（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	/**
	 * 创建二维码ticket
	 * @param scene_str		场景值ID（字符串形式的ID），字符串类型，长度限制为1到64，仅永久二维码支持此字段 
	 * @return {"ticket":"gQH47joAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2taZ2Z3TVRtNzJXV1Brb3ZhYmJJAAIEZ23sUwMEmm3sUw==","url":"http:\/\/weixin.qq.com\/q\/kZgfwMTm72WWPkovabbI"}
	 * 	ticket：获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码
	 * 	url：二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
	 */
	public Object createTicket(String scene_str){
		String token = AccessToken.getAccessToken();
		String url = " https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + token ;
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("action_name", "QR_LIMIT_SCENE");
		JSONObject action_info = new JSONObject();
		action_info.put("scene_str",scene_str);
		jsonObj.put("action_info", action_info);
		log.info("创建二维码ticket（POST）" + url + "，参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("创建二维码ticket（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	/**
	 * 创建二维码ticket
	 * @param sence_id		场景值ID，永久二维码时最大值为100000（目前参数只支持1--100000） 
	 * @return {"ticket":"gQH47joAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2taZ2Z3TVRtNzJXV1Brb3ZhYmJJAAIEZ23sUwMEmm3sUw==","url":"http:\/\/weixin.qq.com\/q\/kZgfwMTm72WWPkovabbI"}
	 * 	ticket：获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码
	 * 	url：二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
	 */
	public Object createTicket(int scene_id){
		String token = AccessToken.getAccessToken();
		String url = " https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + token ;
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("action_name", "QR_LIMIT_STR_SCENE");
		JSONObject action_info = new JSONObject();
		action_info.put("scene_id",scene_id);
		jsonObj.put("action_info", action_info);
		log.info("创建二维码ticket（POST）" + url + "，参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("创建二维码ticket（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	/**
	 * 通过ticket换取二维码
	 * @param ticket
	 * @return
	 */
	public File getQRCode(String ticket){
		String url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + ticket;
		log.info("通过ticket换取二维码(GET):" + url);
		File file = HttpUtils.downloadFileSSL(url);
		if(file != null){
			log.info("通过ticket换取二维码(GET)成功");
		}else{
			log.info("通过ticket换取二维码(GET)失败");
		}
		return file;
	}
}
