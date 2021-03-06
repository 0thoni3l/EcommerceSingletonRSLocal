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

public class TxManagerDb2 {
	protected TxManagerDb2(){ }
	private static ThreadLocal<Connection> myContextThreadLocal= new ThreadLocal<Connection>();
	
	private static Map<String, DataSource> dst = new HashMap<>();


	public static void init(List<ConexionXml> lhBasicDataSourceConfig) throws NamingException {
		InitialContext cxt = new InitialContext();
		for (ConexionXml hBasicDataSourceConfig : lhBasicDataSourceConfig) {
			DataSource ds = (DataSource) cxt.lookup("java:/comp/env/"+hBasicDataSourceConfig.getJdbc());
			dst.put(hBasicDataSourceConfig.getTagXml(), ds);
			ds = null;
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
			conn = getConexion(lDb, false);
			
			myContextThreadLocal.set(conn);
		} catch (Exception ex) {
			throw new Exception(EnumGlobal.ERROR_INICIAR_TRANSACCION.getAction() + ex);
		}
		return conn;
	}

	private static Connection getConexion(List<String> lDb, boolean readOnly
			) throws SQLException {
		Connection conn = null;
		for(String conexion :lDb ){
			conn = obtenerConexion(conexion);
			if(conn != null){
				conn.setAutoCommit(false);
				conn.setReadOnly(readOnly);
				break;
			}
		}
		return conn;
	}

	private static Connection obtenerConexion(String conexion)
			 {
		Connection conn;
		try {
			conn = dst.get(conexion).getConnection();
		} catch (SQLException e) {
			conn = null;
		}
		return conn;
	}


	public static Connection beginQuery(List<String> lDb) throws Exception{
		Connection conn = null;
		try {
			conn = getConexion(lDb, true);
			myContextThreadLocal.set(conn);
		} catch (Exception ex) {
			throw new Exception(EnumGlobal.ERROR_DS_CONEXION_READONLY.getAction() + ex);
		}
		return conn;
	}

	public static Connection getConnection() throws Exception {
		Connection conn;
		try{
			conn = myContextThreadLocal.get();
		if (conn == null) {
			throw new Exception(EnumGlobal.ERROR_DS_CONECTION.getAction());
			}
		}catch(Exception ex){
			throw new Exception(EnumGlobal.ERROR_DS_CONECTION.getAction()+ex);
		}
		return conn;
	}

	public static void commit() throws Exception {
		Connection conn = null;
		try {
		
			conn = getConnection();
			conn.commit();
			closeConnection(conn);
				
		} catch (Exception ex) {
			throw new Exception(EnumGlobal.ERROR_DS_COMMIT.getAction()+ex);
		} 
	}

	public static void rollback() throws Exception {
		Connection conn = null;
		try {
			conn = getConnection();
			conn.rollback();
			closeConnection(conn);
		} catch (Exception ex) {
			throw new Exception(EnumGlobal.ERROR_DS_ROLLBACK.getAction()+ex);
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

