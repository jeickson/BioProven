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
            result="<div class='createError'>you may put charecters no valids</div>";
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
}
