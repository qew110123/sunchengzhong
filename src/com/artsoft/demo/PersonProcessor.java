package us.codecraft.jobhunter.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import us.codecraft.jobhunter.mapper.UrlMapper;
import us.codecraft.jobhunter.model.Person;
import us.codecraft.jobhunter.model.PersonAward;
import us.codecraft.jobhunter.model.PersonCareer;
import us.codecraft.jobhunter.model.PersonDescription;
import us.codecraft.jobhunter.model.PersonImg;
import us.codecraft.jobhunter.model.PersonOpus;
import us.codecraft.jobhunter.model.PersonRelatedActor;
import us.codecraft.jobhunter.model.PersonRelationshipActor;
import us.codecraft.jobhunter.model.PersonRolePlay;
import us.codecraft.jobhunter.model.PersonSocialActivities;
import us.codecraft.jobhunter.pipeline.PersonDaoPipeline;
import us.codecraft.jobhunter.util.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

/**
 * @author code4crafer@gmail.com
 *         Date: 13-6-23
 *         Time: 下午4:19
 */
@Component
public class PersonProcessor implements PageProcessor {

    @Qualifier("PersonDaoPipeline")
    @Autowired
    private Pipeline personDaoPipeline;
    
    @Resource
    private PersonDaoPipeline _personDaoPipeline;
    
    private static final String MAIN_URL = "http://baike\\.baidu\\.com/view/\\d*.htm";
    private static final String SUBVIEW_URL = "http://baike\\.baidu\\.com/subview/.*.htm";
    private static final String ITEM_URL = "http://baike.baidu.com/item/.*";
    List<String> urlList = new ArrayList<>();
    List<String> urlExistsList = new ArrayList<>();
    List<String> urlErrorList = new ArrayList<>();
    
    public void setUrlList(List<String> urlList) {
		this.urlList = urlList;
	}

	private Site site = Site
            .me()
            .setDomain("baike.baidu.com")
//            .setSleepTime(new Random().nextInt(300000)%(300000-50000+1) + 50000).setCharset("UTF-8")//至少睡眠10s
//            .setSleepTime(new Random().nextInt(90000)%(90000-10000+1) + 10000).setCharset("UTF-8")//至少睡眠10s 临时注释
            .setSleepTime(new Random().nextInt(50000)%(50000-10000+1) + 10000).setCharset("UTF-8")//至少睡眠10s 临时注释
//            .setSleepTime(new Random().nextInt(200000)%(200000-20000+1) + 20000).setCharset("UTF-8")//至少睡眠10s
//            .setSleepTime(5000).setCharset("UTF-8")
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
    
    
	@Override
	public void process(Page page) {
		System.out.println("sleep : "+site.getSleepTime() + "----"+" url ： "+page.getUrl());
//		site.setSleepTime(new Random().nextInt(300000)%(300000-50000+1) + 50000).setCharset("UTF-8");//最少50秒 最高5分钟
//		site.setSleepTime(new Random().nextInt(200000)%(200000-20000+1) + 20000).setCharset("UTF-8");//最少50秒 最高5分钟
//		site.setSleepTime(new Random().nextInt(90000)%(90000-10000+1) + 10000).setCharset("UTF-8");//至少睡眠10s 临时注释
		site.setSleepTime(new Random().nextInt(50000)%(50000-10000+1) + 10000).setCharset("UTF-8");//至少睡眠10s 临时注释
		 if (page.getUrl().regex(MAIN_URL).match() || page.getUrl().regex(SUBVIEW_URL).match() || page.getUrl().regex(ITEM_URL).match()) {
			 int flag=0;
			 for (int i = 0; i < urlExistsList.size(); i++) {
				String url = urlExistsList.get(i);
				if(StringUtils.isNotEmpty(url)){
					if(page.getUrl().toString().equals(url.trim())){
						flag = 1;
					}
				}
			}
			List<PersonRelatedActor> personRelatedActorList = new ArrayList<PersonRelatedActor>();//相关演员
			 //相关演员
			this.addRelated(page, personRelatedActorList,urlList); //这个才是正式的代码  临时注释掉
//			this.addRelated(page, personRelatedActorList);//用来直接抓取缺少url的数据
			page.addTargetRequests(urlList); //先执行 是为了继续抓取url
			 if(flag == 0){//url不重复
				
				 Selectable selectable =  page.getHtml().xpath("//div[@class='main_tab main_tab-defaultTab curTab']");
//				 page.addTargetRequests(StringUtils.getPersonUrlList());
				 page.putField("person_contents_info", selectable);
				 Person person = new Person();
				 person.setUrl(page.getUrl().toString());
//				 String rankTitle = page.getHtml().xpath("//div[@class='side-content']//div[@class='rank-list stars-rank']//div[@class='rankList-title']/allText()").toString();
				 String rankTitle = page.getHtml().xpath("//div[@class='side-content']//div[@class='rank-list stars-rank']//div[@class='rankList-title']/allText()|//div[@class='col-sub']//div[@class='rank-list']/allText()").toString();
		           if(StringUtils.isNotEmpty(rankTitle)){
		        	   if(rankTitle.indexOf("女") != -1){
		        		   person.setGender("女");
			            }else if(rankTitle.indexOf("男") != -1){
			            	person.setGender("男");
			            }
		           }
		         //基本信息
		           String baseInfoSource = page.getHtml().xpath("//div[@class='main_tab main_tab-defaultTab curTab']//div[@class='basic-info']|//div[@class='lemma-main-content rainbowlemma-0-']//div[@class='baseInfoWrap']|//div[@class='baseInfoWrap']").toString();
		           person.setSource(baseInfoSource);
//		           List<Selectable> baseInfoNameList = page.getHtml().xpath("//div[@class='main_tab main_tab-defaultTab curTab']//div[@class='basic-info']//dt[@class='basicInfo-item name']").nodes();
//		           List<Selectable> baseInfoValueList = page.getHtml().xpath("//div[@class='main_tab main_tab-defaultTab curTab']//div[@class='basic-info']//dd[@class='basicInfo-item value']").nodes();
		           List<Selectable> baseInfoNameList = page.getHtml().xpath("//div[@class='main_tab main_tab-defaultTab curTab']//div[@class='basic-info']//dt[@class='basicInfo-item name']|//div[@class='lemma-main-content rainbowlemma-0-']//div[@class='baseInfoWrap']//span[@class='biTitle']|//div[@class='baseInfoWrap']//span[@class='biTitle']").nodes();
		           List<Selectable> baseInfoValueList = page.getHtml().xpath("//div[@class='main_tab main_tab-defaultTab curTab']//div[@class='basic-info']//dd[@class='basicInfo-item value']|//div[@class='lemma-main-content rainbowlemma-0-']//div[@class='baseInfoWrap']//div[@class='biContent']|//div[@class='baseInfoWrap']//div[@class='biContent']").nodes();
			         if(baseInfoNameList !=null && baseInfoNameList.size() > 0 && baseInfoValueList !=null && baseInfoValueList.size() > 0){
			         	for (int i = 0; i < baseInfoNameList.size(); i++) {
				          		Selectable baseInfoNameSel= baseInfoNameList.get(i);
				          		String  baseInfoName= baseInfoNameSel.xpath("//dt[@class='basicInfo-item name']/allText()").replace("&nbsp;", "").toString();
				          		if(StringUtils.isEmpty(baseInfoName)){
				          			baseInfoName = baseInfoNameSel.xpath("//span[@class='biTitle']/allText()").toString();
				          		}
				          		if(StringUtils.isNotEmpty(baseInfoName)){
				          			baseInfoName = baseInfoName.replaceAll(" ", "");//gbk编码下的空格
				          			baseInfoName = baseInfoName.replaceAll(" ", "");
				          			if(baseInfoName.indexOf("&nbsp;") != -1){
					          			baseInfoName = baseInfoName.replaceAll("&nbsp;", "");
					          		}
//					          		System.out.println("空格 === "+baseInfoName.indexOf(" "));
//					          		System.out.println("&nbsp; === "+baseInfoName.indexOf("&nbsp;"));
//					          		System.out.println("? ==== "+baseInfoName.indexOf("?"));
					          		Selectable baseInfoValueSel =baseInfoValueList.get(i);
					          		String baseInfoValue = baseInfoValueSel.xpath("//dd[@class='basicInfo-item value']/allText()").toString();
					          		if(StringUtils.isEmpty(baseInfoValue)){
					          			baseInfoValue = baseInfoValueSel.xpath("//div[@class='biContent']/allText()").toString();
					          		}
//					          		baseInfoValue = baseInfoValue.replaceAll("\042", "'");
									this.buildPerson(baseInfoName, baseInfoValue, person);
					          		//System.out.println(i + " ==== "+baseInfoNameList.get(i).toString());
//					          		System.out.println(i + " ==== "+baseInfoName +" ---- value ====" +baseInfoValue);
				          		}
				          		
							}
			       }
			    String occupation = person.getOccupation(); 
			    occupation = occupation.replaceAll(" ", "");
			    //if(StringUtils.isNotEmpty(person.getName()) && (occupation.indexOf("演员") != -1 || occupation.indexOf("制片") != -1 || occupation.indexOf("导演") != -1|| occupation.indexOf("艺人") != -1|| occupation.indexOf("编剧") != -1|| occupation.indexOf("主持人") != -1)){
			    if(StringUtils.isNotEmpty(person.getName())){
			    	List<PersonOpus> personOpuList = new ArrayList<PersonOpus>();//代表作品1
			        List<PersonRelationshipActor> personRelationshipActorList=new ArrayList<PersonRelationshipActor>();//演员关系
			        List<PersonDescription> personDescriptionList= new ArrayList<PersonDescription>();//基本介绍
			        List<PersonAward> personAwardList= new ArrayList<PersonAward>();//获奖记录
			       
			       	List<PersonRolePlay> personRolePlayList=new ArrayList<PersonRolePlay>();//参演作品
			        List<PersonCareer> personCareerList= new ArrayList<PersonCareer>();//演职生涯
			        List<PersonImg> personImgList= new ArrayList<PersonImg>();//图片集合
			        List<PersonSocialActivities> personSocialActivitiesList= new ArrayList<PersonSocialActivities>();//图片集合
			       
						//代表作品1
			         this.addOpus(page, personOpuList);
			           //明星关系
			         this.addRelationshipActor(page, personRelationshipActorList);
			           //基本介绍
			         this.addDescription(page, personDescriptionList,person);
			 		//person.setFlowers(flowers);
			 		//person.setImg_url(img_url);
				         
				       //演员的职业生涯
				     this.addCareer(page, personCareerList);
			        //参演作品 电影/电视剧
				   	 this.addRolePlay(page, personRolePlayList);
				   	 //社会活动
//				   	 person.setSocial_activities(this.getSocialActivities(page));
				   	 this.addSocialActivities(page, personSocialActivitiesList);
			       
				   	//获奖记录
				    this.addAward(page, personAwardList);
			     
				   //人物评价
			        person.setEvaluation(this.getEvaluation(page));
				   
					//图片集合
					this.addImg(page, personImgList);
					person.setPersonAwardList(personAwardList);
					person.setPersonCareerList(personCareerList);
					person.setPersonDescriptionList(personDescriptionList);
					person.setPersonImgList(personImgList);
					person.setPersonOpuList(personOpuList);
					person.setPersonRelatedActorList(personRelatedActorList);
					person.setPersonRelationshipActorList(personRelationshipActorList);
					person.setPersonRolePlayList(personRolePlayList);
					person.setPersonSocialActivitiesList(personSocialActivitiesList);
					 page.putField("person", person);
					urlExistsList.add(page.getUrl().toString());
			    } 
			 }
		 }
	}
	public List<String> getUrlExistsList() {
		return urlExistsList;
	}
	public void setUrlExistsList(List<String> urlExistsList) {
		this.urlExistsList = urlExistsList;
	}
	//社会活动
	public void addSocialActivities(Page page,List<PersonSocialActivities> personSocialActivitiesList){
		String social_activities_str =  page.getHtml().regex("社会活动\\s*</\\s*span>.*获奖记录\\s*</\\s*span>").regex("<div\\s*class=\"para\">.*</\\s*div\\s*>").toString();
	    if(StringUtils.isNotEmpty(social_activities_str)){
	    	social_activities_str = page.getHtml().regex("社会活动\\s*</\\s*span>.*人物评价\\s*</\\s*span>").regex("<div\\s*class=\"para\">.*</\\s*div\\s*>").toString();
	    }
	    if(StringUtils.isNotEmpty(social_activities_str)){
	    	social_activities_str = page.getHtml().regex("社会活动\\s*</\\s*span>.*<a\\s*name=\"a\"\\s*class=\"lemma-anchor a\">\\s*</\\s*a>").regex("<div\\s*class=\"para\">.*</\\s*div\\s*>").toString();
	    }
	    if(StringUtils.isNotEmpty(social_activities_str)){
	    	Html html = Html.create(social_activities_str);
	    	List<Selectable> socialActivitiesList =  html.xpath("//div[@class='para']").nodes();
		   	 if(socialActivitiesList != null && socialActivitiesList.size() > 0){
		            for( int i = 0 ; i < socialActivitiesList.size(); i++){
		            	Selectable socialActivitiesSel =  socialActivitiesList.get(i);
		            	String content = socialActivitiesSel.xpath("//div[@class='para']/allText()").toString();
		            	PersonSocialActivities personSocialActivities = new PersonSocialActivities();
		            	personSocialActivities.setContent(content);
		            	personSocialActivities.setSource(socialActivitiesSel.toString());
		            	personSocialActivitiesList.add(personSocialActivities);
//		            	 System.out.println(content);
		            }
		        }
	    }
	}
	//代表作品
	public void addOpus(Page page,List<PersonOpus> personOpuList){
		 List<Selectable> opusList = page.getHtml().xpath("//div[@class='star-info-block works']//div[@class='viewport']//ul[@class='slider maqueeCanvas']/li").nodes();//代表作品1
         if(opusList !=null && opusList.size() > 0){
         	for (int i = 0; i < opusList.size(); i++) {
         		Selectable opusSelectable= opusList.get(i);
         		String  opus_url= opusSelectable.xpath("//a/@href").toString();
         		String  opus_name= opusSelectable.xpath("//div[@class='name']/allText()").toString();
         		PersonOpus personOpus = new PersonOpus();
         		personOpus.setOpus_name(opus_name);
         		personOpus.setOpus_url(opus_url);
         		personOpus.setSource(opusSelectable.toString());
         		personOpuList.add(personOpus);
//         	System.out.println(i + " ==== "+opusUrl +"----"+opus);
				}
         }
	}
	//明星关系
	public void addRelationshipActor(Page page,List<PersonRelationshipActor> personRelationshipActorList){
		List<Selectable> relationshipList = page.getHtml().xpath("//div[@class='star-info-block relations']//div[@class='viewport']//ul[@class='slider maqueeCanvas']/li").nodes();//代表作品1
        if(relationshipList !=null && relationshipList.size() > 0){
        	for (int i = 0; i < relationshipList.size(); i++) {
        		Selectable opusSelectable= relationshipList.get(i);
        		String  relationship_type= opusSelectable.xpath("//div[@class='name']/text()").toString();
        		String  actor_name= opusSelectable.xpath("//div[@class='name']//em/text()").toString();
        		String  actor_url= opusSelectable.xpath("//a/@href").toString();
        			PersonRelationshipActor personRelationshipActor = new PersonRelationshipActor();
        			personRelationshipActor.setActor_name(actor_name);
        			personRelationshipActor.setActor_url(actor_url);
        			personRelationshipActor.setRelationship_type(relationship_type);
        			personRelationshipActor.setSource(opusSelectable.toString());
        			personRelationshipActorList.add(personRelationshipActor);
//        		System.out.println(i + " ==== "+relationship_type  + "---"+ actor_name+ " ---" + actor_url);
				}
        }
	}
	
	//基本介绍
	public void addDescription(Page page,List<PersonDescription> personDescriptionList,Person person){
		List<Selectable> descriptionList = page.getHtml().xpath("//div[@class='main_tab main_tab-defaultTab curTab']//div[@class='lemma-summary']//div[@class='para']|//dd[@class='desc']//div[@class='lemma-summary']//div[@class='para']").nodes();
        if(descriptionList !=null && descriptionList.size() > 0){
        	for (int i = 0; i < descriptionList.size(); i++) {
	          		Selectable descriptionSelectable= descriptionList.get(i);
	          		String  description= descriptionSelectable.xpath("//div[@class='para']/allText()").toString();
	          		if(StringUtils.isEmpty(person.getGender())){
	          			if(description.indexOf("女演员") != -1 ||description.indexOf("女") != -1){
	          				person.setGender("女");
	          			}
	          			if(description.indexOf("男演员") != -1 ||description.indexOf("男") != -1){
	          				person.setGender("男");
	          			}
	          		}
	          		PersonDescription personDescription = new PersonDescription();
	          		personDescription.setDescription(description);
	          		personDescription.setSource(descriptionSelectable.toString());
	          		personDescriptionList.add(personDescription);
//	          		System.out.println(i + " ==== "+descriptionList.get(i).toString());
//	          		System.out.println(i + " ==== "+description);
				}
      }
	}
	
	public Person buildPerson(String baseInfoName,String baseInfoValue,Person person){
		if("中文名".equals(baseInfoName)){
			person.setName(baseInfoValue);
		}
		if("外文名".equals(baseInfoName)){
			person.setAlias_en(baseInfoValue);
		}
		if("别名".equals(baseInfoName)){
			person.setAlias_cn(baseInfoValue);
		}
		if("国籍".equals(baseInfoName)){
			person.setNationality(baseInfoValue);
		}
		if("民族".equals(baseInfoName)){
			person.setVolk(baseInfoValue);
		}
		if("星座".equals(baseInfoName)){
			person.setConstellation(baseInfoValue);
		}
		if("血型".equals(baseInfoName)){
			person.setBloodtype(baseInfoValue);
		}
		if("身高".equals(baseInfoName)){
			person.setHeight(baseInfoValue);
		}
		if("体重".equals(baseInfoName)){
			person.setWeight(baseInfoValue);
		}
		if("出生地".equals(baseInfoName)){
			person.setBirthday_place(baseInfoValue);
		}
		if("出生日期".equals(baseInfoName)){
			person.setBirthday(baseInfoValue);
		}
		if("死亡日期".equals(baseInfoName) || "逝世日期".equals(baseInfoName)){
			person.setDeathday(baseInfoValue);
		}
		if("职业".equals(baseInfoName)){
			person.setOccupation(baseInfoValue);
		}
		if("毕业院校".equals(baseInfoName)){
			person.setShcool(baseInfoValue);
		}
		if("经纪公司".equals(baseInfoName)){
			person.setBrokerage_firm(baseInfoValue);
		}
		if("代表作品".equals(baseInfoName)){
			person.setOpus(baseInfoValue);
		}
		if("主要成就".equals(baseInfoName)){
			person.setMajor_awards(baseInfoValue);
		}
		if("籍贯".equals(baseInfoName)){
			person.setHomeplace(baseInfoValue);
		}
		if("经纪人".equals(baseInfoName)){
			person.setBrokers(baseInfoValue);
		}
		if("性别".equals(baseInfoName)){
			person.setGender(baseInfoValue);
		}
//		if("恋人".equals(baseInfoName)){
//			
//		}
//		if("配偶".equals(baseInfoName)){
//			
//		}
		
		
		return person;
	}
//	演职生涯
	public void addCareer(Page page,List<PersonCareer> personCareerList){
//		 String careerStr =  page.getHtml().regex("<span\\s*class=\"title-text\">\\s*演艺经历\\s*</\\s*span>.*个人生活\\s*</\\s*span>|<span\\s*class=\"title-text\">\\s*演艺经历\\s*</\\s*span>.*主要作品\\s*</\\s*span>|<span\\s*class=\"title-text\">\\s*演艺经历\\s*</\\s*span>.*家庭生活\\s*</\\s*span>").regex("<div\\s*class=\"para\">.*</\\s*div\\s*>").toString();
//		 String careerStr =  page.getHtml().regex("演艺经历\\s*</\\s*span>.*个人生活\\s*</\\s*span>|演艺经历\\s*</\\s*span>.*主要作品\\s*</\\s*span>|演艺经历\\s*</\\s*span>.*家庭生活\\s*</\\s*span>").regex("<div\\s*class=\"para\">.*</\\s*div\\s*>").toString();
		String careerStr =  page.getHtml().regex("演艺经历\\s*</\\s*span>.*个人生活\\s*</\\s*span>|演艺经历\\s*</\\s*span>.*主要作品\\s*</\\s*span>|演艺经历\\s*</\\s*span>.*影视作品\\s*</\\s*span>|演艺经历\\s*</\\s*span>.*参演电影\\s*</\\s*span>|演艺经历\\s*</\\s*span>.*家庭生活\\s*</\\s*span>|电影生涯\\s*</\\s*span>.*个人生活\\s*</\\s*span>|职业生涯\\s*</\\s*span>.*个人生活\\s*</\\s*span>|从艺经历\\s*</\\s*span>.*获奖经历\\s*</\\s*span>|演艺经历\\s*</\\s*div>.*个人生活\\s*</\\s*span>|演艺经历\\s*</\\s*div>.*参演电视剧\\s*</\\s*span>|演艺经历\\s*</\\s*span>.*主持节目\\s*</\\s*span>").regex("<div\\s*class=\"para\">.*</\\s*div\\s*>").toString();
		if(StringUtils.isEmpty(careerStr)){
			careerStr =  page.getHtml().regex("早年经历\\s*</\\s*span>.*参演电视剧\\s*</\\s*span>|人物经历\\s*</\\s*span>.*主要作品\\s*</\\s*span>|人物经历\\s*</\\s*span>.*参演电视剧\\s*</\\s*span>|人物经历\\s*</\\s*span>.*个人生活\\s*</\\s*span>|人物经历\\s*</\\s*span>.*人物生活\\s*</\\s*span>|早年经历\\s*</\\s*span>.*个人生活\\s*</\\s*span>|早年经历\\s*</\\s*span>.*主要作品\\s*</\\s*span>|早年经历\\s*</\\s*span>.*家庭生活\\s*</\\s*span>|人物生平\\s*</\\s*span>.*主要作品\\s*</\\s*span>|人物生平\\s*</\\s*span>.*作品年表\\s*</\\s*span>|人物经历\\s*</\\s*span>.*个人作品\\s*</\\s*span>|人物经历\\s*</\\s*span>.*代表作品\\s*</\\s*span>|角色经历\\s*</\\s*span>.*相关作品\\s*</\\s*span>|生平简介\\s*</\\s*span>.*影视作品\\s*</\\s*span>|个人经历\\s*</\\s*span>.*个人生活\\s*</\\s*span>|演艺生涯\\s*</\\s*span>.*参演电影\\s*</\\s*span>|从业经历\\s*</\\s*span>.*主要作品\\s*</\\s*span>|到港经历\\s*</\\s*span>.*参演电影\\s*</\\s*span>").regex("<div\\s*class=\"para\">.*</\\s*div\\s*>").toString();
		}
		if(StringUtils.isEmpty(careerStr)){
			System.out.println("error ==== "+ page.getUrl().toString());
//			urlErrorList.add(page.getUrl().toString());
		}else{
		//if(StringUtils.isNotEmpty(careerStr)){
	    	   Html careerHtml = Html.create(careerStr);
		       List<String> strList =  careerHtml.xpath("//div[@class='para']").all();
		   	 if(strList != null && strList.size() > 0){
		            for( int i = 0 ; i < strList.size(); i++){
			           	 Html temHtml = Html.create(strList.get(i));
			           	PersonCareer personCareer = new PersonCareer();
			           	String career = temHtml.xpath("//div[@class='para']/allText()").toString();
			           	personCareer.setCareer(career);
			           	personCareer.setSource(temHtml.toString());
			           	personCareerList.add(personCareer);
	//		           	 System.out.println("career " + i+" == "+career);
		            }
		        }
//	       }
		}
	}
//    获奖记录
	public void addAward(Page page,List<PersonAward> personAwardList){
//	     List<Selectable> awardList = page.getHtml().xpath("//table[@nslog-type='603']//tbody//tr").nodes();
		 List<Selectable> awardList = page.getHtml().xpath("//table[@nslog-type='603']//tbody//tr|//table[@class='module-common-table-0 module-common-table-rongyujilu']//tbody//tr").nodes();
	     if(awardList != null && awardList.size() > 1){
	    	 
	         //i=0是列名称，所以i从1开始
	         for( int i = 0 ; i < awardList.size(); i++){
	       	  Selectable selectable = awardList.get(i);
//	       	  String type = selectable.xpath("//td[@class='normal title-td']/text()").toString();//类型
	       	 String type = selectable.xpath("//td[@class='normal title-td']/text()|//td[@class='module-common-0-td2 module-common-title-font']/text()").toString();//类型
	       	  if(type != null && !"".equals(type)){
	       		  Selectable contentSelectable = awardList.get(i+1);
//	       		  List<Selectable> rowList = contentSelectable.xpath("//ul[@class='list-module j-common-module']//li[@class='row']").nodes();
	       		List<Selectable> rowList = contentSelectable.xpath("//ul[@class='list-module j-common-module']//li[@class='row']|//table[@log-set-param='table_view']//tbody//tr").nodes();
	       		  if(rowList != null && rowList.size() > 0){
	       			  for (int j = 0; j <rowList.size(); j++) {
	       				  Selectable rowSelectable =  rowList.get(j);
	       				PersonAward personAward = new PersonAward();
//	       				 String years =  rowSelectable.xpath("//span[@class='first column']/text()").replace(" ", "").toString();
//	       				String award = rowSelectable.xpath("//span[3]/allText()").replace(" ", "").toString();
	       				String years =  rowSelectable.xpath("//span[@class='first column']/text()|td[@nowrap='nowrap']/allText()").replace(" ", "").toString();
	       				String award = rowSelectable.xpath("//span[3]/allText()|//td[@class='module-common-ziran-comlumn2']/allText()").replace(" ", "").toString();
	       				personAward.setAward(award);
	       				personAward.setSource(type +":"+ rowSelectable.toString());
	       				personAward.setYears(years);
	       				personAwardList.add(personAward);
//	       				 String contents = rowSelectable.xpath("//span[@class='column']").toString();
//	       				 System.out.println("type = "+ type + "=== years==="+years + " == contents == "+award);
					  }
	       		  }
	       	  }
	
	         }
	     }
	}
//	参演作品 电影/电视剧
	public void addRolePlay(Page page,List<PersonRolePlay> personRolePlayList){
		 List<String> questionList = page.getHtml().xpath("//div[@class='starMovieAndTvplay']//div[@class='viewport']/ul").all();
		 if(questionList == null || questionList.size() <=0){
			 String str = page.getHtml().regex("主要作品\\s*</\\s*span>.*获奖记录\\s*</\\s*span>|主要作品\\s*</\\s*span>.*社会活动\\s*</\\s*span>|主要作品\\s*</\\s*span>.*个人经历\\s*</\\s*span>|主要作品\\s*</\\s*span>.*个人荣誉\\s*</\\s*span>|主要作品\\s*</\\s*span>.*个人生活\\s*</\\s*span>|主要作品\\s*</\\s*span>.*人物评价\\s*</\\s*span>|个人作品\\s*</\\s*span>.*音乐作品\\s*</\\s*span>|参演电影\\s*</\\s*span>.*相关事件\\s*</\\s*span>|作品\\s*</\\s*span>.*词条图册\\s*</\\s*span>").toString();
         	if(StringUtils.isNotEmpty(str)){
         		 Html html = Html.create(str);
             	 List<Selectable> tableSelList =html.xpath("//table").nodes();
             	if(tableSelList != null && tableSelList.size() > 0){
             	 for (int i = 0; i < tableSelList.size(); i++) {
             		 Selectable tableSel =  tableSelList.get(i);
             		 List<Selectable> theadSelList =  tableSel.xpath("//table//thead//th").nodes();
                  	 List<Selectable> trSelList =  tableSel.xpath("//table//tbody//tr").nodes();
                  	 for (int j = 0; j < trSelList.size(); j++) {
                  		Selectable trSel= trSelList.get(j);
                  		List<Selectable> tdSelList =trSel.xpath("//td").nodes();
              			PersonRolePlay personRolePlay = new PersonRolePlay();
                  		for (int k = 0; k < theadSelList.size(); k++) {
                  			Selectable thsel= theadSelList.get(k);
                  			if(tdSelList.size() == theadSelList.size()){
                  				Selectable tdsel= tdSelList.get(k);
                      			String type = thsel.xpath("//th/allText()").toString();
                      			if(type.indexOf("时间") != -1){
                      				personRolePlay.setPlay_years(tdsel.xpath("//td/allText()").toString());
                      			}
                      			if(type.indexOf("剧名") != -1){
                      				personRolePlay.setPlay_name(tdsel.xpath("//td/allText()").toString());
                      				personRolePlay.setPlay_url(tdsel.xpath("//td/a/@href").toString());
                      			}
                      			if(type.indexOf("导演") != -1){
                      				personRolePlay.setPlay_director(tdsel.xpath("//td").toString());
                      			}
                      			if(type.indexOf("角色") != -1){
                      				personRolePlay.setRole(tdsel.xpath("//td/allText()").toString());
                      			}
                      			if(type.indexOf("演员") != -1){
                      				personRolePlay.setPlay_actors(tdsel.xpath("//td").toString());
                      			}
                  			}
                  			
    					}
                  		personRolePlayList.add(personRolePlay);
//                  	System.out.println("playYears: " +playYears + "=playName:"+playName + " =playUrl:"+playUrl + " =playDirector:" +playDirector +"=roleName:" + roleName + "=playActors:"+playActors);
    					}
    				}
             		}else{
             			List<Selectable> paraSelList= html.xpath("//div[@class='para']").nodes();
	               		 for (int i = 0; i < paraSelList.size(); i++) {
	               			 Selectable paraSel=  paraSelList.get(i);
	               			 Selectable _paraSel = paraSel.xpath("//div[@class='para']/html()");
	               			 String paraStr = _paraSel.toString();
	               			 if(StringUtils.isNotEmpty(paraStr)){
	               				 if(paraStr.indexOf("《") != -1){
	               					PersonRolePlay personRolePlay = new PersonRolePlay();
	               					 String years ="";
	               					 if(paraStr.indexOf("年") !=-1){
	               						 years = paraStr.substring(0, paraStr.indexOf("年"));//年份
	               					 }
	               					 if(StringUtils.isEmpty(years) && paraStr.indexOf("电影") !=-1){
	               						 years =  paraStr.substring(0, paraStr.indexOf("电影"));
	               					 }
	               					 if(StringUtils.isEmpty(years)){
	               						 years =  paraStr.substring(0, paraStr.indexOf("《"));
	               					 }
	               					 if(StringUtils.isNotEmpty(years)){
	               						 if(years.indexOf("</a>") != -1){
	               							 years =  years.substring(years.indexOf("</a>"),years.length());
	               						 }
	               					 }
	               					personRolePlay.setPlay_years(years);
	               					 String playContent = paraStr.substring(paraStr.indexOf("《"),paraStr.indexOf("》"));
	               					 String playName = "";
	               					 String playUrl="";
	               					 if(StringUtils.isNotEmpty(playContent)){
	               						 Html playContentHtml = Html.create(playContent);
	               						 playUrl = playContentHtml.xpath("//a/@href")+"";
	               						 if(StringUtils.isNotEmpty(playUrl)){
	               							 playName = playContentHtml.xpath("//a/allText()")+"";
	               						 }else{
	               							 playName = playContent.replaceAll("《", "");
	               						 }
	               					 }else{
	               						 System.out.println("主要作品剧名 error = " + page.getUrl());
	               					 }
	               					personRolePlay.setPlay_url(playUrl);
	               					personRolePlay.setPlay_name(playName);
	               					
	               					 String roleContent = "";
	               					 String roleName ="";
	               					 if(paraStr.indexOf("饰演") != -1){
	               						 roleContent = paraStr.substring(paraStr.indexOf("饰演"),paraStr.length());
	               						 roleContent = roleContent.replaceAll("饰演", "");
	               					 }
	               					 if(paraStr.indexOf("饰") != -1){
	               						 roleContent = paraStr.substring(paraStr.indexOf("饰"),paraStr.length());
	               						 roleContent = roleContent.replaceAll("饰", "");
	               					 }
	               					 
	               					 if(StringUtils.isNotEmpty(roleContent)){
	               						 Html roleContentHtml = Html.create(roleContent);
	               						 roleName = roleContentHtml.xpath("/allText()")+"";
	               					 }
	               					 if(roleName.indexOf(":") != -1 || roleName.indexOf("：") !=-1){
	               						 roleName =roleName.replaceAll(":", "");
	               						 roleName =roleName.replaceAll("：", "");
	               					 }
	               					personRolePlay.setRole(roleName);
	               					personRolePlayList.add(personRolePlay);
//	               					 System.out.println(i+" -- years="+years +"-- playName="+playName+"--playUrl="+playUrl+"--roleName="+roleName);
	               					 
	               				 }
	               			 }
	//               			 System.out.println(i+" ---- "+_paraSel);
	   					}
             			
             			
             		}
         	}else{
         		System.out.println("代表作品 error = "+page.getUrl());
         	}
         	
		 }else{
	           //
	           for( int i = 0 ; i < questionList.size(); i++)
	           {
//	               System.out.println("questionList" + i+" === "+questionList.get(i));
	               Html tempHtml =  Html.create(questionList.get(i));
	               List<Selectable> selList = tempHtml.xpath("//li[@class='pages']//ul[@class='starWorksList']").nodes();
	               if(selList != null &&selList.size() > 0){
	               	for (int j = 0; j < selList.size(); j++) {
	               		Selectable sel = selList.get(j);
	               		 List<Selectable> _selList = sel.xpath("//li[@class='listItem']").nodes();
	               		 if(_selList != null &&_selList.size() > 0){
	                        	for (int k = 0; k < _selList.size(); k++) {
	                        		Selectable _sel = _selList.get(k);
	                        		PersonRolePlay personRolePlay = new PersonRolePlay();
	                        		String play_name = _sel.xpath("//div[@class='info']/p//b[@class='title']//a/text()").toString();
	                         		String play_url = _sel.xpath("//div[@class='info']/p//b[@class='title']//a/@href").toString();
	                         		String play_years = _sel.xpath("//div[@class='info']/p//b[2]/text()").toString();
	                         		personRolePlay.setPlay_name(play_name);
	                         		personRolePlay.setPlay_url(play_url);
	                         		personRolePlay.setPlay_years(play_years);
	                         		personRolePlay.setSource(_sel.toString());
	                         		 List<Selectable> actorNameList =_sel.xpath("//div[@class='info']//dl//dt").nodes();
	                         		 List<Selectable> actorValueList =_sel.xpath("//div[@class='info']//dl//dd").nodes();
	                         		 for (int l = 0; l < actorNameList.size(); l++) {
	                         			Selectable nameSel= actorNameList.get(l);
	                         			String actorName = nameSel.xpath("//dt/text()").replace(" ", "").replace(" ", "").toString();
	                         			if(StringUtils.isNotEmpty(actorName)){
	                         				if("饰演".equals(actorName)){
	                         					personRolePlay.setRole(actorValueList.get(l).xpath("//dd/allText()").toString());
	                         				}
	                         				if("导演".equals(actorName)){
	                         					personRolePlay.setPlay_director(actorValueList.get(l).toString());
	                         				}
	                         				if("主演".equals(actorName)){
	                         					personRolePlay.setPlay_actors(actorValueList.get(l).toString());
	                         				}
//	                         				System.out.println(actorName + " == " + actorValueList.get(l));
	                         			}
	                         			
									}
	                         		personRolePlayList.add(personRolePlay);
//	                        		String actor_original = _sel.xpath("//div[@class='info']//dl").all().toString();
//	                        		System.out.println("name = " + name +" --- url =="+url+" --- years="+years  + " --- actor_original = "+ actor_original+ " --- "+k);
	                        	}
	                        }
						}
	               }
	
	           }
	       }
	}
	
//	社会活动
	public String getSocialActivities(Page page){
//	     String social_activities_str =  page.getHtml().regex("<span\\s*class=\"title-text\">\\s*社会活动\\s*</\\s*span>.*获奖记录\\s*</\\s*span>").regex("<div\\s*class=\"para\">.*</\\s*div\\s*>").toString();
//	    if(StringUtils.isNotEmpty(social_activities_str)){
//	   	 social_activities_str = page.getHtml().regex("<span\\s*class=\"title-text\">\\s*社会活动\\s*</\\s*span>.*人物评价\\s*</\\s*span>").regex("<div\\s*class=\"para\">.*</\\s*div\\s*>").toString();
//	    }
//	    if(StringUtils.isNotEmpty(social_activities_str)){
//	   	 social_activities_str = page.getHtml().regex("<span\\s*class=\"title-text\">\\s*社会活动\\s*</\\s*span>.*<a\\s*name=\"a\"\\s*class=\"lemma-anchor a\">\\s*</\\s*a>").regex("<div\\s*class=\"para\">.*</\\s*div\\s*>").toString();
//	    }
	    String social_activities_str =  page.getHtml().regex("社会活动\\s*</\\s*span>.*获奖记录\\s*</\\s*span>").regex("<div\\s*class=\"para\">.*</\\s*div\\s*>").toString();
	    if(StringUtils.isNotEmpty(social_activities_str)){
	    	social_activities_str = page.getHtml().regex("社会活动\\s*</\\s*span>.*人物评价\\s*</\\s*span>").regex("<div\\s*class=\"para\">.*</\\s*div\\s*>").toString();
	    }
	    if(StringUtils.isNotEmpty(social_activities_str)){
	    	social_activities_str = page.getHtml().regex("社会活动\\s*</\\s*span>.*<a\\s*name=\"a\"\\s*class=\"lemma-anchor a\">\\s*</\\s*a>").regex("<div\\s*class=\"para\">.*</\\s*div\\s*>").toString();
	    }
	    StringBuilder social_activities = new StringBuilder();
	    if(StringUtils.isNotEmpty(social_activities_str)){
	    	Html html = Html.create(social_activities_str);
		     List<String> socialActivitiesList =  html.xpath("//div[@class='para']/allText()").all();
		   	 if(socialActivitiesList != null && socialActivitiesList.size() > 0){
		            for( int i = 0 ; i < socialActivitiesList.size(); i++){
		           	 String _str = socialActivitiesList.get(i);
		           	 if(_str != null && !"".equals(_str)){
		           		 if("参与活动".equals(_str) || "社会职务".equals(_str) || "慈善活动".equals(_str) || "商业代言".equals(_str)){
		           			 social_activities.append(socialActivitiesList.get(i)).append(":<br/>");
		           		 }else{
		           			 social_activities.append(socialActivitiesList.get(i)).append("<br/>");
		           		 }
		//           		 System.out.println("question " + i+" == "+strList.get(i));
		           	 }
		            }
		        }
	    }
	     
//	   	 System.out.println(social_activities);
	   	 return social_activities.toString();
	}
//	获取评价
	public String getEvaluation(Page page){
//		String evaluationStr =  page.getHtml().regex("人物评价\\s*</\\s*span>.*<a\\s*name=\"a\"\\s*class=\"lemma-anchor a\">\\s*</\\s*a>").toString();
		 String evaluationStr =  page.getHtml().regex("人物评价\\s*</\\s*span>.*<a\\s*name=\"a\"\\s*class=\"lemma-anchor a\">\\s*</\\s*a>").toString();
         if(StringUtils.isNotEmpty(evaluationStr)){
       	  evaluationStr =  page.getHtml().regex("人物评价\\s*</\\s*span>.*<\\s*script\\s*>").toString();
         }
	    String evaluation = "";
	     if(StringUtils.isNotEmpty(evaluationStr)){
//	   	  if(evaluationStr.indexOf("class=\"anchor-list\"") != -1){
//	   		  evaluationStr   = evaluationStr.substring(0, evaluationStr.indexOf("class=\"anchor-list\""));
////	   		  System.out.println("evaluationStr === " + evaluationStr);
//	   		  Html html = Html.create(evaluationStr);
////	   		  System.out.println("html === " + html);
//	   		  List<String> strList = html.xpath("//div[@class='para']/allText()").all();
//	   		  for (int i = 0; i < strList.size(); i++) {
//	   			  if("".equals(evaluation)){
//	   				  evaluation = strList.get(i);
//	   			  }else{
//	   				  evaluation = evaluation+"<br/>"+strList.get(i);
//	   			  }
//				}
////	   		  System.out.println(evaluation);
//	   	  }
	   	  
	   	  if(evaluationStr.indexOf("class=\"anchor-list\"") != -1){
    		  evaluationStr   = evaluationStr.substring(0, evaluationStr.indexOf("class=\"anchor-list\""));
    	  }
//		  System.out.println("evaluationStr === " + evaluationStr);
		  Html html = Html.create(evaluationStr);
//		  System.out.println("html === " + html);
		  List<String> strList = html.xpath("//div[@class='para']/allText()").all();
		  for (int i = 0; i < strList.size(); i++) {
				  if("".equals(evaluation)){
					  evaluation = strList.get(i);
				  }else{
					  evaluation = evaluation+"<br/>"+strList.get(i);
				  }
			}
//		  System.out.println(evaluation);
	     }
	     return evaluation;
	}
//	相关演员
	public void addRelated(Page page,List<PersonRelatedActor> personRelatedActorList,List<String> urlList){
		 List<Selectable> relatedActorList = page.getHtml().xpath("//div[@class='zhixin-group js-group']//div[@class='zhixin-list zhixin-list-3']//div[@class='zhixin-item']|//div[@class='zhixin-group js-group']//div[@class='zhixin-list zhixin-list-3']//div[@class='zhixin-item nslog-area:10000101']").nodes();//相关演员
		   if(relatedActorList == null || relatedActorList.size() <= 0){
		 	  relatedActorList = page.getHtml().xpath("//div[@class='zhixin-group js-group']//div[@class='zhixin-list zhixin-list-5']//div[@class='zhixin-item']|//div[@class='zhixin-group js-group']//div[@class='zhixin-list zhixin-list-5']//div[@class='zhixin-item nslog-area:10000101']").nodes();//相关演员
		   }
		     if(relatedActorList !=null && relatedActorList.size() > 0){
		     	for (int i = 0; i < relatedActorList.size(); i++) {
		     		Selectable relatedActorSel= relatedActorList.get(i);
		     		PersonRelatedActor personRelatedActor = new PersonRelatedActor();
		     		String  actor_url= relatedActorSel.xpath("//div[@class='portraitbox']//a/@href").toString();
		     		urlList.add(actor_url);//添加到要爬取的url
		     		String  actor_name= relatedActorSel.xpath("//p[@class='item-title js-name']/allText()").toString();
		     		personRelatedActor.setActor_name(actor_name);
		     		personRelatedActor.setActor_url(actor_url);
		     		personRelatedActor.setSource(relatedActorSel.toString());
		     		personRelatedActorList.add(personRelatedActor);
//		     		System.out.println(i + " ==== "+actor_url + " ---- "+actor_name);
				}
		     }
	}
//	相关演员
	public void addRelated(Page page,List<PersonRelatedActor> personRelatedActorList){
		List<Selectable> relatedActorList = page.getHtml().xpath("//div[@class='zhixin-group js-group']//div[@class='zhixin-list zhixin-list-3']//div[@class='zhixin-item']|//div[@class='zhixin-group js-group']//div[@class='zhixin-list zhixin-list-3']//div[@class='zhixin-item nslog-area:10000101']").nodes();//相关演员
		if(relatedActorList == null || relatedActorList.size() <= 0){
			relatedActorList = page.getHtml().xpath("//div[@class='zhixin-group js-group']//div[@class='zhixin-list zhixin-list-5']//div[@class='zhixin-item']|//div[@class='zhixin-group js-group']//div[@class='zhixin-list zhixin-list-5']//div[@class='zhixin-item nslog-area:10000101']").nodes();//相关演员
		}
		if(relatedActorList !=null && relatedActorList.size() > 0){
			for (int i = 0; i < relatedActorList.size(); i++) {
				Selectable relatedActorSel= relatedActorList.get(i);
				PersonRelatedActor personRelatedActor = new PersonRelatedActor();
				String  actor_url= relatedActorSel.xpath("//div[@class='portraitbox']//a/@href").toString();
//				urlList.add(actor_url);//添加到要爬取的url
				String  actor_name= relatedActorSel.xpath("//p[@class='item-title js-name']/allText()").toString();
				personRelatedActor.setActor_name(actor_name);
				personRelatedActor.setActor_url(actor_url);
				personRelatedActor.setSource(relatedActorSel.toString());
				personRelatedActorList.add(personRelatedActor);
//		     		System.out.println(i + " ==== "+actor_url + " ---- "+actor_name);
			}
		}
	}
	//图片集合
	public void addImg(Page page,List<PersonImg> personImgList){
	List<Selectable> imgList=  page.getHtml().xpath("//img[@class='picture']").nodes();
   	 if(imgList != null && imgList.size() > 0){
            for( int i = 0 ; i < imgList.size(); i++){
           	Selectable imgSel = imgList.get(i);
           	String img_name = imgSel.xpath("//img/@alt").toString();
           	String img_url = imgSel.xpath("//img/@src").toString();
           	PersonImg personImg = new PersonImg();
           	personImg.setImg_name(img_name);
           	personImg.setImg_url(img_url);
           	personImg.setSource(imgSel.toString());
           	personImgList.add(personImg);
//          System.out.println("career " + i+" == "+img_name + " === "+img_url );
            }
        }
	}

	@Override
	public Site getSite() {
		return site;
	}
	
	public void run(){
		List<String> urlList = StringUtils.getPersonUrlList();
		List<String> _urlList = StringUtils.getPersonUrlList2();
		for (int k = 0; k < _urlList.size(); k++) {
			urlList.add(_urlList.get(k));
		}
		List<UrlMapper> list = _personDaoPipeline.getUrlList();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i) != null){
				String urlExist = list.get(i).getUrl();
				if(StringUtils.isNotEmpty(urlExist)){
//					int flag = 0;
					for (int j = 0; j < urlList.size(); j++) {
						String url = urlList.get(j);
						if(url.equals(urlExist)){
							urlList.remove(j);
						}
					}
					urlExistsList.add(list.get(i).getUrl());
				}
			}
		}
		System.out.println(urlList.size());
		PersonProcessor personProcessor = new PersonProcessor();
		personProcessor.setUrlExistsList(urlExistsList);
		personProcessor.setUrlList(urlList);
//		Spider.create(personProcessor).addUrl("http://baike.baidu.com/view/9209.htm")
//		Spider.create(personProcessor).addUrl("http://baike.baidu.com/view/2156717.htm")
//		Spider.create(personProcessor).addUrl("http://baike.baidu.com/subview/3539/10605302.htm")
//		Spider.create(personProcessor).addUrl("http://baike.baidu.com/view/1678.htm")
//		Spider.create(personProcessor).addUrl("http://baike.baidu.com/view/10319533.htm")
		
		Spider.create(personProcessor).addUrl("http://baike.baidu.com/item/%E4%B9%98%E7%91%B6")
		.addPipeline(personDaoPipeline)
		.addPipeline(new FilePipeline("E:\\spiderwork\\person\\"))
      .run();
	}
}
