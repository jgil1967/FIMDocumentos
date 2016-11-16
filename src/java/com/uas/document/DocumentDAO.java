/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.document;

import com.uas.dbutil.getTomcatDataSource;
import com.uas.keyword.KeywordFacade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author jonathangil
 */
public class DocumentDAO implements DocumentInterface {
KeywordFacade kFac = null;

    @Override
    public DocumentDTO getDocument(DocumentDTO dDto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<DocumentDTO> getDocuments() {
       kFac = new KeywordFacade ();
        ArrayList<DocumentDTO> documents = null;
     DocumentDTO document = null;
       getTomcatDataSource gd = new getTomcatDataSource();
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
       documents = new ArrayList<DocumentDTO> ();
         try{
               c = gd.getTomcatDataSource().getConnection();
               String SQL = "SELECT \"object\".\"id\", \"object\".\"name\", \"object\".\"description\", \"object\".\"createdOn\", \"object\".\"createdBy\", \"object\".\"color\", \"object\".\"kind\", \"document\".\"fileName\", \"document\".\"idArea\" FROM \"document\" JOIN \"object\" ON \"document\".\"id\" = \"object\".\"id\"";
               ps = c.prepareStatement(SQL);
                 rs = ps.executeQuery();
                   while (rs.next()) {
                       document = new DocumentDTO();
                       document.setId(rs.getInt("id"));
                       document.setName(rs.getString("name"));
                       document.setDescription(rs.getString("description"));
                        document.setColor(rs.getString("color"));
                        document.setCreatedOn(rs.getTimestamp("createdOn"));
                        document.setKind(rs.getString("kind"));
                        document.setFilename(rs.getString("filename"));
                        document.setKeywords(kFac.getKeywordsByDocument(document));
                      documents.add(document);
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
        return documents;
    }

    @Override
    public DocumentDTO createDocument(DocumentDTO dDto) {
      PreparedStatement preparedStmt = null;
        Connection c = null;
        ResultSet rs =null;
        // getTomcatDataSource gd = new getTomcatDataSource();
                 getTomcatDataSource gd = new getTomcatDataSource();
         try {
        c = gd.getTomcatDataSource().getConnection();
          String SQL = "INSERT INTO \"public\".\"document\" (\"id\",\"fileName\") VALUES (?,?)";
     	preparedStmt = c.prepareStatement(SQL);
         preparedStmt.setInt(1, dDto.getId());
            preparedStmt.setString(2, dDto.getFilename());
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
    return dDto;  }

//    @Override
//    public DocumentDTO updateDocument(DocumentDTO dDto) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public ArrayList<DocumentDTO> searchDocuments(DocumentDTO dDto) {
          kFac = new KeywordFacade ();
        ArrayList<DocumentDTO> documents = null;
     DocumentDTO document = null;
       getTomcatDataSource gd = new getTomcatDataSource();
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
       documents = new ArrayList<DocumentDTO> ();
         try{
               c = gd.getTomcatDataSource().getConnection();
               String SQL = "SELECT \"object\".\"id\", \"object\".\"name\", \"object\".\"description\", \"object\".\"createdOn\", \"object\".\"createdBy\", \"object\".\"color\", \"object\".\"kind\", \"document\".\"fileName\", \"document\".\"idArea\" FROM \"document\" JOIN \"object\" ON \"document\".\"id\" = \"object\".\"id\"  where \"object\".\"name\" ILIKE ? or \"object\".\"description\" ILIKE ?";
               ps = c.prepareStatement(SQL);
               ps.setString(1, "%"+dDto.getQuery() + "%");
               ps.setString(2, "%"+dDto.getQuery() + "%");
                 rs = ps.executeQuery();
                   while (rs.next()) {
                       document = new DocumentDTO();
                       document.setId(rs.getInt("id"));
                       document.setName(rs.getString("name"));
                       document.setDescription(rs.getString("description"));
                        document.setColor(rs.getString("color"));
                        document.setCreatedOn(rs.getTimestamp("createdOn"));
                        document.setKind(rs.getString("kind"));
                        document.setFilename(rs.getString("filename"));
                        document.setKeywords(kFac.getKeywordsByDocument(document));
                      documents.add(document);
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
        return documents;}
    
}
