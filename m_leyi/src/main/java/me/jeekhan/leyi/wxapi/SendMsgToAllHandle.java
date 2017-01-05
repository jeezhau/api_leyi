package me.jeekhan.leyi.wxapi;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.jeekhan.leyi.common.HttpUtils;

/**
 * 高级消息群发
 * 订阅号提供了每天一条的群发权限，为服务号提供每月（自然月）4条的群发权限。而对于某些具备开发能力的公众号运营者，可以通过高级群发接口，实现更灵活的群发能力
 * 消息发送返回：{ "errcode":0,"errmsg":"send job submission success","msg_id":34182, "msg_data_id": 206227730}
 * @author Jee Khan
 *
 */
public class SendMsgToAllHandle {
	private static Logger log = LoggerFactory.getLogger(CustomServiceMsgHandle.class);
	
	/**
	 * 上传图文消息内的图片
	 * 本接口所上传的图片不占用公众号的素材库中图片数量的5000个的限制。图片仅支持jpg/png格式，大小必须在1MB以下
	 * http请求方式: POST https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN
	 * 调用示例（使用curl命令，用FORM表单方式上传一个图片）：
	 * curl -F media=@test.jpg "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN"
	 * @param file
	 * @return 接口正确返回：{"url":  "http://mmbiz.qpic.cn/mmbiz/gLO17UPS6FS2xsypf378iaNhWacZ1G1UplZYWEYfwvuU6Ont96b1roYs CNFwaRrSaKTPCUdBK9DgEHicsKwWCBRQ/0"}
	 */
	public static Object uploadImg(File file){
		
		return null;
	}
	
	/**
	 * 上传图文消息素材
	 * http请求方式: POST https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN
	 * @param list（articles） 图文消息，一个图文消息支持1到8条图文 
	 * 	thumb_media_id ：图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得
	 * 	author ：图文消息的作者，可为空
	 * 	title ：图文消息的标题 
	 * 	content_source_url ：在图文消息页面点击“阅读原文”后的页面 ，可为空
	 * 	content：图文消息页面的内容，支持HTML标签。具备微信支付权限的公众号，可以使用a标签，其他公众号不能使用 
	 * 	digest ：图文消息的描述 ，可为空
	 * 	show_cover_pic：是否显示封面，1为显示，0为不显示 ，可为空
	 * @return	接口正确返回：{"type":"news","media_id":"CsEf3ldqkAYJAU6EJeIkStVDSvffUJ54vqbThMgplD-VJXXof6ctX5fI6-aYyUiQ","created_at":1391857799}
	 */
	public static Object uploadNews(List<Map<String,String>> list){
		JSONObject jsonObj = new JSONObject();
		JSONArray articles = new JSONArray();
		for(Map<String,String> item:list){
			JSONObject article = new JSONObject();
			article.put("thumb_media_id", item.get("thumb_media_id"));
			article.put("author", item.get("author"));
			article.put("title", item.get("title"));
			article.put("content_source_url", item.get("content_source_url"));
			article.put("content", item.get("content"));
			article.put("digest", item.get("digest"));
			article.put("show_cover_pic", item.get("show_cover_pic"));
			articles.put(article);
		}
		jsonObj.put("articles", articles);
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=" + token;
		log.info("上传图文消息素材（POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("上传图文消息素材（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	/**
	 * 获取通过基础控件上传的视频素材
	 * 返回的 media_id 用于群发视频消息时使用
	 * http请求方式: POST https://file.api.weixin.qq.com/cgi-bin/media/uploadvideo?access_token=ACCESS_TOKEN
	 * 
	 * @param mediaId		需通过基础支持中的上传下载多媒体文件来得到
	 * @param title
	 * @param description
	 * @return	{"type":"video","media_id":"IhdaAQXuvJtGzwwc0abfXnzeezfO0NgPK6AQYShD8RQYMTtfzbLdBIQkQziv2XJc","created_at":1398848981}
	 */
	public static Object uploadVideo(String mediaId,String title,String description){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("media_id", mediaId);
		jsonObj.put("title", title);
		jsonObj.put("description", description);
		String token = AccessToken.getAccessToken();
		String url = "https://file.api.weixin.qq.com/cgi-bin/media/uploadvideo?access_token=" + token;
		log.info("获取通过基础控件上传的视频素材（POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("获取通过基础控件上传的视频素材（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	/**
	 * 根据分组进行群发图文消息
	 * http请求方式: POST https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN
	 * @param mediaId	图文消息的媒体ID
	 * @param groupId	分组ID 
	 * @return
	 */
	public static Object sendNewsMsg(String groupId,String mediaId){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("msgtype", "mpnews");
		JSONObject mpnews = new JSONObject();
		mpnews.put("media_id", mediaId);
		jsonObj.put("mpnews", mpnews);
		JSONObject filter = new JSONObject();
		filter.put("is_to_all", false);
		filter.put("group_id", groupId);
		jsonObj.put("filter", filter);
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=" + token;
		log.info("根据分组进行群发（POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("根据分组进行群发（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}

	/**
	 * 根据分组进行群发文本消息
	 * http请求方式: POST https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN
	 * @param content	文本消息内容
	 * @param groupId	分组ID 
	 * @return
	 */
	public static Object sendTextMsg(String groupId,String content){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("msgtype", "text");
		JSONObject text = new JSONObject();
		text.put("content", content);
		jsonObj.put("text", text);
		JSONObject filter = new JSONObject();
		filter.put("is_to_all", false);
		filter.put("group_id", groupId);
		jsonObj.put("filter", filter);
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=" + token;
		log.info("根据分组进行群发文本消息（POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("根据分组进行群发文本消息（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	/**
	 * 根据分组进行群发语音消息
	 * http请求方式: POST https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN
	 * @param mediaId	需通过基础支持中的上传下载多媒体文件来得到
	 * @param groupId	分组ID 
	 * @return
	 */
	public static Object sendVoiceMsg(String groupId,String mediaId){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("msgtype", "voice");
		JSONObject voice = new JSONObject();
		voice.put("media_id", mediaId);
		jsonObj.put("voice", voice);
		JSONObject filter = new JSONObject();
		filter.put("is_to_all", false);
		filter.put("group_id", groupId);
		jsonObj.put("filter", filter);
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=" + token;
		log.info("根据分组进行群发语音消息（POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("根据分组进行群发语音消息（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	/**
	 * 根据分组进行群发图片消息
	 * http请求方式: POST https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN
	 * @param mediaId	需通过基础支持中的上传下载多媒体文件来得到
	 * @param groupId	分组ID 
	 * @return
	 */
	public static Object sendImageMsg(String groupId,String mediaId){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("msgtype", "image");
		JSONObject image = new JSONObject();
		image.put("media_id", mediaId);
		jsonObj.put("image", image);
		JSONObject filter = new JSONObject();
		filter.put("is_to_all", false);
		filter.put("group_id", groupId);
		jsonObj.put("filter", filter);
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=" + token;
		log.info("根据分组进行群发图片消息（POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("根据分组进行群发图片消息（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	
	
	/**
	 * 根据分组进行群发视频消息
	 * http请求方式: POST https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN
	 * @param mediaId	由已上传的视频获得
	 * @param groupId	分组ID 
	 * @return
	 */
	public static Object sendVideoMsg(String groupId,String mediaId){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("msgtype", "mpvideo");
		JSONObject mpvideo = new JSONObject();
		mpvideo.put("media_id", mediaId);
		jsonObj.put("mpvideo", mpvideo);
		JSONObject filter = new JSONObject();
		filter.put("is_to_all", false);
		filter.put("group_id", groupId);
		jsonObj.put("filter", filter);
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=" + token;
		log.info("根据分组进行群发视频消息（POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("根据分组进行群发视频消息（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	/**
	 * 根据分组进行群发卡券消息
	 * http请求方式: POST https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN
	 * @param mediaId	
	 * @param groupId	分组ID 
	 * @return
	 */
	public static Object sendWxcardMsg(String groupId,String mediaId){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("msgtype", "wxcard");
		JSONObject wxcard = new JSONObject();
		wxcard.put("media_id", mediaId);
		jsonObj.put("wxcard", wxcard);
		JSONObject filter = new JSONObject();
		filter.put("is_to_all", false);
		filter.put("group_id", groupId);
		jsonObj.put("filter", filter);
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=" + token;
		log.info("根据分组进行群发卡券消息（POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("根据分组进行群发卡券消息（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	/**
	 * 根据OpenID列表群发图文消息
	 * http请求方式: POST https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN
	 * @param mediaId	图文消息的媒体ID
	 * @param openIds	OpenID列表 
	 * @return
	 */
	public static Object sendNewsOpenIdsMsg(List<String> openIds,String mediaId){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("msgtype", "mpnews");
		JSONObject mpnews = new JSONObject();
		mpnews.put("media_id", mediaId);
		jsonObj.put("mpnews", mpnews);
		JSONArray touser = new JSONArray();
		for(String openId:openIds){
			touser.put(openId);
		}
		jsonObj.put("touser", touser);
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=" + token;
		log.info("根据分组进行群发（POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("根据分组进行群发（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}

	/**
	 * 根据OpenID列表群发文本消息
	 * http请求方式: POST https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN
	 * @param content	文本消息内容
	 * @param openIds	OpenID列表  
	 * @return
	 */
	public static Object sendTextMsg(List<String> openIds,String content){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("msgtype", "text");
		JSONObject text = new JSONObject();
		text.put("content", content);
		jsonObj.put("text", text);
		JSONArray touser = new JSONArray();
		for(String openId:openIds){
			touser.put(openId);
		}
		jsonObj.put("touser", touser);
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=" + token;
		log.info("根据分组进行群发文本消息（POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("根据分组进行群发文本消息（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	/**
	 * 根据OpenID列表群发语音消息
	 * http请求方式: POST https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN
	 * @param mediaId	需通过基础支持中的上传下载多媒体文件来得到
	 * @param openIds	OpenID列表 
	 * @return
	 */
	public static Object sendVoiceMsg(List<String> openIds,String mediaId){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("msgtype", "voice");
		JSONObject voice = new JSONObject();
		voice.put("media_id", mediaId);
		jsonObj.put("voice", voice);
		JSONArray touser = new JSONArray();
		for(String openId:openIds){
			touser.put(openId);
		}
		jsonObj.put("touser", touser);
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=" + token;
		log.info("根据分组进行群发语音消息（POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("根据分组进行群发语音消息（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	/**
	 * 根据OpenID列表进行群发图片消息
	 * http请求方式: POST https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN
	 * @param mediaId	需通过基础支持中的上传下载多媒体文件来得到
	 * @param openIds	OpenID列表 
	 * @return
	 */
	public static Object sendImageMsg(List<String> openIds,String mediaId){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("msgtype", "image");
		JSONObject image = new JSONObject();
		image.put("media_id", mediaId);
		jsonObj.put("image", image);
		JSONArray touser = new JSONArray();
		for(String openId:openIds){
			touser.put(openId);
		}
		jsonObj.put("touser", touser);
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=" + token;
		log.info("根据分组进行群发图片消息（POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("根据分组进行群发图片消息（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	
	
	/**
	 * 根据OpenID列表进行群发视频消息
	 * http请求方式: POST https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN
	 * @param mediaId	由已上传的视频获得
	 * @param title			
	 * @param description	
	 * @param openIds	OpenID列表 
	 * @return
	 */
	public static Object sendVideoMsg(List<String> openIds,String mediaId,String title,String description){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("msgtype", "video");
		JSONObject mpvideo = new JSONObject();
		mpvideo.put("media_id", mediaId);
		mpvideo.put("title", title);
		mpvideo.put("media_id", mediaId);
		jsonObj.put("description", description);
		JSONArray touser = new JSONArray();
		for(String openId:openIds){
			touser.put(openId);
		}
		jsonObj.put("touser", touser);
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=" + token;
		log.info("根据分组进行群发视频消息（POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("根据分组进行群发视频消息（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	/**
	 * 根据OpenID列表进行群发卡券消息
	 * http请求方式: POST https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN
	 * @param mediaId	
	 * @param openIds	OpenID列表  
	 * @return
	 */
	public static Object sendWxcardMsg(List<String> openIds,String mediaId){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("msgtype", "wxcard");
		JSONObject wxcard = new JSONObject();
		wxcard.put("media_id", mediaId);
		jsonObj.put("wxcard", wxcard);
		JSONArray touser = new JSONArray();
		for(String openId:openIds){
			touser.put(openId);
		}
		jsonObj.put("touser", touser);
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=" + token;
		log.info("根据分组进行群发卡券消息（POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("根据分组进行群发卡券消息（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	/**
	 * 删除群发
	 * 群发只有在刚发出的半小时内可以删除，发出半小时之后将无法被删除
	 * http请求方式: POST https://api.weixin.qq.com/cgi-bin/message/mass/delete?access_token=ACCESS_TOKEN
	 * @param msgId
	 * @return	00-cg，【其他】-失败信息
	 */
	public static String deleteSendedMsg(String msgId){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/mass/delete?access_token=" + token;
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("msg_id", "msgId");
		log.info("删除群发（POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("删除群发（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		if(jsonRet.has("errcode") && jsonRet.getInt("errcode") == 0){
			return "00";
		}else{
			if(jsonRet.has("errmsg")){
				return jsonRet.getString("errmsg");
			}else{
				return "删除群发失败";
			}
		}
	}
	
	/**
	 * 查询群发消息发送状态
	 * http请求方式: POST https://api.weixin.qq.com/cgi-bin/message/mass/get?access_token=ACCESS_TOKEN
	 * @param msgId	消息ID
	 * @return	
	 */
	public static boolean getSendStatus(String msgId){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/mass/get?access_token=" + token;
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("msg_id", "msgId");
		log.info("查询群发消息发送状态（POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("查询群发消息发送状态（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		if(jsonRet.has("msg_status") && "SEND_SUCCESS".equals(jsonRet.getString("msg_status"))){
			return true;
		}else{
			return false;
		}
	}
	
}
