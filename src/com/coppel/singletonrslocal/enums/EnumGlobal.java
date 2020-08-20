package com.coppel.singletonrslocal.enums;

public enum EnumGlobal {
	CONTRASENIA("Temporal123"),
	SESSIO_ID("sessionID"),
	REMOTE_ADDRESS("remoteAddress"),
	PARAMETROS("Parametros: "),
	RESPONSE( "Response: "),
	ERROR_LECTURA_ARCHIVO_XML("Error al leer archivo de configuracion."),
	ERROR_LECTURA_ARCHIVO_XML_BASICDATASOURCECONFIG("Error al leer el tag BasicDataSourceConfig."),
	CLASSFORNAME("org.postgresql.Driver"),
	CLASSFORNAMEDB2("com.ibm.db2.jcc.DB2Driver"),	
	TIPO_CLIENTE_NO_CORRESPONDE("El tipo de cliente no corresponde al número de cliente recibido."),
	CON_CORREOSV2("connCorreosEcommerceV2"),
	CON_CORREOSDESFASEV2("conCorreosDesfaseV2"),
	CON_TIENDAVIRTUAL("conTVirtual"),
	ERROR_DS_CLOSE("Error al cerrar conexion"),
	ERROR_DS_CONECTION("Error al obtener la conexion bd"),
	ERROR_DS_CONEXION_READONLY("Error al poner bd modo readonly"),
	ERROR_DS_COMMIT("Error hacer commit"),
	ERROR_DS_ROLLBACK("Error hacer rollback"),
	ERROR_DS_GENERAL("Hubo un error al iniciar el proceso de ThreadLocal"),
	NUMCLIENTECONTADO("90001"),
	ADICIONAR_CERO("El adicional debe de ser 0."),
	CLIENTE_DIFERENTE_CLIENTE_RECIBIDO("El tipo de cliente no corresponde al número de cliente recibido."),
	ERROR("Error: "),
	ERROR_INICIAR_TRANSACCION("Problema al iniciar transaccion");
	
	private String action; 
	  
    public String getAction() 
    { 
        return this.action; 
    } 
    private EnumGlobal(String action) 
    { 
        this.action = action; 
    } 
}
