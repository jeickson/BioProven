/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Persist.UserADO;
import Model.UserClass;
import ValidationForm.ValidationForm;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alumne
 */
public class UserController { 
    
    //Declare
   
    
    //Constructs
    public UserController() {
    } 

    
    //methods
    
    public String login (HttpServletRequest request, HttpServletResponse response,String ruta){
        UserClass UserObj=new UserClass();
        UserObj.setPassword(request.getParameter("nickLogin"));
        UserObj.setNick(request.getParameter("passwdLogin"));
        UserADO userADOObj = new UserADO(ruta+"/DataBase/users.csv");
        int checkLogin=userADOObj.findUserTwoField(UserObj,0,1);
        String result="";
                switch (checkLogin) {
                    case 0:
                          result ="<p>Nick or password no valid</p>";
                        break;
                    case 1:
                        result ="OK";

                                HttpSession session = request.getSession();
                                if(session.getAttribute("user")==null){
                                   session.setAttribute("user",UserObj);
                                   session.setMaxInactiveInterval(-1);
                                }else{
                                 result ="there already is an active session";
                                }
                        

                        break;
                    default:
                        result ="<p>Error access to Base Data</p>";
                        break;
                }

        return result;
    }
    
    public String register (HttpServletRequest request, HttpServletResponse response,String ruta){
        Boolean check=true;
        String result="";
        ValidationForm validation=new ValidationForm();
        if (!validation.noEspecialNick(request.getParameter("user"))){
            check=false;
            result+="<p>Nick cant have especials Characters(just '-_')</p>";
        }
        if(!validation.noEspecialChars(request.getParameter("name"))){
            check=false;
            result+="<p>Name must be only characters</p>";
        }
        if(!validation.noEspecialChars(request.getParameter("surname"))){
            check=false;
            result+="<p>Surname must be only characters</p>";
        }
        if(!validation.matchDNI(request.getParameter("dni"))){
            check=false;
            result+="<p>DNI has a bad format</p>";
        }
        if(!validation.noEspecialEmail(request.getParameter("email"))){
            check=false;
            result+="<p>E-mail has a bad format</p>";
        }
        if(!validation.matchDate(request.getParameter("date"))){
            check=false;
            result+="<p>date must be (yyyy-mm-dd)</p>";
        }
        if(request.getParameter("depart")==null){
            check=false;
            result+="<p>Need a department</p>";
        }
        if(request.getParameter("sexe")==null){
            check=false;
            result+="<p>must select a sex</p>";
        }
        if(request.getParameterValues("interests")==null){
            check=false;
            result+="<p>Need to select at least a interest</p>";
        }
        if(!request.getParameter("passwd").equals(request.getParameter("passwd2"))){
            check=false;
            result+="<p>Confirm Password is not de same</p>";
        }
        if(check){
             UserClass UserObj=new UserClass(request.getParameter("user"),request.getParameter("passwd"),request.getParameter("name"),
                                             request.getParameter("surname"),request.getParameter("dni"),request.getParameter("email"),
                                             request.getParameter("date"),request.getParameter("depart"),request.getParameter("sexe"),
                                             request.getParameterValues("interests"));
             if (request.getParameter("business")!=null){
                 UserObj.setBusiness(request.getParameter("business"));
             }
             UserADO userADOObj = new UserADO(ruta+"/DataBase/users.csv");
             int checkInsert=userADOObj.insert(UserObj);
                switch (checkInsert) {
                    case 0:
                        result+="<p>Register has Completed, please login now</p>";
                        File folder = new File(ruta+"/files/"+UserObj.getNick());
                        if (!folder.exists()) { 
                            folder.mkdir();
                        }
                        File file = new File(ruta+"/files/"+UserObj.getNick()+"/"+UserObj.getNick()+UserObj.getDni()+".csv");
                        // if file doesnt exists, then create it
                        if (!file.exists()) {
                            try {
                                file.createNewFile();
                            } catch (IOException ex) {
                                result+="<p>it couldnt create file user</p>";
                            }
                            
                        }   
                        File folder2 = new File(ruta+"/files/"+UserObj.getNick()+"/"+UserObj.getNick()+UserObj.getDni());
                        if (!folder2.exists()) { 
                            folder2.mkdir();
                        }
                        break;
                    case 1:
                        result+="<p>User already Exists</p>";
                        break;
                    default:
                        result+="<p>Error access to Base Data</p>";
                        break;
                }
             
        }
        return result;
    }
    
}
