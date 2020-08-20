package com.coppel.singletonrslocal.configuracion;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.coppel.singletonrslocal.dto.ConexionXml;
import com.coppel.singletonrslocal.dto.xml.WebServices;
import com.coppel.singletonrslocal.enums.EnumGlobal;

public class LecturaConexionBaseXML {

	private static final String PATH_APACHETOMCAT = System.getProperty("catalina.base");
	private static final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	private List<ConexionXml> lstCon = null;
	private Base baseXml = new Base();
	private String dirXmlBase ;
	private String dirXmlCorreoBase ;
	private String nombreAplicacion;
	  public String getNombreAplicacion() {
		return nombreAplicacion;
	}


	public void setNombreAplicacion(String nombreAplicacion) {
		this.nombreAplicacion = nombreAplicacion;
	}


	public String getDirXmlBase() {
		  if(dirXmlBase == null || dirXmlBase.equals("")){
			  dirXmlBase = PATH_APACHETOMCAT + File.separator + "conf" + File.separator + "XML_BASE.xml";
		  }
		return dirXmlBase;
	}


	public void setDirXmlBase(String dirXmlBase) {
		this.dirXmlBase = dirXmlBase;
	}
	public String getDirXmlCorreoBase() {
		  if(dirXmlCorreoBase == null || dirXmlCorreoBase.equals("")){
			  dirXmlCorreoBase = PATH_APACHETOMCAT + File.separator + "conf" + File.separator + "FormatoCorreos.xml";
		  }
		return dirXmlCorreoBase;
	}


	public void setDirXmlCorreoBase(String dirXmlCorreoBase) {
		this.dirXmlCorreoBase = dirXmlCorreoBase;
	}

	public LecturaConexionBaseXML(){ }
	public LecturaConexionBaseXML(String dirXmlBase, String dirXmlCorreoBase, String nombreAplicacion){
		this.dirXmlBase = dirXmlBase;
		this.dirXmlCorreoBase = dirXmlCorreoBase;
		this.nombreAplicacion = nombreAplicacion;
	}

	/**
	 * 
	 * @return Node
	 * @throws Exception
	 */
	public Node getCorreos(String nodeFile) throws Exception {
		Node t = null;
		String pathTomcat = getDirXmlCorreoBase();
		File file = new File(pathTomcat);
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			if (file.exists()) {
				Document doc = db.parse(file);
				Element docEle = doc.getDocumentElement();
				NodeList conexionList = docEle.getElementsByTagName(nodeFile);
				if (conexionList != null) {
					for (int i = 0; i < conexionList.getLength(); i++) {
						String name = conexionList.item(i).getAttributes()
								.getNamedItem("name").getNodeValue();
						Node node = conexionList.item(i);
						if (name.equals(nodeFile) && node.getNodeType() == Node.ELEMENT_NODE) {
							
								t = node;
								break;
							
						}
					}
				}
			}
		} catch (Exception e) {
			
			throw new Exception(EnumGlobal.ERROR_LECTURA_ARCHIVO_XML.getAction()+": " + e);
		}
		return t;
	}
	



	public Node getClass(String clase) throws Exception {
		  String pathTomcat = obtenerRutaXML();
		    Node t =null;
		    File file = new File(pathTomcat);
		    boolean existe = false;
		    try {
		    	DocumentBuilder db = dbf.newDocumentBuilder();
		      if (file.exists()) {
		        Document doc = db.parse(file);
		        Element docEle = doc.getDocumentElement();
		        NodeList conexionList = docEle.getElementsByTagName("clases");

		        if (conexionList != null) {
		          for (int i = 0; i < conexionList.getLength(); i++) {
		            String name = conexionList.item(i).getAttributes().getNamedItem("name").getNodeValue();
		            Node node = conexionList.item(i);
		            if (name.equals(clase) && node.getNodeType() == Node.ELEMENT_NODE) {
		                t = node;
		                existe = true;
		                break;
		              
		            }
		          }
		          
		          if (!existe){
		            throw new Exception("Error no existe una clase "+ clase);
		          }
		        }
		        else{
		          throw new Exception(EnumGlobal.ERROR_LECTURA_ARCHIVO_XML.getAction());
		        }
		      }
		    } catch (Exception e) {
		    	
		    	throw new Exception(EnumGlobal.ERROR_LECTURA_ARCHIVO_XML.getAction()+": " + e);
		    }
		    return t;
		  }

	  public Node getConexion() throws Exception {
		  String pathTomcat = obtenerRutaXML();
		    File file = new File(pathTomcat);
		    Node root = null;
		    NodeList nl = null;
		    try {
		    	
		    	Document doc1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(pathTomcat));
	            XPath xPath1 = XPathFactory.newInstance().newXPath();
	            XPathExpression exp = xPath1.compile("//Conexiones/conexion/");
	            nl = (NodeList)exp.evaluate(doc1, XPathConstants.NODESET);
	            System.out.println("Found " + nl.getLength() + " results");
	            for (int i = 0; i < nl.getLength(); i++) {
	            	Node node = nl .item(i);
	            	  if (node.getNodeType() == Node.ELEMENT_NODE) {
	            	      // do something with the current element
	            	      System.out.println(node.getNodeName());
	            	      System.out.println(node.getTextContent());
	            	    }
	            	
	            }

		    } catch (Exception e) {
		 
		    	throw new Exception(EnumGlobal.ERROR_LECTURA_ARCHIVO_XML.getAction()+": " + e);
		    }
		    return root;
		  }
	  public List<ConexionXml> getTagJdbc(List<String> lConexiones) throws Exception{
		  String pathTomcat = obtenerRutaXML();
		    NodeList nl = null;
		    List<ConexionXml> ltagXml = new ArrayList<ConexionXml>();
		    try {
		    	
		    	Document doc1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(pathTomcat));
	            XPath xPath1 = XPathFactory.newInstance().newXPath();
	            for(String nameTag : lConexiones){
	            	String xpathExpression = "//Conexiones/conexion[@name = '"+nameTag+"']/jdbc";
		            XPathExpression exp = xPath1.compile(xpathExpression);
		            nl = (NodeList)exp.evaluate(doc1, XPathConstants.NODESET);
		            System.out.println("Found " + nl.getLength() + " results");
		            ConexionXml tagXml = new ConexionXml();
		            for (int i = 0; i < nl.getLength(); i++) {
		            	Node node = nl .item(i);
		            	  if (node.getNodeType() == Node.ELEMENT_NODE) {
		            	      // do something with the current element
		            	      System.out.println(node.getNodeName());
		            	      tagXml.setTagXml(nameTag);
		            	      tagXml.setJdbc(node.getTextContent());
		            	      System.out.println(node.getTextContent());
		            	      ltagXml.add(tagXml);
		            	    }
		            	
		            }
	            }

		    } catch (Exception e) {
		 
		    	throw new Exception(EnumGlobal.ERROR_LECTURA_ARCHIVO_XML.getAction()+": " + e);
		    }
		    return ltagXml;
	  }

	  private String obtenerBD(String nameBd){
	
		  return lstCon
	                .stream()
	                .filter(x -> x.equals(nameBd))
	                .collect(Collectors.toList()).toString();
	  }
	  public List<ConexionXml> listaBaseDatos(List<String> lstConexiones, String nombreAplicacionValue){
		  List<ConexionXml> lDb = new ArrayList<>();
		  
		  for(String bd : lstConexiones){
			  ConexionXml con = new ConexionXml();
			  con.setJdbc(obtenerBD(bd));
			  con.setTagXml(bd);
			  lDb.add(con);
		  }
		  return lDb;
	  }

	  public List<WebServices> getWebServices(List<String> lServicios) throws Exception{
		  String pathTomcat = obtenerRutaXML();
		    NodeList nl = null;
		    List<WebServices> ltagXml = new ArrayList<WebServices>();
	    	Document doc1;
			try {
				doc1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(pathTomcat));
	
	            XPath xPath1 = XPathFactory.newInstance().newXPath();
	            for(String servicio : lServicios){
	            	String xpathExpression = "//WebServices/websevice[@name = '"+servicio+"']/host";
		            XPathExpression exp = xPath1.compile(xpathExpression);
		            nl = (NodeList)exp.evaluate(doc1, XPathConstants.NODESET);
		            System.out.println("Found " + nl.getLength() + " results");
		            WebServices tagXml = new WebServices();
		            for (int i = 0; i < nl.getLength(); i++) {
		            	Node node = nl .item(i);
		            	  if (node.getNodeType() == Node.ELEMENT_NODE) {
		            	      // do something with the current element
		            	      System.out.println(node.getNodeName());
		            	      tagXml.setNameTag(servicio);
		            	      tagXml.setUrlWebServices(node.getTextContent());
		            	      System.out.println(node.getTextContent());
		            	      ltagXml.add(tagXml);
		            	    }
		            	
		            }
	            }
			} catch (SAXException | IOException | ParserConfigurationException e) {
				throw new Exception(e);
			}
		  return ltagXml;
	  }
	  public WebServices getWebServices(String webservice) throws Exception{
		  String pathTomcat = obtenerRutaXML();
		    NodeList nl = null;
		    WebServices ltagXml = new WebServices();
	    	Document doc1;
			try {
				doc1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(pathTomcat));
	
	            XPath xPath1 = XPathFactory.newInstance().newXPath();
	            	String xpathExpression = "//WebServices/websevice[@name = '"+webservice+"']/host";
		            XPathExpression exp = xPath1.compile(xpathExpression);
		            nl = (NodeList)exp.evaluate(doc1, XPathConstants.NODESET);
		            System.out.println("Found " + nl.getLength() + " results");
		            for (int i = 0; i < nl.getLength(); i++) {
		            	Node node = nl .item(i);
		            	  if (node.getNodeType() == Node.ELEMENT_NODE) {
		            	      // do something with the current element
		            	      System.out.println(node.getNodeName());
		            	      ltagXml.setNameTag(webservice);
		            	      ltagXml.setUrlWebServices(node.getTextContent());
		            	      System.out.println(node.getTextContent());
		            	    }
		            	
		            }
	            
			} catch (SAXException | IOException | ParserConfigurationException e) {
				throw new Exception(e);
			}
		  return ltagXml;
	  }
	  
	  public Node getNodeXPathClass(String xpath) throws Exception {
		  //"//WebServices/websevice[@name = '"+webservice+"']/host"
		  String pathTomcat = obtenerRutaXML();
		    NodeList nl = null;
	    	Document doc1;
	    	Node root = null;
			try {
				doc1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(pathTomcat));
	
	            XPath xPath1 = XPathFactory.newInstance().newXPath();
	            	String xpathExpression = xpath;
		            XPathExpression exp = xPath1.compile(xpathExpression);
		            nl = (NodeList)exp.evaluate(doc1, XPathConstants.NODESET);
		            System.out.println("Found " + nl.getLength() + " results");
		            for (int i = 0; i < nl.getLength(); i++) {
		            	root = nl.item(i);
		            }
			} catch (SAXException | IOException | ParserConfigurationException e) {
				throw new Exception(e);
			}
			return root;
	  }
	  private final String obtenerRutaXML(){
		  return getDirXmlBase();
	  }
}