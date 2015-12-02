package com.artsoft.downloadThreadpool;

import com.artsoft.pool.TaskInterface;
import com.artsoft.pool.ThreadPool;
import com.artsoft.util.DealProxy;
import com.artsoft.util.DownloadUtil;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class IpFilter implements TaskInterface {
	static ThreadPool pool = new ThreadPool(10);
	
	String urlMain; //
    Proxy proxy ;

	public static void main(String[] args) {
		ipfiler("http://index.haosou.com/");
	}
	
	public IpFilter(String urlMain,Proxy proxy){
		this.urlMain=urlMain;
		this.proxy=proxy;
	}

	private static void ipfiler(String urlMain) {
		// TODO Auto-generated method stub
		String strHtml = DownloadUtil.getHtmlText("http://vxer.daili666api.com/ip/?tid=559245058880483&num=1000",
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
//			System.out.println(urlreturnHtml(urlMain, proxy));
			while (pool.getPoolNum() > 10) {
				try {
					System.out.println("�߳���������10���ȴ�5s");
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("��ǰ�����߳�thread:"+pool.getPoolNum());
			pool.performTask(new IpFilter(urlMain, proxy));
//			if (returnboolean(urlMain, proxy)) {
//				System.out.println("����ʹ�õ�text"+strIp+":"+strPort);
//			}
		}

	}

	public static String urlreturnHtml(String urlBranch, Proxy proxy) {
		String strHtml = "";
		// strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8",
		// null, proxy);
		boolean bb = true;
		int i = 0;
		while (bb) {
			// proxy = DealProxy.getInstance().getPoxxy();
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, proxy);
			if (strHtml != null && !"".equals(strHtml) && strHtml.contains("����ָ��-���������ݷ���ƽ̨")) {
				bb = false;
				if (strHtml.contains("360ָ��_�����쳣����")) {
					bb = true;
					System.out.println(Thread.currentThread().getName());
					System.out.println("ip �������");
				}
			} else {
				System.out.println("�򿪳���" + i + "��,���ӣ�" + urlBranch);
			}
			if (i > 20) {
				bb = false;
			}
			i += 1;
		}
		return strHtml;
	}

	public static boolean returnboolean(String urlBranch, Proxy proxy) {
		String strHtml = "";
		strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, proxy);
		if (strHtml != null && !"".equals(strHtml) && strHtml.contains("����ָ��-���������ݷ���ƽ̨")) {
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
		
		if (returnboolean(urlMain, proxy)) {
			System.out.println("����ʹ�õ�text"+proxy);
		}else{
			System.out.println("������");
		}
	}

}
