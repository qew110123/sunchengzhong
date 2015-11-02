package com.artsoft.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.artsoft.config.ConfigManager;

public class DBOperate {
	
	public static DBOperate dbObj = null;
	private static String driver = null;
	private static String url = null;
	private static String user = null;
	private static String pwd = null;
	
	public Connection conn = null;

	private DBOperate() {
		ConfigManager config = ConfigManager.getInstance();
		driver = config.getConfigValue("driver");
		url = config.getConfigValue("url");
		user = config.getConfigValue("user");
		pwd = config.getConfigValue("pwd");		
		conn = getConn();
	}
	
	public static DBOperate getInstance() {
		if(dbObj == null) {
			dbObj = new DBOperate();
		}
		return dbObj;
	}
	
	public Connection getConnection() {
		try {
			if (conn == null || conn.isClosed())
				conn = getConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * �õ����ݿ�����
	 * @return ���ݿ�����				
	 */
	public Connection getConn() {
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, user, pwd);
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
		
	/**
	 * ��ȡ������ 
	 * @return
	 */
	public String getSequence(Connection conn,String strSable){
		
		String strSql = "select "+strSable+".NEXTVAL from dual";
		String strId = (String)getResultValue(conn,strSql);
		return strId;
	}
	
	/**
	 * ִ�в�ѯ���
	 */
	public String getResultValue(Connection conn,String strSql) {
		PreparedStatement select_stm = null;
		ResultSet result = null;
		try {
			String strValue = "-1";
			select_stm = conn.prepareStatement(strSql,java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY);
			result = select_stm.executeQuery();
			if (result.next()) {
				strValue = result.getString(1);
			}
			
			return strValue;
		} catch (Exception e) {
			e.printStackTrace();
			return "-1";
		}finally {
			try {
				if (result != null)
					result.close();
				if (select_stm != null)
					select_stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * ���ݿ���Ӳ���
	 * @param conn		���ݿ�����
	 * @param strSql	ִ�����
	 */
	public boolean executeRecord(Connection conn,String strSql){
		PreparedStatement insert_stm = null;
		
		try{
			insert_stm=conn.prepareStatement(strSql);
			insert_stm.executeUpdate();
			return true;
		}catch(Exception e){
			return false;
		}finally {
			try {
				if (insert_stm != null)
					insert_stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * �������(�����˶���ֶ�����)
	 * @param Conn		���ݿ�����
	 * @param strSql	ִ�����
	 * @param list		ֵ�б�
	 */
	public static boolean insertRecord(Connection conn,String strSql,List list){
		PreparedStatement stmt = null;
		try{
			stmt = conn.prepareStatement(strSql);
			for (int i=0; i<list.size(); i++) {
				String strVal = list.get(i).toString();
				stmt.setString(i+1,strVal);
			}
			stmt.executeUpdate();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<String> getResultListValue(Connection conn,String strSql,int iNum){
		PreparedStatement select_stm = null;
		ResultSet result = null;
		try{
			select_stm=conn.prepareStatement(
					strSql,java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,
					java.sql.ResultSet.CONCUR_READ_ONLY);
			result=select_stm.executeQuery();
			ArrayList<String> listTemp = new ArrayList<String>();
			if(result.next()){
				for(int i=0;i<iNum;i++){
					listTemp.add(result.getString(i+1));
				}
			}
			return listTemp;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally {
			try {
				if (result != null)
					result.close();
				if (select_stm != null)
					select_stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ִ�в�ѯ���(������ڶ���ֶε�����)
	 * @param Conn				���ݿ�����
	 * @param strSql			ִ�����
	 * @param iNum				��ѯ����
	 * @return					��ѯ�������
	 */
	public static List getResultList(Connection conn,String strSql,int iNum){
		PreparedStatement select_stm = null;
		ResultSet result = null;
		try{
			List list = new ArrayList();
			select_stm=conn.prepareStatement(
					strSql,java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,
					java.sql.ResultSet.CONCUR_READ_ONLY);
			result=select_stm.executeQuery();
			while(result.next()){
				List listTemp = new ArrayList();
				for(int i=0;i<iNum;i++){
					listTemp.add(result.getString(i+1));
				}
				list.add(listTemp);
			}
			
			//�رղ�ѯ����
			if(result != null)
				result.close();
			
			if(select_stm != null)
				select_stm.close();
			
			return list;
		}catch(Exception e){
			return null;
		}finally {
			try {
				if (result != null)
					result.close();
				if (select_stm != null)
					select_stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
