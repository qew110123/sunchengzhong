package com.artsoft.demo;

import com.artsoft.bean.Persion;

import net.sf.json.JSONObject;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Persion p = new Persion();  
        p.setId(11);  
        p.setName("����");  
          
        JSONObject jsonObj1 = JSONObject.fromObject(p);  
          
        System.out.println("JSON�����ģ�" + jsonObj1.toString());  
          
        JSONObject jsonObj2 = JSONObject.fromObject(jsonObj1);  
          
        System.out.println("JSONObject�����ģ�" + jsonObj2.toString());  
          
        Persion p2 = (Persion)JSONObject.toBean(jsonObj1, Persion.class);  
          
        System.out.println("jsonת��Ϊ����������" + p2.getName() + ";����:" + p2.getId());  
          
        /*********����js��ʽ����************/  
        //        JsonConfig config = new JsonConfig();  
        //        config.setIgnoreDefaultExcludes(false);  
        //        config.registerJsonBeanProcessor(Date.class, new JsDateJsonBeanProcessor());  
        /**************����IntegerΪnullʱ���Ϊ0������ �汾��Ҫ2.3**************/  
        //        JsonConfig jsonConfig = new JsonConfig();  
        //        jsonConfig.registerDefaultValueProcessor(Integer.class, new MyDefaultIntegerValueProcessor());  
        //        JsonConfig jsonConfig = new JsonConfig();  
        //        jsonConfig.findJsonValueProcessor(Integer.class, new DefaultValueProcessor()  
        //        {  
        //            public Object getDefaultValue(Class type)  
        //            {  
        //                return null;  
        //            }  
        //        });  
          
    }  


}
