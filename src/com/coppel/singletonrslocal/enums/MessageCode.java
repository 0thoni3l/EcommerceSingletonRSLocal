package com.coppel.singletonrslocal.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase Java para messageCode.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que
 * haya en esta clase.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="messageCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SUCCESS"/>
 *     &lt;enumeration value="FAILED"/>
 *     &lt;enumeration value="ERROR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "messageCode")
@XmlEnum
public enum MessageCode {

	SUCCESS, FAILED, ERROR;

	public String value() {
		return name();
	}

	public static MessageCode fromValue(String v) {
		return valueOf(v);
	}
}