/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.areas;

import java.util.ArrayList;

/**
 *
 * @author jonathangil
 */
public class areaFacade implements areaInterface{
areaInterface dao = null;

    public areaFacade() {
        dao = new areaDAO();
    }


    @Override
    public areaDTO createArea(areaDTO dto) {
  return dao.createArea(dto);}

    @Override
    public ArrayList<areaDTO> getAreas() {
   return dao.getAreas(); }
    
}
