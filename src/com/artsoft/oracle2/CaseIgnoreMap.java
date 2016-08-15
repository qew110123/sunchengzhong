package com.artsoft.oracle2;

import java.util.LinkedHashMap;

public class CaseIgnoreMap<K, V> extends LinkedHashMap<K, V> {

	private static final long serialVersionUID = 3016354686483121490L;

	@Override
	public V get(Object key) { // 返回的数据集的字段全都是小写，所以需要处理一下get方法，使之大小写不敏感
		return super.get(key.toString().toLowerCase());
	}

}
