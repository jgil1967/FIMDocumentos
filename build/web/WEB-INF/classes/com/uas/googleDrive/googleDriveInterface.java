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
public interface googleDriveInterface {
      public void pruebaDrive ();
      public googleDriveBackupDTO subirArchivos ();
     public DocumentDTO backupDocument  (DocumentDTO dto);
}
