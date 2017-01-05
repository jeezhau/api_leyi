package me.jeekhan.leyi.wxapi;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.jeekhan.leyi.common.HttpUtils;

/**
 * 被动回复消息
 * 当用户发送消息给公众号时（或某些特定的用户操作引发的事件推送时），会产生一个POST请求，开发者可以在响应包中返回特定XML结构，来对该消息进行响应（现支持回复文本、图片、图文、语音、视频、音乐）。
 * 严格来说，发送被动响应消息其实并不是一种接口，而是对微信服务器发过来消息的一次回复
 * @author Jee Khan
 *
 */
public class RespMsgHandle {
	public static String USER_ID = WXSysParam.getParam("USER_ID");	//公众号
	private static Logger log = LoggerFactory.getLogger(RespMsgHandle.class);

	/**
	 * 回复文本消息
	 * @param toUser	接收方帐号（收到的OpenID） 
	 * @param content	回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示） 
	 * @return
	 */
	public static String respTextMsg(String toUser,String content){
		WXMap map = new WXMap();
		map.put("ToUserName", toUser);
		map.put("FromUserName", USER_ID);
		map.put("CreateTime", System.currentTimeMillis());
		map.put("MsgType","text");
		map.put("Content", content);
		return "<xml>" + map.toXml() + "</xml>";
	}
	
	/**
	 * 回复图片消息
	 * @param toUser	接收方帐号（收到的OpenID） 
	 * @param mediaId	通过素材管理接口上传多媒体文件，得到的id 
	 * @return
	 */
	public static String respImageMsg(String toUser,String mediaId){
		WXMap map = new WXMap();
		map.put("ToUserName", toUser);
		map.put("FromUserName", USER_ID);
		map.put("CreateTime", System.currentTimeMillis());
		map.put("MsgType","image");
		WXMap image = new WXMap();
		image.put("MediaId", mediaId);
		map.put("Image", image);
		return "<xml>" + map.toXml() + "</xml>";
	}
	
	/**
	 * 回复语音消息
	 * @param toUser	接收方帐号（收到的OpenID） 
	 * @param mediaId	通过素材管理接口上传多媒体文件，得到的id 
	 * @return
	 */
	public static String respVoiceMsg(String toUser,String mediaId){
		WXMap map = new WXMap();
		map.put("ToUserName", toUser);
		map.put("FromUserName", USER_ID);
		map.put("CreateTime", System.currentTimeMillis());
		map.put("MsgType","voice");
		WXMap voice = new WXMap();
		voice.put("MediaId", mediaId);
		map.put("Voice", voice);
		return "<xml>" + map.toXml() + "</xml>";
	}
	
	/**
	 * 回复视频消息
	 * @param toUser	接收方帐号（收到的OpenID） 
	 * @param mediaId	通过素材管理接口上传多媒体文件，得到的id 
	 * @param Title 	视频消息的标题 
	 * @param Description 视频消息的描述 
	 * @return
	 */
	public static String respVideoMsg(String toUser,String mediaId,String title,String description){
		WXMap map = new WXMap();
		map.put("ToUserName", toUser);
		map.put("FromUserName", USER_ID);
		map.put("CreateTime", System.currentTimeMillis());
		map.put("MsgType","video");
		WXMap video = new WXMap();
		video.put("MediaId", mediaId);
		video.put("Title", title);
		video.put("Description", description);
		map.put("Video", video);
		return "<xml>" + map.toXml() + "</xml>";
	}
	
	/**
	 * 回复音乐消息
	 * @param toUser	接收方帐号（收到的OpenID） 
	 * @param title		音乐标题 
	 * @param description	音乐描述 
	 * @param musicUrl		音乐链接 
	 * @param hQMusicUrl	高质量音乐链接，WIFI环境优先使用该链接播放音乐 
	 * @param thumbMediaId	缩略图的媒体id，通过素材管理接口上传多媒体文件，得到的id 
	 * @return
	 */
	public static String respMusicMsg(String toUser,String musicUrl,String title,String description,String hQMusicUrl,String thumbMediaId){
		WXMap map = new WXMap();
		map.put("ToUserName", toUser);
		map.put("FromUserName", USER_ID);
		map.put("CreateTime", System.currentTimeMillis());
		map.put("MsgType","music");
		WXMap music = new WXMap();
		music.put("Title", title);
		music.put("Description", description);
		music.put("MusicUrl", musicUrl);
		music.put("HQMusicUrl", hQMusicUrl);
		music.put("ThumbMediaId", thumbMediaId);
		map.put("Music", music);
		return "<xml>" + map.toXml() + "</xml>";
	}
	
	/**
	 * 回复图文消息
	 * @param toUser			接收方帐号（收到的OpenID） 
	 * @param ArticleCount 		图文消息个数，限制为10条以内 
	 * @param list(Articles) 	多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
	 * 	Title 		图文消息标题 
	 * 	Description 图文消息描述
	 * 	PicUrl 		图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
	 * 	Url			点击图文消息跳转链接
	 * @return
	 */
	public static String respVoiceMsg(String toUser,List<Map<String,Object>> list){
		WXMap map = new WXMap();
		map.put("ToUserName", toUser);
		map.put("FromUserName", USER_ID);
		map.put("CreateTime", System.currentTimeMillis());
		map.put("MsgType","news");
		map.put("ArticleCount", list.size());
		String news = "";
		for(Map<String,Object> item:list){
			WXMap m = new WXMap();
			m.put("Title", item.get("Title"));
			m.put("Description", item.get("Description"));
			m.put("PicUrl", item.get("PicUrl"));
			m.put("Url", item.get("Url"));
			news += "<item>" + m.toXml() + "</item>";
		}
		map.put("Articles", news);
		return "<xml>" + map.toXml() + "</xml>";
	}
	
	/**
	 * 获取自动回复规则
	 * http请求方式: GET https://api.weixin.qq.com/cgi-bin/get_current_autoreply_info?access_token=ACCESS_TOKEN
	 * @return
	 */
	public static Object uploadVideo(){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/get_current_autoreply_info?access_token=" + token;
		log.info("获取自动回复规则（POST）：" + url);
		String ret = HttpUtils.doGetSSL(url);
		log.info("获取自动回复规则（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
}
