package com.artsoft.demo;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DemoJsoup {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		File input = new File("/tmp/input.html");
		Document doc = null;
		try {
//			doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
			doc =Jsoup.connect("http://baike.baidu.com/link?url=aoe7rD8rFgF6w_1UD08-XlUAt5Uqr_6Sb7Aw0dfHt5reNDSWJ3k1BIQBjOn2MchyxuZ4XpFxQGWaoMOe_NNkda").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(doc.toString());
//
//		Element content = doc.getElementById("content");
//		Elements links = content.getElementsByTag("a");
//		for (Element link : links) {
//		  String linkHref = link.attr("href");
//		  String linkText = link.text();
//		}

	}

}
