
import java.io.Serializable;

/** ods.TEM_DIM_VARIETY_PLATFORM
	TVPLAY_ID	NUMBER(18)
	TVPLAY_NAME	VARCHAR2(200)
	TVPLAY_URL	VARCHAR2(600)
	BASIC_INFO	VARCHAR2(4000)
	PLAY_AMOUNT	VARCHAR2(600)
	TIME_INFO	VARCHAR2(4000)
	MAJOR_ACTORS	VARCHAR2(2000)
	SOURCE	NUMBER(4)
	INTO_DATE	DATE
	DATA_TYPE	NUMBER(2)
	PRODUCE_AREA	VARCHAR2(200)
	SUBJECT_NAME	VARCHAR2(200)
	CHANNEL_NAME	VARCHAR2(200)
	TVPLAY_NAME_SMALL	VARCHAR2(200)
	URL	VARCHAR2(200)
*/
public class Ods.TEM_DIM_VARIETY_PLATFORM implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long tvplayId;
	private String tvplayName;
	private String tvplayUrl;
	private String basicInfo;
	private String playAmount;
	private String timeInfo;
	private String majorActors;
	private Integer source;
	private Date intoDate;
	private Integer dataType;
	private String produceArea;
	private String subjectName;
	private String channelName;
	private String tvplayNameSmall;
	private String url;

	public void setTvplayId(Long tvplayId){
		this.tvplayId=tvplayId;
	}
	public Long getTvplayId(){
		return tvplayId;
	}
	public void setTvplayName(String tvplayName){
		this.tvplayName=tvplayName;
	}
	public String getTvplayName(){
		return tvplayName;
	}
	public void setTvplayUrl(String tvplayUrl){
		this.tvplayUrl=tvplayUrl;
	}
	public String getTvplayUrl(){
		return tvplayUrl;
	}
	public void setBasicInfo(String basicInfo){
		this.basicInfo=basicInfo;
	}
	public String getBasicInfo(){
		return basicInfo;
	}
	public void setPlayAmount(String playAmount){
		this.playAmount=playAmount;
	}
	public String getPlayAmount(){
		return playAmount;
	}
	public void setTimeInfo(String timeInfo){
		this.timeInfo=timeInfo;
	}
	public String getTimeInfo(){
		return timeInfo;
	}
	public void setMajorActors(String majorActors){
		this.majorActors=majorActors;
	}
	public String getMajorActors(){
		return majorActors;
	}
	public void setSource(Integer source){
		this.source=source;
	}
	public Integer getSource(){
		return source;
	}
	public void setIntoDate(Date intoDate){
		this.intoDate=intoDate;
	}
	public Date getIntoDate(){
		return intoDate;
	}
	public void setDataType(Integer dataType){
		this.dataType=dataType;
	}
	public Integer getDataType(){
		return dataType;
	}
	public void setProduceArea(String produceArea){
		this.produceArea=produceArea;
	}
	public String getProduceArea(){
		return produceArea;
	}
	public void setSubjectName(String subjectName){
		this.subjectName=subjectName;
	}
	public String getSubjectName(){
		return subjectName;
	}
	public void setChannelName(String channelName){
		this.channelName=channelName;
	}
	public String getChannelName(){
		return channelName;
	}
	public void setTvplayNameSmall(String tvplayNameSmall){
		this.tvplayNameSmall=tvplayNameSmall;
	}
	public String getTvplayNameSmall(){
		return tvplayNameSmall;
	}
	public void setUrl(String url){
		this.url=url;
	}
	public String getUrl(){
		return url;
	}
}

