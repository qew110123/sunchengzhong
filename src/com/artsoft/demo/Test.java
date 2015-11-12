package com.artsoft.demo;

import com.artsoft.bean.Persion;

import net.sf.json.JSONObject;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Persion p = new Persion();  
        p.setId(11);  
        p.setName("测试");  
          
        JSONObject jsonObj1 = JSONObject.fromObject(p);  
          
        System.out.println("JSON输出后的：" + jsonObj1.toString());  
          
        JSONObject jsonObj2 = JSONObject.fromObject(jsonObj1);  
          
        System.out.println("JSONObject输出后的：" + jsonObj2.toString());  
          
        Persion p2 = (Persion)JSONObject.toBean(jsonObj1, Persion.class);  
          
        System.out.println("json转化为对象：姓名：" + p2.getName() + ";年龄:" + p2.getId());  
          
        /*********处理js格式问题************/  
        //        JsonConfig config = new JsonConfig();  
        //        config.setIgnoreDefaultExcludes(false);  
        //        config.registerJsonBeanProcessor(Date.class, new JsDateJsonBeanProcessor());  
        /**************处理Integer为null时输出为0的问题 版本需要2.3**************/  
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
