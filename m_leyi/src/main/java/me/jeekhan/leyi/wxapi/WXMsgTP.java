package me.jeekhan.leyi.wxapi;
/**
 * 微信消息类型
 * @author Jee Khan
 *
 */
public enum WXMsgTP {
	text("文本消息");
	private String desc;
	
	private WXMsgTP(String desc){
		this.desc = desc;
	}
	
	public String getDesc(){
		return this.desc;
	}

}
