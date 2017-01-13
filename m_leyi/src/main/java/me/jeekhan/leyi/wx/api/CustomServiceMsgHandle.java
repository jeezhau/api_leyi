package me.jeekhan.leyi.wx.api;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.jeekhan.leyi.common.HttpUtils;
import me.jeekhan.leyi.wx.AccessToken;

/**
 * 客服消息
 * 
 * @author Jee Khan
 *
 */
public class CustomServiceMsgHandle {
	private static Logger log = LoggerFactory.getLogger(CustomServiceMsgHandle.class);
	
	/**
	 * 客服发送文本消息
	 * http请求方式: POST https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN
	 * @param toUser	普通用户openid 
	 * @param content	文本消息内容 
	 * @param account	客服账号，可为空
	 * @return	00-成功，【其他】-失败信息
	 */
	public static String sendTextMsg(String toUser,String content,String account){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("touser", toUser);
		jsonObj.put("msgtype", "text");
		JSONObject text = new JSONObject();
		text.put("content", content);
		jsonObj.put("text", text);
		if(account != null && account.length()>0){
			JSONObject customservice = new JSONObject();
			customservice.put("kf_account", account);
			jsonObj.put("customservice", customservice);
		}
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + token;
		log.info("客服发送文本消息（POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("客服发送文本消息（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		if(jsonRet.has("errcode") && jsonRet.getInt("errcode") == 0){
			return "00";
		}else{
			if(jsonRet.has("errmsg")){
				return jsonRet.getString("errmsg");
			}else{
				return "客服发送文本消息失败";
			}
		}
	}
	
	/**
     * 客服发送图片消息 
     * @param toUser	普通用户openid 
     * @param mediaId	发送的图片的媒体ID 
     * @param account	客服账号，可为空
	 * @return
	 */
	public static Object sendImageMsg(String toUser,String mediaId,String account){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("touser", toUser);
		jsonObj.put("msgtype", "image");
		JSONObject image = new JSONObject();
		image.put("media_id", mediaId);
		jsonObj.put("image", image);
		if(account != null && account.length()>0){
			JSONObject customservice = new JSONObject();
			customservice.put("kf_account", account);
			jsonObj.put("customservice", customservice);
		}
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + token;
		log.info("客服发送图片消息 （POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("客服发送图片消息（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		if(jsonRet.has("errcode") && jsonRet.getInt("errcode") == 0){
			return "00";
		}else{
			if(jsonRet.has("errmsg")){
				return jsonRet.getString("errmsg");
			}else{
				return "客服发送图片消息 失败";
			}
		}
	}
	
	/**
	 * 客服发送语音消息
	 * @param toUser	普通用户openid 
	 * @param mediaId	语音的媒体ID 
	 * @param account	客服账号，可为空
	 * @return
	 */
	public static Object sendVoiceMsg(String toUser,String mediaId,String account){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("touser", toUser);
		jsonObj.put("msgtype", "voice");
		JSONObject voice = new JSONObject();
		voice.put("media_id", mediaId);
		jsonObj.put("voice", voice);
		if(account != null && account.length()>0){
			JSONObject customservice = new JSONObject();
			customservice.put("kf_account", account);
			jsonObj.put("customservice", customservice);
		}
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + token;
		log.info("客服发送语音消息 （POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("客服发送语音消息  （POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		if(jsonRet.has("errcode") && jsonRet.getInt("errcode") == 0){
			return "00";
		}else{
			if(jsonRet.has("errmsg")){
				return jsonRet.getString("errmsg");
			}else{
				return "客服发送语音消息 失败";
			}
		}
	}
	
	/**
	 * 客服发送视频消息
	 * @param toUser		普通用户openid 
	 * @param mediaId		视频的媒体ID 
	 * @param thumbMediaId	缩略图的媒体ID 
	 * @param title			视频消息的标题 
	 * @param description	视频消息的描述 
	 * @param account		客服账号，可为空
	 * @return
	 */
	public static Object sendVideoMsg(String toUser,String mediaId,String thumbMediaId,String title,String description,String account){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("touser", toUser);
		jsonObj.put("msgtype", "video");
		JSONObject video = new JSONObject();
		video.put("media_id", mediaId);
		video.put("thumb_media_id", thumbMediaId);
		video.put("title", title);
		video.put("description", description);
		jsonObj.put("video", video);
		if(account != null && account.length()>0){
			JSONObject customservice = new JSONObject();
			customservice.put("kf_account", account);
			jsonObj.put("customservice", customservice);
		}
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + token;
		log.info("客服发送视频消息（POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("客服发送视频消息（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		if(jsonRet.has("errcode") && jsonRet.getInt("errcode") == 0){
			return "00";
		}else{
			if(jsonRet.has("errmsg")){
				return jsonRet.getString("errmsg");
			}else{
				return "客服发送视频消息失败";
			}
		}
	}
	
	/**
	 * 客服发送音乐消息
	 * @param toUser		普通用户openid 
	 * @param title			音乐消息的标题 
	 * @param description	音乐消息的描述 
	 * @param musicurl		音乐链接 
	 * @param hqmusicurl	高品质音乐链接，wifi环境优先使用该链接播放音乐 
	 * @param thumbMediaId	缩略图的媒体ID 
	 * @param account		客服账号，可为空
	 * @return
	 */
	public static Object sendMusicMsg(String toUser,String title,String description,String musicurl,String hqmusicurl,String thumbMediaId,String account){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("touser", toUser);
		jsonObj.put("msgtype", "music");
		JSONObject music = new JSONObject();
		music.put("title", title);
		music.put("description", description);
		music.put("musicurl", musicurl);
		music.put("hqmusicurl", hqmusicurl);
		music.put("thumb_media_id", thumbMediaId);
		jsonObj.put("music", music);
		if(account != null && account.length()>0){
			JSONObject customservice = new JSONObject();
			customservice.put("kf_account", account);
			jsonObj.put("customservice", customservice);
		}
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + token;
		log.info("客服发送视频消息（POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("客服发送视频消息（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		if(jsonRet.has("errcode") && jsonRet.getInt("errcode") == 0){
			return "00";
		}else{
			if(jsonRet.has("errmsg")){
				return jsonRet.getString("errmsg");
			}else{
				return "客服发送视频消息失败";
			}
		}
	}
	
	/**
	 * 发送图文消息（点击跳转到图文消息页面） 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应
	 * @param toUser	普通用户openid 
	 * @param mediaId	图文消息（点击跳转到图文消息页）的媒体ID
	 * @param account	客服账号，可为空 
	 * @return
	 */
	public static Object sendNewsMsg(String toUser,String mediaId,String account){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("touser", toUser);
		jsonObj.put("msgtype", "mpnews");
		JSONObject mpnews = new JSONObject();
		mpnews.put("media_id", mediaId);
		jsonObj.put("news", mpnews);
		if(account != null && account.length()>0){
			JSONObject customservice = new JSONObject();
			customservice.put("kf_account", account);
			jsonObj.put("customservice", customservice);
		}
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + token;
		log.info("客服发送图文消息（POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("客服发送图文消息（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		if(jsonRet.has("errcode") && jsonRet.getInt("errcode") == 0){
			return "00";
		}else{
			if(jsonRet.has("errmsg")){
				return jsonRet.getString("errmsg");
			}else{
				return "客服发送图文消息失败";
			}
		}
	}
	
	/**
	 * 客服发送图文消息（点击跳转到外链） 
	 * 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应。 
	 * @param toUser	普通用户openid 
	 * @param list(news)
	 * 	title 		图文消息的标题 
	 * 	description 图文消息的描述 
	 * 	url 		图文消息被点击后跳转的链接 
	 * 	picurl 		图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80 
	 * @param account	客服账号，可为空
	 * @return
	 */
	public static Object sendMPNewsMsg(String toUser,List<Map<String,String>> list,String account){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("touser", toUser);
		jsonObj.put("msgtype", "news");
		JSONObject news = new JSONObject();
		JSONArray articles = new JSONArray();
		for(Map<String,String> item:list){
			JSONObject article = new JSONObject();
			article.put("title", item.get("title"));
			article.put("description", item.get("description"));
			article.put("url", item.get("url"));
			article.put("picurl", item.get("picurl"));
			articles.put(article);
		}
		news.put("articles", articles);
		if(account != null && account.length()>0){
			JSONObject customservice = new JSONObject();
			customservice.put("kf_account", account);
			jsonObj.put("customservice", customservice);
		}
		jsonObj.put("news", news);
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + token;
		log.info("客服发送图文消息（POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("客服发送图文消息（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		if(jsonRet.has("errcode") && jsonRet.getInt("errcode") == 0){
			return "00";
		}else{
			if(jsonRet.has("errmsg")){
				return jsonRet.getString("errmsg");
			}else{
				return "客服发送图文消息失败";
			}
		}
	}
	
	/**
	 * 客服发送卡券 
     * 	{"touser":"OPENID", "msgtype":"wxcard",
     *   "wxcard":{"card_id":"123dsdajkasd231jhksad",
     *       "card_ext": "{\"code\":\"\",\"openid\":\"\",\"timestamp\":\"1402057159\",\"signature\":\"017bb17407c8e0058a66d72dcc61632b70f511ad\"}"            
     * 	},}
	 * @return
	 */
	public static Object sendWXCardMsg(String toUser,String wxcardInfo,String account){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("touser", toUser);
		jsonObj.put("msgtype", "wxcard");
		JSONObject wxcard = new JSONObject();
		wxcard.put("card_id", "");
		
		jsonObj.put("wxcard", wxcard);
		if(account != null && account.length()>0){
			JSONObject customservice = new JSONObject();
			customservice.put("kf_account", account);
			jsonObj.put("customservice", customservice);
		}
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + token;
		log.info("客服发送卡券（POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("客服发送卡券（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		if(jsonRet.has("errcode") && jsonRet.getInt("errcode") == 0){
			return "00";
		}else{
			if(jsonRet.has("errmsg")){
				return jsonRet.getString("errmsg");
			}else{
				return "客服发送卡券失败";
			}
		}
	}
	
}
