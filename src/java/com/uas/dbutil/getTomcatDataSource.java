/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.dbutil;

import java.util.Hashtable;
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
DataSource ds = (DataSource)initialContext.lookup("java:/comp/env/jdbc/knowledgeBase");
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
