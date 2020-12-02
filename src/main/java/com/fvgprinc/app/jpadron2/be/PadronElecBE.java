/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fvgprinc.app.jpadron2.be;

import java.sql.Date;

/**
 *
 * @author garfi
 */
public class PadronElecBE {

    int id;
    String cedula;
    String codElect;
    String fecCaduca;
    String nombre;
    String Apellido1;
    String Apellido2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCodElect() {
        return codElect;
    }

    public void setCodElect(String codElect) {
        this.codElect = codElect;
    }

    public String getFecCaduca() {
        return fecCaduca;
    }

    public void setFecCaduca(String fecCaduca) {
        this.fecCaduca = fecCaduca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return Apellido1;
    }

    public void setApellido1(String Apellido1) {
        this.Apellido1 = Apellido1;
    }

    public String getApellido2() {
        return Apellido2;
    }

    public void setApellido2(String Apellido2) {
        this.Apellido2 = Apellido2;
    }
    
    

}
