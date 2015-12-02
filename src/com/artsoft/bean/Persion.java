package com.artsoft.bean;

public class Persion {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
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

	public String getFlowers() {
		return flowers;
	}

	public void setFlowers(String flowers) {
		this.flowers = flowers;
	}

	public String getVolk() {
		return volk;
	}

	public void setVolk(String volk) {
		this.volk = volk;
	}

	public String getBrokers() {
		return brokers;
	}

	public void setBrokers(String brokers) {
		this.brokers = brokers;
	}

	private Integer id;
	// 演员名称
	private String name = "";
	// 图片url
	private String img_url = "";
	// 百度百科地址
	private String url = "";
	// 性别
	private String gender = "";
	// 国籍
	private String nationality = "";
	// 民族
	// private String volk="";

	// 血型
	private String bloodtype = "";
	// 身高
	private String height = "";
	// 体重
	private String weight = "";
	// 职业
	 private String occupation="";
	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	// 星座
	private String constellation = "";
	// 出生日期
	private String birthday = "";
	// 死亡日期
	private String deathday = "";
	// 英文别名
	private String alias_en = "";
	// 中文别名
	private String alias_cn = "";
	// 祖籍/籍贯
	private String homeplace = "";
	// 基本描述
	// private String description="";
	// 国家
	private String nation = "";
	// 基本信息中的主要获奖记录
	private String major_awards = "";
	// 毕业院校
	private String shcool = "";
	// 经纪公司
	private String brokerage_firm = "";
	// 基本信息上面的代表作
	private String opus = "";
	//
	private String sub_path = "";
	// 明星关系 大字段
	// private String relationship="";
	// 职业生涯
	// private String career="";
	// 基本信息的代表作品
	// private String opus_next="";
	// 参演角色大字段
	// private String role="";
	// 评价
	private String evaluation = "";
	// 出生地
	private String birthday_place = "";

	// 影视奖项大字段
	// private String film_award="";
	// 社会活动大字段
	private String social_activities = "";

	public String getSocial_activities() {
		return social_activities;
	}

	public void setSocial_activities(String social_activities) {
		this.social_activities = social_activities;
	}

	// 送花数量
	private String flowers = "";
	// 民族
	private String volk = "";
	// 经纪人
	private String brokers = "";
	// 基本信息大字段
	 private String description_text="";
	 
	// 爱好
	 private String hobby="";
	// 获奖信息
	// private List<PersonAward> personAwardList;
	// // 职业生涯
	// private List<PersonCareer> personCareerList;
	// // 基本信息
	// private List<PersonDescription> personDescriptionList;
	// // 代表作品
	// private List<PersonOpus> personOpuList;
	// // 相关演员
	// private List<PersonRelatedActor> personRelatedActorList;
	// // 演员之间关系
	// private List<PersonRelationshipActor> personRelationshipActorList;
	// // 饰演角色
	// private List<PersonRolePlay> personRolePlayList;
	// // 图片集合
	// private List<PersonImg> personImgList;
	// 社会活动
	// private List personSocialActivitiesList;

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getDescription_text() {
		return description_text;
	}

	public void setDescription_text(String description_text) {
		this.description_text = description_text;
	}

	public void print() {
		System.out.println("id:				" + id);
		System.out.println("name: 		    " + name);
		System.out.println("img_url: 		" + img_url);
		System.out.println("url: 		    " + url);
		System.out.println("gender: 		" + gender);
		System.out.println("nationality: 	" + nationality);
		System.out.println("bloodtype: 		" + bloodtype);
		System.out.println("height: 		" + height);
		System.out.println("weight: 		" + weight);
		System.out.println("occupation: 	" + occupation);
		System.out.println("constellation:	" + constellation);
		System.out.println("birthday: 		" + birthday);
		System.out.println("deathday: 		" + deathday);
		System.out.println("alias_en: 		" + alias_en);
		System.out.println("alias_cn: 		" + alias_cn);
		System.out.println("homeplace: 		" + homeplace);
		System.out.println("nation: 		" + nation);
		System.out.println("major_awards: 	" + major_awards);
		System.out.println("shcool: 		" + shcool);
		System.out.println("brokerage_firm: " + brokerage_firm);
		System.out.println("opus: 		    " + opus);
		System.out.println("sub_path: 		" + sub_path);
		System.out.println("evaluation: 	" + evaluation);
		System.out.println("birthday_place:	" + birthday_place);
		System.out.println("flowers: 		" + flowers);
		System.out.println("volk: 		    " + volk);
		System.out.println("brokers: 		" + brokers);
		System.out.println("social_activities:   " + social_activities);
		System.out.println("description_text:   " + description_text);
		
	}

}
