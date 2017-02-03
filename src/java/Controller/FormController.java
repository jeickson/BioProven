/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.FormClass;
import Model.Persist.FormADO;
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
    public String createForm(HttpServletRequest request, HttpServletResponse response,String ruta) {
        boolean checkForm=true;
        String result="";
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
            FormClass formObj = new FormClass(request.getParameter("nameFile"),request.getParameter("titleForm"));
            boolean campExists=false;
            boolean check;
            for(int i=1;i<=5;i++){
                 if(!formObj.addField(request.getParameter("camp"+i),request.getParameter("select"+i))){
                     campExists=true;
                 }         
             }
                if(!campExists){
                    FormADO formADOObj = new FormADO(ruta);
                    formADOObj.createForm(formObj);
                    result="<div class='createCorrecly'>Form create correctly!</div>";
                    
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
}
