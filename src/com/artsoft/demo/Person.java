package com.artsoft.demo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.annotations.ResultMap;

import us.codecraft.jobhunter.util.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * 百度百科演员信息
 */
public class Person implements AfterExtractor,Serializable {
	private Integer id;
//	演员名称
    private String name="";
//    图片url
    private String img_url="";
//    百度百科地址
    private String url="";
//    性别
    private String gender="";
//    国籍
    private String nationality="";
//    民族
    private String volk="";
    
	//    血型
    private String bloodtype="";
//    身高
    private String height = "";
//    体重
    private String weight="";
//    职业
    private String occupation="";
//    星座
    private String constellation="";
//    出生日期
    private String birthday="";
//    死亡日期
    private String deathday="";
//    英文别名
    private String alias_en="";
//    中文别名
    private String alias_cn="";
//    祖籍/籍贯
    private String homeplace="";
//    基本描述
//    private String description="";
//   基本信息中的主要获奖记录
    private String major_awards="";
//    毕业院校
    private String shcool="";
//    经纪公司
    private String brokerage_firm="";
//    基本信息上面的代表作
    private String opus="";
//    
    private String sub_path="";
//    明星关系 大字段
//    private String relationship="";
//    职业生涯
//    private String career="";
//    基本信息的代表作品
//    private String opus_next="";
//    参演角色大字段
//    private String role="";
//    评价
    private String evaluation="";
//    出生地
    private String birthday_place="";
//    影视奖项大字段
//    private String film_award="";
//    社会活动大字段
//    private String social_activities="";
//    送花数量
    private String flowers="";
//    经纪人
    private String brokers = "";
//    基本信息大字段
//    private String description_text="";
//    获奖信息
    private List<PersonAward> personAwardList;
//    职业生涯
    private List<PersonCareer> personCareerList;
//    基本信息
    private List<PersonDescription>  personDescriptionList;
//    代表作品
    private List<PersonOpus>  personOpuList;
//    相关演员
    private List<PersonRelatedActor>  personRelatedActorList;
//    演员之间关系
    private List<PersonRelationshipActor>  personRelationshipActorList;
//    饰演角色
    private List<PersonRolePlay>  personRolePlayList;
    //图片集合
    private List<PersonImg>  personImgList;
    //社会活动
    private List<PersonSocialActivities> personSocialActivitiesList;
    
    
    
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public List<PersonSocialActivities> getPersonSocialActivitiesList() {
		return personSocialActivitiesList;
	}

	public void setPersonSocialActivitiesList(
			List<PersonSocialActivities> personSocialActivitiesList) {
		this.personSocialActivitiesList = personSocialActivitiesList;
	}

	public List<PersonImg> getPersonImgList() {
		return personImgList;
	}

	public void setPersonImgList(List<PersonImg> personImgList) {
		this.personImgList = personImgList;
	}

	public String getBrokers() {
		return brokers;
	}

	public void setBrokers(String brokers) {
		this.brokers = brokers;
	}

	public String getVolk() {
		return volk;
	}

	public void setVolk(String volk) {
		this.volk = volk;
	}
    
    public List<PersonAward> getPersonAwardList() {
		return personAwardList;
	}

	public void setPersonAwardList(List<PersonAward> personAwardList) {
		this.personAwardList = personAwardList;
	}

	public List<PersonCareer> getPersonCareerList() {
		return personCareerList;
	}

	public void setPersonCareerList(List<PersonCareer> personCareerList) {
		this.personCareerList = personCareerList;
	}

	public List<PersonDescription> getPersonDescriptionList() {
		return personDescriptionList;
	}

	public void setPersonDescriptionList(
			List<PersonDescription> personDescriptionList) {
		this.personDescriptionList = personDescriptionList;
	}

	public List<PersonOpus> getPersonOpuList() {
		return personOpuList;
	}

	public void setPersonOpuList(List<PersonOpus> personOpuList) {
		this.personOpuList = personOpuList;
	}

	public List<PersonRelatedActor> getPersonRelatedActorList() {
		return personRelatedActorList;
	}

	public void setPersonRelatedActorList(
			List<PersonRelatedActor> personRelatedActorList) {
		this.personRelatedActorList = personRelatedActorList;
	}

	public List<PersonRelationshipActor> getPersonRelationshipActorList() {
		return personRelationshipActorList;
	}

	public void setPersonRelationshipActorList(
			List<PersonRelationshipActor> personRelationshipActorList) {
		this.personRelationshipActorList = personRelationshipActorList;
	}

	public List<PersonRolePlay> getPersonRolePlayList() {
		return personRolePlayList;
	}

	public void setPersonRolePlayList(List<PersonRolePlay> personRolePlayList) {
		this.personRolePlayList = personRolePlayList;
	}

	//    来源 大字段
    private String source="";
    
    public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	private String urlMd5="";
    

    public String getUrlMd5() {
    	urlMd5 = DigestUtils.md5Hex(this.url);
		return urlMd5;
	}

	public void setUrlMd5(String urlMd5) {
		this.urlMd5 = urlMd5;
	}

	public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        this.urlMd5 = DigestUtils.md5Hex(url);
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getBloodtype() {
		return bloodtype;
	}

	public void setBloodtype(String bloodtype) {
		this.bloodtype = bloodtype;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getDeathday() {
		return deathday;
	}

	public void setDeathday(String deathday) {
		this.deathday = deathday;
	}

	public String getAlias_en() {
		return alias_en;
	}

	public void setAlias_en(String alias_en) {
		this.alias_en = alias_en;
	}

	public String getAlias_cn() {
		return alias_cn;
	}

	public void setAlias_cn(String alias_cn) {
		this.alias_cn = alias_cn;
	}

	public String getHomeplace() {
		return homeplace;
	}

	public void setHomeplace(String homeplace) {
		this.homeplace = homeplace;
	}

	public String getMajor_awards() {
		return major_awards;
	}

	public void setMajor_awards(String major_awards) {
		this.major_awards = major_awards;
	}

	public String getShcool() {
		return shcool;
	}

	public void setShcool(String shcool) {
		this.shcool = shcool;
	}

	public String getBrokerage_firm() {
		return brokerage_firm;
	}

	public void setBrokerage_firm(String brokerage_firm) {
		this.brokerage_firm = brokerage_firm;
	}

	public String getOpus() {
		return opus;
	}

	public void setOpus(String opus) {
		this.opus = opus;
	}

	public String getSub_path() {
		return sub_path;
	}

	public void setSub_path(String sub_path) {
		this.sub_path = sub_path;
	}
	
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public String getRelationship() {
//		return relationship;
//	}
//
//	public void setRelationship(String relationship) {
//		this.relationship = relationship;
//	}
//
//	public String getCareer() {
//		return career;
//	}
//
//	public void setCareer(String career) {
//		this.career = career;
//	}
//
//	public String getOpus_next() {
//		return opus_next;
//	}
//
//	public void setOpus_next(String opus_next) {
//		this.opus_next = opus_next;
//	}
//
//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		this.role = role;
//	}
//	
//	public String getFilm_award() {
//	return film_award;
//}
//
//public void setFilm_award(String film_award) {
//	this.film_award = film_award;
//}
//
//	public String getDescription_text() {
//		return description_text;
//	}
//
//	public void setDescription_text(String description_text) {
//		this.description_text = description_text;
//	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public String getBirthday_place() {
		return birthday_place;
	}

	public void setBirthday_place(String birthday_place) {
		this.birthday_place = birthday_place;
	}



//	public String getSocial_activities() {
//		return social_activities;
//	}
//
//	public void setSocial_activities(String social_activities) {
//		this.social_activities = social_activities;
//	}

	public String getFlowers() {
		return flowers;
	}

	public void setFlowers(String flowers) {
		this.flowers = flowers;
	}


//	@Override
//    public String toString() {
//        return "playInfo{" +
//                "name='" + name + '\'' +
//                ", score='" + score + '\'' +
//                ", subject='" + subject + '\'' +
//                ", actors='" + actors + '\'' +
//                ", totalplay='" + totalplay + '\'' +
//                ", comment='" + comment + '\'' +
//                ", url='" + url + '\'' +
//                '}';
//    }

    @Override
    public void afterProcess(Page page) {
    }
}
