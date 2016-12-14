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
          String SQL = "INSERT INTO \"public\".\"usuario\" (\"id\",\"idCarrera\",\"contraseña\") VALUES (?,?,?)";
     	preparedStmt = c.prepareStatement(SQL);
         preparedStmt.setInt(1, oDto.getId());
            preparedStmt.setInt(2, oDto.getIdCarrera());
            preparedStmt.setString(3, oDto.getcontrasena());
            
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
               String SQL = "SELECT \"objeto\".\"id\", \"objeto\".\"nombre\", \"objeto\".\"descripcion\", \"usuario\".\"idCarrera\", \"usuario\".\"contraseña\" FROM \"usuario\" JOIN \"objeto\" ON \"usuario\".\"id\" = \"objeto\".\"id\" WHERE \"objeto\".\"nombre\" = ? AND \"usuario\".\"contraseña\" = ?";
               ps = c.prepareStatement(SQL);
               ps.setString(1, dto.getName());
                ps.setString(2, dto.getContrasena());
               rs = ps.executeQuery();
                   while (rs.next()) {
                       System.out.println("Hello");
                       objeto = new UsuarioDTO();
                       
                       objeto.setId(rs.getInt("id"));
                       objeto.setIdCarrera(rs.getInt("idCarrera"));
                       objeto.setName(rs.getString("nombre"));
                       objeto.setVerified(true);
                       //objeto.setDescription(rs.getString("descripcion"));
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
    
}
