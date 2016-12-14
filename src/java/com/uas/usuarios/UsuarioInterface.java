/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.usuarios;

/**
 *
 * @author jonathangil
 */
public interface UsuarioInterface {
     UsuarioDTO createUsuario (UsuarioDTO oDto);
     public UsuarioDTO iniciarSesion(UsuarioDTO dto) throws Exception;
     
}
