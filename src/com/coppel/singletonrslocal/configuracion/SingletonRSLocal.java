package com.coppel.singletonrslocal.configuracion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.coppel.singletonrslocal.dto.BasicDataSourceConfig;
import com.coppel.singletonrslocal.dto.xml.WebServices;

public class SingletonRSLocal  {

	  protected static    List<String> listaConexiones =new ArrayList<String>();
	  protected static 	  List<String> lstConexionesPgBouncer =new ArrayList<String>();
	  private static LecturaConexionBaseXML conexionBaseXML =null ;
	  protected static  String dirXmlBase;
	  protected static   String dirXmlCorreoBase;
	  protected static   String nombreAplicacion;

	  protected static Map<String,BasicDataSourceConfig> hsMapBasicDataSource;
	  protected static Map<String,BasicDataSourceConfig> hsMapBasicDataSourceBg;
	  protected static List<String> lServicios;
	  protected static List<WebServices> lWServicios;
	public static List<WebServices> getlWServicios() {
		return lWServicios;
	}

	public static void setlWServicios(List<WebServices> lWServicios) {
		SingletonRSLocal.lWServicios = lWServicios;
	}

	public static List<String> getlServicios() {
		return lServicios;
	}

	public static void setlServicios(List<String> lServicios) {
		SingletonRSLocal.lServicios = lServicios;
	}

	public static Map<String, BasicDataSourceConfig> getHsMapBasicDataSourceBg() {
		return hsMapBasicDataSourceBg;
	}

	public static void setHsMapBasicDataSourceBg(
			Map<String, BasicDataSourceConfig> hsMapBasicDataSourceBg) {
		SingletonRSLocal.hsMapBasicDataSourceBg = hsMapBasicDataSourceBg;
	}

	public static Map<String, BasicDataSourceConfig> getHsMapBasicDataSource() {
		return hsMapBasicDataSource;
	}

	public static void setHsMapBasicDataSource(
			Map<String, BasicDataSourceConfig> hsMapBasicDataSource) {
		SingletonRSLocal.hsMapBasicDataSource = hsMapBasicDataSource;
	}

	public SingletonRSLocal(List<String> lstConexiones,
			List<String> lstConexionesPgBouncer,String dirXmlBase, 
			String dirXmlCorreoBase, String nombreAplicacion){
		SingletonRSLocal.listaConexiones = lstConexiones;
		SingletonRSLocal.dirXmlBase = dirXmlBase;
		SingletonRSLocal.dirXmlCorreoBase = dirXmlCorreoBase;
		SingletonRSLocal.nombreAplicacion = nombreAplicacion;
		SingletonRSLocal.lstConexionesPgBouncer = lstConexionesPgBouncer;
	}
	
	public SingletonRSLocal(String dirXmlBase, String dirXmlCorreoBase, String nombreAplicacion){
		SingletonRSLocal.dirXmlBase = dirXmlBase;
		SingletonRSLocal.dirXmlCorreoBase = dirXmlCorreoBase;
		SingletonRSLocal.nombreAplicacion = nombreAplicacion;
	}
	public SingletonRSLocal(String dirXmlBase, String dirXmlCorreoBase, String nombreAplicacion,List<String> lServicios){
		SingletonRSLocal.dirXmlBase = dirXmlBase;
		SingletonRSLocal.dirXmlCorreoBase = dirXmlCorreoBase;
		SingletonRSLocal.nombreAplicacion = nombreAplicacion;
		SingletonRSLocal.lServicios = lServicios;
	}
   protected  static  SingletonRSLocal instance = null; 
  public SingletonRSLocal getInstance() {
	return SingletonRSLocal.instance;
}

public void setInstance(SingletonRSLocal instance) {
	SingletonRSLocal.instance = instance;
}



public static LecturaConexionBaseXML getConexionBaseXML() {
	return conexionBaseXML;
}

public static void setConexionBaseXML(LecturaConexionBaseXML conexionBaseXML) {
	SingletonRSLocal.conexionBaseXML = conexionBaseXML;
}

protected  SingletonRSLocal() throws Exception {
    try {
    	conexionBaseXML = new LecturaConexionBaseXML(dirXmlBase, dirXmlCorreoBase, nombreAplicacion);

        if( !getlServicios().isEmpty() ){
        	setlWServicios(conexionBaseXML.getWebServices(getlServicios()));
        }
    } catch (Exception e) {
    	throw new Exception(e);
    }
  }



public static String getDirXmlBase() {
	return dirXmlBase;
}

public static void setDirXmlBase(String dirXmlBase) {
	SingletonRSLocal.dirXmlBase = dirXmlBase;
}

public static String getDirXmlCorreoBase() {
	return SingletonRSLocal.dirXmlCorreoBase;
}

public static  void setDirXmlCorreoBase(String dirXmlCorreoBase) {
	SingletonRSLocal.dirXmlCorreoBase = dirXmlCorreoBase;
}

public static String getNombreAplicacion() {
	return SingletonRSLocal.nombreAplicacion;
}

public static void setNombreAplicacion(String nombreAplicacion) {
	SingletonRSLocal.nombreAplicacion = nombreAplicacion;
}

public static SingletonRSLocal instance() throws Exception {
    if (SingletonRSLocal.instance == null) {
    	instance = new SingletonRSLocal();
    }
    return instance;
  }
  
  @SuppressWarnings("-access")
  public static void setConexiones(List<String> value){
	  SingletonRSLocal.listaConexiones = value;
  }

	public static List<String> getLstConexionesPgBouncer() {
	return lstConexionesPgBouncer;
}

public static void setLstConexionesPgBouncer(List<String> lstConexionesPgBouncer) {
	SingletonRSLocal.lstConexionesPgBouncer = lstConexionesPgBouncer;
}

	public static List<String> getListaConeciones() {
		return SingletonRSLocal.listaConexiones;
	}
}