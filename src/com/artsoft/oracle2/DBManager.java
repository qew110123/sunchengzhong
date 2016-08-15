package com.artsoft.oracle2;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.druid.pool.DruidDataSource;

public class DBManager {

	private static Logger log = Logger.getLogger(DBManager.class);
	private static DBManager dbm;

	private DruidDataSource ds;

	static {
		dbm = new DBManager();
		dbm.init();
	}

	public static DBManager instance() {
		return dbm;
	}

	public DBManager() {
		super();
	}

	private void init() {
		ProParseUtil util = new ProParseUtil("jdbc");
		ds = new DruidDataSource();

		ds.setDriverClassName(util.getValue("jdbc.driverClassName"));
		ds.setUrl(util.getValue("jdbc.url"));
		ds.setUsername(util.getValue("jdbc.username"));
		ds.setPassword(util.getValue("jdbc.password"));

		ds.setInitialSize(Integer.parseInt(util.getValue("initialSize")));
		ds.setMaxActive(Integer.parseInt(util.getValue("maxActive")));
		ds.setMinIdle(Integer.parseInt(util.getValue("minIdle")));

		ds.setMaxWait(Long.parseLong(util.getValue("maxWait")));
		ds.setTimeBetweenConnectErrorMillis(Long.parseLong(util.getValue("timeBetweenEvictionRunsMillis")));
		ds.setMinEvictableIdleTimeMillis(Long.parseLong(util.getValue("minEvictableIdleTimeMillis")));

		ds.setValidationQuery("SELECT 'x' FROM DUAL");
		ds.setTestWhileIdle(true);
		ds.setTestOnBorrow(false);
		ds.setTestOnReturn(false);

		ds.setPoolPreparedStatements(false);

		ds.setDefaultAutoCommit(true);

		String useStat = util.getValue("useStat");
		if (Boolean.parseBoolean(useStat)) {
			try {
				ds.setFilters("stat");
				// ds.setFilters("stat,log4j");
			} catch (SQLException e) {
				log.error("连接池配置出错", e);
			}
		}

	}
	
	/**
	 * 预留一个执行sql的方法
	 * 
	 * @param sql
	 * @return
	 */
	public Long executeInsertSQL(String sql,String nameId) {
		Long result =0L;
		try {
			PreparedStatement stmt = null;
			Connection conn = ds.getConnection();
			String generatedColumns[] = {nameId};
//			stmt = conn.prepareStatement(sql);
			stmt = conn.prepareStatement(sql, generatedColumns);
			stmt.executeUpdate();
			ResultSet rs =stmt.getGeneratedKeys();
			if (rs.next()) {
                result = rs.getLong(1); 
                System.out.println("数据主键：" + rs.getLong(1)); 
            }
			log.info(sql);
			close(null, stmt, conn);
		} catch (SQLException e) {
			log.info(sql);
			log.error("executeSQL出错", e);
		}
		return result;
	}

	/**
	 * 预留一个执行sql的方法
	 * 
	 * @param sql
	 * @return
	 */
	public boolean executeSQL(String sql) {
		boolean result = false;
		try {
			PreparedStatement stmt = null;
			Connection conn = ds.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
//			log.info(sql);
			close(null, stmt, conn);
			result = true;
		} catch (SQLException e) {
			log.info(sql);
			log.error("executeSQL出错", e);
		}
		return result;
	}

	public List<Map<String, String>> queryToList(String sql) {
		Date date1 = new Date();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Date date2 = new Date();
			long temp = date2.getTime() - date1.getTime();
			log.info("[" + temp + "ms]" + sql);
			if (temp >= 1000) {
				log.info("---------------Query Cost=" + temp + "ms");
			}
			if (rs != null) {
				ResultSetMetaData rsm = rs.getMetaData();
				int rowColumn = rsm.getColumnCount();
				while (rs.next()) {
					Map<String, String> map = new LinkedHashMap<String, String>();
					for (int i = 1; i <= rowColumn; i++) {
						map.put(rsm.getColumnName(i).toLowerCase(), rs.getString(i));
					}
					list.add(map);
				}
			}
			close(rs, stmt, conn);
		} catch (SQLException e) {
			log.info(sql);
			log.error("queryToList出错", e);
		}
		return list;
	}

	public List<Map<String, Object>> query(String sql) {
		Date date1 = new Date();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Date date2 = new Date();
			long temp = date2.getTime() - date1.getTime();
			log.info("[" + temp + "ms]" + sql);
			if (temp >= 1000) {
				log.info("---------------Query Cost=" + temp + "ms");
			}
			if (rs != null) {
				ResultSetMetaData rsm = rs.getMetaData();
				int rowColumn = rsm.getColumnCount();
				while (rs.next()) {
					Map<String, Object> map = new CaseIgnoreMap<String, Object>();
					for (int i = 1; i <= rowColumn; i++) {
						map.put(rsm.getColumnName(i).toLowerCase(), rs.getObject(i)); // 不加toLowerCase()，字段名也是小写。这里确保一下。
					}
					list.add(map);
				}
			}
			close(rs, stmt, conn);
		} catch (SQLException e) {
			log.info(sql);
			log.error("query出错", e);
		}
		return list;
	}
	
	/**
	 * 调用存储过程
	 * 
	 * @param sql
	 * @return
	 */
	public Long executeCall(String procedureSql) {
		Long result =0L;
		try {
			PreparedStatement stmt = null;
			Connection conn = ds.getConnection();
			
			CallableStatement proc = conn.prepareCall(procedureSql);
			proc.execute();
			log.info(procedureSql);
			close(null, stmt, conn);
		} catch (SQLException e) {
			log.info(procedureSql);
			log.error("executeSQL出错", e);
		}
		return result;
	}

	public Map<String, String> queryToMap(String sql) {
		// log.info(sql);

		List<Map<String, String>> list = queryToList(sql);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public int queryToInt(String sql) {
		log.info(sql);

		int num = 0;
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					num = rs.getInt(1);
					break;
				}
			}
			close(rs, stmt, conn);
		} catch (SQLException e) {
			log.error("queryToInt出错", e);
		}
		return num;
	}

	// 增删改方法
	public int updateUser(String sql, String userGroupId, String type) {
		int id = 0;
		String sql_id = null;
		if ("0".equals(type)) {
			sql_id = "SELECT nextval('user_group_id_seq')";
		} else if ("1".equals(type)) {
			sql_id = "SELECT nextval('user_id_seq')";
		}
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = ds.getConnection();
			if (StringUtil.isEmpty(userGroupId)) {
				stmt = conn.prepareStatement(sql_id);
				ResultSet rs = stmt.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						id = rs.getInt(1);
						break;
					}
				}
				stmt = conn.prepareStatement(sql);
				stmt.executeUpdate();
			} else {
				stmt = conn.prepareStatement(sql);
				stmt.executeUpdate();
			}
			close(null, stmt, conn);
		} catch (SQLException e) {
			log.error("updateUser出错", e);
		}
		if (id == 0) {
			id = Integer.parseInt(userGroupId);
		}
		return id;
	}
	public int update(String sql) {
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
			return 1;
		} catch (SQLException e) {
			log.error("update出错", e);
			return 0;
		} finally {
			log.info(sql);
			close(null, stmt, conn);
		}
	}
	public int batchUpdate(String sql) {
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			stmt.addBatch(sql);
			stmt.executeBatch();
			stmt.clearBatch();
			return 1;
		} catch (SQLException e) {
			log.error("update出错", e);
			return 0;
		} finally {
			log.info(sql);
			close(null, stmt, conn);
		}
	}
	
	public void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			log.error("close出错", e);
		}
	}
	public void close(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			log.error("close出错", e);
		}
	}

	private void close() {
		if (ds != null) {
			try {
				ds.close();
				ds = null;
			} catch (Exception e) {
				log.error("close出错", e);
			}
		}
	}

	static void closeDS() {
		if (dbm != null) {
			dbm.close();
		}
		dbm = null;
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			init();
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public Connection getConnection(DBManager dbm) {
		Connection conn = null;
		try {
			init();
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
