package com.transinfo.batch;

import java.io.InputStream;
import java.util.Properties;


public class ReadProperties
{

public static String inDir="";
public static String outDir="";
public static String jdbcDriver="";
public static String dbUser="";
public static String jdbcURL="";
public static String dbPassword="";
public static String initSize="";
public static String maxActive="";
public static String merRefnoIn="";
public static String merRefnoOut="";


public static void readProperties()
{

	Properties properties = new Properties();
	try
	{
		
		InputStream in = ReadProperties.class.getResourceAsStream("/GL.properties");
		properties.load(in);
		in.close();
		
		inDir = properties.getProperty("InDir");
		outDir = properties.getProperty("OutDir");
		jdbcDriver = properties.getProperty("JDBCDriver");
		jdbcURL = properties.getProperty("JDBCURL");
		dbUser = properties.getProperty("DBUserID");
		dbPassword = properties.getProperty("DBPassword");
		initSize = properties.getProperty("DBInitSize");
		maxActive = properties.getProperty("DBMaxActive");
		merRefnoIn= properties.getProperty("MerRefnoIn");
		merRefnoOut= properties.getProperty("MerRefnoOut");

	} catch (Exception e)
	{
		System.out.println(e);
	}


}

}
