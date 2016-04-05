package com.artsoft.downloadThreadpool;

import com.artsoft.pool.TaskInterface;
import com.artsoft.pool.ThreadPool;
import com.artsoft.util.DealProxy;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.ReadTxtFile;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class IpFilter implements TaskInterface {
	static ThreadPool pool = new ThreadPool(10);
	static String strproxy = "";
	String urlMain; //
	Proxy proxy;
	String stringip;

	public IpFilter(String urlMain, Proxy proxy, String stringip) {
		this.urlMain = urlMain;
		this.proxy = proxy;
		this.stringip = stringip;
	}

	private static void ipfiler(String urlMain) {
		// TODO Auto-generated method stub
//		String strHtml = DownloadUtil.getHtmlText("http://dev.kuaidaili.com/api/getproxy?orderid=915195947631121&num=999&area=&area_ex=&port=&port_ex=&ipstart=&carrier=0&an_ha=1&an_an=1&protocol=1&method=2&quality=0&sort=0&b_pcchrome=1&b_pcie=1&b_pcff=1&showtype=1",
//				1000 * 30, "UTF-8", null, null);
		
		String strHtml = DownloadUtil.getHtmlText("http://qsrdk.daili666api.com/ip/?tid=559375659838998&num=1000&delay=5&category=2&foreign=none",
				1000 * 30, "UTF-8", null, null);
		// System.out.println(strHtml);
		String[] iplist = strHtml.split("\r\n");
		// System.out.println(iplist.length);
		for (String stringip : iplist) {
			String strIp = stringip.split(":")[0];
			String strPort = stringip.split(":")[1];
			Proxy proxy = proxy(strIp, strPort);
			System.out.println(proxy);
			// DownloadUtil.getHtmlText("http://vxer.daili666api.com/ip/?tid=559245058880483&num=1000",
			// 1000 * 30, "UTF-8", null, proxy );
			// System.out.println(urlreturnHtml(urlMain, proxy));
			while (pool.getPoolNum() > 10) {
				try {
					System.out.println("线程数量大于10，等待5s");
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("当前启动线程thread:" + pool.getPoolNum());
			pool.performTask(new IpFilter(urlMain, proxy, stringip));
			// if (returnboolean(urlMain, proxy)) {
			// System.out.println("可以使用的text"+strIp+":"+strPort);
			// }
		}

	}

	// public static String urlreturnHtml(String urlBranch, Proxy proxy,String
	// stringip) {
	// String strHtml = "";
	// // strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8",
	// // null, proxy);
	// boolean bb = true;
	// int i = 0;
	// while (bb) {
	// // proxy = DealProxy.getInstance().getPoxxy();
	// strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null,
	// proxy);
	// if (strHtml != null && !"".equals(strHtml) &&
	// strHtml.contains("好搜指数-搜索大数据分享平台")) {
	// bb = false;
	// if (strHtml.contains("360指数_访问异常出错")) {
	// bb = true;
	// System.out.println(Thread.currentThread().getName());
	// System.out.println("ip 代理出错");
	// }
	// } else {
	// System.out.println("打开出错" + i + "次,链接：" + urlBranch);
	// }
	// if (i > 20) {
	// bb = false;
	// }
	// i += 1;
	// }
	// return strHtml;
	// }

	public static boolean returnboolean(String urlBranch, Proxy proxy, String stringip) {
		String strHtml = "";
		strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, proxy);
//		if (strHtml != null && !"".equals(strHtml) && strHtml.contains("好搜指数-搜索大数据分享平台")) {
			if (strHtml != null && !"".equals(strHtml)) {
			return true;
		} else {
			return false;
		}
	}

	private static Proxy proxy(String strIp, String strPort) {
		InetSocketAddress addr = new InetSocketAddress(strIp, Integer.valueOf(strPort));
		Proxy proxydata = new Proxy(Proxy.Type.HTTP, addr);
		return proxydata;
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub

		if (returnboolean(urlMain, proxy, stringip)) {
			System.out.println("可以使用的text" + proxy);
			strproxy = strproxy + stringip + "\r\n";
			System.out.println(strproxy);
		} else {
			System.out.println("不可以");
		}
	}

	public static void mainip(String urlma) {
		boolean bb=true;
		while (bb) {
			try {
				strproxy="";
				ipfiler(urlma);
				ReadTxtFile.wirterfile("proxy.txt", strproxy);
				bb=false;
						
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("获取时间超时，重新获取数据");
			}
		}
		
		
	}

	public static void main(String[] args) {
		// ReadTxtFile.wirterfile("proxy.txt", "231321321");
		// ReadTxtFile.wirterfile("proxy.txt", "nihao你好");

		// TODO Auto-generated method stub
		
//		String strHtml = ReadTxtFile.getKeyWordFromFile("proxy.txt");
//		// System.out.println(strHtml);
//		String[] iplist = strHtml.split("\n");
//		// System.out.println(iplist.length);
//		for (String stringip : iplist) {
//			String strIp = stringip.split(":")[0];
//			String strPort = stringip.split(":")[1];
//			Proxy proxy = proxy(strIp, strPort);
//			System.out.println(proxy);
//			// DownloadUtil.getHtmlText("http://vxer.daili666api.com/ip/?tid=559245058880483&num=1000",
//			// 1000 * 30, "UTF-8", null, proxy );
//			// System.out.println(urlreturnHtml(urlMain, proxy));
//			while (pool.getPoolNum() > 10) {
//				try {
//					System.out.println("线程数量大于10，等待5s");
//					Thread.sleep(5000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			System.out.println("当前启动线程thread:" + pool.getPoolNum());
//			pool.performTask(new IpFilter("http://index.haosou.com/", proxy, stringip));
//			// if (returnboolean(urlMain, proxy)) {
//			// System.out.println("可以使用的text"+strIp+":"+strPort);
//			// }
//		}
////		strproxy
////		System.out.println(strproxy);
//		ReadTxtFile.wirterfile("proxy1.txt", strproxy);
		mainip("http://www.baidu.com/");
	}

}
