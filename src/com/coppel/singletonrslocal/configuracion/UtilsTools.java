package com.coppel.singletonrslocal.configuracion;

import java.util.ArrayList;
import java.util.List;


public class UtilsTools {
	
	public static List<String> mergeList(List<String> lstPrimero, List<String> lstSegunda){
		List<String> newList = new ArrayList<>(); 
		newList.addAll(lstPrimero); newList.addAll(lstSegunda); 
		return newList;
	}
	
	public static List<String> addStringToList(String[] lista){
		List<String> list = new ArrayList<>();
		for (String strTemp: lista) {
			list.add(strTemp);
		}
		return list;
		
	}
}
