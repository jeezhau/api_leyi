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
	protected Object recvEventSubscribe(WXMap msgMap){
		
		return null;
	}
	/**
	 * 取消关注
	 * @param msgMap
	 * @return
	 */
	protected Object recvEventUnsubscribe(WXMap msgMap){
		
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
	protected Object recvEventScan(WXMap msgMap){
		
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
	protected Object recvEventLocation(WXMap msgMap){
		
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
	protected Object recvEventClick(WXMap msgMap){
		
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
	protected Object recvEventView(WXMap msgMap){
		
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
	protected Object recvEventScancodePush(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * scancode_waitmsg：扫码推事件且弹出“消息接收中”提示框的事件推送
	 * 同上
	 */
	protected Object recvEventScancodeWaitmsg(WXMap msgMap){
		
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
	protected Object recvEventPicSysphoto(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * pic_photo_or_album：弹出拍照或者相册发图的事件推送 
	 * 同上
	 */
	protected Object recvEventPicPhotoOralbum(WXMap msgMap){
		
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
	protected Object recvEventPicWeixin(WXMap msgMap){
		
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
	protected Object recvEventLocationSelect(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * 事件推送群发结果
	 * 1、由于群发任务提交后，群发任务可能在一定时间后才完成，因此，群发接口调用时，仅会给出群发任务是否提交成功的提示，若群发任务提交成功，
	 * 则在群发任务结束时，会向开发者在公众平台填写的开发者URL（callback URL）推送事件。
	 * 2、由于群发任务彻底完成需要较长时间，将会在群发任务即将完成的时候，就推送群发结果，此时的推送人数数据将会与实际情形存在一定误差 。
	 * <xml>
	 * <ToUserName><![CDATA[gh_3e8adccde292]]></ToUserName>		公众号的微信号
	 * <FromUserName><![CDATA[oR5Gjjl_eiZoUpGozMo7dbBJ362A]]></FromUserName>	公众号群发助手的微信号，为mphelper 
	 * <CreateTime>1394524295</CreateTime>			创建时间的时间戳 
	 * <MsgType><![CDATA[event]]></MsgType>			消息类型，此处为event 
	 * <Event><![CDATA[MASSSENDJOBFINISH]]></Event>	事件信息，此处为MASSSENDJOBFINISH 
	 * <MsgID>1988</MsgID>							群发的消息ID 
	 * <Status><![CDATA[sendsuccess]]></Status>		群发的结构，为“send success”或“send fail”或“err(num)”。但send success时，也有可能因用户拒收公众号的消息、系统错误等原因造成少量用户接收失败。err(num)是审核失败的具体原因
	 * <TotalCount>100</TotalCount>					group_id下粉丝数；或者openid_list中的粉丝数 
	 * <FilterCount>80</FilterCount>				过滤（过滤是指特定地区、性别的过滤、用户设置拒收的过滤，用户接收已超4条的过滤）后，准备发送的粉丝数，原则上，FilterCount = SentCount + ErrorCount 
	 * <SentCount>75</SentCount>					发送成功的粉丝数 
	 * <ErrorCount>5</ErrorCount>					发送失败的粉丝数 
	 * </xml>
	 */
	protected Object recvEventMassSendJobFinish(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * 事件推送模板消息发送结果
	 * 在模版消息发送任务完成后，微信服务器会将是否送达成功作为通知
	 *<xml>
	 * <ToUserName><![CDATA[gh_7f083739789a]]></ToUserName>		公众号微信号 
	 * <FromUserName><![CDATA[oia2TjuEGTNoeX76QEjQNrcURxG8]]></FromUserName>	 接收模板消息的用户的openid 
	 * <CreateTime>1395658920</CreateTime>				创建时间 
	 * <MsgType><![CDATA[event]]></MsgType>				消息类型是事件 
	 * <Event><![CDATA[TEMPLATESENDJOBFINISH]]></Event>	事件为模板消息发送结束 
	 * <MsgID>200163836</MsgID>							消息id 
	 * <Status><![CDATA[Status]]></Status>				发送状态：success-成功；failed:user block-用户拒收；failed: system failed-其他原因失败 
	 *</xml>
	 */
	protected Object recvEventTemplateSendJobFinish(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * 资质认证成功（此时立即获得接口权限） 
	 * <xml>
	 * <ToUserName><![CDATA[toUser]]></ToUserName>			开发者微信号 
	 * <FromUserName><![CDATA[FromUser]]></FromUserName>	发送方帐号（一个OpenID，此时发送方是系统帐号） 
	 * <CreateTime>1442401156</CreateTime>					消息创建时间 （整型），时间戳 
	 * <MsgType><![CDATA[event]]></MsgType>					消息类型，event 
	 * <Event><![CDATA[qualification_verify_success]]></Event>	事件类型 qualification_verify_success 
	 * <ExpiredTime>1442401156</ExpiredTime>				有效期 (整形)，指的是时间戳，将于该时间戳认证过期 
	 * </xml> 
	 */
	protected Object recvQualificationVerifySuccess(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * 资质认证失败
	 * <xml>
	 * <ToUserName><![CDATA[toUser]]></ToUserName>			开发者微信号 
	 * <FromUserName><![CDATA[FromUser]]></FromUserName>	发送方帐号（一个OpenID，此时发送方是系统帐号） 
	 * <CreateTime>1442401156</CreateTime>					消息创建时间 （整型），时间戳 
	 * <MsgType><![CDATA[event]]></MsgType>					消息类型，event 
	 * <Event><![CDATA[qualification_verify_fail]]></Event>	事件类型 qualification_verify_fail 
	 * <FailTime>1442401122</FailTime>						失败发生时间 (整形)，时间戳 
	 * <FailReason><![CDATA[by time]]></FailReason>			认证失败的原因 
	 * </xml> 
	 */
	protected Object recvQualificationVerifyFail(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * 名称认证成功（即命名成功） 
	 * <xml>
	 * <ToUserName><![CDATA[toUser]]></ToUserName>			开发者微信号 
	 * <FromUserName><![CDATA[FromUser]]></FromUserName>	发送方帐号（一个OpenID，此时发送方是系统帐号） 
	 * <CreateTime>1442401156</CreateTime>					消息创建时间 （整型），时间戳 
	 * <MsgType><![CDATA[event]]></MsgType>					消息类型，event 
	 * <Event><![CDATA[naming_verify_success]]></Event>		事件类型 naming_verify_success 
	 * <ExpiredTime>1442401093</ExpiredTime>				有效期 (整形)，指的是时间戳，将于该时间戳认证过期 
	 * </xml> 
	 */
	protected Object recvNamingVerifySuccess(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * 名称认证失败（这时虽然客户端不打勾，但仍有接口权限） 
	 * <xml>
	 * <ToUserName><![CDATA[toUser]]></ToUserName>			开发者微信号 
	 * <FromUserName><![CDATA[FromUser]]></FromUserName>	发送方帐号（一个OpenID，此时发送方是系统帐号） 
	 * <CreateTime>1442401156</CreateTime>					消息创建时间 （整型），时间戳 
	 * <MsgType><![CDATA[event]]></MsgType>					消息类型，event 
	 * <Event><![CDATA[naming_verify_fail]]></Event>		事件类型 naming_verify_fail 
	 * <FailTime>1442401061</FailTime>						失败发生时间 (整形)，时间戳 
	 * <FailReason><![CDATA[by time]]></FailReason>			认证失败的原因 
	 * </xml> 
	 */
	protected Object recvNamingVerifyFail(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * 年审通知 
	 * <xml>
	 * <ToUserName><![CDATA[toUser]]></ToUserName>			开发者微信号 
	 * <FromUserName><![CDATA[FromUser]]></FromUserName>	发送方帐号（一个OpenID，此时发送方是系统帐号） 
	 * <CreateTime>1442401156</CreateTime>					消息创建时间 （整型），时间戳 
	 * <MsgType><![CDATA[event]]></MsgType>					消息类型，event 
	 * <Event><![CDATA[annual_renew]]></Event>				事件类型 annual_renew，提醒公众号需要去年审了 
	 * <ExpiredTime>1442401004</ExpiredTime>				有效期 (整形)，指的是时间戳，将于该时间戳认证过期，需尽快年审 
	 * </xml>
	 */
	protected Object recvAnnualRenew(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * 认证过期失效通知 
	 * <xml>
	 * <ToUserName><![CDATA[toUser]]></ToUserName>			开发者微信号 
	 * <FromUserName><![CDATA[FromUser]]></FromUserName>	发送方帐号（一个OpenID，此时发送方是系统帐号） 
	 * <CreateTime>1442401156</CreateTime>					消息创建时间 （整型），时间戳 
	 * <MsgType><![CDATA[event]]></MsgType>					消息类型，event 
	 * <Event><![CDATA[verify_expired]]></Event>			事件类型 verify_expired 
	 * <ExpiredTime>1442400900</ExpiredTime>				有效期 (整形)，指的是时间戳，表示已于该时间戳认证过期，需要重新发起微信认证 
	 * </xml>
	 */
	protected Object recvVerifyExpired(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * 订单付款通知
	 * 在用户在微信中付款成功后，微信服务器会将订单付款通知推送给开发者
	 * <xml>
	 * <ToUserName><![CDATA[toUser]]></ToUserName>			开发者微信号 
	 * <FromUserName><![CDATA[FromUser]]></FromUserName>	发送方帐号（一个OpenID，此时发送方是系统帐号） 
	 * <CreateTime>1442401156</CreateTime>					消息创建时间 （整型），时间戳 
	 * <MsgType><![CDATA[event]]></MsgType>					消息类型，event 
	 * <Event><![CDATA[merchant_order]]></Event>			事件类型 merchant_order 
	 * <OrderId><![CDATA[test_order_id]]></OrderId>			订单ID
	 * <OrderStatus>2</OrderStatus>							订单状态
	 * <ProductId><![CDATA[test_product_id]]></ProductId>	商品ID
	 * <SkuInfo><![CDATA[10001:1000012;10002:100021]]></SkuInfo>	商品SKU
	 * </xml>
	 */
	protected Object recvMerchantOrder(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * 买单事件推送
	 * <xml>
	 * <ToUserName><![CDATA[toUser]]></ToUserName>			开发者微信号 
	 * <FromUserName><![CDATA[FromUser]]></FromUserName>	发送方帐号（一个OpenID，此时发送方是系统帐号） 
	 * <CreateTime>1442401156</CreateTime>					消息创建时间 （整型），时间戳 
	 * <MsgType><![CDATA[event]]></MsgType>					消息类型，event 
	 * <Event><![CDATA[user_pay_from_pay_cell]]></Event>	事件类型 user_pay_from_pay_cell
	 * <CardId><![CDATA[po2VNuCuRo-8sxxxxxxxxxxx]]></CardId>	卡券ID
	 * <UserCardCode><![CDATA[38050000000]]></UserCardCode>		卡券Code码
	 * <TransId><![CDATA[10022403432015000000000]]></TransId>	微信支付交易订单号（只有使用买单功能核销的卡券才会出现）
	 * <LocationId>291710000</LocationId>						门店名称，当前卡券核销的门店名称（只有通过卡券商户助手和买单核销时才会出现） 
	 * <Fee><![CDATA[10000]]></Fee>								实付金额，单位为分
	 * <OriginalFee><![CDATA[10000]]> </OriginalFee>			应付金额，单位为分
	 * </xml>
	 */
	protected Object recvUserPay(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * 用户领取卡券
	 * <xml>
	 * <ToUserName><![CDATA[toUser]]></ToUserName>			开发者微信号 
	 * <FromUserName><![CDATA[FromUser]]></FromUserName>	发送方帐号（一个OpenID，此时发送方是系统帐号） 
	 * <CreateTime>1442401156</CreateTime>					消息创建时间 （整型），时间戳 
	 * <MsgType><![CDATA[event]]></MsgType>					消息类型，event 
	 * <Event><![CDATA[user_get_card]]></Event>				事件类型 user_get_card
	 *<CardId><![CDATA[cardid]]></CardId> 					卡券ID
	 *<IsGiveByFriend>1</IsGiveByFriend>					是否为转赠，1代表是，0代表否。 
	 *<UserCardCode><![CDATA[12312312]]></UserCardCode>		code序列号。自定义code及非自定义code的卡券被领取后都支持事件推送。 
	 *<OuterId>1</OuterId>									场景值
	 *</xml>
	 */
	protected Object recvUserGetCard(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * 卡券审核事件推送
	 * <xml> 
	 * <ToUserName><![CDATA[toUser]]></ToUserName>			开发者微信号 
	 * <FromUserName><![CDATA[FromUser]]></FromUserName>	发送方帐号（一个OpenID，此时发送方是系统帐号） 
	 * <CreateTime>1442401156</CreateTime>					消息创建时间 （整型），时间戳 
	 * <MsgType><![CDATA[event]]></MsgType>					消息类型，event  
	 * <Event><![CDATA[card_pass_check]]></Event>  			事件类型，card_pass_check(卡券通过审核)、card_not_pass_check（卡券未通过审核） 
	 * <CardId><![CDATA[cardid]]></CardId> 					卡券ID 
	 * </xml>
	 */
	protected Object recvCardCheck(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * 用户删除卡券事件
	 * <xml>
	 * <ToUserName><![CDATA[toUser]]></ToUserName>			开发者微信号 
	 * <FromUserName><![CDATA[FromUser]]></FromUserName>	发送方帐号（一个OpenID，此时发送方是系统帐号） 
	 * <CreateTime>1442401156</CreateTime>					消息创建时间 （整型），时间戳 
	 * <MsgType><![CDATA[event]]></MsgType>					消息类型，event 
	 * <Event><![CDATA[user_del_card]]></Event> 			事件类型，user_del_card(用户删除卡券) 
	 * <CardId><![CDATA[cardid]]></CardId> 					卡券ID
	 * <UserCardCode><![CDATA[12312312]]></UserCardCode>	code序列号。自定义code及非自定义code的卡券被领取后都支持事件推送
	 * </xml>
	 */
	protected Object recvUserDelCard(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * 核销事件
	 * <xml> 
	 * <ToUserName><![CDATA[toUser]]></ToUserName>			开发者微信号 
	 * <FromUserName><![CDATA[FromUser]]></FromUserName>	发送方帐号（一个OpenID，此时发送方是系统帐号） 
	 * <CreateTime>1442401156</CreateTime>					消息创建时间 （整型），时间戳 
	 * <MsgType><![CDATA[event]]></MsgType>					消息类型，event 
	 * <Event><![CDATA[user_consume_card]]></Event> 		事件类型，user_consume_card(核销事件) 
	 * <CardId><![CDATA[cardid]]></CardId> 					卡券ID
	 * <UserCardCode><![CDATA[12312312]]></UserCardCode>	卡券Code码
	 * <ConsumeSource><![CDATA[(FROM_API)]]></ConsumeSource>	核销来源。支持开发者统计API核销（FROM_API）、公众平台核销（FROM_MP）、卡券商户助手核销（FROM_MOBILE_HELPER）（核销员微信号） 
	 * <OutTradeNo><![CDATA[aaaaaaaaaaaa]]></OutTradeNo>		
	 * <TransId><![CDATA[bbbbbbbbbb]]></TransId>				
	 * <LocationId><![CDATA[222222]]></LocationId>				门店名称，当前卡券核销的门店名称（只有通过卡券商户助手和买单核销时才会出现）
	 * <StaffOpenId><![CDATA[obLatjjwDolFjRRd3doGIdwNqRXw]></StaffOpenId>	核销该卡券核销员的openid（只有通过卡券商户助手核销时才会出现）
	 * </xml>
	 */
	protected Object recvUserConsumeCard(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * 进入会员卡事件
	 * <xml> 
	 * <ToUserName><![CDATA[toUser]]></ToUserName> 
	 * <FromUserName><![CDATA[FromUser]]></FromUserName> 
	 * <CreateTime>123456789</CreateTime> 
	 * <MsgType><![CDATA[event]]></MsgType> 
	 * <Event><![CDATA[user_view_card]]></Event> 	事件类型，user_view_card(核销事件) 
	 * <CardId><![CDATA[cardid]]></CardId> 			卡券ID
	 * <UserCardCode><![CDATA[12312312]]></UserCardCode>	商户自定义code值。非自定code推送为空串
	 * </xml>
	 */
	protected Object recvUserViewCard(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * 从卡券进入公众号会话事件
	 * 用户在卡券里点击查看公众号进入会话时（需要用户已经关注公众号），微信会把这个事件推送
	 * <xml> 
	 * <ToUserName><![CDATA[toUser]]></ToUserName> 
	 * <FromUserName><![CDATA[FromUser]]></FromUserName> 
	 * <CreateTime>123456789</CreateTime> 
	 * <MsgType><![CDATA[event]]></MsgType> 
	 * <Event><![CDATA[user_enter_session_from_card]]></Event> 事件类型，user_enter_session_from_card(用户从卡券进入公众号会话)
	 * <CardId><![CDATA[cardid]]></CardId> 					卡券ID
	 * <UserCardCode><![CDATA[12312312]]></UserCardCode>	Code码
	 * </xml>
	 */
	protected Object recvUserEnterSessionFromCard(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * 会员卡内容更新事件
	 * 当用户的会员卡积分余额发生变动时，微信会推送事件告知开发者
	 * <xml><ToUserName><![CDATA[gh_9e1765b5568e]]></ToUserName>
	 * <FromUserName><![CDATA[ojZ8YtyVyr30HheH3CM73y7h4jJE]]></FromUserName>
	 * <CreateTime>1445507140</CreateTime>
	 * <MsgType><![CDATA[event]]></MsgType>
	 * <Event><![CDATA[update_member_card]]></Event>	事件类型，update_member_card(用户从卡券进入公众号会话)
	 * <CardId><![CDATA[pjZ8Ytx-nwvpCRyQneH3Ncmh6N94]]></CardId>	卡券ID
	 * <UserCardCode><![CDATA[485027611252]]></UserCardCode>	Code码
	 * <ModifyBonus>3</ModifyBonus>			变动的积分值
	 * <ModifyBalance>0</ModifyBalance>		变动的余额值
	 * </xml>
	 */
	protected Object recvUpdateMemberCard(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * 库存报警事件
	 * 用户领券时，若此时库存数小于预警值（默认为100），会发送事件给商户，事件每隔12小时发送一次
	 * <xml>
	 * <ToUserName><![CDATA[gh_2d62d*****0]]></ToUserName>
	 * <FromUserName><![CDATA[oa3LFuBvWb7*********]]></FromUserName> 
	 * <CreateTime>1443838506</CreateTime>
	 * <MsgType><![CDATA[event]]></MsgType>
	 * <Event><![CDATA[card_sku_remind]]></Event>		事件类型，card_sku_remind库存报警 
	 * <CardId><![CDATA[pa3LFuAh2P65**********]]></CardId>	卡券ID 
	 * <Detail><![CDATA[the card's quantity is equal to 0]]></Detail>	报警详细信息 
	 * </xml>
	 */
	protected Object recvCardSkuRemind(WXMap msgMap){
		
		return null;
	}
	
	/**
	 * 券点流水详情事件
	 * 当商户朋友的券券点发生变动时，微信服务器会推送消息给商户服务器
	 * <xml>
	 *  <ToUserName><![CDATA[gh_7223c83d4be5]]></ToUserName>
	 *  <FromUserName><![CDATA[ob5E7s-HoN9tslQY3-0I4qmgluHk]]></FromUserName>
	 *  <CreateTime>1453295737</CreateTime>
	 *  <MsgType><![CDATA[event]]></MsgType>
	 *  <Event><![CDATA[card_pay_order]]></Event>		事件类型，card_pay_order券点流水详情事件 
	 *  <OrderId><![CDATA[404091456]]></OrderId>		本次推送对应的订单号 
	 *  <Status><![CDATA[ORDER_STATUS_FINANCE_SUCC]]></Status>	本次订单号的状态,ORDER_STATUS_WAITING 等待支付 ORDER_STATUS_SUCC 支付成功 ORDER_STATUS_FINANCE_SUCC 加代币成功 ORDER_STATUS_QUANTITY_SUCC 加库存成功 ORDER_STATUS_HAS_REFUND 已退币 ORDER_STATUS_REFUND_WAITING 等待退币确认 ORDER_STATUS_ROLLBACK 已回退,系统失败 ORDER_STATUS_HAS_RECEIPT 已开发票
	 *  <CreateTime>1453295737</CreateTime>		购买券点时，支付二维码的生成时间 
	 *  <PayFinishTime>0</PayFinishTime>		购买券点时，实际支付成功的时间 
	 *  <Desc><![CDATA[]]></Desc>				支付方式，一般为微信支付充值 
	 *  <FreeCoinCount><![CDATA[200]]></FreeCoinCount>	剩余免费券点数量 
	 *  <PayCoinCount><![CDATA[0]]></PayCoinCount>		剩余付费券点数量
	 *  <RefundFreeCoinCount><![CDATA[0]]></RefundFreeCoinCount>	本次变动的免费券点数量 
	 *  <RefundPayCoinCount><![CDATA[0]]></RefundPayCoinCount>		本次变动的付费券点数量 
	 *  <OrderType><![CDATA[ORDER_TYPE_SYS_ADD]]></OrderType>	所要拉取的订单类型 ORDER_TYPE_SYS_ADD 平台赠送券点 ORDER_TYPE_WXPAY 充值券点 ORDER_TYPE_REFUND 库存未使用回退券点 ORDER_TYPE_REDUCE 券点兑换库存 ORDER_TYPE_SYS_REDUCE 平台扣减 
	 *  <Memo><![CDATA[开通账户奖励]]></Memo>	系统备注，说明此次变动的缘由，如开通账户奖励、门店奖励、核销奖励以及充值、扣减。 
	 *  <ReceiptInfo><![CDATA[]]></ReceiptInfo>	所开发票的详情 
	 * </xml>	
	 */
	protected Object recvCardPayOrder(WXMap msgMap){
		
		return null;
	}
	
}
