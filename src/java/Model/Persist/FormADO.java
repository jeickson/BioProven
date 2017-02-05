/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Persist;

import Model.FormClass;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public List<FormClass> getAllForms() throws FileNotFoundException, IOException {
       DataFileManagement dataFileObj= new DataFileManagement(this.ruta+".csv");
       List<String> allLine=new ArrayList();
       List<FormClass> allForms=new ArrayList();
       FormClass formObj;
       
       allLine=dataFileObj.getAllData();
       
       
       for (String line:allLine){
           String[] dataLine= line.split(":");
           formObj= new FormClass(dataLine[0],dataLine[1]);
           allForms.add(formObj);
       }
       return allForms;
    }
}
