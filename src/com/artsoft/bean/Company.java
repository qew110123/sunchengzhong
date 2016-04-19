package com.artsoft.bean;

public class Company {

	private Integer PRODUCE_ID;
	// 制片公司名称
	private String COMPANY_NAME = "";
	// 英文别名
	private String ENGLISH_NAME = "";
	// 别名
	private String ALIAS_NAME = "";
	// 成立时间
	private String FOUNDED_DATE = "";
	// 总部地点
	private String HEADQUARTERS = "";
	// 经营范围
	private String BUSINESS_SCOPE = "";
	// 公司性质
	private String COMPANY_NATURE = "";
	// 简介
	private String DESCRIPTION = "";
	// 作品简介
	private String WORKS_DESCRIPTION = "";
	// logo的Url地址
	private String LOGO_URL = "";
	// 服务领域
	private String SERVICE_AREA = "";
	// 年营业额
	private String YEAR_MONEY = "";
	// 员工数
	private String STAFF_NUM = "";
	// 董事长
	private String CEO_NAME = "";
	// 是否上市 1 否 2上市 3 未知
	private Integer IS_IPO = 1;
	
	private String url = "";

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPRODUCE_ID() {
		return PRODUCE_ID;
	}

	public void setPRODUCE_ID(Integer pRODUCE_ID) {
		PRODUCE_ID = pRODUCE_ID;
	}

	public String getCOMPANY_NAME() {
		return COMPANY_NAME;
	}

	public void setCOMPANY_NAME(String cOMPANY_NAME) {
		COMPANY_NAME = cOMPANY_NAME;
	}

	public String getENGLISH_NAME() {
		return ENGLISH_NAME;
	}

	public void setENGLISH_NAME(String eNGLISH_NAME) {
		ENGLISH_NAME = eNGLISH_NAME;
	}

	public String getALIAS_NAME() {
		return ALIAS_NAME;
	}

	public void setALIAS_NAME(String aLIAS_NAME) {
		ALIAS_NAME = aLIAS_NAME;
	}

	public String getFOUNDED_DATE() {
		return FOUNDED_DATE;
	}

	public void setFOUNDED_DATE(String fOUNDED_DATE) {
		FOUNDED_DATE = fOUNDED_DATE;
	}

	public String getHEADQUARTERS() {
		return HEADQUARTERS;
	}

	public void setHEADQUARTERS(String hEADQUARTERS) {
		HEADQUARTERS = hEADQUARTERS;
	}

	public String getBUSINESS_SCOPE() {
		return BUSINESS_SCOPE;
	}

	public void setBUSINESS_SCOPE(String bUSINESS_SCOPE) {
		BUSINESS_SCOPE = bUSINESS_SCOPE;
	}

	public String getCOMPANY_NATURE() {
		return COMPANY_NATURE;
	}

	public void setCOMPANY_NATURE(String cOMPANY_NATURE) {
		COMPANY_NATURE = cOMPANY_NATURE;
	}

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}

	public String getWORKS_DESCRIPTION() {
		return WORKS_DESCRIPTION;
	}

	public void setWORKS_DESCRIPTION(String wORKS_DESCRIPTION) {
		WORKS_DESCRIPTION = wORKS_DESCRIPTION;
	}

	public String getLOGO_URL() {
		return LOGO_URL;
	}

	public void setLOGO_URL(String lOGO_URL) {
		LOGO_URL = lOGO_URL;
	}

	public String getSERVICE_AREA() {
		return SERVICE_AREA;
	}

	public void setSERVICE_AREA(String sERVICE_AREA) {
		SERVICE_AREA = sERVICE_AREA;
	}

	public String getYEAR_MONEY() {
		return YEAR_MONEY;
	}

	public void setYEAR_MONEY(String yEAR_MONEY) {
		YEAR_MONEY = yEAR_MONEY;
	}

	public String getSTAFF_NUM() {
		return STAFF_NUM;
	}

	public void setSTAFF_NUM(String sTAFF_NUM) {
		STAFF_NUM = sTAFF_NUM;
	}

	public String getCEO_NAME() {
		return CEO_NAME;
	}

	public void setCEO_NAME(String cEO_NAME) {
		CEO_NAME = cEO_NAME;
	}

	public Integer getIS_IPO() {
		return IS_IPO;
	}

	public void setIS_IPO(Integer iS_IPO) {
		IS_IPO = iS_IPO;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
