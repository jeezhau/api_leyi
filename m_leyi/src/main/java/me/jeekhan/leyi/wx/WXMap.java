package me.jeekhan.leyi.wx;

import java.util.HashMap;
import java.util.Map.Entry;

public class WXMap {
	private HashMap<String,Object> map = new HashMap<>();
	
	public void put(String key,Object object){
		map.put(key, object);
	}
	
	public Object get(String key){
		return map.get(key);
	}
	
	public boolean containsKey(String key){
		return map.containsKey(key);
	}
	
	public Object removeKey(String key){
		return map.remove(key);
	}
	public String getToUser(){
		return map.get("ToUserName").toString();
	}
	
	public void clear(){
		map.clear();
	}
	
	public String toXml(){
		StringBuffer  sb = new StringBuffer();
		for(Entry<String, Object> entry: map.entrySet()){
			sb.append("<").append(sb.append(entry.getKey())).append(">");
			if(entry.getValue() instanceof String){
				sb.append("<![CDATA[").append(sb.append(entry.getValue())).append("]]>");
			}else if(entry.getValue() instanceof WXMap){
				sb.append(sb.append(((WXMap)entry.getValue()).toXml()));
			}else{
				sb.append(sb.append(entry.getValue()));
			}
			sb.append("</").append(sb.append(entry.getKey())).append(">");
		}
		return sb.toString();
	}

}
