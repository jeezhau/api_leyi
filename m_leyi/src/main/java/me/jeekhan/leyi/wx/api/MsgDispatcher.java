package me.jeekhan.leyi.wx.api;

import me.jeekhan.leyi.wx.WXMap;
import me.jeekhan.leyi.wx.WXMsgTP;
/**
 * 消息/事件分发处理控制类
 * @author Jee Khan
 *
 */
public class MsgDispatcher {
	private RecvMsgHandle recvMsgHandle ;//具体的消息处理逻辑
	/**
	 * 注入具体的消息/事件助理逻辑类
	 * @param recvMsgHandle
	 */
	public MsgDispatcher(RecvMsgHandle recvMsgHandle){
		this.recvMsgHandle = recvMsgHandle;
	}
	public MsgDispatcher(){
		recvMsgHandle = new RecvMsgHandle();
	}
	//根据接收到的消息的类型做出相应的处理
	public Object handle(WXMap msgMap){
		String msgType = (String) msgMap.get("MsgType");
		String event = (String) msgMap.get("Event");
		if(event!= null && event.length()>0){
			msgType = event;
		}
		WXMsgTP wxMsgTp = WXMsgTP.valueOf(msgType);
		switch(wxMsgTp){
		case text:
			//文本消息
			return recvMsgHandle.recvdText(msgMap);
		case voice:
			//语音消息
			return recvMsgHandle.recvdVoice(msgMap);
		case video:
			//视频消息
			return recvMsgHandle.recvdVideo(msgMap);
		case shortvideo:
			//小视频消息
			return recvMsgHandle.recvdShortVideo(msgMap);
		case location:
			//位置消息
			return recvMsgHandle.recvdLocation(msgMap);
		case link:
			//链接消息
			return recvMsgHandle.recvdLink(msgMap);
		case subscribe:
			//用户关注
			return recvMsgHandle.recvEventSubscribe(msgMap);
		case unsubscribe:
			//用户取消关注
			return recvMsgHandle.recvEventUnsubscribe(msgMap);
		case SCAN:
			//用户二次关注
			return recvMsgHandle.recvEventScan(msgMap);
		case LOCATION:
			//用户上报地理位置
			return recvMsgHandle.recvEventLocation(msgMap);
		default:
			break;
		}
		
		return null;
	}
	
	 
}
