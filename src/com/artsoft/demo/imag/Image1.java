package com.artsoft.demo.imag;

import java.util.List;

import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadImage;
import com.artsoft.util.TimeTest;

public class Image1 {

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		// try {
		// DownloadImage.download("http://hiphotos.baidu.com/zhixin/abpic/item/ac754782b2b7d0a21ced714ac9ef76094a369a85.jpg",
		// "’‘“˙≥….jpg","D:\\image\\");
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		int i=0;
		List<String> listArray = OracleHaoSou.selectphoto1();
		// CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss")+":"+);
		for (Object Objstring : listArray) {

			// System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
//			System.out.println(listTemp.get(1));
//			System.out.println(listTemp.get(2));
//			System.out.println(listTemp.get(3));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
				try {
					if (listTemp.get(0).contains("http")) {
						System.out.println(++i);
						String[] namelist=listTemp.get(0).split("/");
	//					DownloadImage.download(listTemp.get(1), listTemp.get(3) + "_"+listTemp.get(0).replace("*", "")+"_–°_"+i+".jpg", "D:\\image_baidu_xiao\\");
	//					DownloadImage.download(listTemp.get(1), namelist[namelist.length-1], "D:\\image_baidu_xiao\\");
						DownloadImage.download(listTemp.get(0), namelist[namelist.length-1], "D:\\yanyuanjuyao\\");
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
//					System.out.println(listTemp.get(0));
//					System.out.println(listTemp.get(1));
//					CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + listTemp.get(0) + listTemp.get(1));
				}
				
//				try {
////					System.out.println(++i);
//					String[] namelist=listTemp.get(1).split("/");
////					DownloadImage.download(listTemp.get(1), listTemp.get(3) + "_"+listTemp.get(0).replace("*", "")+"_–°_"+i+".jpg", "D:\\image_baidu_xiao\\");
////					DownloadImage.download(listTemp.get(1), namelist[namelist.length-1], "D:\\image_baidu_xiao\\");
//					DownloadImage.download(listTemp.get(1), namelist[namelist.length-1], "D:\\image_baidu_big_tvplay\\");
//					
//					
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
////					System.out.println(listTemp.get(0));
////					System.out.println(listTemp.get(1));
////					CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + listTemp.get(0) + listTemp.get(1));
//				}
				
				
				
				
			}
		}

	}
}
