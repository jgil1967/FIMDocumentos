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
public class UsuarioFacade implements UsuarioInterface{
UsuarioInterface uDao = null;

    public UsuarioFacade() {
        uDao = new UsuarioDAO();
    }
    @Override
    public UsuarioDTO createUsuario(UsuarioDTO oDto) {
    return uDao.createUsuario(oDto);}

    @Override
    public UsuarioDTO iniciarSesion(UsuarioDTO dto) throws Exception {
        return uDao.iniciarSesion(dto);
    }
    
}
