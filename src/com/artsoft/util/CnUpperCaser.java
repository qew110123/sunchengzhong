package com.artsoft.util;

public class CnUpperCaser { // ��������
	  private String integerPart;
	  // С������
	  private String floatPart;
	  
	  // ������ת��Ϊ���ֵ�����,��Ϊ����ʵ����Ҫʹ��������Ϊ��̬
	  private static final char[] cnNumbers={'��', 'һ', '��', '��', '��', '��', '��', '��', '��', '��'};
	  
	  // ���ּ�ת��������,��Ϊ����ʵ����Ҫʹ��������Ϊ��̬
	  private static final char[] series={' ','ʰ','��','Ǫ','��','ʰ','��','Ǫ','��'};
	  
	  /**
	   * ���캯��,ͨ������������������ʽ���ַ�������
	   * @param original
	   */
	  public CnUpperCaser(String original){ 
	    // ��Ա������ʼ��
	    integerPart="";
	    floatPart="";
	    
	    if(original.contains(".")){
	      // �������С����
	      int dotIndex=original.indexOf(".");
	      integerPart=original.substring(0,dotIndex);
	      floatPart=original.substring(dotIndex+1);
	    }
	    else{
	      // ������С����
	      integerPart=original;
	    }
	  }
	  
	  /**
	   * ȡ�ô�д��ʽ���ַ���
	   * @return
	   */
	  public String getCnString(){
	    // ��Ϊ���ۼ�������StringBuffer
	    StringBuffer sb=new StringBuffer();
	    
	    // �������ִ���
	    for(int i=0;i<integerPart.length();i++){
	      int number=getNumber(integerPart.charAt(i));
	      
	      sb.append(cnNumbers[number]);
	      sb.append(series[integerPart.length()-1-i]);
	    }
	    
	    // С�����ִ���
	    if(floatPart.length()>0){
	      sb.append("��");
	      for(int i=0;i<floatPart.length();i++){
	        int number=getNumber(floatPart.charAt(i));
	        
	        sb.append(cnNumbers[number]);
	      }
	    }
	    
	    // ����ƴ�Ӻõ��ַ���
	    return sb.toString();
	  }
	  
	  /**
	   * ���ַ���ʽ������ת��Ϊ��������
	   * ��Ϊ����ʵ����Ҫ�õ������þ�̬����
	   * @param c
	   * @return
	   */
	  private static int getNumber(char c){
	    String str=String.valueOf(c);   
	    return Integer.parseInt(str);
	  }
	  
	  /**
	   * @param args
	   */
	  public static void main(String[] args) {
	    System.out.println(new CnUpperCaser("123456789.12345").getCnString());
	    System.out.println(new CnUpperCaser("123456789").getCnString());
	    System.out.println(new CnUpperCaser(".123456789").getCnString());
	    System.out.println(new CnUpperCaser("0.1234").getCnString());
	    System.out.println(new CnUpperCaser("1").getCnString());
	    System.out.println(new CnUpperCaser("12").getCnString());
	    System.out.println(new CnUpperCaser("123").getCnString());
	    System.out.println(new CnUpperCaser("1234").getCnString());
	    System.out.println(new CnUpperCaser("12345").getCnString());
	    System.out.println(new CnUpperCaser("123456").getCnString());
	    System.out.println(new CnUpperCaser("1234567").getCnString());
	    System.out.println(new CnUpperCaser("12345678").getCnString());
	    System.out.println(new CnUpperCaser("123456789").getCnString());
	  }}
