package com.artsoft.downloadThreadpool;

import com.artsoft.downloadThreadpool.people.HaoSouWordAdmin;
import com.artsoft.pool.TaskInterface;

public class MyHaoSoutask implements TaskInterface {
	String urlBranch; //
    String tvplayId;//
    String tyPlayName;//
    String DataType;//
	public MyHaoSoutask(String urlBranch,String tvplayId, String tyPlayName, String DataType){
		this.urlBranch=urlBranch;
		this.tvplayId=tvplayId;
		this.tyPlayName=tyPlayName;
		this.DataType=DataType;
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		HaoSouWordAdmin.runadminpool(urlBranch, tvplayId, tyPlayName, DataType);

	}

}
