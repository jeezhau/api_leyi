package me.jeekhan.leyi.wxapi;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.jeekhan.leyi.common.HttpUtils;

/**
 * 客服帐号管理
 * 每个公众号最多添加10个客服账号
 * 
 * @author Jee Khan
 *
 */
public class CustomServiceAccountHandle {
	static Logger log = LoggerFactory.getLogger(CustomServiceAccountHandle.class);
	
	/**
	 * 添加客服帐号
	 * http请求方式: POST https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN
	 * @return 00-成功，【其他】-失败信息
	 */
	protected String addKF(String account,String nickname,String pwd){
		String jsonStr = "{\"kf_account\":\""+ account +"\",\"nickname\":\""+nickname+"\",\"password\":\""+pwd+"\"}";
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=" + token;
		log.info("添加客服帐号（POST）：" + url + ",参数：" + jsonStr);
		String ret = HttpUtils.doPostSSL(url, jsonStr);
		log.info("添加客服帐号（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		if(jsonRet.has("errcode") && jsonRet.getInt("errcode") == 0){
			return "00";
		}else{
			if(jsonRet.has("errmsg")){
				return jsonRet.getString("errmsg");
			}else{
				return "添加客服帐号失败";
			}
		}
	}

	/**
	 * 修改客服帐号
	 * http请求方式: POST https://api.weixin.qq.com/customservice/kfaccount/update?access_token=ACCESS_TOKEN
	 * @return 00-成功，【其他】-失败信息
	 */
	protected String updateKF(String account,String nickname,String pwd){
		String jsonStr = "{\"kf_account\":\""+ account +"\",\"nickname\":\""+nickname+"\",\"password\":\""+pwd+"\"}";
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/customservice/kfaccount/update?access_token=" + token;
		log.info("修改客服帐号（POST）：" + url + ",参数：" + jsonStr);
		String ret = HttpUtils.doPostSSL(url, jsonStr);
		log.info("修改客服帐号（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		if(jsonRet.has("errcode") && jsonRet.getInt("errcode") == 0){
			return "00";
		}else{
			if(jsonRet.has("errmsg")){
				return jsonRet.getString("errmsg");
			}else{
				return "修改客服帐号失败";
			}
		}
	}
	
	/**
	 * 删除客服帐号
	 * http请求方式: POST https://api.weixin.qq.com/customservice/kfaccount/del?access_token=ACCESS_TOKEN
	 * @return 00-成功，【其他】-失败信息
	 */
	protected Object deleteKF(String account,String nickname,String pwd){
		String jsonStr = "{\"kf_account\":\""+ account +"\",\"nickname\":\""+nickname+"\",\"password\":\""+pwd+"\"}";
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/customservice/kfaccount/del?access_token=" + token;
		log.info("修改客服帐号（POST）：" + url + ",参数：" + jsonStr);
		String ret = HttpUtils.doPostSSL(url, jsonStr);
		log.info("修改客服帐号（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		if(jsonRet.has("errcode") && jsonRet.getInt("errcode") == 0){
			return "00";
		}else{
			if(jsonRet.has("errmsg")){
				return jsonRet.getString("errmsg");
			}else{
				return "修改客服帐号失败";
			}
		}
	}
	
	/**
	 * 设置客服帐号的头像
	 * 开发者可调用本接口来上传图片作为客服人员的头像，头像图片文件必须是jpg格式，推荐使用640*640大小的图片以达到最佳效果
	 * 调用示例：使用curl命令，用FORM表单方式上传一个多媒体文件，curl命令的具体用法请自行了解
	 * http请求方式: POST/FORM
     * http://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token=ACCESS_TOKEN&kf_account=KFACCOUNT
     * @return
	 */
	protected Object uploadHeadImg(){
		
		return null;
	}
	
	/**
	 * 获取所有客服账号
	 * 开发者通过本接口，获取公众号中所设置的客服基本信息，包括客服工号、客服昵称、客服登录账号。 
	 * http请求方式: GET
     * https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=ACCESS_TOKEN
     * {
     *     "kf_list": [
     *         {
     *             "kf_account": "test1@test", 
     *             "kf_nick": "ntest1", 
     *             "kf_id": "1001"
     *             "kf_headimgurl": " http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw/0"
     *         }, 
     *         ....
     *     ]
     * }
     */
	protected Object getKFList(){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=" + token;
		log.info("获取所有客服账号（GET）：" + url );
		String ret = HttpUtils.doGetSSL(url);
		log.info("获取所有客服账号（GET）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet; 
	}
}
