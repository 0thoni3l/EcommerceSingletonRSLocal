package com.coppel.singletonrslocal.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BasicDataSource")
public class BasicDataSourceConfig {
	private  String minIdle;
	private  String maxIdle;
	private  String maxOpenPreparedStatements;

	public String getClassNameDriver() {
		return classNameDriver;
	}
	public void setClassNameDriver(String classNameDriver) {
		this.classNameDriver = classNameDriver;
	}
	public String getUrlDriver() {
		return urlDriver;
	}
	public void setUrlDriver(String urlDriver) {
		this.urlDriver = urlDriver;
	}
	public String getUsernameDB() {
		return usernameDB;
	}
	public void setUsernameDB(String usernameDB) {
		this.usernameDB = usernameDB;
	}
	public String getPasswordDB() {
		return passwordDB;
	}
	public void setPasswordDB(String passwordDB) {
		this.passwordDB = passwordDB;
	}
	public String getDefaultQueryTimeOut() {
		return defaultQueryTimeOut;
	}
	public void setDefaultQueryTimeOut(String defaultQueryTimeOut) {
		this.defaultQueryTimeOut = defaultQueryTimeOut;
	}
	private   String classNameDriver;
	private  String urlDriver;
	private   String usernameDB;
	private   String passwordDB;
	private   String defaultQueryTimeOut;
	private  String maxTotal;
	private String nombreConexion;
	public String getNombreConexion() {
		return nombreConexion;
	}
	public void setNombreConexion(String nombreConexion) {
		this.nombreConexion = nombreConexion;
	}
	@XmlElement (name = "MaxTotal")
	public String getMaxTotal() {
		return maxTotal;
	}
	public void setMaxTotal(String maxTotal) {
		this.maxTotal = maxTotal;
	}
	@XmlElement (name = "MinIdle")
	public String getMinIdle() {
		return minIdle;
	}
	public  void setMinIdle(String value) {
		minIdle = value;
	}
	@XmlElement (name = "MaxIdle")
	public  String getMaxIdle() {
		return maxIdle;
	}
	public  void setMaxIdle(String value) {
		maxIdle = value;
	}
	@XmlElement (name = "MaxOpenPreparedStatements")
	public  String getMaxOpenPreparedStatements() {
		return maxOpenPreparedStatements;
	}
	public  void setMaxOpenPreparedStatements(String value) {
		maxOpenPreparedStatements = value;
	}
}
