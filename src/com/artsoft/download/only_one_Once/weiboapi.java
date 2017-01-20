package com.artsoft.download.only_one_Once;

import java.net.MalformedURLException;
import java.net.URL;

import com.artsoft.util.DownloadUtil;

public class weiboapi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(DownloadUtil.getHtmlText("https://api.weibo.com/oauth2/access_token?client_id=2406922355&client_secret=bea8ed544a30f1e98608b41e886099fa&grant_type=authorization_code&redirect_uri=http://www.art-d.com.cn&code=ddfefec653e0acd7701ee07cdcdc822f", 1000 * 30, "UTF-8", null, null));
		URL url=null;
		try {
			url = new URL("https://api.weibo.com/oauth2/access_token?client_id=2406922355&client_secret=bea8ed544a30f1e98608b41e886099fa&grant_type=authorization_code&redirect_uri=http://www.art-d.com.cn&code=ddfefec653e0acd7701ee07cdcdc822f");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DownloadUtil.getContent(url);
	}

}
