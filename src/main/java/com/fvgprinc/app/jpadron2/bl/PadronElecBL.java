/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fvgprinc.app.jpadron2.bl;

import com.fvgprinc.app.jpadron2.be.PadronElecBE;
import com.fvgprinc.app.jpadron2.dl.PadronElecDL;
import com.fvgprinc.tools.db.CommonDALExceptions;
import com.fvgprinc.tools.db.ParamAction;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author garfi
 */
public class PadronElecBL {

    private PadronElecDL pedl;
    
    public PadronElecBL() throws SQLException, CommonDALExceptions {
        pedl = new  PadronElecDL();
    }

    public void insertar(PadronElecBE padronElecBE) throws SQLException, FileNotFoundException, IOException, CommonDALExceptions {
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
        pedl.deleteAll();
                
    }
    
    public boolean existe(String numCedula) throws SQLException, CommonDALExceptions {
        boolean res = false;

        res = pedl.existe(numCedula);
        return res;

    }

    public ArrayList<PadronElecBE> listar(ArrayList<ParamAction> params) throws SQLException {
        ArrayList<PadronElecBE> res = null;
        res = pedl.listar( params);
        return res;
    }

}
