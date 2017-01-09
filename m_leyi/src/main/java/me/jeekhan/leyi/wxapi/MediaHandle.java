package me.jeekhan.leyi.wxapi;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.jeekhan.leyi.common.HttpUtils;

/**
 * 多媒体素材管理
 * @author Jee Khan
 *
 */
public class MediaHandle {
	private static Logger log = LoggerFactory.getLogger(MediaHandle.class);
	
	/**
	 * 新增临时素材
	 * 媒体文件在后台保存时间为3天，即3天后media_id失效
	 * 图片（image）: 2M，支持bmp/png/jpeg/jpg/gif格式 
	 * 语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式 
	 * 视频（video）：10MB，支持MP4格式 
	 * 缩略图（thumb）：64KB，支持JPG格式
	 * @param file
	 * @param content_type	文件的MIME类型
	 * @param type	媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb） 
	 * @return	{"type":"TYPE","media_id":"MEDIA_ID","created_at":123456789}
	 */
	public static Object addTempMedia(File file,String type){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=" + token + "&type=" + type;
		log.info("新增临时素材（POST）：" + url + ",媒体类型：" + type);
		String ret = HttpUtils.uploadFileSSL(url, file,"media",new HashMap<String,String>());
		log.info("新增临时素材（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	/**
	 * 获取临时素材
	 * @param mediaId
	 * @return
	 */
	public static File getTempMedia(String mediaId){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=" + token + "&media_id=" + mediaId;
		log.info("获取临时素材（GET）：" + url );
		File file = HttpUtils.downloadFileSSL(url);
		if(file != null){
			log.info("获取临时素材（GET）返回：" + file.getName() );
		}else{
			log.info("获取临时素材失败");
		}
		return file;
	}
	
	/**
	 * 获取临时视频素材
	 * @param mediaId
	 * @return
	 */
	public static File getTempVideo(String mediaId){
		String token = AccessToken.getAccessToken();
		String url = "http://api.weixin.qq.com/cgi-bin/media/get?access_token=" + token + "&media_id=" + mediaId;
		log.info("获取临时视频素材（GET）：" + url );
		File file = HttpUtils.downloadFileSSL(url);
		if(file != null){
			log.info("获取临时视频素材（GET）返回：" + file.getName() );
		}else{
			log.info("获取临时视频素材失败");
		}
		return file;
	}
	
	/**
	 * 新增永久图文素材
	 * 若新增的是多图文素材，则此处应有几段article结构
	 * @param articles
	 * 	title 			标题 
	 * 	thumb_media_id	图文消息的封面图片素材id（必须是永久mediaID）
	 * 	author 			作者
	 * 	digest 			图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
	 * 	show_cover_pic	是否显示封面，0为false，即不显示，1为true，即显示
	 *  content 		图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS 
	 *  content_source_url 	图文消息的原文地址，即点击“阅读原文”后的URL 
	 * @return {"media_id":MEDIA_ID}
	 */
	public static Object addPermanentNews(List<Map<String,Object>> articles){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=" + token ;
		JSONObject jsonObj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Map<String,Object> item:articles){
			JSONObject article = new JSONObject();
			article.put("title", item.get("title"));
			article.put("thumb_media_id", item.get("thumb_media_id"));
			article.put("author", item.get("author"));
			article.put("digest", item.get("digest"));
			article.put("show_cover_pic", item.get("show_cover_pic"));
			article.put("content", item.get("content"));
			article.put("content_source_url", item.get("content_source_url"));
			array.put(article);
		}
		jsonObj.put("articles", array);
		log.info("新增永久图文素材（POST）：" + url + "，参数：" + jsonObj);
		String ret = HttpUtils.doPost(url, jsonObj);
		log.info("新增永久图文素材（POST）返回：" + ret);
		JSONObject retObj = new JSONObject(ret);
		return retObj;
	}
	/**
	 * 上传图文消息内的图片获取URL 
	 * @param file	
	 * @return {"url":  "http://mmbiz.qpic.cn/mmbiz/gLO17UPS6FS2xsypf378iaNhWacZ1G1UplZYWEYfwvuU6Ont96b1roYs CNFwaRrSaKTPCUdBK9DgEHicsKwWCBRQ/0"}
	 */
	public static Object addPermanentNewsImg(File file){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=" + token ;
		log.info("上传图文消息内的图片获取URL（POST）：" + url);
		String ret = HttpUtils.uploadFileSSL(url, file,"media",new HashMap<String,String>());
		log.info("上传图文消息内的图片获取URL（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	/**
	 * 新增其他类型永久素材
	 * @param file
	 * @param content_type
	 * @param type
	 * @return {"media_id":MEDIA_ID,"url":URL}
	 */
	public static Object addPernamentMedia(File file ,String type){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=" + token + "&type=" + type;
		log.info("新增其他类型永久素材（POST）：" + url + ",媒体类型：" + type);
		String ret =  HttpUtils.uploadFileSSL(url, file,"media",new HashMap<String,String>());
		log.info("新增其他类型永久素材（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	/**
	 * 新增视频永久素材
	 * @param file			视频文件
	 * @param title			视频素材的标题
	 * @param introduction	视频素材的描述 
	 * @return	{"media_id":MEDIA_ID,"url":URL}
	 */
	public static Object addPernamentVideo(File file,String title,String introduction){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=" + token ;
		log.info("新增视频永久素材（POST）：" + url );
		String ret = "";
		Map<String,String> map = new HashMap<String,String>();
		map.put("title",title);
		map.put("introduction", introduction);
		ret = HttpUtils.uploadFileSSL(url, file,"media",map);
		log.info("新增视频永久素材（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	/**
	 * 获取图文永久素材
	 * @param mediaId
	 * @return
	 */
	public static Object getPermanentNews(String mediaId){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=" + token ;
		JSONObject json = new JSONObject();
		json.put("media_id", mediaId);
		log.info("获取图文永久素材（POST）" + url + "，参数：" + json);
		String ret = HttpUtils.doPostSSL(url, json);
		log.info("获取图文永久素材（POST）返回：" + ret );
		JSONObject retObj = new JSONObject(ret);
		return retObj;
	}
	
	/**
	 * 获取视频永久素材
	 * @param mediaId
	 * @return { "title":TITLE,"description":DESCRIPTION,"down_url":DOWN_URL,}
	 */
	public static Object getPermanentVideo(String mediaId){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=" + token ;
		JSONObject json = new JSONObject();
		json.put("media_id", mediaId);
		log.info("获取视频永久素材（POST）" + url + "，参数：" + json);
		String ret = HttpUtils.doPostSSL(url, json);
		log.info("获取视频永久素材（POST）返回：" + ret );
		JSONObject retObj = new JSONObject(ret);
		return retObj;
	}
	
	/**
	 * 获取其他类型的素材
	 * @param mediaId
	 * @return
	 */
	public static File getPermanentMedia(String mediaId){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=" + token ;
		JSONObject json = new JSONObject();
		json.put("media_id", mediaId);
		log.info("获取视频永久素材（POST）" + url + "，参数：" + json);
		File file = HttpUtils.downloadFileSSL(url, json);
		if(file != null){
			log.info("获取视频永久素材（POST）返回：" + file.getName() );
		}else{
			log.info("获取视频永久素材（POST）失败" );
		}
		return file;
	}
	
	/**
	 * 删除永久素材
	 * @param mediaId
	 * @return 00-成功，【其他】-失败信息
	 */
	public static String deletePermanentMedia(String mediaId){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=" + token ;
		JSONObject json = new JSONObject();
		json.put("media_id", mediaId);
		log.info("删除永久素材（POST）" + url + "，参数：" + json);
		String ret = HttpUtils.doPostSSL(url, json);
		log.info("删除永久素材（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		if(jsonRet.has("errcode") && jsonRet.getInt("errcode") == 0){
			return "00";
		}else{
			if(jsonRet.has("errmsg")){
				return jsonRet.getString("errmsg");
			}else{
				return "删除永久素材失败";
			}
		}
	}
	
	/**
	 * 修改永久图文素材
	 * @param mediaId	媒体ID
	 * @param index		要更新的文章在图文消息中的位置（多图文消息时，此字段才有意义），第一篇为0 
	 * @param acticle	文章信息
	 * 	title 			标题 
	 * 	thumb_media_id	图文消息的封面图片素材id（必须是永久mediaID）
	 * 	author 			作者
	 * 	digest 			图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
	 * 	show_cover_pic	是否显示封面，0为false，即不显示，1为true，即显示
	 *  content 		图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS 
	 *  content_source_url 	图文消息的原文地址，即点击“阅读原文”后的URL 
	 * @return  00-成功，【其他】-失败信息
	 */
	public static String updatePermanentNews(String mediaId,String index,Map<String,String> acticle){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=" + token ;
		JSONObject jsonObj = new JSONObject();
		JSONObject article = new JSONObject();
		article.put("title", article.get("title"));
		article.put("thumb_media_id", article.get("thumb_media_id"));
		article.put("author", article.get("author"));
		article.put("digest", article.get("digest"));
		article.put("show_cover_pic", article.get("show_cover_pic"));
		article.put("content", article.get("content"));
		article.put("content_source_url", article.get("content_source_url"));
		jsonObj.put("articles", article);
		log.info("修改永久图文素材（POST）" + url + "，参数：" + jsonObj);
		String ret = HttpUtils.doPost(url, jsonObj);
		log.info("修改永久图文素材（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		if(jsonRet.has("errcode") && jsonRet.getInt("errcode") == 0){
			return "00";
		}else{
			if(jsonRet.has("errmsg")){
				return jsonRet.getString("errmsg");
			}else{
				return "修改永久图文素材失败";
			}
		}
	}
	
	/**
	 * 获取永久素材总数
	 * @return {"voice_count":COUNT,"video_count":COUNT,"image_count":COUNT, "news_count":COUNT}
	 */
	public static Object getPermanentCount(){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=" + token ;
		log.info("获取永久素材总数（GET）" + url );
		String ret = HttpUtils.doGetSSL(url);
		log.info("获取永久素材总数（GET）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	/**
	 * 获取永久素材列表
	 * @param type		素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news） 
	 * @param offset	从全部素材的该偏移位置开始返回，0表示从第一个素材 返回 
	 * @param count		返回素材的数量，取值在1到20之间 
	 * @return
	 */
	public static Object getPermanentList(String type,int offset,int count){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=" + token ;
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("type", type);
		jsonObj.put("offset", offset);
		jsonObj.put("count", count);
		log.info("获取永久素材列表（POST）" + url + "，参数：" + jsonObj);
		String ret = HttpUtils.doPost(url, jsonObj);
		log.info("获取永久素材列表（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	
}
