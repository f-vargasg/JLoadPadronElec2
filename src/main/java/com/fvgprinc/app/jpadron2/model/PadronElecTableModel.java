/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fvgprinc.app.jpadron2.model;

import com.fvgprinc.app.jpadron2.be.PadronElecBE;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author garfi
 */
public class PadronElecTableModel extends AbstractTableModel {

    private final String[] columnNames = {"id", "cedula", "codelec", "fecCaduca", "nombre", "primApe", "segApe"};

    private List<PadronElecBE> padElecLst;

    public PadronElecTableModel(ArrayList<PadronElecBE> pPadElecLst) {
        this.padElecLst = pPadElecLst;
    }

    public List<PadronElecBE> getCatClienteLst() {
        return padElecLst;
    }

    public void setCatClienteLst(List<PadronElecBE> catClienteLst) {
        this.padElecLst = catClienteLst;
    }

    @Override
    public int getRowCount() {
        return padElecLst.size();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PadronElecBE padElecBe = padElecLst.get(rowIndex);
        switch (columnIndex) {
            case 0 -> {
                return padElecBe.getId();
            }
            case 1 -> {
                return padElecBe.getCedula();
            }
            case 2 -> {
                return padElecBe.getCodElect();
            }
            case 3 -> {
                return padElecBe.getFecCaduca();
            }
            case 4 -> {
                return padElecBe.getNombre();
            }
            case 5 -> {
                return padElecBe.getApellido1();
            }
            case 6 -> {
                return padElecBe.getApellido2();
            }
            default -> {
            }
        }
        return null;
    }

}
