/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.coppel.singletonrslocal.configuracion;

/**
 *
 * @author manuel.paez
 */
public class Conexion {

	private String servidor;
	private String bd;
	private String puerto;
	private String usuario;
	private String password;
	private String app;
	private Integer timeOut;

	/**
	 * @return String
	 */
	public String getServidor() {
		return servidor;
	}

	/**
	 * @param servidor
	 *            the servidor to set
	 */
	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

	/**
	 * @return String
	 */
	public String getBd() {
		return bd;
	}

	/**
	 * @param bd
	 *            the bd to set
	 */
	public void setBd(String bd) {
		this.bd = bd;
	}

	/**
	 * @return String
	 */
	public String getPuerto() {
		return puerto;
	}

	/**
	 * @param puerto
	 *            the puerto to set
	 */
	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}

	/**
	 * @return String
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return String
	 */
	public String getApp() {
		return app;
	}

	/**
	 * 
	 * @param app
	 */
	public void setApp(String app) {
		this.app = app;
	}

	/**
	 * @return Integer
	 */
	public Integer getTimeOut() {
		return timeOut;
	}

	/**
	 * @param timeOut
	 */
	public void setTimeOut(Integer timeOut) {
		this.timeOut = timeOut;
	}
}
