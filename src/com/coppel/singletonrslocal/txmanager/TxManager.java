package com.coppel.singletonrslocal.txmanager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.coppel.singletonrslocal.dto.ConexionXml;
import com.coppel.singletonrslocal.enums.EnumGlobal;

public class TxManager {
	protected TxManager(){ }
	private static ThreadLocal<Map<String, Connection>> myContextThreadLocal= new ThreadLocal<Map<String, Connection>>();
	
	private static Map<String,DataSource> dst = new HashMap<>();

	public static void init(List<ConexionXml> lhBasicDataSourceConfig) throws NamingException, SQLException {
		InitialContext cxt = new InitialContext();
		for (ConexionXml hBasicDataSourceConfig : lhBasicDataSourceConfig) {
			DataSource dataSource = (DataSource) cxt.lookup("java:/comp/env/"+hBasicDataSourceConfig.getJdbc());
			dst.put(hBasicDataSourceConfig.getTagXml(), dataSource);
			dataSource = null;
			
		}
	}

	public static void close(String db)  throws Exception{
		try {
			obtenerConexion(db).close();
		} catch (Exception ex) {
			throw new Exception(EnumGlobal.ERROR_DS_CLOSE.getAction() + ex);
		}
	}

	public static Connection beginTx(List<String> lDb) throws Exception {
		Connection conn = null;
		try {
			Map<String, Connection> txCon = new HashMap<>();
			
			for(String conexion :lDb ){
				conn = obtenerConexion(conexion);
				conn.setAutoCommit(false);
				conn.setReadOnly(false);
				txCon.put(conexion, conn);
			}
			myContextThreadLocal.set(txCon);
		} catch (Exception ex) {
			throw new Exception(EnumGlobal.ERROR_INICIAR_TRANSACCION.getAction() + ex);
		}
		return conn;
	}

	private static Connection obtenerConexion(String conexion)
			throws SQLException {
		Connection conn;
		conn = dst.get(conexion).getConnection();
		return conn;
	}


	public static Connection beginQuery(List<String> lDb) throws Exception{
		Connection conn = null;
		try {
			Map<String, Connection> txCon = new HashMap<>();
			
			for(String conexion :lDb ){
				conn = obtenerConexion(conexion);
				conn.setAutoCommit(false);
				conn.setReadOnly(true);
				txCon.put(conexion, conn);
			}
			myContextThreadLocal.set(txCon);
		} catch (Exception ex) {
			throw new Exception(EnumGlobal.ERROR_DS_CONEXION_READONLY.getAction() + ex);
		}
		return conn;
	}

	public static Connection getConnection(String db) throws Exception {
		Connection conn;
		try{
			conn = myContextThreadLocal.get().get(db);
		if (conn == null) {
			throw new Exception(EnumGlobal.ERROR_DS_CONECTION.getAction());
			}
		}catch(Exception ex){
			throw new Exception(EnumGlobal.ERROR_DS_CONECTION.getAction()+ex);
		}
		return conn;
	}

	public static void commit(List<String> lDb) throws Exception {
		Connection conn = null;
		String db = "";
		try {
			for(String conexion :lDb ){
				db = conexion;
				conn = getConnection(conexion);
				conn.commit();
				closeConnection(conn);
			}
			
		} catch (Exception ex) {
			throw new Exception(EnumGlobal.ERROR_DS_COMMIT.getAction()+" "+db+" "+ex);
		} 
	}
	public static void commit(String db) throws Exception {
		Connection conn = null;
		try {
			conn = getConnection(db);
			conn.commit();
			closeConnection(conn);
		} catch (Exception ex) {
			throw new Exception(EnumGlobal.ERROR_DS_COMMIT.getAction()+ex);
		} 
	}
	public static void rollback(String db) throws Exception {
		Connection conn = null;
		try {
			conn = getConnection(db);
			conn.rollback();
			closeConnection(conn);
		} catch (Exception ex) {
			throw new Exception(EnumGlobal.ERROR_DS_ROLLBACK.getAction()+ex);
		}
	}
	public static void rollback(List<String> lDb) throws Exception {
		Connection conn = null;
		try {
			for(String conexion :lDb ){
				conn = getConnection(conexion);
				conn.rollback();
				closeConnection(conn);
			}
			
		} catch (Exception ex) {
			throw new Exception(EnumGlobal.ERROR_DS_COMMIT.getAction()+ex);
		}
	}
	
	public static void closeConnection(Connection conn) throws Exception{
		try {
			if(!conn.isClosed() || conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			throw new Exception(EnumGlobal.ERROR.getAction()+e);
		}
	}
}

