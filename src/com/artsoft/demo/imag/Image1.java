package com.artsoft.demo.imag;

import com.artsoft.download.TVPlay.HuaXu.HuaXuYouKu;
import com.artsoft.util.DownloadImage;
import com.artsoft.util.TimeTest;

public class Image1 {

	public static String downloadimg(String urldownload) {
		String nameurl = "";
		String[] namelist = urldownload.split("/");
		try {
			nameurl = namelist[namelist.length - 1] + ".jpg";
			// DownloadImage.download(urldownload, nameurl,
			// "D:\\Image\\"+TimeTest.getNowTime("yyyyMMdd")+"\\weixin\\");
			DownloadImage.download(urldownload, nameurl, "D:\\Image\\"+TimeTest.getNowTime("yyyyMMdd")+"\\huaxu\\");
			System.out.println("图片下载成功");
//			weixin.leibiaoFavFTPUtil(nameurl);
			HuaXuYouKu.leibiaoFavFTPUtil(nameurl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("图片下载出错！");
		}
		return nameurl;

	}
	
	
	public static String downloadimg_no_jpg(String urldownload) {
		String nameurl = "";
		String[] namelist = urldownload.split("/");
		try {
			nameurl = namelist[namelist.length - 1] ;
			// DownloadImage.download(urldownload, nameurl,
			// "D:\\Image\\"+TimeTest.getNowTime("yyyyMMdd")+"\\weixin\\");
			DownloadImage.download(urldownload, nameurl, "D:\\Image\\"+TimeTest.getNowTime("yyyyMMdd")+"\\huaxu\\");
			System.out.println("图片下载成功");
//			weixin.leibiaoFavFTPUtil(nameurl);
			HuaXuYouKu.leibiaoFavFTPUtil(nameurl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("图片下载出错！");
		}
		return nameurl;

	}
	
	
	public static String downloadimg_baidu_Tvplay_big(String urldownload) {
		String nameurl = "";
		String[] namelist = urldownload.split("/");
		try {
			nameurl = namelist[namelist.length - 1] ;
			// DownloadImage.download(urldownload, nameurl,
			// "D:\\Image\\"+TimeTest.getNowTime("yyyyMMdd")+"\\weixin\\");
			DownloadImage.download(urldownload, nameurl, "D:\\Image\\"+TimeTest.getNowTime("yyyyMMdd")+"\\tvplay\\stills\\big\\");
			System.out.println("图片下载成功");
//			weixin.leibiaoFavFTPUtil(nameurl);
//			HuaXuYouKu.leibiaoFavFTPUtil(nameurl);
			ftp_pathname.FavFTPUtilbig(nameurl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("图片下载出错！");
		}
		return nameurl;

	}
	
	
	public static String downloadimg_baidu_Tvplay_small(String urldownload) {
		String nameurl = "";
		String[] namelist = urldownload.split("/");
		try {
			nameurl = namelist[namelist.length - 1] ;
			// DownloadImage.download(urldownload, nameurl,
			// "D:\\Image\\"+TimeTest.getNowTime("yyyyMMdd")+"\\weixin\\");
			DownloadImage.download(urldownload, nameurl,  "D:\\Image\\"+TimeTest.getNowTime("yyyyMMdd")+"\\tvplay\\stills\\small\\");
			System.out.println("图片下载成功");
//			weixin.leibiaoFavFTPUtil(nameurl);
//			HuaXuYouKu.leibiaoFavFTPUtil(nameurl);
			ftp_pathname.FavFTPUtilsmall(nameurl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("图片下载出错！");
		}
		return nameurl;

	}

	public static void main(String[] args) {

		downloadimg("http://r4.ykimg.com/054204085260D1356A0A471F0FFA2D40");

	}
}
