package com.artsoft.oracle2;

import java.util.Locale;
import java.util.ResourceBundle;

public class ProParseUtil {
	private ResourceBundle bundle;

	public ProParseUtil(String fileName) {
		System.out.println(Locale.getDefault());
		this.bundle = ResourceBundle.getBundle(fileName, Locale.getDefault()); // 从xxx.properties中读取配置信息
	}

	public String getValue(String key) {
		return this.bundle.getString(key);
	}
}
