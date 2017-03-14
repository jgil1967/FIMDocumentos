/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.googleDrive;

import com.uas.document.DocumentDTO;
import com.uas.googleDriveBackups.googleDriveBackupDTO;

/**
 *
 * @author jonathangil
 */
public class googleDriveFacade implements googleDriveInterface {
googleDriveInterface dao = null;

    public googleDriveFacade() {
        dao = new googleDriveDAO();
    }


    @Override
    public DocumentDTO backupDocument(DocumentDTO dto) {
   return dao.backupDocument(dto);   }
    @Override
    public void pruebaDrive() {
    dao.pruebaDrive();
    
    }

    @Override
    public googleDriveBackupDTO subirArchivos() {
   return  dao.subirArchivos(); }
    
}
