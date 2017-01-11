/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.usuarios;

import java.util.ArrayList;

/**
 *
 * @author jonathangil
 */
public interface UsuarioInterface {
     UsuarioDTO createUsuario (UsuarioDTO oDto);
     UsuarioDTO updateUsuario (UsuarioDTO oDto);
     ArrayList <UsuarioDTO> obtenerUsuarios ();
     public UsuarioDTO iniciarSesion(UsuarioDTO dto) throws Exception;
     public UsuarioDTO verificaDisponibilidadUsuario(UsuarioDTO dto) throws Exception;
}
