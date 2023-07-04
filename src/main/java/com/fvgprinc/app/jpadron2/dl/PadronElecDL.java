/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fvgprinc.app.jpadron2.dl;

import com.fvgprinc.app.jpadron2.globals.GloblaConstants;
import com.fvgprinc.app.jpadron2.be.PadronElecBE;
import com.fvgprinc.tools.common.app.dbconnection4.DIContainer;
import com.fvgprinc.tools.common.app.layers.Mapper;
import com.fvgprinc.tools.common.app.layers.ParamAction;
import com.fvgprinc.tools.common.datalayer.CommonDALExceptions;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author garfi
 */
public class PadronElecDL extends Mapper {

    private final String tableName = "PadronElect";
    private final String selectStm = "Select *  from " + tableName;
    private final String insertStm = "insert into " + tableName + " ("
            + " cedula,   codelec,  fecCaduca, nombre,   primApe,  segApe  ) values ("
            + "?,?,?,?,?,?)";
    private final String updateStm = "update " + tableName + " set "
            + " cedula = ? , codelec = ?, fecCaduca = ?, nombre = ?, "
            + " primApe,  segApe"
            + " where "
            + " id = ?";
    private final String deleteStm = "delete from " + tableName;

    private final String deleteByPkStm = deleteStm + " where "
            + " id = ?";

    private final String FindStm = selectStm + " where "
            + " cedula = ?";

    public PadronElecDL() throws SQLException, CommonDALExceptions {
        this.dm = DIContainer.getInstance().getDataManager(GloblaConstants.MARIADBCONN);
        this.conn = dm.getConnectioin();
    }

    @Override
    public void insert(ArrayList<ParamAction> paramDLs) throws SQLException {
        doStatement(insertStm, paramDLs);
    }

    @Override
    public void update(ArrayList<ParamAction> paramDLs) throws SQLException {
        doStatement(updateStm, paramDLs);
    }

    @Override
    public void delete(ArrayList<ParamAction> keyFields) throws SQLException {
        doStatement(deleteByPkStm, keyFields);
    }

    public void deleteAll() throws SQLException {
        ArrayList<ParamAction> dummyLst = new ArrayList<>();
        doStatement(deleteStm, dummyLst);
    }

    @Override
    protected Object doLoad(ResultSet rs) throws SQLException {
        PadronElecBE padronElecBE = new PadronElecBE();

        padronElecBE.setId(rs.getInt("id"));
        padronElecBE.setCedula(rs.getString("cedula"));
        padronElecBE.setCodElect(rs.getString("codelec"));
        padronElecBE.setFecCaduca(rs.getString("fecCaduca"));
        padronElecBE.setNombre(rs.getString("nombre"));
        padronElecBE.setApellido1(rs.getString("primApe"));
        padronElecBE.setApellido2(rs.getString("segApe"));

        return padronElecBE;
    }

    @Override
    protected Object doFind(ArrayList<ParamAction> keyFiedls) throws SQLException {
        PadronElecBE pebe = null;
        ResultSet res = this.doStmReturnData(FindStm, keyFiedls);
        if (res.next()) {
            pebe = (PadronElecBE) load(res);
        }
        return pebe;
    }

    public boolean existe(String numCedula) throws SQLException {
        ArrayList<ParamAction> lst = new ArrayList<>();

        lst.add(new ParamAction(ParamAction.JavaTypes.STRING, numCedula));
        Object res = doFind(lst);
        return (res != null);
    }

}
