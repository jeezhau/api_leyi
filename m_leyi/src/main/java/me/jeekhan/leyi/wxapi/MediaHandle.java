package me.jeekhan.leyi.wxapi;

import java.io.File;
import java.io.IOException;
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
	public Object addTempMedia(File file,String type){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=" + token + "&type=" + type;
		log.info("新增临时素材（POST）：" + url + ",媒体类型：" + type);
		String ret = "";
		try {
			ret = HttpUtils.uploadFileSSL(url, file,new HashMap<String,String>());
			log.info("新增临时素材（POST）返回：" + ret );
		} catch (IOException e) {
			log.info("新增临时素材（POST）失败：" + e.getMessage() );
		}
		
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	/**
	 * 获取临时素材
	 * @param mediaId
	 * @return
	 */
	public Object getTempMedia(String mediaId){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=" + token + "&media_id=" + mediaId;
		log.info("获取临时素材（GET）：" + url );
		byte[] ret = null;
		try {
			ret = HttpUtils.downloadFileSSL(url);
			log.info("获取临时素材（GET）返回：" + ret );
		} catch (IOException e) {
			log.info("获取临时素材（GET）失败：" + e.getMessage() );
		}
		return ret;
	}
	
	/**
	 * 获取临时视频素材
	 * @param mediaId
	 * @return
	 */
	public Object getTempVideo(String mediaId){
		String token = AccessToken.getAccessToken();
		String url = "http://api.weixin.qq.com/cgi-bin/media/get?access_token=" + token + "&media_id=" + mediaId;
		log.info("获取临时视频素材（GET）：" + url );
		byte[] ret = null;
		try {
			ret = HttpUtils.downloadFileSSL(url);
			log.info("获取临时视频素材（GET）返回：" + ret );
		} catch (IOException e) {
			log.info("获取临时视频素材（GET）失败：" + e.getMessage() );
		}
		return ret;
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
	public Object addPermanentNews(List<Map<String,Object>> articles){
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
		String ret = HttpUtils.doPost(url, jsonObj);
		JSONObject retObj = new JSONObject(ret);
		return retObj;
	}
	/**
	 * 上传图文消息内的图片获取URL 
	 * @param file	
	 * @return {"url":  "http://mmbiz.qpic.cn/mmbiz/gLO17UPS6FS2xsypf378iaNhWacZ1G1UplZYWEYfwvuU6Ont96b1roYs CNFwaRrSaKTPCUdBK9DgEHicsKwWCBRQ/0"}
	 */
	public Object addPermanentNewsImg(File file){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=" + token ;
		log.info("上传图文消息内的图片获取URL（POST）：" + url);
		String ret = "";
		try {
			ret = HttpUtils.uploadFileSSL(url, file,new HashMap<String,String>());
			log.info("上传图文消息内的图片获取URL（POST）返回：" + ret );
		} catch (IOException e) {
			log.info("上传图文消息内的图片获取URL（POST）失败：" + e.getMessage() );
		}
		
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
	public Object addPernamentMedia(File file ,String type){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=" + token + "&type=" + type;
		log.info("新增其他类型永久素材（POST）：" + url + ",媒体类型：" + type);
		String ret = "";
		try {
			ret = HttpUtils.uploadFileSSL(url, file,new HashMap<String,String>());
			log.info("新增其他类型永久素材（POST）返回：" + ret );
		} catch (IOException e) {
			log.info("新增其他类型永久素材（POST）失败：" + e.getMessage() );
		}
		
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
	public Object addPernamentVideo(File file,String title,String introduction){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=" + token ;
		log.info("新增视频永久素材（POST）：" + url );
		String ret = "";
		try {
			Map<String,String> map = new HashMap<String,String>();
			map.put("title",title);
			map.put("introduction", introduction);
			ret = HttpUtils.uploadFileSSL(url, file,map);
			log.info("新增视频永久素材（POST）返回：" + ret );
		} catch (IOException e) {
			log.info("新增视频永久素材（POST）失败：" + e.getMessage() );
		}
		
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	
}
