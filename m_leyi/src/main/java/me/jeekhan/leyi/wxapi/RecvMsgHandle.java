package me.jeekhan.leyi.wxapi;

import java.util.Map;

/**
 * 接收微信消息，并作相应的处理
 * @author Administrator
 *
 */
public class RecvMsgHandle {
	//根据接收到的消息的类型做出相应的处理
	public Object handle(Map<String,String> msgMap){
		String msgType = msgMap.get("MsgType");
		if("text".equals(msgType)){
			//文本消息
			return recvdText(msgMap);
		} else if("event".equals(msgType)){
			//事件消息
			
		}
		return null;
	}
	
	
	/**
	 * 接收到文本消息
	 *  <xml>
	 * <ToUserName><![CDATA[toUser]]></ToUserName>
	 * <FromUserName><![CDATA[fromUser]]></FromUserName> 
	 * <CreateTime>1348831860</CreateTime>
	 * <MsgType><![CDATA[text]]></MsgType>
	 * <Content><![CDATA[this is a test]]></Content>
	 * <MsgId>1234567890123456</MsgId>
	 * </xml>
	 * @param msgMap
	 * @return
	 */
	protected String recvdText(Map<String,String> msgMap){
		
		return "success";
	}
	
	/**
	 * 接收到图片消息
	 *  <xml>
	 *  <ToUserName><![CDATA[toUser]]></ToUserName>
	 *  <FromUserName><![CDATA[fromUser]]></FromUserName>
	 *  <CreateTime>1348831860</CreateTime>
	 *  <MsgType><![CDATA[image]]></MsgType>
	 *  <PicUrl><![CDATA[this is a url]]></PicUrl>
	 *  <MediaId><![CDATA[media_id]]></MediaId>
	 *  <MsgId>1234567890123456</MsgId>
	 *  </xml>
	 */
	protected Object recvdImage(Map<String,String> msgMap){
		
		return null;
	}
	
	/**
	 * 语音消息
	 * <xml>
	 * <ToUserName><![CDATA[toUser]]></ToUserName>
	 * <FromUserName><![CDATA[fromUser]]></FromUserName>
	 * <CreateTime>1357290913</CreateTime>
	 * <MsgType><![CDATA[voice]]></MsgType>
	 * <MediaId><![CDATA[media_id]]></MediaId>
	 * <Format><![CDATA[Format]]></Format>
	 * <Recognition><![CDATA[腾讯微信团队]]></Recognition>
	 * <MsgId>1234567890123456</MsgId>
	 * </xml>
	 */
	protected Object recvdVoice(Map<String,String> msgMap){
		
		return null;
	}
	
	
	/**
	 * 视频消息
	 * <xml>
	 * <ToUserName><![CDATA[toUser]]></ToUserName>
	 * <FromUserName><![CDATA[fromUser]]></FromUserName>
	 * <CreateTime>1357290913</CreateTime>
	 * <MsgType><![CDATA[video]]></MsgType>
	 * <MediaId><![CDATA[media_id]]></MediaId>
	 * <ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId>
	 * <MsgId>1234567890123456</MsgId>
	 * </xml>
	 * 	 */
	protected Object recvdVideo(Map<String,String> msgMap){
		
		return null;
	}
	
	/**
	 * 小视频消息
	 * <xml>
	 * <ToUserName><![CDATA[toUser]]></ToUserName>
	 * <FromUserName><![CDATA[fromUser]]></FromUserName>
	 * <CreateTime>1357290913</CreateTime>
	 * <MsgType><![CDATA[shortvideo]]></MsgType>
	 * <MediaId><![CDATA[media_id]]></MediaId>
	 * <ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId>
	 * <MsgId>1234567890123456</MsgId>
	 * </xml>
	 */
	protected Object recvdShortVideo(Map<String,String> msgMap){
		
		return null;
	}
	
	/**
	 * 地理位置消息
	 * <xml>
	 * <ToUserName><![CDATA[toUser]]></ToUserName>
	 * <FromUserName><![CDATA[fromUser]]></FromUserName>
	 * <CreateTime>1351776360</CreateTime>
	 * <MsgType><![CDATA[location]]></MsgType>
	 * <Location_X>23.134521</Location_X>
	 * <Location_Y>113.358803</Location_Y>
	 * <Scale>20</Scale>
	 * <Label><![CDATA[位置信息]]></Label>
	 * <MsgId>1234567890123456</MsgId>
	 * </xml>
	 */
	protected Object recvdLocation(Map<String,String> msgMap){
		
		return null;
	}
	
	/**
	 * 链接消息
	 * <xml>
	 * <ToUserName><![CDATA[toUser]]></ToUserName>
	 * <FromUserName><![CDATA[fromUser]]></FromUserName>
	 * <CreateTime>1351776360</CreateTime>
	 * <MsgType><![CDATA[link]]></MsgType>
	 * <Title><![CDATA[公众平台官网链接]]></Title>
	 * <Description><![CDATA[公众平台官网链接]]></Description>
	 * <Url><![CDATA[url]]></Url>
	 * <MsgId>1234567890123456</MsgId>
	 * </xml>
	 */
	protected Object recvdLink(Map<String,String> msgMap){
		
		return null;
	}
	
	
}
