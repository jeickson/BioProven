/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.FormClass;
import Model.Persist.FormADO;
import Model.UserClass;
import ValidationForm.ValidationForm;
import Views.FormView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alumne
 */
public class FormController {
    
    
    
    public String CreateFormView(){
        FormView formView= new FormView();
        return formView.createFormView();
    }
    
    
    
    public String createForm(HttpServletRequest request, HttpServletResponse response,String ruta) {
        boolean checkForm=true;
        String result="";
        if(!ValidationForm.onlyVarchar(request.getParameter("nameFile"))){
            checkForm=false;
        }
        if(!ValidationForm.onlyVarcharWthSpace(request.getParameter("titleForm"))){
            checkForm=false;
        }
        for(int i=1;i<=5;i++){
           if(!ValidationForm.onlyVarchar(request.getParameter("camp"+i))){
                checkForm=false;
            } 
           if(request.getParameter("select"+i)==null){
                checkForm=false;
            } 
        }
        if(checkForm){
                FormClass formObj = new FormClass(request.getParameter("nameFile"),request.getParameter("titleForm"));
                boolean campExists=false;
                boolean check;
                for(int i=1;i<=5;i++){
                    if(!formObj.addField(request.getParameter("camp"+i),request.getParameter("select"+i))){
                        campExists=true;
                       }         
                }
                if(!campExists){
                    HttpSession session = request.getSession(true);
                    UserClass userObj = (UserClass) session.getAttribute("user");
                    
                    FormADO formADOObj = new FormADO(ruta+"/files/"+userObj.getNick()+"/"+userObj.getNick()+userObj.getDni());
                    try {
                        if(formADOObj.createForm(formObj)){
                            result="<div class='createCorrecly'>Form creates correctly!</div>";
                        }
                        else{
                            result="<div class='createError'>This Form already exists</div>";
                        }
                        
                    } catch (IOException ex) {
                       result="<div class='createError'>it couldnt connect to the BaseData </div>";
                    }
                    
                    
                }
                else{
                    result="<div class='createError'>there are repeated camps </div>";
                }
        }
        else{
            result="<div class='createError'>You may have left empty fields or entered invalid characters</div>";
        }
        return result;
    }
    
    public String searchForm(HttpServletRequest request, HttpServletResponse response,String ruta) throws FileNotFoundException, IOException{
     FormView formView= new FormView();
     List<FormClass> allForms=new ArrayList();
     
     HttpSession session = request.getSession(true);
     UserClass userObj = (UserClass) session.getAttribute("user");
     FormADO formADOObj = new FormADO(ruta+"/files/"+userObj.getNick()+"/"+userObj.getNick()+userObj.getDni());
     
     allForms=formADOObj.getAllForms();
     
     return formView.SearchFormView(allForms);
    }
    
    public String searchFormMenu() {
                
        FormView formView= new FormView();
        return formView.menuSearchView();
    }
    public String listForm(HttpServletRequest request, HttpServletResponse response,String nameFormSelected,String ruta)throws FileNotFoundException, IOException {
        FormClass formObj=new FormClass(nameFormSelected,"");       
        HttpSession session = request.getSession(true);
        UserClass userObj = (UserClass) session.getAttribute("user");
        FormADO formADOObj = new FormADO(ruta+"/files/"+userObj.getNick()+"/"+userObj.getNick()+userObj.getDni()+"/"+nameFormSelected);
     
        formObj.setData(formADOObj.getDataForm());
        String[]  ArrayData={};
        ArrayData=formObj.getData().toArray(ArrayData);
        FormView formView= new FormView();
        
        
       return formView.tableListView(ArrayData,nameFormSelected);
    }
    public String addRowViewForm(HttpServletRequest request, HttpServletResponse response,String nameFormSelected,String ruta) throws FileNotFoundException, IOException{
        FormClass formObj=new FormClass(nameFormSelected,"");       
        HttpSession session = request.getSession(true);
        UserClass userObj = (UserClass) session.getAttribute("user");
        FormADO formADOObj = new FormADO(ruta+"/files/"+userObj.getNick()+"/"+userObj.getNick()+userObj.getDni()+"/"+nameFormSelected);
     
        formObj.setData(formADOObj.getDataForm());
        String[]  ArrayData={};
        FormView formView= new FormView();
        
        return formView.addRowView(formObj.getData().toArray(ArrayData)[0]);
    }

    public boolean addRow(HttpServletRequest request, HttpServletResponse response,String nameFormSelected,String ruta)throws FileNotFoundException, IOException {
        FormClass formObj=new FormClass(nameFormSelected,"");       
        HttpSession session = request.getSession(true);
        UserClass userObj = (UserClass) session.getAttribute("user");
        FormADO formADOObj = new FormADO(ruta+"/files/"+userObj.getNick()+"/"+userObj.getNick()+userObj.getDni()+"/"+nameFormSelected);
        formObj.setData(formADOObj.getDataForm());
        
        boolean check=true;
        String lineBuilder="";
        String[]  ArrayData={};
        ArrayData=formObj.getData().toArray(ArrayData);
        
        for(String camp:ArrayData[0].split(":")){
            if(camp.split(";")[1].equals("text")){
                    if(ValidationForm.noEspecialCharsWthSpace(request.getParameter(camp.split(";")[0]+"Add"))){
                        lineBuilder+=request.getParameter(camp.split(";")[0]+"Add")+":";
                    }
                    else{
                        check=false;
                    }
            }
            else{
                    if(ValidationForm.isInt(request.getParameter(camp.split(";")[0]+"Add"))!=-1){
                        lineBuilder+=request.getParameter(camp.split(";")[0]+"Add")+":";
                    }
                    else{
                        check=false;
                    }
            }
            
        }
        
        if(check){
            lineBuilder+="\r\n";
            formADOObj.insertRow(lineBuilder);
            return true;
        }
        else{
               return false;
        }
    }
    public String consultRowForm(HttpServletRequest request, HttpServletResponse response,String nameFormSelected,String ruta)throws FileNotFoundException, IOException{
        FormClass formObj=new FormClass(nameFormSelected,"");       
        HttpSession session = request.getSession(true);
        UserClass userObj = (UserClass) session.getAttribute("user");
        FormADO formADOObj = new FormADO(ruta+"/files/"+userObj.getNick()+"/"+userObj.getNick()+userObj.getDni()+"/"+nameFormSelected);
     
        formObj.setData(formADOObj.getDataForm());
        String[] ArrayCamps={};
        ArrayCamps= formObj.getData().toArray(ArrayCamps);
        
        String formConsultBuilder="";
        FormView formView= new FormView();
        formConsultBuilder=formView.SelectFieldAndFilterView(ArrayCamps[0]);
        
        return formConsultBuilder;
    }

    public String findRows(HttpServletRequest request, HttpServletResponse response, String nameFormSelected, String ruta)throws FileNotFoundException, IOException {
       FormClass formObj=new FormClass(nameFormSelected,"");       
        HttpSession session = request.getSession(true);
        UserClass userObj = (UserClass) session.getAttribute("user");
        FormADO formADOObj = new FormADO(ruta+"/files/"+userObj.getNick()+"/"+userObj.getNick()+userObj.getDni()+"/"+nameFormSelected);
        
        formObj.setData(formADOObj.findRowsForm());
         String[] ArrayCamps={};
         ArrayCamps= formObj.getData().toArray(ArrayCamps);
        List<String> arrayrows=new ArrayList(); 
        arrayrows.add(ArrayCamps[0]);
                
        int i=0;
        for(String camp:ArrayCamps[0].split(":")){
            if(request.getParameter("selectedFieldToFilter").equals(camp.split(";")[0])){
                break;
            }
            i++;
        }
        for(int k=1;k<ArrayCamps.length;k++){
            if(ArrayCamps[k].split(":")[i].equals(request.getParameter("inputFilter"))){
                arrayrows.add(ArrayCamps[k]);
            }
        }
        String[] finalArrayRowFinded={};
        finalArrayRowFinded=arrayrows.toArray(finalArrayRowFinded);
       
        FormView formView= new FormView();
       
        return formView.tableListView(finalArrayRowFinded,nameFormSelected);
    }
}
