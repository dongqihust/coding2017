package com.minjvm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		//只考虑windows情况
		byte[] retBytes = null;
		for(String classpath :clzPaths){
			String segFilePath=className.replace(".", "//")+".class";
			String classFilePath = classpath+"//"+segFilePath;
			File classFile = new File(classFilePath);
			
			if(classFile.exists()){
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(classFile);
					retBytes = new byte[fis.available()];
					fis.read(retBytes);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				return retBytes;
			}
			
			
		}
		
		return null;	
		
		
	}
	
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	
	
	public String getClassPath(){
		String retClzPath = "";
		for(String s:clzPaths){
			retClzPath+=s;
			retClzPath+=";";
		}
		if(retClzPath.endsWith(";")){
			retClzPath = retClzPath.substring(0, retClzPath.length()-1);
		}
		
		return retClzPath;
	}

	

	

}
