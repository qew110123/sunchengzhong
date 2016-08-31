package com.artsoft.downloadThreadpool;

import com.artsoft.downloadThreadpool.people.HaoSouWordAdmin;
import com.artsoft.downloadThreadpool.tvplay.HaoSouTV;
import com.artsoft.pool.TaskInterface;

public class HaoSouTVtask implements TaskInterface {
	String urlBranch; //
    String tvplayId;//
    String tyPlayName;//
    String DataType;//
    int TV_TYPE;
    
	public HaoSouTVtask(String urlBranch,String tvplayId, String tyPlayName, String DataType,int TV_TYPE){
		this.urlBranch=urlBranch;
		this.tvplayId=tvplayId;
		this.tyPlayName=tyPlayName;
		this.DataType=DataType;
		this.TV_TYPE=TV_TYPE;
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		HaoSouTV.HaosouBranch(urlBranch, tvplayId, tyPlayName, DataType ,TV_TYPE);

	}

}
