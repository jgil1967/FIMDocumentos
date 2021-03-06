/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.document;

import com.uas.areas.areaDTO;
import com.uas.dates.filters.filtersDTO.FiltersDTO;
import com.uas.usuarios.UsuarioDTO;
import java.util.ArrayList;

/**
 *
 * @author jonathangil
 */
public class DocumentFacade implements DocumentInterface {
DocumentInterface dDao = null;

    public DocumentFacade() {
dDao = new DocumentDAO ();
    }

    @Override
    public DocumentDTO getDocument(DocumentDTO dDto) {
  return dDao.getDocument(dDto) ; }

    @Override
    public ArrayList<DocumentDTO> getDocuments() {
        return dDao.getDocuments();
    }

    @Override
    public DocumentDTO createDocument(DocumentDTO dDto) {
        return dDao.createDocument(dDto);
    }

//    @Override
//    public DocumentDTO updateDocument(DocumentDTO dDto) {
//return dDao.updateDocument (dDto);
//    }

    @Override
    public ArrayList<DocumentDTO> searchDocuments(DocumentDTO oDto) {
   return dDao.searchDocuments (oDto);
    }

    @Override
    public DocumentDTO updateDocument(DocumentDTO dDto) {
        return dDao.updateDocument(dDto);
    }

    @Override
    public ArrayList<DocumentDTO> getDocuments(FiltersDTO dto) {
   return dDao.getDocuments(dto);
    }

    @Override
    public ArrayList<DocumentDTO> getDocumentsOnlyEnabled(ArrayList<areaDTO> areas) {
      return dDao.getDocumentsOnlyEnabled(areas);
    }

    @Override
    public ArrayList<DocumentDTO> getDocumentsByUser(UsuarioDTO dto) {
    return dDao.getDocumentsByUser(dto); }
    
}
