/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.areaRelationships;

import com.uas.dbutil.getTomcatDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author jonathangil
 */
public class areaRelationshipsDAO implements areaRelationshipsInterface{

    @Override
    public areaRelationshipsDTO createAreaRelationship(areaRelationshipsDTO dto) {
      PreparedStatement preparedStmt = null;
        Connection c = null;
        ResultSet rs =null;
        // getTomcatDataSource gd = new getTomcatDataSource();
                 getTomcatDataSource gd = new getTomcatDataSource();
         try {
          c = gd.getTomcatDataSource().getConnection();
          String SQL = "INSERT INTO \"public\".\"areaRelationships\" (\"idArea1\",\"idArea2\") VALUES (?,?)";
     	preparedStmt = c.prepareStatement(SQL);
         preparedStmt.setInt(1, dto.getIdArea1());
         preparedStmt.setInt(2, dto.getIdArea2());   
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
    public areaRelationshipsDTO deleteAreaRelationship(areaRelationshipsDTO dto) {
       // System.out.println(" dto.getIdArea1() : " + dto.getIdArea1());
        //System.out.println(" dto.getIdArea2() : " + dto.getIdArea2());
     PreparedStatement preparedStmt = null;
        Connection c = null;
        ResultSet rs =null;
        // getTomcatDataSource gd = new getTomcatDataSource();
                 getTomcatDataSource gd = new getTomcatDataSource();
         try {
          c = gd.getTomcatDataSource().getConnection();
          String SQL = "delete from \"public\".\"areaRelationships\" where \"idArea1\"=? and \"idArea2\"=? ";
     	preparedStmt = c.prepareStatement(SQL);
         preparedStmt.setInt(1, dto.getIdArea1());
         preparedStmt.setInt(2, dto.getIdArea2());   
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
    
}
