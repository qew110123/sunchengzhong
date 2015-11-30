package com.artsoft.demo;

import java.io.File;

public class tunliunxtxt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File dir = new File("/home1/sxl/out");
		dir.setWritable(true, false);

		if (!dir.exists()) {
			boolean test = dir.mkdir();
			System.out.println(test);
		}
		System.out.println("222222222222");

	}

}
