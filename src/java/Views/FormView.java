/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Model.FormClass;
import java.util.List;

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

    public String SearchFormView(List<FormClass> allForms) {
        String formBuilder=""
                + "<form method='post' id='formToSearch' action='MainController'>"
                    + "<select name='formName'>";
        for(FormClass form:allForms){
                formBuilder+="<option value='"+form.getSaveIn()+"'>"+form.getTitle()+"</option>";
        }             
          formBuilder+="</select>"
                     + "<input type='submit' name='searchFormBtn' value='Search'>"
                + "</form>";
          return formBuilder;
    }

    public String menuSearchView() {
        String menuBuilder=""
                + "<form action='MainController' method='post' id='menuSearchForm'>"
                        + "<button type='submit' name='actionMenuSearch' value='toList'>List</button>"
                        + "<button type='submit' name='actionMenuSearch' value='toAdd'>Add</button>"
                        + "<button type='submit' name='actionMenuSearch' value='toConsult'>Consult</button>"
                        + "<button type='submit' name='actionMenuSearch' value='toDelete'>Delete</button>"
                        + "<button type='submit' name='actionMenuSearch' value='inform'>Inform</button>"
                + "</form>";
        return menuBuilder;
    }

    public String tableListView(String[] ArrayData, String nameFormSelected) {
           String tableBuilder=""
                    + "<div id='tableListForm'><table >"
                        + "<tr>"
                            + "<th colspan='"+ArrayData[0].split(":").length+"'>"+nameFormSelected+"</th>"
                        + "</tr>"
                         + "<tr>";
          String[] camps=ArrayData[0].split(":");  
            for(String field:camps){
                 tableBuilder+="<th>"+field.split(";")[0]+"</th>";                  
            }
            tableBuilder+="</tr>";
            for(int i=1;i<ArrayData.length;i++){
                tableBuilder+="<tr>";
                for (String field:ArrayData[i].split(":")){
                   tableBuilder+="<td>"+field+"</td>";
                }
                tableBuilder+="</tr>";
            }
           tableBuilder+="</table></div>";
           
           return  tableBuilder;
    }

    public String addRowView(String line) {
       String AddRowBuilder=""
               + "<form method='post' action='MainController' id='AddRowForm'>"
                    + "<table id='tableAdd'>";     
            for(String camp:line.split(":")){
                 AddRowBuilder+="<tr>";                
                    AddRowBuilder+="<th>"+camp.split(";")[0]+":</th>";
                    AddRowBuilder+="<td><input type='text'name='"+camp.split(";")[0]+"Add'/></td>";
                 AddRowBuilder+="</tr>";
            } 
            AddRowBuilder+="<tr>"; 
                AddRowBuilder+="<td><input type='reset' name='addRowReset' value='Reset'/></td>";
                AddRowBuilder+="<td><input type='submit' name='addRowSubmit' value='Add'/></td>";
            AddRowBuilder+="</tr>";
       AddRowBuilder+="</table></form>";
       return AddRowBuilder;
    }
    public String SelectFieldAndFilterView(String camps){
       String  consultFormBuilder=""
                + "<form method='post'action='MainController' id='formConsultRow'>"
                    + "<table id='tableConsultRow'>"
                            + "<tr>"
                                + "<th>Field</th>"
                                + "<th>Filter</th>"
                                + "<th></th>"
                            + "</tr>"
                            + "<tr>"
                                + "<td>"
                                    + "<select name='selectedFieldToFilter' id='selectFieldToFilter'>";
       for(String field:camps.split(":")){
                     consultFormBuilder+="<option value='"+field.split(";")[0]+"'>"+field.split(";")[0]+"</option>";
           
       }
                 consultFormBuilder+="</select>"
                                + "</td>"
                                + "<td><input type='text' name='inputFilter'/></td>"
                                + "<td><input type='submit' name='consultRowSubmit' value='Search'/></td>"
                            + "</tr>"
                    + "</table>"
            + "</form>";
        return consultFormBuilder;
    }

    
}
