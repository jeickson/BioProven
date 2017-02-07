/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Persist;

import Model.UserClass;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumne
 */
public class UserADO {

    String ruta;

    public UserADO(String ruta) {
        this.ruta = ruta;
    }
    
    
    //methods
    
    public int insert(UserClass user){
        try {
            if(this.findUserEspecificField(user,4)!=null){
                return 1;
            }
            else{
                
                DataFileManagement dataFileObj= new DataFileManagement(this.ruta);
                dataFileObj.insertRegister(user.toCSV(":"));
                return 0;
            }
            
        }catch(FileNotFoundException e){
            return 2;
        } catch (IOException ex) {
            return 3;
        }
    }
    
    
    public String findUserEspecificField (UserClass user,int n) throws FileNotFoundException,IOException{        
        DataFileManagement dataFileObj= new DataFileManagement(this.ruta);     
        return dataFileObj.findBySpecificField(user.getDni(),n);
           
    }
    public int findUserTwoField (UserClass user,int n,int n2){
        DataFileManagement dataFileObj= new DataFileManagement(this.ruta); 
         try {
            if(dataFileObj.findByTwoField(user.getNick(),user.getPassword(),n,n2)!=null){
                return 1;
            }
            else{
                return 0;
            }
            
        }catch(FileNotFoundException e){
            return 2;
        } catch (IOException ex) {
            return 3;
        }
    }
    public String getUser (UserClass user,int n,int n2){
        DataFileManagement dataFileObj= new DataFileManagement(this.ruta);        
        try {
            return dataFileObj.findByTwoField(user.getNick(),user.getPassword(),n,n2);
        } catch (IOException ex) {
            return null;
        }
            
    }
}
