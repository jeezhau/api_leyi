package me.jeekhan.leyi.wxapi;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.jeekhan.leyi.common.HttpUtils;

public class CommonHandle {
	private  Logger log = LoggerFactory.getLogger(CommonHandle.class);
	/**
	 * 通过ticket换取二维码
	 * @param ticket
	 * @return
	 */
	public File getQRCode(String ticket){
		String url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + ticket;
		log.info("通过ticket换取二维码(GET):" + url);
		File file = HttpUtils.downloadFileSSL(url);
		if(file != null){
			log.info("通过ticket换取二维码(GET)成功");
		}else{
			log.info("通过ticket换取二维码(GET)失败");
		}
		return file;
	}
	
	
}
