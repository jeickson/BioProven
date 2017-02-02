/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Views.FormView;

/**
 *
 * @author alumne
 */
public class FormController {
    
    
    public String CreateForm(){
        FormView formView= new FormView();
        return formView.createForm();
    }
}
