package com.artsoft.downloadThreadpool.people.DanChiPeoPle.meitiguanzhudu;

import com.artsoft.downloadThreadpool.people.HaoSouWordAdmin;
import com.artsoft.pool.TaskInterface;

public class MyHaoSoutask_meitiguanzhudu_return2day  implements TaskInterface {
	String urlBranch; //
    String tvplayId;//
    String tyPlayName;//
    String DataType;//
	public MyHaoSoutask_meitiguanzhudu_return2day(String urlBranch,String tvplayId, String tyPlayName, String DataType){
		this.urlBranch=urlBranch;
		this.tvplayId=tvplayId;
		this.tyPlayName=tyPlayName;
		this.DataType=DataType;
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		meitiguanzhudu.runadminpool(urlBranch, tvplayId, tyPlayName, DataType);

	}
}
