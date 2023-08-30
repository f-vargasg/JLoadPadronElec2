/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fvgprinc.app.jpadron2.dl;

import com.fvgprinc.app.jpadron2.globals.GloblaConstants;
import com.fvgprinc.app.jpadron2.be.PadronElecBE;
import com.fvgprinc.tools.common.string.MyCommonString;
import com.fvgprinc.tools.db.CommonDALExceptions;
import com.fvgprinc.tools.db.DIContainer;
import com.fvgprinc.tools.db.Mapper;
import com.fvgprinc.tools.db.ParamAction;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author garfi
 */
public class PadronElecDL extends Mapper {

    private final String TABLE_NAME = "PadronElect";
    private final String SELECT_STM = "Select *  from " + TABLE_NAME;
    private final String INSERT_STM = "insert into " + TABLE_NAME + " ("
            + " cedula,   codelec,  fecCaduca, nombre,   primApe,  segApe  ) values ("
            + "?,?,?,?,?,?)";
    private final String UPDATE_STM = "update " + TABLE_NAME + " set "
            + " cedula = ? , codelec = ?, fecCaduca = ?, nombre = ?, "
            + " primApe,  segApe"
            + " where "
            + " id = ?";
    private final String DELETE_STM = "delete from " + TABLE_NAME;

    private final String DELETE_BY_PK_STM = DELETE_STM + " where "
            + " id = ?";

    private final String FIND_STM_BY_PK = SELECT_STM + " where "
            + " cedula = ?";

    public PadronElecDL() throws SQLException, CommonDALExceptions {
        this.dm = DIContainer.getInstance().getDataManager(GloblaConstants.MARIADBCONN);
    }

    @Override
    public void insert(ArrayList<ParamAction> paramDLs) throws SQLException {
        doStatement(INSERT_STM, paramDLs);
    }

    @Override
    public void update(ArrayList<ParamAction> paramDLs) throws SQLException {
        doStatement(UPDATE_STM, paramDLs);
    }

    @Override
    public void delete(ArrayList<ParamAction> keyFields) throws SQLException {
        doStatement(DELETE_BY_PK_STM, keyFields);
    }

    public void deleteAll() throws SQLException {
        ArrayList<ParamAction> dummyLst = new ArrayList<>();
        doStatement(DELETE_STM, dummyLst);
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
    public Object doFind(ArrayList<ParamAction> keyFiedls) throws SQLException {
        PadronElecBE pebe = null;
        //ResultSet rs = this.doStmReturnData(FindStm, keyFiedls);
        //PreparedStatement ps = conn.prepareStatement(FIND_STM_BY_PK);
        //ps = this.setParamPreparedStm(ps, keyFiedls);
        try ( Connection conn = dm.getConnectioin();  PreparedStatement ps = conn.prepareStatement(FIND_STM_BY_PK)) {
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pebe = (PadronElecBE) load(rs);
                }
            }
        }

        return pebe;
    }

    public boolean existe(String numCedula) throws SQLException {
        ArrayList<ParamAction> lst = new ArrayList<>();

        lst.add(new ParamAction(ParamAction.JavaTypes.STRING, numCedula));
        Object res = doFind(lst);
        return (res != null);
    }

    public ArrayList<PadronElecBE> listar(ArrayList<ParamAction> params) throws SQLException {
        ArrayList<PadronElecBE> lstRes = new ArrayList<>();
        String condSql = ParamAction.queryCond(params);
        String sqlStm = SELECT_STM + (condSql.length() > 0 ? " WHERE " : MyCommonString.EMPTYSTR)
                + condSql;
        try ( Connection conn = dm.getConnectioin();  PreparedStatement ps = conn.prepareStatement(sqlStm)) {
            this.setParamPreparedStm(ps, params);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PadronElecBE ub = (PadronElecBE) doLoad(rs);
                    lstRes.add(ub);
                }
            }
            return lstRes;
        }
    }

}
