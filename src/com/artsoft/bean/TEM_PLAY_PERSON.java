package com.artsoft.bean;

import java.io.Serializable;

/** ods.TEM_PLAY_PERSON
	DATA_ID	VARCHAR2(50)
	DATA_NAME	VARCHAR2(50)
	DATA_URL	VARCHAR2(200)
	PERSON_NAME	VARCHAR2(50)
	PERSON_URL	VARCHAR2(200)
	ROLE_NAME	VARCHAR2(200)
	PERSON_SMALL_URL	VARCHAR2(200)
	DUBBING_NAME	VARCHAR2(200)
	ROLE_INTRO	VARCHAR2(3000)
	INTO_DATE	DATE
	SOURCE	NUMBER(2)
*/
public class TEM_PLAY_PERSON implements Serializable {
	private static final long serialVersionUID = 1L;
	private String dataId;
	private String dataName;
	private String dataUrl;
	private String personName;
	private String personUrl;
	private String roleName;
	private String personSmallUrl;
	private String dubbingName;
	private String roleIntro;
	private Integer source;

	public void setDataId(String dataId){
		this.dataId=dataId;
	}
	public String getDataId(){
		return dataId;
	}
	public void setDataName(String dataName){
		this.dataName=dataName;
	}
	public String getDataName(){
		return dataName;
	}
	public void setDataUrl(String dataUrl){
		this.dataUrl=dataUrl;
	}
	public String getDataUrl(){
		return dataUrl;
	}
	public void setPersonName(String personName){
		this.personName=personName;
	}
	public String getPersonName(){
		return personName;
	}
	public void setPersonUrl(String personUrl){
		this.personUrl=personUrl;
	}
	public String getPersonUrl(){
		return personUrl;
	}
	public void setRoleName(String roleName){
		this.roleName=roleName;
	}
	public String getRoleName(){
		return roleName;
	}
	public void setPersonSmallUrl(String personSmallUrl){
		this.personSmallUrl=personSmallUrl;
	}
	public String getPersonSmallUrl(){
		return personSmallUrl;
	}
	public void setDubbingName(String dubbingName){
		this.dubbingName=dubbingName;
	}
	public String getDubbingName(){
		return dubbingName;
	}
	public void setRoleIntro(String roleIntro){
		this.roleIntro=roleIntro;
	}
	public String getRoleIntro(){
		return roleIntro;
	}
	public void setSource(Integer source){
		this.source=source;
	}
	public Integer getSource(){
		return source;
	}
}


