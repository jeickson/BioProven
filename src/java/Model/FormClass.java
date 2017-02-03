/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.HashMap;

/**
 *
 * @author alumne
 */
public class FormClass {
    //Properties
    private String saveIn;
    private String title;
    HashMap<String,String>fields=null;
    private String[] data;
    
    //Contructs

    public FormClass(String saveIn, String title) {
        this.saveIn = saveIn;
        this.title = title;
        this.fields=new HashMap<>();
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
    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
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
    
}
