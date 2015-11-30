package com.artsoft.downloadThreadpool;

import com.artsoft.pool.TaskInterface;
import com.artsoft.downloadThreadpool.HaoSouWordAdmin;

public class HaoSouTVtask implements TaskInterface {
	String urlBranch; //
    String tvplayId;//
    String tyPlayName;//
    String DataType;//
	public HaoSouTVtask(String urlBranch,String tvplayId, String tyPlayName, String DataType){
		this.urlBranch=urlBranch;
		this.tvplayId=tvplayId;
		this.tyPlayName=tyPlayName;
		this.DataType=DataType;
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		HaoSouTV.HaosouBranch(urlBranch, tvplayId, tyPlayName, DataType);

	}

}
