package com.artsoft.demo.ftp;

import junit.framework.TestCase;
 
public class FavFTPTest extends TestCase {
  
 public void testFavFTPUtil(){
 String hostname = "192.168.1.18";
 int port = 21;
 String username = "shareuser";
 String password = "shareuser18";
 String pathname = "/contents"; 
 String filename = "1.jpg"; 
 String originfilename = "D:\\1.jpg";
 FavFTPUtil.uploadFileFromProduction(hostname, port, username, password, pathname, filename, originfilename);
// String localpath = "D:/";
  
// FavFTPUtil.downloadFile(hostname, port, username, password, pathname, filename, localpath);
 }
 
 
 
 
 public static void main(String[] args) {
	 FavFTPTest bb= new FavFTPTest();
	 bb.testFavFTPUtil();
}
 
}