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
public class FormClass {
    //Properties
    private String saveIn;
    private String title;
    private String camp1;
    private String camp2;
    private String camp3;
    private String camp4;
    private String[] data;
    
    //Contructs

    public FormClass(String saveIn, String title, String camp1, String camp2, String camp3, String camp4) {
        this.saveIn = saveIn;
        this.title = title;
        this.camp1 = camp1;
        this.camp2 = camp2;
        this.camp3 = camp3;
        this.camp4 = camp4;
    }
    
    //Getters and Setters

    public String getSaveIn() {
        return saveIn;
    }

    public void setSaveIn(String saveIn) {
        this.saveIn = saveIn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCamp1() {
        return camp1;
    }

    public void setCamp1(String camp1) {
        this.camp1 = camp1;
    }

    public String getCamp2() {
        return camp2;
    }

    public void setCamp2(String camp2) {
        this.camp2 = camp2;
    }

    public String getCamp3() {
        return camp3;
    }

    public void setCamp3(String camp3) {
        this.camp3 = camp3;
    }

    public String getCamp4() {
        return camp4;
    }

    public void setCamp4(String camp4) {
        this.camp4 = camp4;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
    
}
