/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.document;

import com.uas.keyword.KeywordDTO;
import com.uas.object.ObjectDTO;
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

   
     
}
