package ValidationForm;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author alumne
 */
public class ValidationForm {
    
   public static Double isDouble(String value){
       double returned=0.;
        try{
            returned=Double.parseDouble(value);
        }catch(Exception error){
            
        }
        return returned;
    }
    
     public static Double  valuesBetween(double value,double min, double max){
       double returned=-1;
         if(value> min && value< max){
           returned=value;
       }
        return returned;
    }
    
    public static float isFloat(String value){
       float returned=0;
        try{
            returned=Float.parseFloat(value);
        }catch(Exception error){
            
        }
        return returned;
    }
    public static int isInt(String value){
      int returned=0;
        try{
            returned=Integer.parseInt(value);
        }catch(Exception error){
            
        }
        return returned;
    }
    public static int intBetween(int value,int min, int max){
       int returned=0;
         if(value> min && value< max){
           returned=value;
       }
        return returned;
    }
    public static boolean noEspecialChars (String nombre){
        Pattern pat = Pattern.compile("^[a-zA-Z]+$");
        Matcher mat = pat.matcher(nombre);
       return mat.matches();
    }
    public static boolean noEspecialNick (String nombre){
        Pattern pat = Pattern.compile("^[A-Za-z0-9-_]+$");
        Matcher mat = pat.matcher(nombre);
       return mat.matches();
    }
    public static boolean noEspecialEmail (String nombre){
        Pattern pat = Pattern.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,3})$");
        Matcher mat = pat.matcher(nombre);
       return mat.matches();
    }
    public static boolean matchDNI (String nombre){
        Pattern pat = Pattern.compile("(\\d{8})([-]?)([A-Za-z]{1})");
        Matcher mat = pat.matcher(nombre);
       return mat.matches();
    }
    public static boolean matchDate (String date){
        Pattern pat = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
        Matcher mat = pat.matcher(date);
       return mat.matches();
    }
}
