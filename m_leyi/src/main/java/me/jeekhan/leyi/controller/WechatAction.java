package me.jeekhan.leyi.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import me.jeekhan.leyi.common.SunSHAUtils;
import me.jeekhan.leyi.wxapi.CommonAPI;
/**
 * 微信
 * @author Jee Khan
 *
 */
@Controller
public class WechatAction {

	
	/**
	 * 微信服务器验证
	 * 1. 将token、timestamp、nonce三个参数进行字典序排序
	 * 2. 将三个参数字符串拼接成一个字符串进行sha1加密
	 * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
	 * @param mode
	 * @param articleId
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/wx",method=RequestMethod.GET,params={"signature","timestamp","nonce","echostr"})
	@ResponseBody
	public String virifyWXServer(@RequestParam("signature")String signature,@RequestParam("timestamp")String timestamp,
			@RequestParam("nonce")String nonce, @RequestParam("echostr")String echostr){
		String token = CommonAPI.getParam("TOKEN");
		String[] arr = {token,timestamp,nonce};
		Arrays.sort(arr);
		String strAll = arr[0] + arr[1] + arr[2];
		String ret = null;
		try {
			ret = SunSHAUtils.encodeSHAHex(strAll);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(signature.equals(ret)){
			return echostr;
		}
		return "fail";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/wx",method=RequestMethod.POST)
	@ResponseBody
	public String recvMsg(InputStream is){
		BufferedReader br;
		StringBuffer sb = new StringBuffer();
		Map<String,String> map = new HashMap<>();
		try {
			String line = "";
			br = new BufferedReader(new InputStreamReader(is,"utf-8"));
			while((line=br.readLine())!=null){
				sb.append(line);
			}
			Document doc = DocumentHelper.parseText(sb.toString());
			Element xml = doc.getRootElement();
			/*  URL:http://m.jeekhan.me/leyi4m/wx
				ToUserName:jee_khan_zhao
				FromUserName:jee_khan_zhao
				CreateTime:1231313
				MsgType:text
				Content:这个一个测试
				MsgId:5643535434535*/
            for (Iterator i = xml.elementIterator(); i.hasNext();) {
                Element node = (Element) i.next();
                map.put(node.getName(), node.getText());
                System.out.println(node.getName() + ":" + node.getText());
            }

		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		}
		return "success";
	}

}
