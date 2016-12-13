/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.document;

import com.uas.dates.filters.filtersDTO.FiltersDTO;
import java.util.ArrayList;

/**
 *
 * @author jonathangil
 */
public interface DocumentInterface {
     DocumentDTO getDocument(DocumentDTO dDto);
       ArrayList<DocumentDTO> getDocuments();
       ArrayList<DocumentDTO> getDocuments(FiltersDTO dto);
       DocumentDTO createDocument(DocumentDTO dDto);
     DocumentDTO updateDocument(DocumentDTO dDto);
       ArrayList <DocumentDTO> searchDocuments (DocumentDTO oDto);
}
