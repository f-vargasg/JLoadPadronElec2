/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fvgprinc.app.jpadron2.bl;

import com.fvgprinc.app.jpadron2.be.PadronElecBE;
import com.fvgprinc.tools.common.datalayer.CommonDALExceptions;
import java.awt.Cursor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 *
 * @author garfi
 */
public class CargadorArchivos extends SwingWorker<String, Void> {

    private final JFrame frame;
    private final JProgressBar jpb;
    private final String pathFile;
    private final String fname;

    public CargadorArchivos(JFrame frame, JProgressBar jpb, String pathFile, String fname) {
        this.frame = frame;
        this.jpb = jpb;
        this.pathFile = pathFile;
        this.fname = fname;

    }

    private int cantLines(String ffname) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(ffname));
        int lines = 0;
        while (reader.readLine() != null) {
            lines++;
        }
        reader.close();
        return lines;
    }

    private void cargarPadron() throws SQLException, IOException, CommonDALExceptions {
        PadronElecBL pebl = new PadronElecBL();

        PadronElecBE pebe = new PadronElecBE();

        int i = 0;

        boolean finCarga = false;
        
        

        String ffname = pathFile + File.separator + fname;
        int numLineas = cantLines(ffname);
        
        this.jpb.setMaximum(numLineas);
        System.out.println("Numero de lineas: " + Integer.toString(numLineas));        

        // pebl.deleteAll();
        File fi = new File(ffname);
        FileInputStream fis = new FileInputStream(fi);
        // Charset charset = Charset.forName("UTF-16");
        InputStreamReader isr = new InputStreamReader(fis, "UTF8");
        // String en = isr.getEncoding();
        try (BufferedReader br = new BufferedReader(isr)) {
            // <editor-fold defaultstate="collapsed" desc=" Buffered-reader ">
            String line;
            while ((line = br.readLine()) != null) {
                String[] scrap = line.split(",", 7);
                pebe.setCedula(scrap[0].trim());
                pebe.setCodElect(scrap[1].trim());
                pebe.setFecCaduca(scrap[2].trim());
                pebe.setNombre(scrap[4].trim());
                pebe.setApellido1(scrap[5].trim());
                pebe.setApellido2(scrap[6].trim());
                boolean existeCed = pebl.existe(pebe.getCedula());
                if (!existeCed) {
                    pebl.insertar(pebe);
                }
                this.jpb.setValue(i++);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        JOptionPane.showMessageDialog(frame, "Proceso Finalizado....!");

    }

    @Override
    protected String doInBackground() throws Exception {
        String result = null;
        cargarPadron();
        return result;
    }

    @Override
    protected void done() {
        // super.done(); //To change body of generated methods, choose Tools | Templates.
        try {
            String result = get();
            //do stuff 
        } catch (ExecutionException | InterruptedException ex) {
            // display error
            JOptionPane.showMessageDialog(frame,
                    ex.getMessage(),
                    "Error opening file",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            frame.setCursor(Cursor.getDefaultCursor());
        }
    }

}
