/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Persist;

import Model.FormClass;
import java.io.IOException;

/**
 *
 * @author alumne
 */
public class FormADO {
    
    //declare
    private String ruta;
    
    //construct
    public FormADO(String ruta) {
        this.ruta = ruta;
    }
    //methods
    public boolean createForm(FormClass formObj) throws IOException{
        //Nom Fitxer:Titol:
        DataFileManagement dataFileObj= new DataFileManagement(this.ruta+".csv");
        if(dataFileObj.findBySpecificField(formObj.getSaveIn()+".csv", 0)==null){
            dataFileObj.insertRegister(formObj.getSaveIn()+".csv"+":"+formObj.getTitle()+":\r\n");
            dataFileObj.setFilePath(this.ruta+"/"+formObj.getSaveIn()+".csv");
            dataFileObj.insertRegister(formObj.toCSV(":"));
            return true;
        }
        else{
            return false;
        }
    }
}
