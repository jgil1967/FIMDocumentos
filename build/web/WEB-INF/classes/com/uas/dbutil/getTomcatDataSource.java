/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.dbutil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author jonathangilsantillan
 */
public class getTomcatDataSource {
 
  DataSource ds = null;
  
  
    public String returnDataSource(){
       // //System.out.println(new File(".").getAbsolutePath());
	Properties prop = new Properties();
	InputStream input = null;

	try {

		input = new FileInputStream("config.properties");

		// load a properties file
		prop.load(input);

		// get the property value and print it out
		return prop.getProperty("dataSourceName");

	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
        return null;
    }
  
   public  javax.sql.DataSource  getTomcatDataSource () throws NamingException
  
  
  {
     // System.out.println("1");
         try{
                Context initialContext = new InitialContext();
                    //  System.out.println("2");
if ( initialContext == null){
    //  System.out.println("JNDI problem. Cannot get InitialContext.");
          //  System.out.println("3");
}
      // System.out.println("4");
DataSource ds = (DataSource)initialContext.lookup("java:/comp/env/"+returnDataSource());
                              //System.out.println("5");     
if (ds != null) {
  //  System.out.println("Encontré el datasource");
        return ds;
}
if (ds.getConnection() == null){
    // System.out.println("This shit is empty");
}
        }
        catch (Exception e){
            e.printStackTrace();
        }
         return ds;
   }
    public getTomcatDataSource() {
     

    }
    
}
