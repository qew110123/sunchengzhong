package com.artsoft.oracle2;

import java.util.LinkedHashMap;

public class CaseIgnoreMap<K, V> extends LinkedHashMap<K, V> {

	private static final long serialVersionUID = 3016354686483121490L;

	@Override
	public V get(Object key) { // ���ص����ݼ����ֶ�ȫ����Сд��������Ҫ����һ��get������ʹ֮��Сд������
		return super.get(key.toString().toLowerCase());
	}

}
