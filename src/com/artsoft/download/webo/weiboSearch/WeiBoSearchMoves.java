package com.artsoft.download.webo.weiboSearch;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.artsoft.downloadThreadpool.IpFilter;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.TimeTest;

public class WeiBoSearchMoves {
	
	
	public static void mainProgram(int statnum, int endnum,int TV_TYPE) {
		// TODO Auto-generated method stub
		List<String> listArray = OracleHaoSou.selectdim_film(Integer.toString(statnum), Integer.toString(endnum));
//		System.out.println(listArray.size());
		for (Object Objstring : listArray) {
//			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			System.out.println(listTemp.get(1));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))&&listTemp.get(1) != null && !"".equals(listTemp.get(1))) {
				if (Integer.parseInt(listTemp.get(0))>4000) {
					
				
				String urlBranch = "";
				try {
					urlBranch = "http://s.weibo.com/weibo/" + java.net.URLEncoder.encode("#"+listTemp.get(1)+"#", "utf-8")
							+ "&Refer=STopic_box";

//					hunanBranch(urlBranch, listTemp.get(0), listTemp.get(1), "3");
					try {
						seleepTime(10);
						WeiBoSearchTVplay.WeiBoBranch(urlBranch, listTemp.get(0), TV_TYPE);
					} catch (Exception e) {
						// TODO: handle exception
//						WeiBoSearchTVplay.webDriver= null;
					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}

			}
			// intoPlayAmont("0", "���Ӿ�", "222", "0", "2014-10-15 23:10:10",
			// "baidu.com", "0", "3", "2014-10-15 23:10:10");
		}
	}
	

	public static void openstatic() {

		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ʼ");
		// IpFilter.mainip("http://index.haosou.com/");
		// CommonUtil.setLog("ip����ʱ��" + TimeTest.getNowTime("yyyy-MM-dd
		// HH:mm:ss"));
		String returnNumTVle = OracleHaoSou.returnNumPeople("ods.dim_film");
		System.out.println("��Ҫ�ɼ�����������Ϊ" + returnNumTVle);
		for (int i = 0; i < Integer.parseInt(returnNumTVle); i = i + 1000) {
			// i=15780;
//			if (i>16001) {
				
				int TV_TYPE = 3;
//				seleepTime(2);
				try {
					mainProgram(i, i + 1000, TV_TYPE);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
//			}
		}

		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ��");

	}

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":��ʼ");
		// String strurl = DownYoukuMovie
		// .youkuMaim("http://www.youku.com/v_olist/c_97_s_1_d_1_g_%E4%BC%98%E9%85%B7%E5%87%BA%E5%93%81.html");
		openstatic();
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":����");
	}

	// �ж����ݿ�ʼʱ��
	public static void TimingTime(int hh, int mm, int ss) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hh); // ����ʱ
		calendar.set(Calendar.MINUTE, mm); // ���Ʒ�
		calendar.set(Calendar.SECOND, ss); // ������

		Date time = calendar.getTime(); // �ó�ִ�������ʱ��,�˴�Ϊ�����12��00��00

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				System.out.println("-------�趨Ҫָ������--------");
				runstatic();
			}
		}, time, 1000 * 60 * 60 * 24);// �����趨����ʱÿ��̶�ִ��
	}

	public static void seleepTime(int t) {
		t = (int) (t * Math.random());
		t = t + 20;
		// t = 2;
		try {
			System.out.println("��ǰ�ȴ�" + t + "��");
			// System.out.println("�ȴ�2��,�ȴ�" + t + "��");
			Thread.sleep(t * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimingTime(1, 00, 00);
	}

}
