package com.artsoft.download.BaiDu;

import java.util.List;

import com.artsoft.oracle.OracleBaidu;

public class tem_tvplay_person_tvplay {

	public static void runupdate() {
		List<String> listArray = OracleBaidu.selectbaidudianshijuTVplay();

		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			String id = "";
			String name = "";
			String url = "";
			System.out.println(id = listTemp.get(0));
			System.out.println(url = listTemp.get(2));
			System.out.println(name = listTemp.get(1));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0)) && listTemp.get(1) != null
					&& !"".equals(listTemp.get(1)) && listTemp.get(2) != null && !"".equals(listTemp.get(2))) {
//				BaiDuTeleplayDownload.mainmore(id, url, name);
				tem_tvplay_person.play_people(id, name, url, 0);
			}

		}
		System.out.println(listArray.size());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runupdate();
	}

}
