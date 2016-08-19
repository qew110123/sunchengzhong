package com.artsoft.demo.imag;

import java.util.List;

import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadImage;
import com.artsoft.util.TimeTest;

public class Image2 {
	
	public static String imagUrldownload(String urldownload){
		String nameurl="";
		String[] namelist=urldownload.split("/");
		try {
			nameurl=namelist[namelist.length-2]+".jpg";
			DownloadImage.download(urldownload, nameurl, "D:\\Image\\weixin\\");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("图片下载出错！");
		}
		return nameurl;
	}
	
	
	public static String imagUrldownload_1(String urldownload){
		String nameurl="";
		String[] namelist=urldownload.split("/");
		try {
			nameurl=namelist[namelist.length-1]+"";
			DownloadImage.download(urldownload, nameurl, "D:\\Image\\weixin\\");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("图片下载出错！");
		}
		return nameurl;
	}

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		// try {
		// DownloadImage.download("http://hiphotos.baidu.com/zhixin/abpic/item/ac754782b2b7d0a21ced714ac9ef76094a369a85.jpg",
		// "赵寅成.jpg","D:\\image\\");
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
	//					DownloadImage.download(listTemp.get(1), listTemp.get(3) + "_"+listTemp.get(0).replace("*", "")+"_小_"+i+".jpg", "D:\\image_baidu_xiao\\");
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
////					DownloadImage.download(listTemp.get(1), listTemp.get(3) + "_"+listTemp.get(0).replace("*", "")+"_小_"+i+".jpg", "D:\\image_baidu_xiao\\");
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
