package me.jeekhan.leyi.controller;

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
 * 寰俊
 * @author Jee Khan
 *
 */
@Controller
public class WechatAction {

	
	/**
	 * 楠岃瘉鏈嶅姟鍣ㄥ湴鍧�鐨勬湁鏁堟��
	 * 1. 灏唗oken銆乼imestamp銆乶once涓変釜鍙傛暟杩涜瀛楀吀搴忔帓搴�
	 * 2. 灏嗕笁涓弬鏁板瓧绗︿覆鎷兼帴鎴愪竴涓瓧绗︿覆杩涜sha1鍔犲瘑
	 * 3. 寮�鍙戣�呰幏寰楀姞瀵嗗悗鐨勫瓧绗︿覆鍙笌signature瀵规瘮锛屾爣璇嗚璇锋眰鏉ユ簮浜庡井淇�
	 * @param mode
	 * @param articleId
	 * @param map
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
	
	@RequestMapping(value="/hl",method=RequestMethod.GET)
	@ResponseBody
	public String test(){
		return "ok";
	}

}
