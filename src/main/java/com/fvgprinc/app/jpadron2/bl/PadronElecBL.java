/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fvgprinc.app.jpadron2.bl;

import com.fvgprinc.app.jpadron2.be.PadronElecBE;
import com.fvgprinc.app.jpadron2.dl.PadronElecDL;
import com.fvgprinc.tools.common.app.layers.ParamAction;
import com.fvgprinc.tools.common.datalayer.CommonDALExceptions;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author garfi
 */
public class PadronElecBL {

    public void insertar(PadronElecBE padronElecBE) throws SQLException, FileNotFoundException, IOException, CommonDALExceptions {
        PadronElecDL pedl = new PadronElecDL();
        ArrayList<ParamAction> lst = new ArrayList<ParamAction>();
//        lst.add(new ParamAction(ParamAction.JavaTypes.LONG, usuarioBE.getId()));
        lst.add(new ParamAction(ParamAction.JavaTypes.STRING, padronElecBE.getCedula()));
        lst.add(new ParamAction(ParamAction.JavaTypes.STRING, padronElecBE.getCodElect()));
        lst.add(new ParamAction(ParamAction.JavaTypes.STRING, padronElecBE.getFecCaduca()));
        lst.add(new ParamAction(ParamAction.JavaTypes.STRING, padronElecBE.getNombre()));
        lst.add(new ParamAction(ParamAction.JavaTypes.STRING, padronElecBE.getApellido1()));
        lst.add(new ParamAction(ParamAction.JavaTypes.STRING, padronElecBE.getApellido2()));

        pedl.insert(lst);
    }

    public void deleteAll() throws SQLException, CommonDALExceptions {
        PadronElecDL pedl = new PadronElecDL();
                
        pedl.deleteAll();
                
    }
    
    public boolean existe(String numCedula) throws SQLException, CommonDALExceptions {
        boolean res = false;

        PadronElecDL pedl = new PadronElecDL();
        res = pedl.existe(numCedula);
        return res;

    }
}
