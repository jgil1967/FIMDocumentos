/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.areas;

import com.uas.dbutil.getTomcatDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author jonathangil
 */
public class areaDAO implements areaInterface {

    @Override
    public areaDTO createArea(areaDTO dto) {
      PreparedStatement preparedStmt = null;
        Connection c = null;
        ResultSet rs =null;
        // getTomcatDataSource gd = new getTomcatDataSource();
                 getTomcatDataSource gd = new getTomcatDataSource();
         try {
          c = gd.getTomcatDataSource().getConnection();
          String SQL = "INSERT INTO \"public\".\"area\" (\"id\") VALUES (?)";
     	preparedStmt = c.prepareStatement(SQL);
         preparedStmt.setInt(1, dto.getId());
            
          preparedStmt.executeUpdate();
             
         
         
         }
          catch (Exception e)
            {
        	e.printStackTrace();
            }
        finally{
            try {
               if (c != null) {
                    c.close();
                }
                if (rs != null) {

                    rs.close();
                }
                if (preparedStmt != null) {
                    preparedStmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    return dto;}

    @Override
    public ArrayList<areaDTO> getAreas() {
     ArrayList<areaDTO> list = null;
     areaDTO dto = null;
       getTomcatDataSource gd = new getTomcatDataSource();
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
       list = new ArrayList<areaDTO> ();
         try{
               c = gd.getTomcatDataSource().getConnection();
               String SQL = "SELECT \"area\".\"id\", \"object\".\"name\" FROM \"area\" JOIN \"object\" ON \"area\".\"id\" = \"object\".\"id\"";
               ps = c.prepareStatement(SQL);
                 rs = ps.executeQuery();
                   while (rs.next()) {
                       dto = new areaDTO();
                       dto.setId(rs.getInt("id"));
                       dto.setName(rs.getString("name"));
                      list.add(dto);
                   }
         }
         catch (Exception e){
             e.printStackTrace();
         }
         finally{
             try{
                 if (rs != null){
                     rs.close();
                 }
                  if (c != null){
                     c.close();
                 }
                   if (ps != null){
                     ps.close();
                 }
             }
             catch (Exception e2){
                 e2.printStackTrace();
             }
         }
        return list;}
    
}
