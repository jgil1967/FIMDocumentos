/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.document;

import com.uas.dbutil.getTomcatDataSource;
import com.uas.keyword.KeywordFacade;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

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
               String SQL = "SELECT \"object\".\"id\", \"object\".\"name\", \"object\".\"description\", \"object\".\"createdOn\", \"object\".\"createdBy\", \"object\".\"color\", \"object\".\"kind\", \"document\".\"fileName\", \"document\".\"fileDate\", \"document\".\"idArea\" FROM \"document\" JOIN \"object\" ON \"document\".\"id\" = \"object\".\"id\" order by \"object\".\"createdOn\" asc";
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
                    document.setFileDate(rs.getString("fileDate"));
                  //  document.setFileDateDate(rs.getDate("fileDate"));
//                        String pattern = "yyyy-MM-dd hh:mm:ss";
//SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//Date parse = sdf.parse(rs.getDate("fileDate"));
                        //document.setFileDateDate(rs.getDate("fileDate"));
                        
                        
                       // System.out.println(document.toString());
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
          String SQL = "INSERT INTO \"public\".\"document\" (\"id\",\"fileName\",\"fileDate\") VALUES (?,?,?)";
     	preparedStmt = c.prepareStatement(SQL);
         preparedStmt.setInt(1, dDto.getId());
            preparedStmt.setString(2, dDto.getFilename());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(dDto.getFileDate().substring(0,10));
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            preparedStmt.setTimestamp(3, timestamp);
            
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

    @Override
    public DocumentDTO updateDocument(DocumentDTO dDto) {
    DocumentDTO objectDto = null;
              getTomcatDataSource gd = new getTomcatDataSource(); 
     // getTomcatDataSource gd = new getTomcatDataSource();
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement preparedStmt = null;
     
         try{
               c = gd.getTomcatDataSource().getConnection();
               String SQL = "update \"public\".\"document\" set \"fileDate\"=? where \"id\"=? ";
                preparedStmt = c.prepareStatement(SQL);
          SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(dDto.getFileDate().substring(0,10));
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            preparedStmt.setTimestamp(1, timestamp);
            preparedStmt.setInt(2, dDto.getId());
            
                preparedStmt.executeUpdate();
              
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
                   if (preparedStmt != null){
                     preparedStmt.close();
                 }
             }
             catch (Exception e2){
                 e2.printStackTrace();
             }
         
         }
         return dDto; }
    
}
