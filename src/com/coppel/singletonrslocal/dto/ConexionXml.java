package com.coppel.singletonrslocal.dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "conexion")
public class ConexionXml {
	private String jdbc;	
	private String tagXml;
	
	@XmlElement (name = "jdbc")
	public String getJdbc() {
		return jdbc;
	}
	public void setJdbc(String value) {
		this.jdbc = value;
	}
	
	@XmlAttribute (name = "name")	
	public String getTagXml() {
		return tagXml;
	}
	public void setTagXml(String value) {
		this.tagXml = value;
	}
}
