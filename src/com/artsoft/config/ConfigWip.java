package com.artsoft.config;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class ConfigWip{
	
	public Document m_xmlDoc = null; /**主文档对象*/
	static ConfigWip config = null;
	
	public static ConfigWip getInstance(){
		if(config == null)
			config = new ConfigWip();
		
		return config;
	}
	
	private ConfigWip() {
        try {  
        	SAXReader reader = new SAXReader();      	
        	m_xmlDoc = reader.read("config/config.xml");   //File      	          
        } catch (DocumentException e) {
        	e.printStackTrace();
            return;
        }
	}
	
	
	/**
	 * 获取配置文件信息
	 * @param strParam
	 * @return
	 */
	public String getConfigValue(String strParam) {
		Element root = m_xmlDoc.getRootElement();
		Node data = root.selectSingleNode("//" + strParam);
		return data.getText();
	}
	
	/**
	 * 设置配置文件值
	 * lusd 2010-03-31
	 */
	public synchronized void setConfigValue(String strKey,String strVal) {
		Element root = m_xmlDoc.getRootElement();
		Node data = root.selectSingleNode(strKey);
		data.setText(strVal);
		write();
	}
	
	public void deleteTaskByIndex(String strPatId){
		Element root = m_xmlDoc.getRootElement();
		Element element = (Element)root.selectSingleNode("//Task[@ID='"+strPatId+"']");
		if(element == null)
			return;
		element.detach();
//		
//		Node data = root.selectSingleNode("//Task[@ID='"+strPatId+"']");
//		root.remove(data);
		write();
	}
	
	public void addTaskByIndex(String strPatId,String strTask ){
		
		Element root = m_xmlDoc.getRootElement();
		Element downloadTask = root.element("WipoTask");
		
		Element element = downloadTask.addElement("Task");
		element.setAttributeValue("ID", strPatId);
		element.setText(strPatId+"##"+strTask);
		write();
	}
	
	public ArrayList<String>  initLastTask(){
		
		Element root = m_xmlDoc.getRootElement();
		Element element = root.element("WipoTask");
		
		List list = element.elements();
		ArrayList<String> listPatDown = new ArrayList<String>() ; 
		
		if (list.size()==0){
			System.out.println("没有任务！");
			return null;
		}
		
		for(int i=0; i<list.size(); i++){
			Element elementTemp = (Element)list.get(i);
			String strKey = elementTemp.getStringValue();
			DownTask.addTask(strKey);
		}
		return listPatDown;
	}
	
	
	public void write() {
        // 指定文件
        XMLWriter writer = null;
        try {
            // 美化格式
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("gb2312");
            writer = new XMLWriter(new FileOutputStream("config/info.xml"), format);
            writer.write(m_xmlDoc);
            writer.close();
        } catch (IOException e) {
        }
    }
	
	public static void main(String[] args){
		String str = ConfigWip.getInstance().getConfigValue("URL");
		System.out.println(str);
	}
}
