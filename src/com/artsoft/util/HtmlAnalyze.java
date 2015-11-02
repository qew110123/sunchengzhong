package com.artsoft.util;


import java.util.StringTokenizer;

public class HtmlAnalyze {
	
	private static String[] strTypes = {"pdf", ".ps", "dwf", "kml", "kmz", "xls", "ppt", "doc", "rtf", "swf", "txt"};
	/**
     * <pre>去除网页标签</pre>
     * @version 1.0, 2006-08-10
     * @param strHtml 网页字符串
     * @return 去除了网页标签的字符串
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
     * <pre>保留网页标签</pre>
     * @version 1.0, 2006-08-10
     * @param strHtml 网页字符串
     * @return 去除了网页标签的字符串
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
     * <pre>得到两个标记之间的文本(不区分大小写)</pre>
     * @version 1.0, 2006-08-10
     * @param strHtml 网页字符串
     * @param strStart 开始标记
     * @param strEnd 结束标记
     * @param bFlag true 返回不包含标记的字符串； false 返回带标记的字符串
     * @return 标记间的文本
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
     * <pre>得到两个标记之间的文本</pre>
     * @version 1.0, 2006-08-10
     * @param strHtml 网页字符串
     * @param strStart 开始标记
     * @param strEnd 结束标记
     * @param strRetText 标记间的文本
     * @param bFlag true 返回不包含标记的字符串； false 返回带标记的字符串
     * @return 结束标记的位置
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
	 * 得到两个标记之间的文本
	 * </pre>
	 * 
	 * @version 1.0, 2006-08-10
	 * @param strHtml
	 *            网页字符串
	 * @param strStart
	 *            开始标记
	 * @param strEnd
	 *            结束标记
	 * @param strRetText
	 *            标记间的文本
	 * @return 结束标记的位置
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
     *<br>方法说明：得到URL链接的文件扩展名
     *<br>输入参数：
     *<br>返回类型：文件扩展名
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
     *<br>方法说明：得到URL链接的文件扩展名
     *<br>输入参数：
     *<br>返回类型：文件扩展名
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
     *<br>方法说明：得到搜索结果的解析字符串
     *<br>输入参数：strText 搜索结果字符串; strItem 解析字符串
     *<br>返回类型：搜索结果的解析字符串
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
     *<br>方法说明：得到搜索结果的两个字符串中间的内容
     *<br>输入参数：strText 搜索结果字符串; strItem1 解析字符串1; strItem2 解析字符串2
     *<br>返回类型：字符串中间的内容
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

