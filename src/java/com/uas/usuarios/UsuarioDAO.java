/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.usuarios;


import com.uas.dbutil.getTomcatDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author jonathangil
 */
public class UsuarioDAO implements UsuarioInterface{

    @Override
    public UsuarioDTO createUsuario(UsuarioDTO oDto) {
     PreparedStatement preparedStmt = null;
        Connection c = null;
        ResultSet rs =null;
        // getTomcatDataSource gd = new getTomcatDataSource();
                 getTomcatDataSource gd = new getTomcatDataSource();
         try {
          c = gd.getTomcatDataSource().getConnection();
          String SQL = "INSERT INTO \"public\".\"usuario\" (\"id\",\"contraseña\",\"isAdministrator\",\"enabled\",\"idArea\") VALUES (?,?,?,?,?)";
     	preparedStmt = c.prepareStatement(SQL);
         preparedStmt.setInt(1, oDto.getId());
            preparedStmt.setString(2, oDto.getcontrasena());
            preparedStmt.setBoolean(3, oDto.getIsAdministrator());
            preparedStmt.setBoolean(4, oDto.getEnabled());
            preparedStmt.setInt(5, oDto.getIdArea());
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
    return oDto;}

    @Override
    public UsuarioDTO iniciarSesion(UsuarioDTO dto) throws Exception {
          ArrayList<UsuarioDTO> lista = null;
     UsuarioDTO objeto = null;
       getTomcatDataSource gd = new getTomcatDataSource();
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
       lista = new ArrayList<UsuarioDTO> ();
         try{
                 c = gd.getTomcatDataSource().getConnection();
               String SQL = "SELECT \"usuario\".\"id\", \"usuario\".\"contraseña\", \"usuario\".\"isAdministrator\", \"object\".\"name\", \"object\".\"description\", \"object\".\"createdOn\", \"object\".\"createdBy\", \"object\".\"color\", \"object\".\"kind\" FROM \"usuario\" JOIN \"object\" ON \"usuario\".\"id\" = \"object\".\"id\" where \"object\".\"name\" = ? and \"usuario\".\"contraseña\" = ?";
               ps = c.prepareStatement(SQL);
               ps.setString(1, dto.getName());
                ps.setString(2, dto.getContrasena());
               rs = ps.executeQuery();
                   while (rs.next()) {
                     //  System.out.println("Hello");
                       objeto = new UsuarioDTO();
                       objeto.setId(rs.getInt("id"));
                       objeto.setName(rs.getString("name"));
                       objeto.setVerified(true);
                       objeto.setIsAdministrator(rs.getBoolean("isAdministrator"));
                      lista.add(objeto);
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
         if (lista.size()==0){
             objeto = new UsuarioDTO();
             objeto.setVerified(false);
         }
        return objeto;
    }

    @Override
    public ArrayList<UsuarioDTO> obtenerUsuarios() {
       
        ArrayList<UsuarioDTO> list = null;
     UsuarioDTO dto = null;
       getTomcatDataSource gd = new getTomcatDataSource();
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
       list = new ArrayList<UsuarioDTO> ();
         try{
               c = gd.getTomcatDataSource().getConnection();
               String SQL = "SELECT \"usuario\".\"id\",\"usuario\".\"contraseña\",\"usuario\".\"idArea\", \"usuario\".\"isAdministrator\", \"usuario\".\"enabled\", \"object\".\"name\", \"object\".\"createdBy\" FROM \"usuario\" JOIN \"object\" ON \"usuario\".\"id\" = \"object\".\"id\"";
               ps = c.prepareStatement(SQL);
                 rs = ps.executeQuery();
                   while (rs.next()) {
                       dto = new UsuarioDTO();
                       dto.setId(rs.getInt("id"));
                       dto.setName(rs.getString("name"));
                       dto.setContrasena(rs.getString("contraseña"));
                        dto.setContrasenaVerify(rs.getString("contraseña"));
                        
                        
                        dto.setEnabled(rs.getBoolean("enabled"));
                        dto.setIsAdministrator(rs.getBoolean("isAdministrator"));
                        dto.setCreatedBy(rs.getInt("createdBy"));
                        dto.setIdArea(rs.getInt("idArea"));
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

    @Override
    public UsuarioDTO updateUsuario(UsuarioDTO oDto) {
   PreparedStatement preparedStmt = null;
        Connection c = null;
        ResultSet rs =null;
        // getTomcatDataSource gd = new getTomcatDataSource();
                 getTomcatDataSource gd = new getTomcatDataSource();
         try {
          c = gd.getTomcatDataSource().getConnection();
          String SQL = "update \"public\".\"usuario\" set \"enabled\"=?, \"contraseña\"=?, \"isAdministrator\"=?,\"idArea\"=?  where \"id\"=? ";
     	preparedStmt = c.prepareStatement(SQL);
         
            preparedStmt.setBoolean(1, oDto.getEnabled());
            preparedStmt.setString(2, oDto.getcontrasena());
            preparedStmt.setBoolean(3, oDto.getIsAdministrator());
            preparedStmt.setInt(4, oDto.getIdArea());
            preparedStmt.setInt(5, oDto.getId());
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
    return oDto; }

    @Override
    public UsuarioDTO verificaDisponibilidadUsuario(UsuarioDTO dto) throws Exception {
        //System.out.println("verificaDisponibilidadUsuario");
       getTomcatDataSource gd = new getTomcatDataSource();
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
       
         try{
               c = gd.getTomcatDataSource().getConnection();
               String SQL = "SELECT \"object\".\"name\" FROM \"usuario\" JOIN \"object\" ON \"usuario\".\"id\" = \"object\".\"id\" where \"object\".\"name\" = ?";
               ps = c.prepareStatement(SQL);
              // System.out.println("dto.getName(); " + dto.getName());
               ps.setString(1, dto.getName());
                 rs = ps.executeQuery();
                while (rs.next()) {
                        dto.setAvailability(false);
                        return dto;
                   }
                       dto.setAvailability(true);
                        return dto; 
                    
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
        return dto;
    }
    
}
