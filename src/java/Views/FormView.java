/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

/**
 *
 * @author alumne
 */
public class FormView {
    
    public String createFormView(){
        
        String formBuilder = ""
                + "<form method='post' action='MainController' id='formToCreateForm'>"
                           + "<table id=tableCrearForm>"
                                + "<tr>"
                                    + "<th>Name File:</th>"
                                    + "<td><input type='text' name='nameFile'/></td>"
                                    + "<td id='tdExtension'>.csv</td>"
                                + "</tr>"
                                + "<tr>"
                                    + "<th>Title:</th>"
                                    + "<td><input type='text' name='titleForm'/></td>"
                                    + "<td></td>"
                                + "</tr>"
                                + "<tr >"
                                    + "<th></th>"
                                    + "<th>Name</th>"
                                    + "<th>Type</th>"
                                + "</tr>"              
                                + "<tr>"
                                    + "<th>Field 1:</th>"
                                    + "<td><input type='text' name='camp1' id='camp1'/></td>"
                                    + "<td>"
                                        + "<select name='select1' id='select1'>"
                                            + "<option value='text'>Text</option>"
                                            + "<option value='number'>Number</option>"
                                        + "</select>"
                                    + "</td>"
                                + "</tr>"
                                + "<tr>"
                                    + "<th>Field 2:</th>"
                                    + "<td><input type='text' name='camp2' id='camp2'/></td>"
                                    + "<td>"
                                        + "<select name='select2' id='select2'>"
                                            + "<option value='text'>Text</option>"
                                            + "<option value='number'>Number</option>"
                                        + "</select>"
                                    + "</td>"
                                + "</tr>"
                                 + "<tr>"
                                    + "<th>Field 3:</th>"
                                    + "<td><input type='text' name='camp3' id='camp3'/></td>"
                                    + "<td>"
                                        + "<select name='select3' id='select3'>"
                                            + "<option value='text'>Text</option>"
                                            + "<option value='number'>Number</option>"
                                        + "</select>"
                                    + "</td>"
                                + "</tr>"
                                + "<tr>"
                                    + "<th>Field 4:</th>"
                                    + "<td><input type='text' name='camp4' id='camp4'/></td>"
                                    + "<td>"
                                        + "<select name='select4' id='select4'>"
                                            + "<option value='text'>Text</option>"
                                            + "<option value='number'>Number</option>"
                                        + "</select>"
                                    + "</td>"
                                + "</tr>"
                                 + "<tr>"
                                    + "<th>Field 5:</th>"
                                    + "<td><input type='text' name='camp5' id='camp5'/></td>"
                                    + "<td>"
                                        + "<select name='select5' id='select5'>"
                                            + "<option value='text'>Text</option>"
                                            + "<option value='number'>Number</option>"
                                        + "</select>"
                                    + "</td>"
                                + "</tr>"
                                    + "<th></th>"
                                    + "<td><input type='reset' name='buttonCreateReset' id='buttonCreateReset' value='Reset'/></td>"
                                    + "<td><input type='submit' name='buttonCreateSubmit' id='buttonCreateSubmit' value='Create'/></td>"
                                + "</tr>"
                            + "</table>"
                + "</form>";
        
        return formBuilder;
    }

    public String SearchFormView(String[] formName) {
        String formBuilder=""
                + "<form method='post' id='formToSearch' action='MainController'>"
                    + "<select name='formName'>";
        for(String name:formName){
                formBuilder+="<option value='"+name+"'>"+name+"</option>";
        }             
          formBuilder+="</select>"
                     + "<input type='submit' name='searchFormBtn' value='Modify'>"
                + "</form>";
          return formBuilder;
    }
    
}
