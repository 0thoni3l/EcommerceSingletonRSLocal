package com.coppel.singletonrslocal.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Conexiones")
public class Conexiones {
	
	private List<ConexionXml> lstConexion = new ArrayList<>();
	@XmlElement(name="conexion")
	public List<ConexionXml> getLstConexion() {
		return lstConexion;
	}

	public void setLstConexion(List<ConexionXml> lstConexion) {
		this.lstConexion = lstConexion;
	}
}
