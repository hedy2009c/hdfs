package com.hdfs.comm.util;

public class fileUtil {

	public static int checkType(String filename){
		int type = 10;
		int index = filename.lastIndexOf(".");
		if (index != -1) {
			String typename=filename.substring(index+1).toLowerCase();
			if(typename.equals("doc")){
				type=1;
			}else if(typename.equals("txt")){
				type=2;
			}else if(typename.equals("gif")){
				type=3;
			}else if(typename.equals("jpg")){
				type=3;
			}else if(typename.equals("jpeg")){
				type=3;
			}else if(typename.equals("exe")){
				type=4;
			}else if(typename.equals("mp3")){
				type=5;
			}else if(typename.equals("aac")){
				type=5;
			}else if(typename.equals("wma")){
				type=5;
			}else if(typename.equals("rar")){
				type=6;
			}else if(typename.equals("zip")){
				type=7;
			}else if(typename.equals("html")){
				type=8;
			}else if(typename.equals("pdf")){
				type=9;
			}else if(typename.equals("xls")){
				type=11;
			}
		}
	    
		return type;
		
		
	}
	
	
}
