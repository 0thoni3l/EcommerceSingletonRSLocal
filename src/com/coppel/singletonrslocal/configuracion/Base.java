package com.coppel.singletonrslocal.configuracion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Base {

	/**
	 * 
	 * genera la url de conexi�n a base de datos postgres
	 * 
	 * @param dataConnection
	 * @return String
	 */
	public String getUrlConexionPostgres(Conexion dataConnection) {
		return "jdbc:postgresql://" + dataConnection.getServidor() + ":"
				+ dataConnection.getPuerto() + "/" + dataConnection.getBd()
				+ "?ApplicationName=" + dataConnection.getApp();
	}
	public String getUrlConexionPostgresBg(Conexion dataConnection) {
		return "jdbc:postgresql://" + dataConnection.getServidor() + ":"
				+ dataConnection.getPuerto() + "/" + dataConnection.getBd()
				+ "?ApplicationName=" + dataConnection.getApp()+"&prepareThreshold=0";
	}
	public String getUrlConexionDb2(Conexion dataConnection) {
        return "jdbc:db2://" + dataConnection.getServidor() + ":" + dataConnection.getPuerto()
            + "/" + dataConnection.getBd();
      }
	public void destruirVariablesDAO(ResultSet rSet,CallableStatement callableStatement) throws Exception {
		try {
			if(rSet !=null){
				rSet.close();
			}
			if(callableStatement!=null){
				callableStatement.close();
			}
		} catch (SQLException e) {
			throw new Exception(e);
		}
	}
	
	public void destruirVariablesDAO(Connection con,ResultSet rSet,CallableStatement callableStatement) throws Exception {
		try {
			if(con!=null){
				con.close();
			}
			if(rSet !=null){
				rSet.close();
			}
			if(callableStatement!=null){
				callableStatement.close();
			}
		} catch (SQLException e) {
			throw new Exception(e);
		}
	}
	public void destruirVariablesDAO(ResultSet rSet,Statement statement) throws Exception {
		try {
			if(rSet !=null){
				rSet.close();
			}
			if(statement!=null){
				statement.close();
			}
		} catch (SQLException e) {
			throw new Exception(e);
		}
	}


}