package me.jeekhan.leyi.wxapi;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.jeekhan.leyi.common.HttpUtils;

/**
 * 用户管理
 * @author Jee Khan
 *
 */
public class UserMgrHandle {
	private static Logger log = LoggerFactory.getLogger(UserMgrHandle.class);
	
	/**
	 * 创建分组
	 * 一个公众账号，最多支持创建100个分组
	 * @param groupName
	 * @return {"group": {"id": 107, "name": "test"}}
	 */
	public Object addGroup(String groupName){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=" + token ;
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("name", groupName);
		log.info("创建分组（POST）" + url + "，参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("创建分组（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	/**
	 * 查询所有分组
	 * @return {"groups": [{"id": 0, "name": "未分组", "count": 72596},]}
	 *  id:	分组id，由微信分配 
	 *  name :分组名字，UTF8编码 
	 *  count :分组内用户数量 
	 */
	public Object searchAllGroup(){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=" + token ;
		log.info("查询所有分组(GET):" + url);
		String ret = HttpUtils.doGetSSL(url);
		log.info("查询所有分组(GET)返回:" + url);
		JSONObject retObj = new JSONObject(ret);
		return retObj;
	}
	
	/**
	 * 查询用户所在分组
	 * @param openId	用户的OpenID 
	 * @return {"groupid": 102}
	 */
	public Object getUsersGroup(String openId){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=" + token ;
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("openid", openId);
		log.info("查询用户所在分组（POST）" + url + "，参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("查询用户所在分组（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	/**
	 * 修改分组名
	 * @param groupName	分组id，由微信分配 
	 * @param groupId	分组名字（30个字符以内） 
	 * @return
	 */
	public String updateGroupName(String groupName,int groupId){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=" + token ;
		JSONObject jsonObj = new JSONObject();
		JSONObject group = new JSONObject();
		group.put("id", groupId);
		group.put("name", groupName);
		jsonObj.put("group", group);
		log.info("修改分组名（POST）" + url + "，参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("修改分组名（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		if(jsonRet.has("errcode") && jsonRet.getInt("errcode") == 0){
			return "00";
		}else{
			if(jsonRet.has("errmsg")){
				return jsonRet.getString("errmsg");
			}else{
				return "修改分组名失败";
			}
		}
	}
	
	/**
	 * 移动用户分组
	 * @param openId		用户唯一标识符 
	 * @param to_groupId	分组id 
	 * @return
	 */
	public String moveUsersGroup(String openId,int to_groupId){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=" + token ;
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("openid", openId);
		jsonObj.put("to_groupid", to_groupId);
		log.info("移动用户分组（POST）" + url + "，参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("移动用户分组（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		if(jsonRet.has("errcode") && jsonRet.getInt("errcode") == 0){
			return "00";
		}else{
			if(jsonRet.has("errmsg")){
				return jsonRet.getString("errmsg");
			}else{
				return "修改分组名失败";
			}
		}
	}
	
	/**
	 * 批量移动用户分组
	 * @param openIds		用户唯一标识符 
	 * @param to_groupId	分组id 
	 * @return
	 */
	public String moveUsersGroup(List<String> openIds,int to_groupId){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=" + token ;
		JSONObject jsonObj = new JSONObject();
		JSONArray array = new JSONArray();
		for(String openId:openIds){
			array.put(openId);
		}
		jsonObj.put("openid_list", array);
		jsonObj.put("to_groupid", to_groupId);
		log.info("批量移动用户分组（POST）" + url + "，参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("批量移动用户分组（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		if(jsonRet.has("errcode") && jsonRet.getInt("errcode") == 0){
			return "00";
		}else{
			if(jsonRet.has("errmsg")){
				return jsonRet.getString("errmsg");
			}else{
				return "批量移动用户分组失败";
			}
		}
	}
	
	/**
	 * 删除分组
	 * @param groupId 分组的id 
	 * @return
	 */
	public String deleteGroup(int groupId){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=" + token ;
		JSONObject jsonObj = new JSONObject();
		JSONObject group = new JSONObject();
		group.put("id", groupId);
		jsonObj.put("group", group);
		log.info("删除分组（POST）" + url + "，参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("删除分组（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		if(jsonRet.has("errcode") && jsonRet.getInt("errcode") == 0){
			return "00";
		}else{
			if(jsonRet.has("errmsg")){
				return jsonRet.getString("errmsg");
			}else{
				return "删除分组失败";
			}
		}
	}

	/**
	 * 设置用户备注名
	 * @param openId	用户标识 
	 * @param remark	新的备注名，长度必须小于30字符 
	 * @return
	 */
	public String setUserRemark(String openId,String remark){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=" + token ;
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("openid", openId);
		jsonObj.put("remark", remark);
		log.info("设置用户备注名（POST）" + url + "，参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("设置用户备注名（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		if(jsonRet.has("errcode") && jsonRet.getInt("errcode") == 0){
			return "00";
		}else{
			if(jsonRet.has("errmsg")){
				return jsonRet.getString("errmsg");
			}else{
				return "修改分组名失败";
			}
		}
	}
	
	/**
	 * 获取用户基本信息（包括UnionID机制）
	 * @param openId
	 * @return
	 */
	public Object getUserInfo(String openId){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + token + "&openid=" + openId + "&lang=" + "zh_CN";
		log.info("获取用户基本信息（GET）" + url );
		String ret = HttpUtils.doGetSSL(url);
		log.info("获取用户基本信息（GET）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	/**
	 * 批量获取用户基本信息
	 * @param openIds	用户的标识，对当前公众号唯一 
	 * @return
	 */
	public Object getUserInfo(List<String> openIds){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=" + token ;
		JSONObject jsonObj = new JSONObject();
		JSONArray array = new JSONArray();
		for(String openId:openIds){
			JSONObject user = new JSONObject();
			user.put("openid",openId);
			user.put("lang","zh_CN");
			array.put(user);
		}
		jsonObj.put("openid_list", array);
		jsonObj.put("user_list", array);
		log.info("批量获取用户基本信息（POST）" + url + "，参数：" + jsonObj);
		String ret = HttpUtils.doPostSSL(url, jsonObj);
		log.info("批量获取用户基本信息（POST）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
	
	/**
	 * 获取用户列表
	 * 公众号可通过本接口来获取帐号的关注者列表，关注者列表由一串OpenID（加密后的微信号，每个用户对每个公众号的OpenID是唯一的）组成。
	 * 	一次拉取调用最多拉取10000个关注者的OpenID，可以通过多次拉取的方式来满足需求
	 * @param next_openId	第一个拉取的OPENID，不填默认从头开始拉取 
	 * @return {"total":2,"count":2,"data":{"openid":["","OPENID1","OPENID2"]},"next_openid":"NEXT_OPENID"}
	 */
	public Object getUserList(String next_openId){
		String token = AccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + token + "next_openid=" + next_openId;
		log.info("获取用户列表（GET）" + url );
		String ret = HttpUtils.doGetSSL(url);
		log.info("获取用户列表（GET）返回：" + ret );
		JSONObject jsonRet = new JSONObject(ret);
		return jsonRet;
	}
}
