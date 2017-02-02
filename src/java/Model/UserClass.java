/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author alumne
 */
public class UserClass {
    //Declare
    private String nick;
    private String password;
    private String name;
    private String Surname;
    private String dni;
    private String email;
    private String birthdayDate;
    private String business;
    private String department;
    private String sex;
    private String[] interests;
    
    //constructs

    public UserClass() {
    }

    public UserClass(String nick, String password, String name, String Surname, String dni, String email, String birthdayDate, String department, String sex, String[] interests) {
        this.nick = nick;
        this.password = password;
        this.name = name;
        this.Surname = Surname;
        this.dni = dni;
        this.email = email;
        this.birthdayDate = birthdayDate;
        this.department = department;
        this.sex = sex;
        this.interests = interests;
    }
    
    //Getters and setters
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
    }
    
    //Methods
    public String toCSV (String delimiter){
        
        String lineFormated="";
        lineFormated+=this.getNick()+delimiter+this.getPassword()+delimiter+this.getName()+delimiter+this.getSurname()+delimiter+this.getDni()+delimiter+this.getDepartment();
        lineFormated+=delimiter+this.getEmail()+delimiter+this.getBirthdayDate()+delimiter+this.getSex()+delimiter;      
        
        int interestLength=this.getInterests().length;
        
        for(int i=0;i<interestLength-1;i++){
            lineFormated+=this.getInterests()[i]+",";
        }
        lineFormated+=this.getInterests()[interestLength-1]+delimiter;
        if (this.getBusiness()!=null){
            lineFormated+=this.getBusiness()+":\r\n";
        }
        return lineFormated;
    }
    
}
