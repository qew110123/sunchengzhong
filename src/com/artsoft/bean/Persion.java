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
	// ��Ա����
	private String name = "";
	// ͼƬurl
	private String img_url = "";
	// �ٶȰٿƵ�ַ
	private String url = "";
	// �Ա�
	private String gender = "";
	// ����
	private String nationality = "";
	// ����
	// private String volk="";

	// Ѫ��
	private String bloodtype = "";
	// ���
	private String height = "";
	// ����
	private String weight = "";
	// ְҵ
	 private String occupation="";
	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	// ����
	private String constellation = "";
	// ��������
	private String birthday = "";
	// ��������
	private String deathday = "";
	// Ӣ�ı���
	private String alias_en = "";
	// ���ı���
	private String alias_cn = "";
	// �漮/����
	private String homeplace = "";
	// ��������
	// private String description="";
	// ����
	private String nation = "";
	// ������Ϣ�е���Ҫ�񽱼�¼
	private String major_awards = "";
	// ��ҵԺУ
	private String shcool = "";
	// ���͹�˾
	private String brokerage_firm = "";
	// ������Ϣ����Ĵ�����
	private String opus = "";
	//
	private String sub_path = "";
	// ���ǹ�ϵ ���ֶ�
	// private String relationship="";
	// ְҵ����
	// private String career="";
	// ������Ϣ�Ĵ�����Ʒ
	// private String opus_next="";
	// ���ݽ�ɫ���ֶ�
	// private String role="";
	// ����
	private String evaluation = "";
	// ������
	private String birthday_place = "";

	// Ӱ�ӽ�����ֶ�
	// private String film_award="";
	// ������ֶ�
	private String social_activities = "";

	public String getSocial_activities() {
		return social_activities;
	}

	public void setSocial_activities(String social_activities) {
		this.social_activities = social_activities;
	}

	// �ͻ�����
	private String flowers = "";
	// ����
	private String volk = "";
	// ������
	private String brokers = "";
	// ������Ϣ���ֶ�
	 private String description_text="";
	 
	// ����
	 private String hobby="";
	// ����Ϣ
	// private List<PersonAward> personAwardList;
	// // ְҵ����
	// private List<PersonCareer> personCareerList;
	// // ������Ϣ
	// private List<PersonDescription> personDescriptionList;
	// // ������Ʒ
	// private List<PersonOpus> personOpuList;
	// // �����Ա
	// private List<PersonRelatedActor> personRelatedActorList;
	// // ��Ա֮���ϵ
	// private List<PersonRelationshipActor> personRelationshipActorList;
	// // ���ݽ�ɫ
	// private List<PersonRolePlay> personRolePlayList;
	// // ͼƬ����
	// private List<PersonImg> personImgList;
	// ���
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
