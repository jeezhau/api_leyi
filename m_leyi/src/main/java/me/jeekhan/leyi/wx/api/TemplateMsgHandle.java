package me.jeekhan.leyi.wx.api;

import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.jeekhan.leyi.common.HttpUtils;
import me.jeekhan.leyi.wx.AccessToken;

/**
 * 模板消息
 * 1、模板消息仅用于公众号向用户发送重要的服务通知，只能用于符合其要求的服务场景中，如信用卡刷卡通知，商品购买成功通知等。不支持广告等营销类消息以及其它所有可能对用户造成骚扰的消息。
 * 2、模板消息的定位是用户触发后的通知消息，不允许在用户没做任何操作或未经用户同意接收的前提下，主动下发消息给用户。目前在特殊情况下允许主动下发的消息只有故障类和灾害警示警告类通知，除此之外都要经过用户同意或用户有触发行为才能下发模板消息。
 * @author Jee Khan
 *
 */
public class TemplateMsgHandle {
	private static Logger log = LoggerFactory.getLogger(TemplateMsgHandle.class);

	/**
	 * 设置所属行业
	 * 设置行业可在MP中完成，每月可修改行业1次，账号仅可使用所属行业中相关的模板
	 * http请求方式: POST https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN
	 * @param industry_id1	行业1代码，具体参照行业代码查询
	 * @param industry_id2	行业2代码，
	 * @return
	 */
	public static Object setIndustry(String industry_id1,String industry_id2){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("industry_id1", industry_id1);
		jsonObj.put("industry_id2", "industry_id2");
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=" + token;
		log.info("设置所属行业（POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("设置所属行业（POST）返回：" + ret );
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
	 * 获取设置的行业信息 
	 * http请求方式：GET https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN
	 * @return {"primary_industry":{"first_class":"运输与仓储","second_class":"快递"},"secondary_industry":{"first_class":"IT科技","second_class":"互联网|电子商务"}}
	 */
	public static Object getIndustry(){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=" + token;
		log.info("获取设置的行业信息（GET）：" + url);
		String ret = HttpUtils.doGetSSL(url);
		log.info("获取设置的行业信息（GET）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	/**
	 * 获取行业模板,添加至账号模板列表
	 * http请求方式: POST https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN
	 * @param template_id_short 模板库中模板的编号，有“TM**”和“OPENTMTM**”等形式 
	 * @return	{"errcode":0,"errmsg":"ok","template_id":"Doclyl5uP7Aciu-qZ7mJNPtWkbkYnWBWVja26EGbNyk"}
	 */
	public static Object addPrivateTemplate(String template_id_short){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("template_id_short", template_id_short);
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=" + token;
		log.info("获取行业模板（POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("获取行业模板（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	/**
	 * 获取模板列表 ,获取已添加至帐号下所有模板列表
	 * http请求方式：GET https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN
	 * @return
	 * {	
	 *  "template_list": [{
	 *       "template_id": "iPk5sOIt5X_flOVKn5GrTFpncEYTojx6ddbt8WYoV5s",
	 *       "title": "领取奖金提醒",
	 *       "primary_industry": "IT科技",
	 *       "deputy_industry": "互联网|电子商务",
	 *       "content": "{ {result.DATA} }\n\n领奖金额:{ {withdrawMoney.DATA} }\n领奖  时间:{ {withdrawTime.DATA} }\n银行信息:{ {cardInfo.DATA} }\n到账时间:  { {arrivedTime.DATA} }\n{ {remark.DATA} }",
	 *       "example": "您已提交领奖申请\n\n领奖金额：xxxx元\n领奖时间：2013-10-10 12:22:22\n银行信息：xx银行(尾号xxxx)\n到账时间：预计xxxxxxx\n\n预计将于xxxx到达您的银行卡"
	 *    }]
	 * }
	 */
	public static Object getAllPrivateTemplate(){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=" + token;
		log.info("获取模板列表（GET）：" + url );
		String ret = HttpUtils.doGetSSL(url);
		log.info("获取模板列表（GET）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	/**
	 * 删除个人模板 
	 * http请求方式post https://api,weixin.qq.com/cgi-bin/template/del_private_template?access_token=ACCESS_TOKEN
	 * @param template_id
	 * @return
	 */
	public static boolean deletePrivateTemplate(String template_id){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("template_id", template_id);
		String token = AccessToken.getAccessToken();
		String url = " https://api,weixin.qq.com/cgi-bin/template/del_private_template?access_token=" + token;
		log.info("获取行业模板（POST）：" + url + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("获取行业模板（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		if(jsonRet.has("errcode") && jsonRet.getInt("errcode") == 0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 发送模板消息 
	 * http请求方式: POST https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN
	 * @param first		标题
	 * @param keynotes	参数[内容，颜色]
	 * @param reamrk	备注
	 * @return   {"errcode":0,"errmsg":"ok","msgid":200228332}
	 */
	public static Object sendMsg(String toUser,String template_id,String url,String[] first,List<String[]> keynotes,String[] remark){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("touser", toUser);
		jsonObj.put("template_id", template_id);
		jsonObj.put("url", url);
		JSONObject data = new JSONObject();
		JSONObject firstObj = new JSONObject();
		firstObj.put("value", first[0]);
		firstObj.put("color", first[1]);
		JSONObject remarkObj = new JSONObject();
		remarkObj.put("value", remark[0]);
		remarkObj.put("color", remark[1]);
		for(int i=0;i<keynotes.size();i++){
			JSONObject keynoteObj = new JSONObject();
			keynoteObj.put("value", keynotes.get(i)[0]);
			keynoteObj.put("color", keynotes.get(i)[1]);
			data.put("keynote" + (i+1), keynoteObj);
		}
		data.put("first", firstObj);
		data.put("remark", remarkObj);
		jsonObj.put("data", data);
		String token = AccessToken.getAccessToken();
		String sendurl = " https://api,weixin.qq.com/cgi-bin/template/del_private_template?access_token=" + token;
		log.info("发送模板消息 （POST）：" + sendurl + ",参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(sendurl, jsonObj);
		log.info("发送模板消息 （POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	
}
