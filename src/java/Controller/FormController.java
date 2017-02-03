/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ValidationForm.ValidationForm;
import Views.FormView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumne
 */
public class FormController {
    
    
    
    public String CreateFormView(){
        FormView formView= new FormView();
        return formView.createFormView();
    }
    public boolean createForm(HttpServletRequest request, HttpServletResponse response,String ruta) {
        boolean checkForm=true;
        if(!ValidationForm.onlyVarchar(request.getParameter("nameFile"))){
            checkForm=false;
        }
        if(!ValidationForm.onlyVarchar(request.getParameter("titleForm"))){
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
            
        }
        return checkForm;
    } 
}
