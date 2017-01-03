package me.jeekhan.leyi.wxapi;

import java.util.Map;

/**
 * 接收微信消息，并作相应的处理
 * 1、当普通微信用户向公众账号发消息时，微信服务器将POST消息的XML数据包
 * 2、在微信用户和公众号产生交互的过程中，用户的某些操作会使得微信服务器通过事件推送的形式通知到开发者
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
	 * <ToUserName><![CDATA[toUser]]></ToUserName>			开发者微信号 
	 * <FromUserName><![CDATA[fromUser]]></FromUserName> 	发送方帐号（一个OpenID）
	 * <CreateTime>1348831860</CreateTime>					消息创建时间 （整型）
	 * <MsgType><![CDATA[text]]></MsgType>					text
	 * <Content><![CDATA[this is a test]]></Content>		文本消息内容 
	 * <MsgId>1234567890123456</MsgId>						消息id，64位整型 
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
	 * <ToUserName><![CDATA[toUser]]></ToUserName>			开发者微信号 
	 * <FromUserName><![CDATA[fromUser]]></FromUserName> 	发送方帐号（一个OpenID）
	 * <CreateTime>1348831860</CreateTime>					消息创建时间 （整型）
	 *  <MsgType><![CDATA[image]]></MsgType>				image
	 *  <PicUrl><![CDATA[this is a url]]></PicUrl>			图片链接 
	 *  <MediaId><![CDATA[media_id]]></MediaId>				图片消息媒体id，可以调用多媒体文件下载接口拉取数据。 
	 * <MsgId>1234567890123456</MsgId>						消息id，64位整型 
	 *  </xml>
	 */
	protected Object recvdImage(Map<String,String> msgMap){
		
		return null;
	}
	
	/**
	 * 语音消息
	 * 开通语音识别后，用户每次发送语音给公众号时，微信会在推送的语音消息XML数据包中，增加一个Recongnition字段
	 * （注：由于客户端缓存，开发者开启或者关闭语音识别功能，对新关注者立刻生效，对已关注用户需要24小时生效。开发者可以重新关注此帐号进行测试）。
	 * <xml>
	 * <ToUserName><![CDATA[toUser]]></ToUserName>			开发者微信号 
	 * <FromUserName><![CDATA[fromUser]]></FromUserName> 	发送方帐号（一个OpenID）
	 * <CreateTime>1348831860</CreateTime>					消息创建时间 （整型）
	 * <MsgType><![CDATA[voice]]></MsgType>					语音为voice 
	 * <MediaId><![CDATA[media_id]]></MediaId>				语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
	 * <Format><![CDATA[Format]]></Format>					语音格式，如amr，speex等 
	 * <Recognition><![CDATA[腾讯微信团队]]></Recognition>
	 * <MsgId>1234567890123456</MsgId>						消息id，64位整型 
	 * </xml>
	 */
	protected Object recvdVoice(Map<String,String> msgMap){
		
		return null;
	}
	
	
	/**
	 * 视频消息
	 * <xml>
	 * <ToUserName><![CDATA[toUser]]></ToUserName>			开发者微信号 
	 * <FromUserName><![CDATA[fromUser]]></FromUserName> 	发送方帐号（一个OpenID）
	 * <CreateTime>1348831860</CreateTime>					消息创建时间 （整型）
	 * <MsgType><![CDATA[video]]></MsgType>					视频为video 
	 * <MediaId><![CDATA[media_id]]></MediaId>				视频消息媒体id，可以调用多媒体文件下载接口拉取数据。 
	 * <ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId>视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。 
	 * <MsgId>1234567890123456</MsgId>						消息id，64位整型 
	 * </xml>
	 * 	 */
	protected Object recvdVideo(Map<String,String> msgMap){
		
		return null;
	}
	
	/**
	 * 小视频消息
	 * <xml>
	 * <ToUserName><![CDATA[toUser]]></ToUserName>			开发者微信号 
	 * <FromUserName><![CDATA[fromUser]]></FromUserName> 	发送方帐号（一个OpenID）
	 * <CreateTime>1348831860</CreateTime>					消息创建时间 （整型）
	 * <MsgType><![CDATA[shortvideo]]></MsgType>			小视频为shortvideo 
	 * <MediaId><![CDATA[media_id]]></MediaId>				视频消息媒体id，可以调用多媒体文件下载接口拉取数据。 
	 * <ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId>视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。 
	 * <MsgId>1234567890123456</MsgId>						消息id，64位整型 
	 * </xml>
	 */
	protected Object recvdShortVideo(Map<String,String> msgMap){
		
		return null;
	}
	
	/**
	 * 地理位置消息
	 * <xml>
	 * <ToUserName><![CDATA[toUser]]></ToUserName>			开发者微信号 
	 * <FromUserName><![CDATA[fromUser]]></FromUserName> 	发送方帐号（一个OpenID）
	 * <CreateTime>1348831860</CreateTime>					消息创建时间 （整型）
	 * <MsgType><![CDATA[location]]></MsgType>				location 
	 * <Location_X>23.134521</Location_X>					地理位置维度
	 * <Location_Y>113.358803</Location_Y>					地理位置经度 
	 * <Scale>20</Scale>									地图缩放大小
	 * <Label><![CDATA[位置信息]]></Label>					地理位置信息
	 * <MsgId>1234567890123456</MsgId>						消息id，64位整型 
	 * </xml>
	 */
	protected Object recvdLocation(Map<String,String> msgMap){
		
		return null;
	}
	
	/**
	 * 链接消息
	 * <xml>
	 * <ToUserName><![CDATA[toUser]]></ToUserName>			开发者微信号 
	 * <FromUserName><![CDATA[fromUser]]></FromUserName> 	发送方帐号（一个OpenID）
	 * <CreateTime>1348831860</CreateTime>					消息创建时间 （整型）
	 * <MsgType><![CDATA[link]]></MsgType>					消息类型，link 
	 * <Title><![CDATA[公众平台官网链接]]></Title>			消息标题 
	 * <Description><![CDATA[公众平台官网链接]]></Description>消息描述
	 * <Url><![CDATA[url]]></Url>							消息链接 
	 * <MsgId>1234567890123456</MsgId>						消息id，64位整型 
	 * </xml>
	 */
	protected Object recvdLink(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * 1、关注/取消关注事件消息
	 * <xml>
	 *<ToUserName><![CDATA[toUser]]></ToUserName>		开发者微信号
	 *<FromUserName><![CDATA[FromUser]]></FromUserName>	发送方帐号（一个OpenID）
	 *<CreateTime>123456789</CreateTime>				消息创建时间 （整型） 
	 *<MsgType><![CDATA[event]]></MsgType>				消息类型，event 
	 *<Event><![CDATA[subscribe/unsubscribe]]></Event>	事件类型，subscribe(订阅)、unsubscribe(取消订阅) 
	 *</xml>
	 * 2、 扫描公众号的带参数二维码事件
	 * 如果用户还未关注公众号，则用户可以关注公众号，关注后微信会将带场景值关注事件推送给开发者。 
	 * 用户未关注时，进行关注后的事件推送  
	 * <xml><ToUserName><![CDATA[toUser]]></ToUserName>		开发者微信号
	 * <FromUserName><![CDATA[FromUser]]></FromUserName>	发送方帐号（一个OpenID）
	 * <CreateTime>123456789</CreateTime>					消息创建时间 （整型） 
	 * <MsgType><![CDATA[event]]></MsgType>					消息类型，event 
	 * <Event><![CDATA[subscribe]]></Event>					事件类型，subscribe(订阅)	
	 * <EventKey><![CDATA[qrscene_123123]]></EventKey>		事件KEY值，qrscene_为前缀，后面为二维码的参数值 
	 * <Ticket><![CDATA[TICKET]]></Ticket>					二维码的ticket，可用来换取二维码图片 
	 * </xml>
	 */
	public Object recvEventSubscribe(WXMap msgMap){
		
		return null;
	}
	/**
	 * 取消关注
	 * @param msgMap
	 * @return
	 */
	public Object recvEventUnsubscribe(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * 扫描公众号的带参数二维码事件
	 * 用户已关注时的事件推送 
	 * <xml>
	 * <xml><ToUserName><![CDATA[toUser]]></ToUserName>		开发者微信号
	 * <FromUserName><![CDATA[FromUser]]></FromUserName>	发送方帐号（一个OpenID）
	 * <CreateTime>123456789</CreateTime>					消息创建时间 （整型） 
	 * <MsgType><![CDATA[event]]></MsgType>					消息类型，event 
	 * <Event><![CDATA[SCAN]]></Event>						事件类型，SCAN
	 * <EventKey><![CDATA[SCENE_VALUE]]></EventKey>			事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id 
	 * <Ticket><![CDATA[TICKET]]></Ticket>					二维码的ticket，可用来换取二维码图片
	 * </xml>
	 */
	public Object recvEventScan(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * 上报地理位置事件
	 * 用户同意上报地理位置后，每次进入公众号会话时，都会在进入时上报地理位置，或在进入会话后每5秒上报一次地理位置，公众号可以在公众平台网站中修改以上设置
	 * <xml>
	 * <ToUserName><![CDATA[toUser]]></ToUserName>			开发者微信号 
	 * <FromUserName><![CDATA[fromUser]]></FromUserName>	发送方帐号（一个OpenID） 
	 * <CreateTime>123456789</CreateTime>					消息创建时间 （整型） 
	 * <MsgType><![CDATA[event]]></MsgType>					消息类型，event 
	 * <Event><![CDATA[LOCATION]]></Event>					事件类型，LOCATION 
	 * <Latitude>23.137466</Latitude>						地理位置纬度 
	 * <Longitude>113.352425</Longitude>					地理位置经度 
	 * <Precision>119.385040</Precision>					地理位置精度
	 * </xml>
	 */
	public Object recvEventLocation(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * 自定义菜单-点击事件
	 * 用户点击自定义菜单后，微信会把点击事件推送给开发者，点击菜单弹出子菜单，不会产生上报
	 * <xml>
	 * <ToUserName><![CDATA[toUser]]></ToUserName>			开发者微信号 
	 * <FromUserName><![CDATA[fromUser]]></FromUserName>	发送方帐号（一个OpenID） 
	 * <CreateTime>123456789</CreateTime>					消息创建时间 （整型） 
	 * <MsgType><![CDATA[event]]></MsgType>					消息类型，event 
	 * <Event><![CDATA[CLICK]]></Event>						事件类型，CLICK 
	 * <EventKey><![CDATA[EVENTKEY]]></EventKey>			事件KEY值，与自定义菜单接口中KEY值对应
	 * </xml>
	 */
	public Object recvEventClick(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * 点击菜单跳转链接时的事件推送 
	 * <xml>
	 * <ToUserName><![CDATA[toUser]]></ToUserName>			开发者微信号 
	 * <FromUserName><![CDATA[fromUser]]></FromUserName>	发送方帐号（一个OpenID） 
	 * <CreateTime>123456789</CreateTime>					消息创建时间 （整型） 
	 * <MsgType><![CDATA[event]]></MsgType>					消息类型，event 
	 * <Event><![CDATA[VIEW]]></Event>						事件类型，VIEW 
	 * <EventKey><![CDATA[www.qq.com]]></EventKey>			事件KEY值，设置的跳转URL 
	 * <MenuId>MENUID</MenuId>	指菜单ID，如果是个性化菜单，则可以通过这个字段，知道是哪个规则的菜单被点击了
	 * </xml>
	 * 	 
	 */
	public Object recvEventView(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * scancode_push：扫码推事件的事件推送 
	 * <xml>
	 * <ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName>			开发者微信号 
	 * <FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName>发送方帐号（一个OpenID）
	 * <CreateTime>1408090502</CreateTime>					消息创建时间（整型）
	 * <MsgType><![CDATA[event]]></MsgType>					消息类型，event 
	 * <Event><![CDATA[scancode_push]]></Event>				事件类型，scancode_push 
	 * <EventKey><![CDATA[6]]></EventKey>					事件KEY值，由开发者在创建菜单时设定 
	 * <ScanCodeInfo>										扫描信息
	 * <ScanType><![CDATA[qrcode]]></ScanType>				扫描类型，一般是qrcode
	 * <ScanResult><![CDATA[1]]></ScanResult>				扫描结果，即二维码对应的字符串信息 
	 * </ScanCodeInfo>						
	 * </xml>
	 */
	public Object recvEventScancodePush(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * scancode_waitmsg：扫码推事件且弹出“消息接收中”提示框的事件推送
	 * 同上
	 */
	public Object recvEventScancodeWaitmsg(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * pic_sysphoto：弹出系统拍照发图的事件推送 
	 * <ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName>			开发者微信号 
	 * <FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName>发送方帐号（一个OpenID）
	 * <CreateTime>1408090606</CreateTime>                          消息创建时间（整型）         
	 * <MsgType><![CDATA[event]]></MsgType>                         消息类型，event  
	 * <Event><![CDATA[pic_sysphoto]]></Event>						事件类型，pic_sysphoto 
	 * <EventKey><![CDATA[6]]></EventKey>							事件KEY值，由开发者在创建菜单时设定 
	 * <SendPicsInfo>												发送的图片信息 
	 * <Count>1</Count>												发送的图片数量
	 * <PicList>													图片列表
	 * <item>
	 * <PicMd5Sum><![CDATA[1b5f7c23b5bf75682a53e7b6d163e185]]></PicMd5Sum>图片的MD5值，开发者若需要，可用于验证接收到图片 
	 * </item>
	 * </PicList>
	 * </SendPicsInfo>
	 * </xml>
	 */
	public Object recvEventPicSysphoto(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * pic_photo_or_album：弹出拍照或者相册发图的事件推送 
	 * 同上
	 */
	public Object recvEventPicPhotoOralbum(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * pic_weixin：弹出微信相册发图器的事件推送 
	 * <ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName>			开发者微信号 
	 * <FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName>发送方帐号（一个OpenID）
	 * <CreateTime>1408090606</CreateTime>                          消息创建时间（整型）         
	 * <MsgType><![CDATA[event]]></MsgType>                         消息类型，event  
	 * <Event><![CDATA[pic_weixin]]></Event>						事件类型，pic_weixin 
	 * <EventKey><![CDATA[6]]></EventKey>							事件KEY值，由开发者在创建菜单时设定 
	 * <SendPicsInfo>												发送的图片信息 
	 * <Count>1</Count>												发送的图片数量
	 * <PicList>													图片列表
	 * <item>
	 * <PicMd5Sum><![CDATA[1b5f7c23b5bf75682a53e7b6d163e185]]></PicMd5Sum>图片的MD5值，开发者若需要，可用于验证接收到图片 
	 * </item>
	 * </PicList>
	 * </SendPicsInfo>
	 * </xml>
	 */
	public Object recvEventPicWeixin(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * location_select：弹出地理位置选择器的事件推送 
	 * <ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName>			开发者微信号 
	 * <FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName>发送方帐号（一个OpenID）
	 * <CreateTime>1408090606</CreateTime>                          消息创建时间（整型）         
	 * <MsgType><![CDATA[event]]></MsgType>                         消息类型，event  
	 * <Event><![CDATA[location_select]]></Event>					事件类型，location_select 
	 * <EventKey><![CDATA[6]]></EventKey>							事件KEY值，由开发者在创建菜单时设定 
	 * <SendLocationInfo>											发送的位置信息
	 * <Location_X><![CDATA[23]]></Location_X>						X坐标信息
	 * <Location_Y><![CDATA[113]]></Location_Y>						Y坐标信息 
	 * <Scale><![CDATA[15]]></Scale>								精度，可理解为精度或者比例尺、越精细的话 scale越高 
	 * <Label><![CDATA[ 广州市海珠区客村艺苑路 106号]]></Label>		地理位置的字符串信息 
	 * <Poiname><![CDATA[]]></Poiname>								朋友圈POI的名字，可能为空 
	 * </SendLocationInfo>
	 * </xml>
	 */
	public Object recvEventLocationSelect(WXMap msgMap){
		
		return null;
	}
}
