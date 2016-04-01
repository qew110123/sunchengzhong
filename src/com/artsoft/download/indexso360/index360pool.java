package com.artsoft.download.indexso360;

import com.artsoft.pool.TaskInterface;

public class index360pool implements TaskInterface {

	String person_id; //
	String data_date;//
	String keyword;//
	String krywordutf8;//

	public index360pool(String person_id, String data_date, String keyword, String krywordutf8) {
		this.person_id = person_id;
		this.data_date = data_date;
		this.keyword = keyword;
		this.krywordutf8 = krywordutf8;
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		indexso360.my360run(person_id, data_date, keyword, krywordutf8);
	}

}
