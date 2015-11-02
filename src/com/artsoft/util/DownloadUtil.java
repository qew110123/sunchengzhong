package com.artsoft.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class DownloadUtil {

	public static String[] strTypes = { "pdf", "ps", "dwf", "kml", "kmz", "xls", "ppt", "doc", "rtf", "swf" };

	public static boolean isHtml(String strFileType) {
		if (strFileType == null)
			return false;

		// �ж��ļ���չ��
		strFileType = strFileType.toLowerCase();
		for (int i = 0; i < strTypes.length; ++i) {
			if (strFileType.compareTo(strTypes[i]) == 0) {
				return false;
			}
		}
		return true;
	}

	public static String getFileType(String strUrl) {

		int index = strUrl.lastIndexOf('.');

		if (index < 0)
			return "web";

		return strUrl.substring(index);

	}

	public static byte[] downBinaryFileEx(String strUrl, int timeout, Proxy proxyData) {
		return downBinaryFile(strUrl, timeout, proxyData);
	}

	public static boolean saveBinaryFile(String strUrl) {
		// SearchSetting searchSetting =
		// (SearchSetting)SystemSetting.mapSearchSetting.get(fileSetting.getSEARCH_ID());
		Proxy proxy = null;

		// String strUrl = fileSetting.getURL();
		File outFile = new File("D:/save");

		boolean bFlag = true;
		if (outFile.exists()) {
			return bFlag;
		}
		if (!outFile.getParentFile().exists()) {
			outFile.getParentFile().mkdirs();
		}
		boolean isMemoryError = false;
		BufferedOutputStream osw = null;
		DataOutputStream dos = null;
		FileOutputStream fio = null;
		BufferedInputStream in = null;
		InputStream urlStream = null;
		HttpURLConnection httpConnection = null;
		try {
			URL url = new URL(strUrl);
			if (proxy != null) {
				httpConnection = (HttpURLConnection) url.openConnection(proxy);
			} else {
				httpConnection = (HttpURLConnection) url.openConnection();
			}
			httpConnection.addRequestProperty("User-Agent", "IcewolfHttp/1.0");
			httpConnection.addRequestProperty("Accept", "www/source; text/html; image/gif; */*");
			httpConnection.addRequestProperty("Accept-Language", "");
			int timeout = 30 * 1000;
			httpConnection.setConnectTimeout(timeout);
			httpConnection.setReadTimeout(timeout);
			urlStream = httpConnection.getInputStream();
			String strEncoding = httpConnection.getContentEncoding();
			if (strEncoding != null && strEncoding.compareTo("gzip") == 0) {
				urlStream = new java.util.zip.GZIPInputStream(urlStream);
			}
			in = new BufferedInputStream(urlStream);
			fio = new FileOutputStream(outFile);
			dos = new DataOutputStream(fio);
			osw = new BufferedOutputStream(dos);
			int b;
			byte[] a = new byte[1024];
			while ((b = in.read(a)) != -1) {
				osw.write(a, 0, b);
				osw.flush();
			}
		} catch (java.lang.OutOfMemoryError out) {
			out.printStackTrace();
			System.out.println(out.getClass() + "������ҳ" + strUrl + "ʧ��");
			bFlag = false;
			isMemoryError = true;
		} catch (Exception e) {
			bFlag = false;
		} finally {
			try {
				if (httpConnection != null) {
					httpConnection.disconnect();
					httpConnection = null;
				}
				if (osw != null) {
					osw.close();
					osw = null;
				}
				if (dos != null) {
					dos.close();
					dos = null;
				}
				if (fio != null) {
					fio.close();
					fio = null;
				}
				if (in != null) {
					in.close();
					in = null;
				}
				if (urlStream != null) {
					urlStream.close();
					urlStream = null;
				}
				if (isMemoryError)
					System.gc();
			} catch (Exception e) {
				bFlag = false;
				e.printStackTrace();
			}
		}
		return bFlag;
	}

	public static boolean saveBinaryFile(String strUrl, int timeout, File file, Proxy proxy) {
		boolean bFlag = true;
		if (file.exists()) {
			return bFlag;
		}
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		boolean isMemoryError = false;
		BufferedOutputStream osw = null;
		DataOutputStream dos = null;
		FileOutputStream fio = null;
		BufferedInputStream in = null;
		InputStream urlStream = null;
		HttpURLConnection httpConnection = null;
		try {
			URL url = new URL(strUrl);
			if (proxy != null) {
				httpConnection = (HttpURLConnection) url.openConnection(proxy);
			} else {
				httpConnection = (HttpURLConnection) url.openConnection();
			}
			httpConnection.addRequestProperty("User-Agent", "IcewolfHttp/1.0");
			httpConnection.addRequestProperty("Accept", "www/source; text/html; image/gif; */*");
			httpConnection.addRequestProperty("Accept-Language", "");
			httpConnection.setConnectTimeout(timeout);
			httpConnection.setReadTimeout(timeout);
			urlStream = httpConnection.getInputStream();
			String strEncoding = httpConnection.getContentEncoding();
			if (strEncoding != null && strEncoding.compareTo("gzip") == 0) {
				urlStream = new java.util.zip.GZIPInputStream(urlStream);
			}
			in = new BufferedInputStream(urlStream);
			fio = new FileOutputStream(file);
			dos = new DataOutputStream(fio);
			osw = new BufferedOutputStream(dos);
			int b;
			byte[] a = new byte[1024];
			while ((b = in.read(a)) != -1) {
				osw.write(a, 0, b);
				osw.flush();
			}
		} catch (java.lang.OutOfMemoryError out) {
			out.printStackTrace();
			System.out.println(out.getClass() + "������ҳ" + strUrl + "ʧ��");
			bFlag = false;
			isMemoryError = true;
		} catch (Exception e) {
			bFlag = false;
		} finally {
			try {
				if (httpConnection != null) {
					httpConnection.disconnect();
					httpConnection = null;
				}
				if (osw != null) {
					osw.close();
					osw = null;
				}
				if (dos != null) {
					dos.close();
					dos = null;
				}
				if (fio != null) {
					fio.close();
					fio = null;
				}
				if (in != null) {
					in.close();
					in = null;
				}
				if (urlStream != null) {
					urlStream.close();
					urlStream = null;
				}
				if (isMemoryError)
					System.gc();
			} catch (Exception e) {
				bFlag = false;
				e.printStackTrace();
				// System.out.println(e.getClass() + "������ҳ" + strUrl +
				// "���ӹر�ʧ��");
			}
		}
		return bFlag;
	}

	/**
	 * <br>
	 * ����˵�������ض������ļ�������Ϊ�ֽ����� <br>
	 * ���������strUrl ��������; proxy ������� <br>
	 * �������ͣ��ֽ�����
	 */
	// public static byte[] downBinaryFile(String strUrl, ProxyData proxydata) {
	// int timeout = SystemSetting.getInstance().getLINK_OUTTIME() * 1000;
	// return downBinaryFileEx(strUrl, timeout, proxydata);
	// }

	// public static byte[] downBinaryFileEx(String strUrl, int timeout,
	// ProxyData proxydata) {
	// if (proxydata == null) {
	// return downBinaryFile(strUrl, timeout, null);
	// }
	// int type = proxydata.type;
	// switch (type) {
	// case 0:
	// return downBinaryFile(strUrl, timeout, null);
	// case -1:
	// for (int i = 0; i < proxydata.proxylist.size(); ++i) {
	// String strProxy = proxydata.proxylist.get(i);
	// Proxy proxy = Golbal.getGolbal().proxyset.getProxy(strProxy);
	// byte[] ret = downBinaryFile(strUrl, timeout, proxy);
	// if (ret != null) {
	// return ret;
	// }
	// }
	// return null;
	// default:
	// Proxy proxy = proxydata.currentProxy;
	// return downBinaryFile(strUrl, timeout, proxy);
	// }
	// }

	public static byte[] downBinaryFile(String strUrl, int timeout, Proxy proxy) {
		byte[] ret = null;
		boolean isMemoryError = false;
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		InputStream urlStream = null;
		HttpURLConnection httpConnection = null;
		BufferedInputStream in = null;
		URL url = null;
		try {
			url = new URL(strUrl);
			if (proxy != null) {
				httpConnection = (HttpURLConnection) url.openConnection(proxy);
			} else {
				httpConnection = (HttpURLConnection) url.openConnection();
			}
			httpConnection.addRequestProperty("User-Agent", "IcewolfHttp/1.0");
			httpConnection.addRequestProperty("Accept", "www/source; text/html; image/gif; */*");
			httpConnection.addRequestProperty("Accept-Language", "");
			httpConnection.setConnectTimeout(timeout);
			httpConnection.setReadTimeout(timeout);

			urlStream = httpConnection.getInputStream();
			String strEncoding = httpConnection.getContentEncoding();

			if (strEncoding != null && strEncoding.compareTo("gzip") == 0) {
				urlStream = new java.util.zip.GZIPInputStream(urlStream);
			}
			in = new BufferedInputStream(urlStream);
			int b;
			byte[] a = new byte[1024];
			while ((b = in.read(a)) != -1) {
				bytes.write(a, 0, b);
			}
			ret = bytes.toByteArray();

		} catch (java.lang.OutOfMemoryError out) {
			out.printStackTrace();
			System.out.println(out.getClass() + "������ҳ" + strUrl + "ʧ��");
			isMemoryError = true;
		} catch (Exception e) {

		} finally {
			try {
				if (httpConnection != null) {
					httpConnection.disconnect();
					httpConnection = null;
				}
				if (in != null) {
					in.close();
					in = null;
				}
				if (urlStream != null) {
					urlStream.close();
					urlStream = null;
				}
				if (bytes != null) {
					bytes.close();
					bytes = null;
				}
				if (isMemoryError)
					System.gc();
			} catch (Exception e) {
				// System.out.println(e.getClass() + "������ҳ��Դ" + strUrl +
				// "���ӹر�ʧ��");
			}
		}
		return ret;
	}

	/**
	 * <br>
	 * ����˵�����õ�html�ı� <br>
	 * ���������strUrl ��������; strEnCoding ��ҳ����; proxy ������� <br>
	 * �������ͣ�html�ı�
	 */
	// public static String getHtmlText(String strUrl, String strEnCoding,
	// ProxyData proxydata) {
	// int timeout = SystemSetting.getInstance().getLINK_OUTTIME() * 1000;
	// if (proxydata == null) {
	// return getHtmlText(strUrl, timeout, strEnCoding, null, null);
	// }
	// int type = proxydata.type;
	// switch (type) {
	// case 0:
	// return getHtmlText(strUrl, timeout, strEnCoding, null, null);
	// case -1:
	// for (int i = 0; i < proxydata.proxylist.size(); ++i) {
	// String strProxy = proxydata.proxylist.get(i);
	// Proxy proxy = Golbal.getGolbal().proxyset.getProxy(strProxy);
	// String ret = getHtmlText(strUrl, timeout, strEnCoding, null,proxy);
	// if (ret != null) {
	// return ret;
	// }
	// }
	// return null;
	// default:
	// Proxy proxy = proxydata.currentProxy;
	// return getHtmlText(strUrl, timeout, strEnCoding, null, proxy);
	// }
	// }

	/**
	 * <pre>
	 * �õ���ҳԴ����
	 * </pre>
	 * 
	 * @version 1.0, 2006-08-10
	 * @param strUrl
	 *            ��ַ�ַ���
	 * @param timeout
	 *            ��ʱ����
	 * @param strEnCoding
	 *            ��ҳ����
	 * @param cookies
	 *            ��ҳcookies
	 * @param proxy
	 *            ��������
	 * @return ��ҳԴ�����ַ���
	 * @author dl
	 */
	public static String getHtmlText(String strUrl, int timeout, String strEnCoding, String cookies, Proxy proxy) {
		if (strUrl == null || strUrl.length() == 0) {
			return null;
		}
		boolean isMemoryError = false;
		StringBuffer strHtml = null;
		String strLine = "";
		HttpURLConnection httpConnection = null;
		InputStream urlStream = null;
		BufferedInputStream buff = null;
		BufferedReader br = null;
		Reader r = null;
		boolean isError = false;
		try {
			// ��������õ���ҳԴ����
			URL url = new URL(strUrl);
			if (proxy != null) {
				httpConnection = (HttpURLConnection) url.openConnection(proxy);
			} else {
				httpConnection = (HttpURLConnection) url.openConnection();

			}

			httpConnection.addRequestProperty("User-Agent", "Mozilla/4.0");
			httpConnection.addRequestProperty("Accept", "www/source; text/html; image/gif; */*");
			httpConnection.addRequestProperty("Accept-Language", "zh-cn,zh;q=0.5");

			if (proxy == null && strEnCoding != null) {
				httpConnection.addRequestProperty("Accept-Charset", strEnCoding);
			}
			if (cookies != null) {
				httpConnection.setRequestProperty("Cookie", cookies);
			}

			httpConnection.setConnectTimeout(timeout);
			httpConnection.setReadTimeout(timeout);
			urlStream = httpConnection.getInputStream();
			buff = new BufferedInputStream(urlStream);
			if (strEnCoding == null || strEnCoding.compareTo("null") == 0) {
				r = new InputStreamReader(buff);
			} else {
				try {
					r = new InputStreamReader(buff, strEnCoding);
				} catch (UnsupportedEncodingException e) {
					r = new InputStreamReader(buff);
				}
			}

			br = new BufferedReader(r);
			strHtml = new StringBuffer("");
			while ((strLine = br.readLine()) != null) {
				strHtml.append(strLine + "\r\n");
			}

		} catch (java.lang.OutOfMemoryError out) {
			out.printStackTrace();
			System.out.println(out.getClass() + "������ҳ" + strUrl + "ʧ��");
			isError = true;
			isMemoryError = true;
		} catch (Exception e) {
			// e.printStackTrace();
			isError = true;
		} finally {
			try {
				if (httpConnection != null) {
					httpConnection.disconnect();
					httpConnection = null;
				}
				if (br != null) {
					br.close();
					br = null;
				}
				if (r != null) {
					r.close();
					r = null;
				}
				if (buff != null) {
					buff.close();
					buff = null;
				}
				if (isMemoryError)
					buff = null;
				if (urlStream != null) {
					urlStream.close();
					urlStream = null;
				}
				if (isMemoryError)
					System.gc();
			} catch (Exception e) {
				// System.out.println(e.getClass() + "������ҳ" + strUrl +
				// "���ӹر�ʧ��");
				return null;
			}
		}

		if (strHtml == null || isError)
			return null;
		if (isMemoryError)
			return null;
		return FormatUtil.fromNCR(strHtml.toString());
	}

	/**
	 * post ������� ��ҳ��Ϣ
	 * 
	 * @param strUrl
	 * @param timeout
	 * @param cookies
	 * @param proxy
	 * @return
	 */
	public static String getHtmlTextNew(String strUrl, int timeout, String strEncoding, String cookies, Proxy proxy) {
		int pos = strUrl.indexOf("?");
		String strPostUrl = strUrl.substring(0, pos);
		String strPostData = strUrl.substring(pos + 1, strUrl.length());

		URL url = null;
		HttpURLConnection httpurlconnection = null;
		InputStream urlStream = null;
		BufferedInputStream buff = null;
		BufferedReader br = null;
		InputStreamReader r = null;
		String strRet = null;
		try {
			url = new URL(strPostUrl);
			httpurlconnection = (HttpURLConnection) url.openConnection();
			httpurlconnection.setConnectTimeout(timeout);
			httpurlconnection.setReadTimeout(timeout);
			httpurlconnection.setDoOutput(true);
			httpurlconnection.setRequestMethod("POST");
			httpurlconnection.getOutputStream().write(strPostData.getBytes());
			httpurlconnection.getOutputStream().flush();
			httpurlconnection.getOutputStream().close();
			// System.out.println(httpurlconnection.getContentType()+"---------");
			urlStream = httpurlconnection.getInputStream();
			buff = new BufferedInputStream(urlStream);
			r = new InputStreamReader(buff, "gb2312");
			br = new BufferedReader(r);
			String strLine = null;
			StringBuffer strHtml = new StringBuffer();
			while ((strLine = br.readLine()) != null) {
				strHtml.append(strLine + "\r\n");
			}
			strRet = strHtml.toString();
		} catch (Exception e) {
			System.out.println("��ҳ����ʧ�ܣ�  " + strUrl);
		} finally {
			try {
				if (httpurlconnection != null)
					httpurlconnection.disconnect();
				if (br != null) {
					br.close();
					br = null;
				}
				if (r != null) {
					r.close();
					r = null;
				}
				if (buff != null) {
					buff.close();
					buff = null;
				}
				if (urlStream != null) {
					urlStream.close();
					urlStream = null;
				}
			} catch (Exception e) {
			}
		}

		return strRet;
	}

	public static String readHtml(String strUrl, int timeout, String strEnCoding, String cookies, Proxy proxy) {
		if (strUrl == null || strUrl.length() == 0) {
			return null;
		}
		String strHtml = null;
		HttpClient client = new HttpClient();
		HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams();

		// �������ӳ�ʱʱ��(��λ����)
		managerParams.setConnectionTimeout(timeout);
		// ���ö����ݳ�ʱʱ��(��λ����)
		managerParams.setSoTimeout(timeout);
		managerParams.setDefaultMaxConnectionsPerHost(32);
		managerParams.setMaxTotalConnections(256);

		if (strEnCoding != null) {
			client.getParams().setContentCharset(strEnCoding);
			client.getParams().setHttpElementCharset(strEnCoding);
		}
		if (proxy != null) {
			HostConfiguration hcf = new HostConfiguration();
			InetSocketAddress address = (InetSocketAddress) proxy.address();
			hcf.setProxy(address.getHostName(), address.getPort());
			client.setHostConfiguration(hcf);
		}

		GetMethod methodget = new GetMethod(strUrl);
		methodget.setFollowRedirects(true);
		methodget.getParams().setParameter(HttpMethodParams.SINGLE_COOKIE_HEADER, true);

		try {
			client.executeMethod(methodget);
			InputStream iStream = methodget.getResponseBodyAsStream();
			strHtml = convertStreamToString(iStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		methodget.releaseConnection();
		return strHtml;
	}

	private static String convertStreamToString(InputStream is) throws UnsupportedEncodingException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "gbk"));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static StringBuffer getContent(URL url) {
		StringBuffer contentBuffer = new StringBuffer();
		int responseCode = -1;
		HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			// IE�����������
			con.setConnectTimeout(60000);
			con.setReadTimeout(60000);
			// �����ҳ������Ϣ��
			responseCode = con.getResponseCode();
			if (responseCode == -1) {
				System.out.println(url.toString() + " : connection is failure...");
				con.disconnect();
				return null;
			}
			System.out.println(url.toString() + " #get response code: " + responseCode);
			if (responseCode >= 400) {
				System.out.println("����ʧ��:get response code: " + responseCode);
				con.disconnect();
				return null;
			}
			InputStream inStr = con.getInputStream();
			InputStreamReader istreamReader = new InputStreamReader(inStr);
			BufferedReader buffStr = new BufferedReader(istreamReader);
			String str = null;
			while ((str = buffStr.readLine()) != null)
				contentBuffer.append(str);
			inStr.close();
		} catch (IOException e) {
			e.printStackTrace();
			contentBuffer = null;
			System.out.println("error: " + url.toString());
		} finally {
			con.disconnect();
		}
		return contentBuffer;
	}

	/**
	 * ��unicode����ת���ɺ���
	 * 
	 * @param theString
	 * @return
	 */
	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		try {
			for (int x = 0; x < len;) {
				aChar = theString.charAt(x++);
				if (aChar == '\\') {
					aChar = theString.charAt(x++);
					if (aChar == 'u') {
						int value = 0;
						for (int i = 0; i < 4; i++) {
							aChar = theString.charAt(x++);
							switch (aChar) {
							case '0':
							case '1':
							case '2':
							case '3':
							case '4':
							case '5':
							case '6':
							case '7':
							case '8':
							case '9':
								value = (value << 4) + aChar - '0';
								break;
							case 'a':
							case 'b':
							case 'c':
							case 'd':
							case 'e':
							case 'f':
								value = (value << 4) + 10 + aChar - 'a';
								break;
							case 'A':
							case 'B':
							case 'C':
							case 'D':
							case 'E':
							case 'F':
								value = (value << 4) + 10 + aChar - 'A';
								break;
							default:
								throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
							}

						}
						outBuffer.append((char) value);
					} else {
						if (aChar == 't')
							aChar = '\t';
						else if (aChar == 'r')
							aChar = '\r';
						else if (aChar == 'n')
							aChar = '\n';
						else if (aChar == 'f')
							aChar = '\f';
						outBuffer.append(aChar);
					}
				} else
					outBuffer.append(aChar);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return theString;
		}
		return outBuffer.toString();
	}

}
