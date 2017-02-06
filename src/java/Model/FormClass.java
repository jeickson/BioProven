/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author alumne
 */
public class FormClass {
    //Properties
    private String saveIn;
    private String title;
    private HashMap<String,String>fields=null;
    private List<String> data;
    
    //Contructs

    public FormClass(String saveIn, String title) {
        this.saveIn = saveIn;
        this.title = title;
        this.fields=new HashMap<>();
        this.data=new ArrayList<>();
    }

    public FormClass() {
        this.fields=new HashMap<>();
        this.data=new ArrayList<>();
    }

   
    
    //Getters and Setters

    public String getSaveIn() {
        return saveIn;
    }

    public void setSaveIn(String saveIn) {
        this.saveIn = saveIn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HashMap<String, String> getFields() {
        return fields;
    }

    public void setFields(HashMap<String, String> fields) {
        this.fields = fields;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
   

    //Methods
    
    public boolean addField(String fieldName,String fieldType){
        
        if (this.fields.containsKey(fieldName)){
            return false;
        }
        else{
            this.fields.put(fieldName,fieldType);
            return true;
        }
    }

    public String toCSV(String delimiter) {
        String result="";
        
        Iterator it = this.fields.keySet().iterator();
        while(it.hasNext()){
          String key = (String) it.next();
          result+=key+";"+this.fields.get(key)+delimiter;
        }
        result+="\r\n";
        return result;
    }
    
}
