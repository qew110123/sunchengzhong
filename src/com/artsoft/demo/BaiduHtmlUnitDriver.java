package com.artsoft.demo;

import org.openqa.selenium.By;  
import org.openqa.selenium.WebDriver;  
import org.openqa.selenium.WebElement;  
import org.openqa.selenium.htmlunit.HtmlUnitDriver;  

public class BaiduHtmlUnitDriver {
	
	
	public static void HtmlUnitDriver(){
		  // TODO Auto-generated method stub  
        WebDriver driver=new HtmlUnitDriver();  
        //�򿪰ٶ���ҳ  
        driver.get("http://www.baidu.com/");  
        //��ӡҳ�����  
        System.out.println("ҳ����⣺"+driver.getTitle());  
        //����id��ȡҳ��Ԫ�������  
        WebElement search=driver.findElement(By.id("kw"));  
        //��id=��kw������������롰selenium��  
        search.sendKeys("selenium");  
        //����id��ȡ�ύ��ť  
        WebElement submit=driver.findElement(By.id("su"));  
        //�����ť��ѯ  
        submit.click();  
        //��ӡ��ǰҳ�����  
        System.out.println("ҳ����⣺"+driver.getTitle());  
        //���ص�ǰҳ���url  
        System.out.println("ҳ��url��"+driver.getCurrentUrl());  
        //���ص�ǰ��������Ĵ��ھ��  
        System.out.println("���ھ����"+driver.getWindowHandle());  
	}


	
	public static void google(){
		  // TODO Auto-generated method stub  
	    // Create a new instance of the html unit driver  
        // Notice that the remainder of the code relies on the interface,   
        // not the implementation.  
        WebDriver driver = new HtmlUnitDriver();  
  
        // And now use this to visit Google  
        driver.get("http://www.google.com");  
  
        // Find the text input element by its name  
        WebElement element = driver.findElement(By.name("q"));  
  
        // Enter something to search for  
        element.sendKeys("Cheese!");  
  
        // Now submit the form. WebDriver will find the form for us from the element  
        element.submit();  
  
        // Check the title of the page  
        System.out.println("Page title is: " + driver.getTitle());  
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		while (true) {
			
			HtmlUnitDriver();
//		}

	}

}
