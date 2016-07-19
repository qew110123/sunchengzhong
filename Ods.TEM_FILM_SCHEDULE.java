
import java.io.Serializable;

/** ods.TEM_FILM_SCHEDULE
	DATA_DATE	VARCHAR2(20)
	FID	VARCHAR2(20)
	URL	VARCHAR2(200)
	CITY_NAME	VARCHAR2(200)
	TITLE	VARCHAR2(200)
	FIELD_NUM_RATE	VARCHAR2(200)
	FIELD_NUM	VARCHAR2(200)
	INTO_DATE	DATE
	COLLECTION_URL	VARCHAR2(200)
*/
public class Ods.TEM_FILM_SCHEDULE implements Serializable {
	private static final long serialVersionUID = 1L;
	private String dataDate;
	private String fid;
	private String url;
	private String cityName;
	private String title;
	private String fieldNumRate;
	private String fieldNum;
	private Date intoDate;
	private String collectionUrl;

	public void setDataDate(String dataDate){
		this.dataDate=dataDate;
	}
	public String getDataDate(){
		return dataDate;
	}
	public void setFid(String fid){
		this.fid=fid;
	}
	public String getFid(){
		return fid;
	}
	public void setUrl(String url){
		this.url=url;
	}
	public String getUrl(){
		return url;
	}
	public void setCityName(String cityName){
		this.cityName=cityName;
	}
	public String getCityName(){
		return cityName;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setFieldNumRate(String fieldNumRate){
		this.fieldNumRate=fieldNumRate;
	}
	public String getFieldNumRate(){
		return fieldNumRate;
	}
	public void setFieldNum(String fieldNum){
		this.fieldNum=fieldNum;
	}
	public String getFieldNum(){
		return fieldNum;
	}
	public void setIntoDate(Date intoDate){
		this.intoDate=intoDate;
	}
	public Date getIntoDate(){
		return intoDate;
	}
	public void setCollectionUrl(String collectionUrl){
		this.collectionUrl=collectionUrl;
	}
	public String getCollectionUrl(){
		return collectionUrl;
	}
}

