package com.artsoft.bean;

public class TvPlay {
	// id
	private Integer tvplay_id;
	// ������
	private String tvplay_name = "";
	// �ٶȰٿ�url
	private String tvplay_url = "";
	// Ӣ�ı���
	private String alias_en = "";
	// ���ı���
	private String alias_cn = "";
	// ��Ҫ��Ա����url
	private String major_actors = "";
	// ��Ҫ����
	private String major_awards = "";
	// ���ݰ���url
	private String director = "";
	// ������url
	private String screenwriter = "";
	// ��Ƭ�˰���url
	private String producer = "";
	// ��Ƭ��˾����url
	private String production_company = "";
	// ���й�˾����url
	private String issuing_company = "";
	// ��������
	private String shoot_time = "";
	// ����ص�
	private String shoot_place = "";
	// ����
	private String subject = "";
	// ��Ʒʱ��
	private String produced_time = "";
	// ��Ʒ��˾����url
	private String produced_company = "";
	// ��Ƭ����
	private String production_area = "";
	// �ײ�ʱ��
	private String premiere_time = "";
	// ����
	private String pages = "";
	// ����ʱ�䳤��
	private String time_length = "";
	// ����ƽ̨
	private String play_platform = "";
	// �ײ�ƽ̨
	private String premiere_platform = "";
	// ��Ӱָ��
	private String phorogrphy_director = "";
	// ����Ƭ
	private String total_production = "";
	// ��Ƭ���ΰ���url
	private String production_chairman = "";
	// ��Ƭ�ɱ�
	private String production_cost = "";
	// �����糡
	private String play_theater = "";
	// �ӵ����Ӿ� ��һ�����Ӿ�
	private String before_eleplay = "";
	// ���ӵ����Ӿ� ��һ�����Ӿ�
	private String next_teleplay = "";
	// ����ʱ��
	private String open_time = "";
	// �ػ�ʱ��
	private String close_time = "";
	// �ܲ߻�
	private String total_planning = "";
	// Ƭ��
	private String film_time = "";
	// Ʊ��
	private String box_office = "";
	// ���� 1 ���Ӿ� 2 ��Ӱ 3 ���� 4 ��Ŀ
	private String type = "";
	// �����˰���url
	private String compere = "";
	// �ܹ����̰���url
	private String total_sponsor = "";
	// ����������url
	private String partners = "";
	// ����֧��
	private String special_support = "";
	// ָ���罻ƽ̨
	private String social_platform = "";
	// ��Ŀ�α�����url
	private String guest_program = "";
	// ��������
	private String season_numbver = "";
	// ¼�Ƴ���
	private String recording_place = "";
	// ���Ӿ����
	private String stills_url = "";
	//�������� ���ܰ���html��ǩ ÿ��֮����||����
	private String basic_info = "";
	//��ӳ����
	private String show_date = "";
	
	//2016��1��26��16:48:46
	private int classnum = 0;
	//����
	private String lgName="";
	//IMDb����
	private String IMDb="";
	
	
	
	public String getIMDb() {
		return IMDb;
	}

	public void setIMDb(String iMDb) {
		IMDb = iMDb;
	}

	public void print() {
		System.out.println("tvplay_id:		" + tvplay_id);
		System.out.println("tvplay_name:	" + tvplay_name);
		System.out.println("tvplay_url:		" + tvplay_url);
		System.out.println("alias_en:		" + alias_en);
		System.out.println("alias_cn:		" + alias_cn);
		System.out.println("major_actors:	" + major_actors);
		System.out.println("major_awards:	" + major_awards);
		System.out.println("director:		" + director);
		System.out.println("screenwriter:	" + screenwriter);
		System.out.println("producer:		" + producer);
		System.out.println("production_company:" + production_company);
		System.out.println("issuing_company:" + issuing_company);
		System.out.println("shoot_time:		" + shoot_time);
		System.out.println("shoot_place:	" + shoot_place);
		System.out.println("subject:		" + subject);
		System.out.println("produced_time: 	" + produced_time);
		System.out.println("produced_company:" + produced_company);
		System.out.println("production_area:" + production_area);
		System.out.println("premiere_time:	" + premiere_time);
		System.out.println("pages:			" + pages);
		System.out.println("time_length:	" + time_length);
		System.out.println("play_platform:	" + play_platform);
		System.out.println("premiere_platform:" + premiere_platform);
		System.out.println("phorogrphy_director:" + phorogrphy_director);
		System.out.println("total_production:" + total_production);
		System.out.println("production_chairman:" + production_chairman);
		System.out.println("production_cost:" + production_cost);
		System.out.println("play_theater:	" + play_theater);
		System.out.println("before_eleplay:	" + before_eleplay);
		System.out.println("next_teleplay:	" + next_teleplay);
		System.out.println("open_time: 		" + open_time);
		System.out.println("close_time: 	" + close_time);
		System.out.println("total_planning:	" + total_planning);
		System.out.println("film_time:		" + film_time);
		System.out.println("box_office: 	" + box_office);
		System.out.println("type: 			" + type);
		System.out.println("compere: 		" + compere);
		System.out.println("total_sponsor:	" + total_sponsor);
		System.out.println("partners: 		" + partners);
		System.out.println("special_support:" + special_support);
		System.out.println("social_platform:" + social_platform);
		System.out.println("guest_program:	" + guest_program);
		System.out.println("season_numbver:	" + season_numbver);
		System.out.println("recording_place:" + recording_place);
		System.out.println("stills_url:		" + stills_url);
		System.out.println("basic_info:		" + basic_info);
		
	}

	public Integer getTvplay_id() {
		return tvplay_id;
	}

	public void setTvplay_id(Integer tvplay_id) {
		this.tvplay_id = tvplay_id;
	}

	public String getTvplay_name() {
		return tvplay_name;
	}

	public void setTvplay_name(String tvplay_name) {
		this.tvplay_name = tvplay_name;
	}

	public String getTvplay_url() {
		return tvplay_url;
	}

	public void setTvplay_url(String tvplay_url) {
		this.tvplay_url = tvplay_url;
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

	public String getMajor_actors() {
		return major_actors;
	}

	public void setMajor_actors(String major_actors) {
		this.major_actors = major_actors;
	}

	public String getMajor_awards() {
		return major_awards;
	}

	public void setMajor_awards(String major_awards) {
		this.major_awards = major_awards;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getScreenwriter() {
		return screenwriter;
	}

	public void setScreenwriter(String screenwriter) {
		this.screenwriter = screenwriter;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getProduction_company() {
		return production_company;
	}

	public void setProduction_company(String production_company) {
		this.production_company = production_company;
	}

	public String getIssuing_company() {
		return issuing_company;
	}

	public void setIssuing_company(String issuing_company) {
		this.issuing_company = issuing_company;
	}

	public String getShoot_time() {
		return shoot_time;
	}

	public void setShoot_time(String shoot_time) {
		this.shoot_time = shoot_time;
	}

	public String getShoot_place() {
		return shoot_place;
	}

	public void setShoot_place(String shoot_place) {
		this.shoot_place = shoot_place;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getProduced_time() {
		return produced_time;
	}

	public void setProduced_time(String produced_time) {
		this.produced_time = produced_time;
	}

	public String getProduced_company() {
		return produced_company;
	}

	public void setProduced_company(String produced_company) {
		this.produced_company = produced_company;
	}

	public String getProduction_area() {
		return production_area;
	}

	public void setProduction_area(String production_area) {
		this.production_area = production_area;
	}

	public String getPremiere_time() {
		return premiere_time;
	}

	public void setPremiere_time(String premiere_time) {
		this.premiere_time = premiere_time;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getTime_length() {
		return time_length;
	}

	public void setTime_length(String time_length) {
		this.time_length = time_length;
	}

	public String getPlay_platform() {
		return play_platform;
	}

	public void setPlay_platform(String play_platform) {
		this.play_platform = play_platform;
	}

	public String getPremiere_platform() {
		return premiere_platform;
	}

	public void setPremiere_platform(String premiere_platform) {
		this.premiere_platform = premiere_platform;
	}

	public String getPhorogrphy_director() {
		return phorogrphy_director;
	}

	public void setPhorogrphy_director(String phorogrphy_director) {
		this.phorogrphy_director = phorogrphy_director;
	}

	public String getTotal_production() {
		return total_production;
	}

	public void setTotal_production(String total_production) {
		this.total_production = total_production;
	}

	public String getProduction_chairman() {
		return production_chairman;
	}

	public void setProduction_chairman(String production_chairman) {
		this.production_chairman = production_chairman;
	}

	public String getProduction_cost() {
		return production_cost;
	}

	public void setProduction_cost(String production_cost) {
		this.production_cost = production_cost;
	}

	public String getPlay_theater() {
		return play_theater;
	}

	public void setPlay_theater(String play_theater) {
		this.play_theater = play_theater;
	}

	public String getBefore_eleplay() {
		return before_eleplay;
	}

	public void setBefore_eleplay(String before_eleplay) {
		this.before_eleplay = before_eleplay;
	}

	public String getNext_teleplay() {
		return next_teleplay;
	}

	public void setNext_teleplay(String next_teleplay) {
		this.next_teleplay = next_teleplay;
	}

	public String getOpen_time() {
		return open_time;
	}

	public void setOpen_time(String open_time) {
		this.open_time = open_time;
	}

	public String getClose_time() {
		return close_time;
	}

	public void setClose_time(String close_time) {
		this.close_time = close_time;
	}

	public String getTotal_planning() {
		return total_planning;
	}

	public void setTotal_planning(String total_planning) {
		this.total_planning = total_planning;
	}

	public String getFilm_time() {
		return film_time;
	}

	public void setFilm_time(String film_time) {
		this.film_time = film_time;
	}

	public String getBox_office() {
		return box_office;
	}

	public void setBox_office(String box_office) {
		this.box_office = box_office;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCompere() {
		return compere;
	}

	public void setCompere(String compere) {
		this.compere = compere;
	}

	public String getTotal_sponsor() {
		return total_sponsor;
	}

	public void setTotal_sponsor(String total_sponsor) {
		this.total_sponsor = total_sponsor;
	}

	public String getPartners() {
		return partners;
	}

	public void setPartners(String partners) {
		this.partners = partners;
	}

	public String getSpecial_support() {
		return special_support;
	}

	public void setSpecial_support(String special_support) {
		this.special_support = special_support;
	}

	public String getSocial_platform() {
		return social_platform;
	}

	public void setSocial_platform(String social_platform) {
		this.social_platform = social_platform;
	}

	public String getGuest_program() {
		return guest_program;
	}

	public void setGuest_program(String guest_program) {
		this.guest_program = guest_program;
	}

	public String getSeason_numbver() {
		return season_numbver;
	}

	public void setSeason_numbver(String season_numbver) {
		this.season_numbver = season_numbver;
	}

	public String getRecording_place() {
		return recording_place;
	}

	public void setRecording_place(String recording_place) {
		this.recording_place = recording_place;
	}

	public String getStills_url() {
		return stills_url;
	}

	public void setStills_url(String stills_url) {
		this.stills_url = stills_url;
	}

	public String getBasic_info() {
		return basic_info;
	}

	public void setBasic_info(String basic_info) {
		this.basic_info = basic_info;
	}

	public String getShow_date() {
		return show_date;
	}

	public void setShow_date(String show_date) {
		this.show_date = show_date;
	}

	public int getClassnum() {
		return classnum;
	}

	public void setClassnum(int classnum) {
		this.classnum = classnum;
	}

	public String getLgName() {
		return lgName;
	}

	public void setLgName(String lgName) {
		this.lgName = lgName;
	}
}
