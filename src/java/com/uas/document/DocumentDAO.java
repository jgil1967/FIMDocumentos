/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.document;

import com.uas.areas.areaDTO;
import com.uas.dates.filters.filtersDTO.FiltersDTO;
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
               String SQL = "SELECT \"object\".\"id\",\"object\".\"createdBy\", \"object\".\"name\", \"object\".\"description\", \"object\".\"createdOn\", \"object\".\"createdBy\", \"object\".\"color\", \"object\".\"kind\", \"document\".\"fileName\", \"document\".\"fileDate\", \"document\".\"idArea\", \"object3\".\"name\" AS \"nameArea\", \"object2\".\"name\" AS \"nameCreatedBy\" FROM \"document\" JOIN \"object\" ON \"document\".\"id\" = \"object\".\"id\" JOIN \"object\" AS \"object3\" ON \"document\".\"idArea\" = \"object3\".\"id\" JOIN \"object\" AS \"object2\" ON \"object\".\"createdBy\" = \"object2\".\"id\" ORDER BY \"object\".\"createdOn\" ASC";
               ps = c.prepareStatement(SQL);
                 rs = ps.executeQuery();
                   while (rs.next()) {
                       document = new DocumentDTO();
                       document.setId(rs.getInt("id"));
                       document.setCreatedBy(rs.getInt("createdBy"));
                       document.setName(rs.getString("name"));
                       document.setDescription(rs.getString("description"));
                        document.setColor(rs.getString("color"));
                        document.setCreatedOn(rs.getTimestamp("createdOn"));
                        document.setKind(rs.getString("kind"));
                        document.setFilename(rs.getString("filename"));
                        document.setKeywords(kFac.getKeywordsByDocument(document));
                    document.setFileDate(rs.getString("fileDate"));
               
                    document.setIdArea(rs.getInt("idArea"));
               document.getArea().setName(rs.getString("nameArea"));
               document.getUser().setName(rs.getString("nameCreatedBy"));
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
          String SQL = "INSERT INTO \"public\".\"document\" (\"id\",\"fileName\",\"fileDate\",\"idArea\") VALUES (?,?,?,?)";
     	preparedStmt = c.prepareStatement(SQL);
         preparedStmt.setInt(1, dDto.getId());
            preparedStmt.setString(2, dDto.getFilename());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(dDto.getFileDate().substring(0,10));
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            preparedStmt.setTimestamp(3, timestamp);
            preparedStmt.setInt(4, dDto.getIdArea());
            
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

    @Override
    public ArrayList<DocumentDTO> getDocuments(FiltersDTO filters) {
     PreparedStatement ps = null;
             Connection c = null;
        ResultSet rs =null;
        ArrayList<DocumentDTO> documents = null;
        getTomcatDataSource gd = new getTomcatDataSource();
        try{
             
       //System.out.println("Keywords : "+ filters.getKeywords().size());
        //System.out.println(filters.toString());
        //System.out.println("QUery : " + filters.getFilterQuery());
        String SQL = "";
        
        
//        if (filters.getKeywords().size()>0){
//            System.out.println("Hay keywords");
//            SQL += "SELECT DISTINCT ON( \"documentKeywordRelationship\".\"idDocument\") \"documentKeywordRelationship\".\"idDocument\", \"object\".\"name\", \"documentKeywordRelationship\".\"idKeyword\", \"document\".\"id\", \"document\".\"fileName\", \"document\".\"idArea\", \"document\".\"fileDate\", \"object2\".\"id\" AS \"id_0\", \"object2\".\"name\" AS \"name_0\", \"object2\".\"description\", \"object2\".\"createdOn\", \"object2\".\"createdBy\", \"object2\".\"color\", \"object2\".\"kind\" FROM \"documentKeywordRelationship\" JOIN \"keyword\" ON \"documentKeywordRelationship\".\"idKeyword\" = \"keyword\".\"id\" JOIN \"object\" ON \"keyword\".\"id\" = \"object\".\"id\" JOIN \"document\" ON \"documentKeywordRelationship\".\"idDocument\" = \"document\".\"id\" JOIN \"object\" AS \"object2\" ON \"document\".\"id\" = \"object2\".\"id\" ";
//        }
        //|| !filters.getFilterQuery().equals("") 
          if (filters.getKeywords().size()>0 ||filters.getDates().getOldestCreatedOn() != null || filters.getDates().getNewestCreatedOn() != null|| filters.getDates().getOldestFileDate() != null|| filters.getDates().getNewestFileDate() != null){
               SQL += "SELECT \"documentKeywordRelationship\".\"idDocument\", \"object\".\"createdBy\", \"object\".\"name\" AS \"keywordName\", \"documentKeywordRelationship\".\"idKeyword\", \"document\".\"id\", \"document\".\"fileName\", \"document\".\"idArea\", \"document\".\"fileDate\", \"object2\".\"id\" AS \"id_0\", \"object2\".\"name\" AS \"name\", \"object2\".\"description\", \"object2\".\"createdOn\", \"object2\".\"createdBy\" AS \"createdBy_0\", \"object2\".\"color\", \"object2\".\"kind\", \"object3\".\"name\" AS \"nameArea\", \"object_alias1\".\"name\" AS \"nameCreatedBy\" FROM \"documentKeywordRelationship\" JOIN \"keyword\" ON \"documentKeywordRelationship\".\"idKeyword\" = \"keyword\".\"id\" JOIN \"object\" ON \"keyword\".\"id\" = \"object\".\"id\" JOIN \"document\" ON \"documentKeywordRelationship\".\"idDocument\" = \"document\".\"id\" JOIN \"object\" AS \"object2\" ON \"document\".\"id\" = \"object2\".\"id\" JOIN \"object\" AS \"object3\" ON \"document\".\"idArea\" = \"object3\".\"id\" JOIN \"object\" AS \"object_alias1\" ON \"object2\".\"createdBy\" = \"object_alias1\".\"id\" ";
        
              SQL +=" where ";
          }
          
          for (int i = 0;i<filters.getKeywords().size();i++){
              SQL = SQL + "\"documentKeywordRelationship\".\"idKeyword\" = "+filters.getKeywords().get(i).getId()+" ";
            
             if (i<filters.getKeywords().size()-1){
                  SQL = SQL + " OR ";
              }
          }
//!filters.getFilterQuery().equals("") || 
          
 if (filters.getKeywords().size()>0 && (filters.getDates().getOldestCreatedOn()!=null||filters.getDates().getNewestCreatedOn() != null|| filters.getDates().getOldestFileDate() != null|| filters.getDates().getNewestFileDate() != null)){
                SQL =SQL + "AND";
            }
            System.out.println("filters.getDates().getOldestCreatedOn() : " + filters.getDates().getOldestCreatedOn());
 if (filters.getDates().getOldestCreatedOn() != null){
             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(filters.getDates().getOldestCreatedOn().substring(0,10));
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            System.out.println("1 " + timestamp);
            SQL =SQL + " \"object\".\"createdOn\" >=  \'"+timestamp+"\' ";
            if (filters.getDates().getNewestCreatedOn() != null|| filters.getDates().getOldestFileDate() != null|| filters.getDates().getNewestFileDate() != null){
                SQL =SQL + " AND ";
            }
        }
        if (filters.getDates().getNewestCreatedOn() != null){
             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(filters.getDates().getNewestCreatedOn().substring(0,10));
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
             
            System.out.println("2  " + timestamp);
            SQL =SQL +" \"object\".\"createdOn\" <= \'"+timestamp+"\' ";
             if ( filters.getDates().getOldestFileDate() != null|| filters.getDates().getNewestFileDate() != null){
                SQL =SQL + " AND ";
            }
        }
       
        if (filters.getDates().getOldestFileDate()!= null){
             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(filters.getDates().getOldestFileDate().substring(0,10));
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
             
            System.out.println("3 " + timestamp);
            SQL =SQL +" \"document\".\"fileDate\" >=  \'"+timestamp+"\' ";
             if (  filters.getDates().getNewestFileDate() != null){
                SQL =SQL + " AND ";
            }
        }
        if (filters.getDates().getNewestFileDate() != null){
             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(filters.getDates().getNewestFileDate().substring(0,10));
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
             
            System.out.println("4 " + timestamp);
            SQL =SQL +" \"document\".\"fileDate\" <=  \'"+timestamp+"\' ";
        }
        
       System.out.println("SQL : " + SQL);
        if (!SQL.equals("")){
        DocumentDTO document = null;
            c = gd.getTomcatDataSource().getConnection();
        ps = c.prepareStatement(SQL);
        rs = ps.executeQuery();
        kFac = new KeywordFacade ();
        documents = new ArrayList<DocumentDTO> ();
         while (rs.next()) {
                       document = new DocumentDTO();
                       document.setId(rs.getInt("id"));
                       document.setCreatedBy(rs.getInt("createdBy"));
                       document.setName(rs.getString("name"));
                       document.setDescription(rs.getString("description"));
                        document.setColor(rs.getString("color"));
                        document.setCreatedOn(rs.getTimestamp("createdOn"));
                        document.setKind(rs.getString("kind"));
                        document.setFilename(rs.getString("filename"));
                        document.setFileDate(rs.getString("fileDate"));
                        document.setIdArea(rs.getInt("idArea"));
                        document.setKeywords(kFac.getKeywordsByDocument(document));
                         document.getArea().setName(rs.getString("nameArea"));
               document.getUser().setName(rs.getString("nameCreatedBy"));
                      documents.add(document);
                   }
            System.out.println("documents size : " + documents.size());
         return documents;
        }
        else{
           // System.out.println("regresando getDocuments");
            return getDocuments();
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
//SELECT "object"."id", "object"."createdBy", "object"."name", "object"."description", "object"."createdOn", "object"."createdBy", "object"."color", "object"."kind", "document"."fileName", "document"."fileDate", "document"."idArea", "object2"."name" AS "nameCreatedBy", "object3"."name" AS "nameArea", "area"."enabled", "area"."enabled" FROM "document" JOIN "object" ON "document"."id" = "object"."id" JOIN "object" AS "object2" ON "object"."createdBy" = "object2"."id" JOIN "area" ON "document"."idArea" = "area"."id" JOIN "object" AS "object3" ON "area"."id" = "object3"."id" ORDER BY "object"."createdOn" ASC WHERE "area"."enabled" = TRUE
    @Override
    public ArrayList<DocumentDTO> getDocumentsOnlyEnabled(ArrayList<areaDTO> areas) {
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
               String SQL = "SELECT \"object\".\"id\", \"object\".\"createdBy\", \"object\".\"name\", \"object\".\"description\", \"object\".\"createdOn\", \"object\".\"createdBy\", \"object\".\"color\", \"object\".\"kind\", \"document\".\"fileName\", \"document\".\"fileDate\", \"document\".\"idArea\", \"object2\".\"name\" AS \"nameCreatedBy\", \"object3\".\"name\" AS \"nameArea\", \"area\".\"enabled\", \"area\".\"enabled\" FROM \"document\" JOIN \"object\" ON \"document\".\"id\" = \"object\".\"id\" JOIN \"object\" AS \"object2\" ON \"object\".\"createdBy\" = \"object2\".\"id\" JOIN \"area\" ON \"document\".\"idArea\" = \"area\".\"id\" JOIN \"object\" AS \"object3\" ON \"area\".\"id\" = \"object3\".\"id\" WHERE \"area\".\"enabled\" = TRUE  ";
              if (areas.size()>0){
                 SQL = SQL + " AND ("; 
              }
               for (int a=0;a<areas.size();a++){
                  SQL = SQL + "\"document\".\"idArea\" = " + areas.get(a).getId();
                  if (!(a == areas.size()-1)){
                      SQL = SQL + " OR ";
                  }
                  else{
                      SQL = SQL + " ) ";
                  }
              }
               ps = c.prepareStatement(SQL);
              // System.out.println("SQL : " + SQL);
                 rs = ps.executeQuery();
                   while (rs.next()) {
                       document = new DocumentDTO();
                       document.setId(rs.getInt("id"));
                       document.setCreatedBy(rs.getInt("createdBy"));
                       document.setName(rs.getString("name"));
                       document.setDescription(rs.getString("description"));
                        document.setColor(rs.getString("color"));
                        document.setCreatedOn(rs.getTimestamp("createdOn"));
                        document.setKind(rs.getString("kind"));
                        document.setFilename(rs.getString("filename"));
                        document.setKeywords(kFac.getKeywordsByDocument(document));
                    document.setFileDate(rs.getString("fileDate"));
               
                    document.setIdArea(rs.getInt("idArea"));
               document.getArea().setName(rs.getString("nameArea"));
               document.getUser().setName(rs.getString("nameCreatedBy"));
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
