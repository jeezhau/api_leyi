package me.jeekhan.leyi.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import me.jeekhan.leyi.common.SunSHAUtils;
/**
 * 微信
 * @author Jee Khan
 *
 */
@Controller
public class WechatAction {

	
	/**
	 * 微信服务器验证
	 *1. 将token、timestamp、nonce三个参数进行字典序排序
	 *2. 将三个参数字符串拼接成一个字符串进行sha1加密
	 *3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信 @param mode
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 */
	@RequestMapping(value="/wx",method=RequestMethod.GET,params={"signature","timestamp","nonce","echostr"})
	@ResponseBody
	public String virifyWXServer(@RequestParam("signature")String signature,@RequestParam("timestamp")String timestamp,
			@RequestParam("nonce")String nonce, @RequestParam("echostr")String echostr){
		String token = "";
		String[] arr = {token,timestamp,nonce};
		Arrays.sort(arr);
		String strAll = arr[0] + arr[1] + arr[2];
		String ret = null;
		try {
			ret = SunSHAUtils.encodeSHAHex(strAll);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(signature == ret){
			return echostr;
		}
		return "fail";
	}
	
	/**
	 * 接收消息
	 * @param is
	 * @return
	 */
	@RequestMapping(value="/wx",method=RequestMethod.POST)
	@ResponseBody
	public String recvMsg(InputStream is) {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuffer msg = new StringBuffer("");
		String line = "";
		try {
			while((line = br.readLine()) != null){
				msg.append(line);
			}
			System.out.println(msg.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

}
