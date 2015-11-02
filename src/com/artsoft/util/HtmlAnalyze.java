package com.artsoft.util;


import java.util.StringTokenizer;

public class HtmlAnalyze {
	
	private static String[] strTypes = {"pdf", ".ps", "dwf", "kml", "kmz", "xls", "ppt", "doc", "rtf", "swf", "txt"};
	/**
     * <pre>ȥ����ҳ��ǩ</pre>
     * @version 1.0, 2006-08-10
     * @param strHtml ��ҳ�ַ���
     * @return ȥ������ҳ��ǩ���ַ���
     * @author dl
     */
    public static String deleteTag(String strHtml) {
        if (strHtml == null || strHtml.length() == 0) {
            return null;
        }
        int nFlag = 0;
        int j = 0;
        char[] chAr = new char[strHtml.length()];
        for (int i = 0; i < strHtml.length(); ++i) {
            char chTemp = strHtml.charAt(i);
            if (chTemp == '<') {
                nFlag = 1;
            }
            if (nFlag == 0) {
                chAr[j] = chTemp;
                j++;
            }
            if (chTemp == '>') {
                nFlag = 0;
            }
        }
        
        String strResult = new String(chAr, 0, j);
        strResult = strResult.trim();
        return strResult;
    }
    
    /**
     * <pre>������ҳ��ǩ</pre>
     * @version 1.0, 2006-08-10
     * @param strHtml ��ҳ�ַ���
     * @return ȥ������ҳ��ǩ���ַ���
     * @author dl
     */
    public static String SaveTag(String strHtml) {
        if (strHtml == null || strHtml.length() == 0) {
            return null;
        }
        int nFlag = 0;
        int j = 0;
        char[] chAr = new char[strHtml.length()];
        for (int i = 0; i < strHtml.length(); ++i) {
            char chTemp = strHtml.charAt(i);
            if (chTemp == '<') {
                nFlag = 0;
            }
            if (nFlag == 0) {
                chAr[j] = chTemp;
                j++;
            }
            if (chTemp == '>') {
                nFlag = 1;
            }
        }
        
        return new String(chAr, 0, j);
    }
    
    /**
     * <pre>�õ��������֮����ı�(�����ִ�Сд)</pre>
     * @version 1.0, 2006-08-10
     * @param strHtml ��ҳ�ַ���
     * @param strStart ��ʼ���
     * @param strEnd �������
     * @param bFlag true ���ز�������ǵ��ַ����� false ���ش���ǵ��ַ���
     * @return ��Ǽ���ı�
     * @author dl
     */
    public static String getTagText(String strHtml, String strStart, String strEnd, boolean bFlag, int pos) {
    	if (strHtml == null || strHtml.length() == 0 || strStart == null || strStart.length() == 0 || strEnd == null || strEnd.length() == 0) {
    		return null;
    	}
    	String strTempHtml = strHtml.toLowerCase();
    	strStart = strStart.toLowerCase();
    	strEnd	 = strEnd.toLowerCase();
    	int iSPos = strTempHtml.indexOf(strStart, pos);
    	int iEPos = 0;
    	if (iSPos != -1) {
    		iEPos = strTempHtml.indexOf(strEnd, iSPos + strStart.length());
    		if (iEPos != -1) {
    			String strTemp = null;
    			if (bFlag) {
    				strTemp = strHtml.substring(iSPos + strStart.length(),iEPos);
    			} else {
    				strTemp = strHtml.substring(iSPos, iEPos + strEnd.length());
    			}
    			return strTemp;
    		} 
    	}
    	return null;
    }
    
    /**
     * <pre>�õ��������֮����ı�</pre>
     * @version 1.0, 2006-08-10
     * @param strHtml ��ҳ�ַ���
     * @param strStart ��ʼ���
     * @param strEnd �������
     * @param strRetText ��Ǽ���ı�
     * @param bFlag true ���ز�������ǵ��ַ����� false ���ش���ǵ��ַ���
     * @return ������ǵ�λ��
     * @author dl
     */
    public static int getTagText(String strHtml, String strStart, String strEnd, StringBuffer strRetText, boolean bFlag, int pos) {
    	if (strHtml == null || strHtml.length() == 0 || strStart == null ||
    			strStart.length() == 0 || strEnd == null || strEnd.length() == 0) {
    		return -1;
    	}
    	int iSPos = strHtml.indexOf(strStart, pos);
    	int iEPos = 0;
    	if (iSPos != -1) {
    		iEPos = strHtml.indexOf(strEnd, iSPos + strStart.length());
    		if (iEPos != -1) {
    			String strTemp = null;
    			if (bFlag) {
    				strTemp = strHtml.substring(iSPos + strStart.length(), iEPos);
    			} else {
    				strTemp = strHtml.substring(iSPos, iEPos + strEnd.length());
    			}
    			strRetText.append(strTemp);
    			pos = iEPos + strEnd.length();
    		} else {
    			pos = -1;
    		}
    	} else {
    		pos = -1;
    	}
    	return pos;
    }
    
    /**
	 * <pre>
	 * �õ��������֮����ı�
	 * </pre>
	 * 
	 * @version 1.0, 2006-08-10
	 * @param strHtml
	 *            ��ҳ�ַ���
	 * @param strStart
	 *            ��ʼ���
	 * @param strEnd
	 *            �������
	 * @param strRetText
	 *            ��Ǽ���ı�
	 * @return ������ǵ�λ��
	 * @author dl
	 */
    public static int getTagText(String strHtml, String strStart, String strEnd,
                                 StringBuffer strRetText, int pos) {
        if (strHtml == null || strHtml.length() == 0 || strStart == null ||
            strStart.length() == 0 || strEnd == null || strEnd.length() == 0 ||
            strRetText == null) {
            strRetText = null;
            return -1;
        }
        int iSPos = strHtml.indexOf(strStart, pos);
        int iEPos = 0;
        if (iSPos != -1) {
            iEPos = strHtml.indexOf(strEnd, iSPos + strStart.length());
            if (iEPos != -1) {
                String strTemp = strHtml.substring(iSPos + strStart.length(), iEPos);
                strRetText.append(deleteTag(strTemp));
            } else {
                strRetText = null;
            }
        } else {
            strRetText = null;
        }
        if (strRetText != null) {
            return iEPos + strEnd.length();
        } else {
            return -1;
        }
    }
    
    /**
     *<br>����˵�����õ�URL���ӵ��ļ���չ��
     *<br>���������
     *<br>�������ͣ��ļ���չ��
     */
    public static String getFileType(String strUrl) {
    	if (strUrl == null || strUrl.length() == 0) {
    		return null;
    	}
    	strUrl = strUrl.toLowerCase();
    	int pos = strUrl.lastIndexOf("#");
    	if (pos != -1) {
    		strUrl = strUrl.substring(0, pos);
    	}
    	pos = strUrl.lastIndexOf("?");
    	if (pos != -1) {
    		strUrl = strUrl.substring(0, pos);
    	}
    	String hz = strUrl.substring(strUrl.length() - 4, strUrl.length());
    	for (int i = 0; i < strTypes.length; ++i) {
    		if (hz.compareTo("." + strTypes[i]) == 0) {
    			return hz.substring(1, hz.length());
    		}
    		String temphz = hz.substring(1, hz.length());
    		if (temphz.compareTo(".ps") == 0)
				return "ps";
    	}
    	return "html";
    }
    
    /**
     *<br>����˵�����õ�URL���ӵ��ļ���չ��
     *<br>���������
     *<br>�������ͣ��ļ���չ��
     */
    public static String getFileTypeMht(String strUrl) {
    	if (strUrl == null || strUrl.length() == 0) {
    		return null;
    	}
    	int pos = strUrl.indexOf("#");
    	if (pos != -1) {
    		strUrl = strUrl.substring(0, pos);
    	}
    	pos = strUrl.lastIndexOf("?");
    	if (pos != -1) {
    		strUrl = strUrl.substring(0, pos);
    	}
    	String hz = strUrl.substring(strUrl.length() - 4, strUrl.length());
    	for (int i = 0; i < strTypes.length; ++i) {
    		if (hz.compareTo("." + strTypes[i]) == 0) {
    			return hz.substring(1, hz.length());
    		}
    		String temphz = hz.substring(1, hz.length());
    		if (temphz.compareTo(".ps") == 0)
				return "ps";
    	}
    	return "mht";
    }
    
    /**
     *<br>����˵�����õ���������Ľ����ַ���
     *<br>���������strText ��������ַ���; strItem �����ַ���
     *<br>�������ͣ���������Ľ����ַ���
     */
	public static String getIndex(String strText, String strItem) {
		StringTokenizer st = new StringTokenizer(strItem, "|");

		while (st.hasMoreTokens()) {
			String strIndex = st.nextToken();
			
			if (strText.indexOf(strIndex) != -1) {
				return strIndex;
			}
		}
		
		return strItem;
	}
    
    /**
     *<br>����˵�����õ���������������ַ����м������
     *<br>���������strText ��������ַ���; strItem1 �����ַ���1; strItem2 �����ַ���2
     *<br>�������ͣ��ַ����м������
     */
	
	public static String getTagText(String strText, String startSign, String endSign) {
//		String strStart = getIndex(strText, strItem1);
//		String strEnd = getIndex(strText, strItem2);
		
		String strStart = startSign;
		String strEnd = endSign;
		
		if (strStart == null || strEnd == null)
			return null;
		StringBuffer strRet = new StringBuffer();
		getTagText(strText, strStart, strEnd, strRet, 0);
		
		if (strRet.length() == 0) {
			return null;
		}
		String strVal = StringTool.getStrVal(strRet.toString());
		return strVal;
	}
	
	public static String removeSpace(String strHtml){
		
		if(strHtml == null || strHtml.length() == 0)
			return "";
		
		String[] arrLine = strHtml.split("\r\n");
		
		StringBuffer buf = new StringBuffer();
		
		for(int i=0; i<arrLine.length; i++){
			
			if(arrLine[i].trim().equals(""))
				continue;
			
			buf.append(arrLine[i].trim()+"<##>");
		}
		
		return buf.toString().trim();
	}
}

