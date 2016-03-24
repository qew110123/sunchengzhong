package com.artsoft.download.doubanDuShu;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.download.Movies.DownDoubanMovie;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class doubanDuShu {
	
	public static void openstatic() {
		
//		String[] list = { "小说", "外国文学", "文学", "随笔", "中国文学", "经典", "散文", "日本文学", "村上春树", "童话", "诗歌", "杂文", "王小波", "儿童文学", "古典文学", "张爱玲", "余华", "名著", "当代文学", "钱钟书", "鲁迅", "外国名著", "诗词", "茨威格", "米兰·昆德拉", "杜拉斯", "港台", "漫画", "绘本", "推理", "青春", "言情", "科幻", "东野圭吾", "悬疑", "武侠", "韩寒", "奇幻", "日本漫画", "耽美", "亦舒", "三毛", "安妮宝贝", "网络小说", "郭敬明", "推理小说", "穿越", "金庸", "轻小说", "阿加莎·克里斯蒂", "几米", "张小娴", "幾米", "魔幻", "青春文学", "科幻小说", "J.K.罗琳", "高木直子", "古龙", "沧月", "落落", "张悦然", "蔡康永", "历史", "心理学", "哲学", "传记", "文化", "社会学", "艺术", "设计", "政治", "社会", "建筑", "宗教", "电影", "数学", "政治学", "回忆录", "思想", "中国历史", "国学", "音乐", "人文", "戏剧", "人物传记", "绘画", "艺术史", "佛教", "军事", "西方哲学", "近代史", "二战", "自由主义", "考古", "美术", "爱情", "旅行", "生活", "励志", "成长", "心理", "摄影", "女性", "职场", "美食", "教育", "游记", "灵修", "健康", "情感", "手工", "养生", "两性", "人际关系", "家居", "自助游", "经济学", "管理", "经济", "金融", "商业", "投资", "营销", "创业", "理财", "广告", "股票", "企业史", "策划", "科普", "互联网", "编程", "科学", "交互设计", "用户体验", "算法", "web", "科技", "UE", "通信", "交互", "UCD", "神经网络", "程序" };
//		for (String stringtext : list) {
//			for (int i = 0; i <= 500; i = i + 15) {
//				try {
//					System.out.println("https://www.douban.com/tag/" + stringtext
//							+ "/book?start=" + i);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//			}
//
//		}
		
			
			mainurl("https://www.douban.com/tag/%E5%B0%8F%E8%AF%B4/book?start=15");
		
	}
	
	private static boolean mainurl(String urlMain) {
		// TODO Auto-generated method stub
		
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			// return;
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}

		Document doc = Jsoup.parse(strHtml);

		Elements links = doc.select("a.title");
		for (Element element : links) {
			String url="";
			String title="";
			String id="";
			System.out.println(url=element.attr("href"));
			System.out.println(title=element.text());
			System.out.println(id = HtmlAnalyze.getTagText(url, "/subject/", "/?"));
			doubanurl(id,title,url);
		}
		
		
		return false;
		
		
	}

	private static void doubanurl(String id, String title, String urlMain) {
		// TODO Auto-generated method stub
		urlMain=urlMain.replace("https:", "http:");
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			// return;
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}

		Document doc = Jsoup.parse(strHtml);
		
		String score ="";
		System.out.println(score=doc.select("strong.rating_num").text());
		
		String score_num="";
		System.out.println(score_num=doc.select("a.rating_people").text().replace("人评价", ""));
		
		String author="";
		System.out.println(author=HtmlAnalyze.getTagText(strHtml, "作者</span>:", "<br/>"));
		
		String press="";
		System.out.println(press=HtmlAnalyze.getTagText(strHtml, "出版社:</span>", "<br/>"));
		
		String original_name="";
		System.out.println(original_name=HtmlAnalyze.getTagText(strHtml, "原作名:</span>", "<br/>"));
		
		String translator="";
		System.out.println(translator=HtmlAnalyze.getTagText(strHtml, "译者</span>:", "<br/>"));
		
		String publication_year="";
		System.out.println(publication_year=HtmlAnalyze.getTagText(strHtml, "出版年:</span>", "<br/>"));
		
		String pages="";
		System.out.println(pages=HtmlAnalyze.getTagText(strHtml, "页数:</span>", "<br/>"));
		
		
		String price="";
		System.out.println(price=HtmlAnalyze.getTagText(strHtml, "定价:</span>", "<br/>"));
		
		String binding="";
		System.out.println(binding=HtmlAnalyze.getTagText(strHtml, "装帧:</span>", "<br/>"));
		
		String series="";
		System.out.println(series=HtmlAnalyze.getTagText(strHtml, "丛书:</span>", "<br/>"));
		
		String ISBN="";
		System.out.println(ISBN=HtmlAnalyze.getTagText(strHtml, "ISBN:</span>", "<br/>"));
		
		String Abstract="";
		System.out.println(Abstract=HtmlAnalyze.getTagText(strHtml, "<span class=\"all hidden\">", "</span>").replace(".intro p{text-indent:2em;}", ""));
		
		String Biography="";
		System.out.println(Biography=HtmlAnalyze.getTagText(strHtml, "<div class=\"indent \">", "</p></div>").replace(".intro p{text-indent:2em;}", ""));
		
		String catalog="";
		System.out.println(catalog=HtmlAnalyze.getTagText(strHtml, "目录</span>", "</div>").replace("&nbsp;", ""));
		
		String label="";
		Elements cataloglinks = doc.select("a.tag");
		for (Element element : cataloglinks) {
			label=label+element.text();
		}
		System.out.println(label);
		
		
		
		
		
	}


	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开始");
		// String strurl = DownYoukuMovie
		// .youkuMaim("http://www.youku.com/v_olist/c_97_s_1_d_1_g_%E4%BC%98%E9%85%B7%E5%87%BA%E5%93%81.html");
		openstatic();
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结束");
	}

	// 判断数据开始时间
	public static void TimingTime(int hh, int mm, int ss) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hh); // 控制时
		calendar.set(Calendar.MINUTE, mm); // 控制分
		calendar.set(Calendar.SECOND, ss); // 控制秒

		Date time = calendar.getTime(); // 得出执行任务的时间,此处为今天的12：00：00

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				System.out.println("-------设定要指定任务--------");
				runstatic();
			}
		}, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// runstatic();
		openstatic();
//		 TimingTime(11, 59, 59);

	}

}
