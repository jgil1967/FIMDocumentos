/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.document;

import com.uas.areas.areaDTO;
import com.uas.keyword.KeywordDTO;
import com.uas.object.ObjectDTO;
import com.uas.usuarios.UsuarioDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jonathangil
 */
@XmlRootElement()
public class DocumentDTO extends ObjectDTO implements Serializable {
    String filename;
    ArrayList <KeywordDTO> keywords;
    String fileDate;
    Date fileDateDate;
    int idArea;
    UsuarioDTO user;
    areaDTO area;
    Boolean visible = true, deleted = false,backedUp=false, isFolder=false;
    int idUsuario;

    public Boolean getIsFolder() {
        return isFolder;
    }

    public void setIsFolder(Boolean isFolder) {
        this.isFolder = isFolder;
    }
    

    public Boolean getBackedUp() {
        return backedUp;
    }

    public void setBackedUp(Boolean backedUp) {
        this.backedUp = backedUp;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    
    
    public DocumentDTO() {
        user = new UsuarioDTO();
        area = new areaDTO();
        
    }
    
    
    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }
    

    public Date getFileDateDate() {
        return fileDateDate;
    }

    public void setFileDateDate(Date fileDateDate) {
        this.fileDateDate = fileDateDate;
    }

    public String getFileDate() {
        return fileDate;
    }

    public void setFileDate(String fileDate) {
        this.fileDate = fileDate;
    }



    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public ArrayList<KeywordDTO> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<KeywordDTO> keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return "DocumentDTO{" + "filename=" + filename + ", keywords=" + keywords + ", fileDate=" + fileDate + ", fileDateDate=" + fileDateDate + '}';
    }

    public UsuarioDTO getUser() {
        return user;
    }

    public void setUser(UsuarioDTO user) {
        this.user = user;
    }

    public areaDTO getArea() {
        return area;
    }

    public void setArea(areaDTO area) {
        this.area = area;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

   
     
}
