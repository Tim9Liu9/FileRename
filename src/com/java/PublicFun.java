package com.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PublicFun 
{

    // ȥ���ַ����еá�*�����滻���еĴ���
    public static String replaceSign(String str){
//    	Pattern p1 = Pattern.compile("\r");     	  
    	Pattern p2 = Pattern.compile("\\*");     	  
//    	Matcher m1 = p1.matcher(str);      	 
//    	String after1 = m1.replaceAll(""); 
    	Matcher m2 = p2.matcher(str); 
    	String after2 = m2.replaceAll("\n\n");   
    	return after2;
    }
	
}
