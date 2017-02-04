/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Persist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author alumne
 */
public class DataFileManagement {
   
    private String filePath;

    public DataFileManagement(String filePath) {
        this.filePath = filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    //methods
    
    public String findBySpecificField(String fieldValue,int fieldCompare) throws FileNotFoundException, IOException{
        BufferedReader br= new BufferedReader(new FileReader(this.filePath));
        String line="";
        String[] tokens;
        String finded=null;
        while((line=br.readLine())!=null){
            tokens=line.split(":");
            if(tokens[fieldCompare].equals(fieldValue)){
                finded=line;
              break;
            }
        }
        br.close();
        return finded;
    }
    public void insertRegister (String line) throws FileNotFoundException, IOException{
                FileWriter fout = new FileWriter(new File(this.filePath), true);
                fout.write(line);
       fout.close();
    }
    public String findByTwoField(String fieldValue,String fieldValue2,int fieldCompare,int fieldCompare2) throws FileNotFoundException, IOException{
        BufferedReader br= new BufferedReader(new FileReader(this.filePath));
        String line="";
        String[] tokens;
        String finded=null;
        while((line=br.readLine())!=null){
            tokens=line.split(":");
            if(tokens[fieldCompare].equals(fieldValue) && tokens[fieldCompare2].equals(fieldValue2)){
                finded=line;
              break;
            }
        }
        br.close();
        return finded;
    }
}
