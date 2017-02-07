/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alumne
 */
@WebServlet(name = "IndexController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String ruta = getServletContext().getRealPath("/WEB-INF");
        UserController userControllerObj=new UserController();  
        RequestDispatcher oDispatcher;
        FormController formControllerObj = new FormController();
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            if(request.getParameter("initBotton")!=null){
                switch(request.getParameter("initBotton")){
                case "Login": 
                        String mssg;
                        mssg=userControllerObj.login(request,response,ruta);
                        if(mssg.equals("OK")){
                             response.sendRedirect("bioproven.jsp");
                        }
                        else{
                            request.setAttribute("message", mssg);

                            oDispatcher=request.getRequestDispatcher("index.jsp");
                            oDispatcher.forward(request,response);
                        }
                        
                        
                     break;
                case "Register":  
                        String result;
                        
                        result=userControllerObj.register(request,response,ruta);
                        request.setAttribute("stringResult", result);
                        oDispatcher=request.getRequestDispatcher("index.jsp");
                        oDispatcher.forward(request,response);

                     break;
                default:
                    break;
                 }
            } else if (request.getParameter("actionForm")!=null){               
                
                String formBuilded;
                
                switch(request.getParameter("actionForm")){
                case "logout": 
                        HttpSession session = request.getSession();
                        session.invalidate();
                        response.sendRedirect("index.jsp");
                     break;
                case "createForm":                  
                        formBuilded=formControllerObj.CreateFormView();
                        request.setAttribute("formBuilded", formBuilded);
                        oDispatcher=request.getRequestDispatcher("bioproven.jsp");
                        oDispatcher.forward(request,response);
                     break;
                case "searchForm":   
                        try{
                            formBuilded=formControllerObj.searchForm(request,response,ruta);
                        }
                        catch(FileNotFoundException notFound){
                            formBuilded="<form id='formToSearch'>Error to connect with DataBase</form>";
                        }
                        catch(IOException ex){
                            formBuilded="<form id='formToSearch'>unexpected error</form>";
                        }
                        request.setAttribute("formBuilded", formBuilded);
                        oDispatcher=request.getRequestDispatcher("bioproven.jsp");
                        oDispatcher.forward(request,response);
                     break;
                default:
                    break;
                 }

            }else if(request.getParameter("buttonCreateSubmit")!=null){
                
                String result="";
                result=formControllerObj.createForm(request,response,ruta);
                request.setAttribute("formBuilded", result);
                oDispatcher=request.getRequestDispatcher("bioproven.jsp");
                oDispatcher.forward(request,response);
                
            }else if(request.getParameter("searchFormBtn")!=null){                
                String formBuilded;
                try{
                    formBuilded=formControllerObj.searchForm(request,response,ruta);
                    formBuilded+=formControllerObj.searchFormMenu();
                     HttpSession session = request.getSession(true);
                     String formName=request.getParameter("formName");
                    session.setAttribute("formSelected",formName);
                }
                catch(FileNotFoundException notFound){
                    formBuilded="<form id='formToSearch'>Error to connect with DataBase</form>";
                }
                catch(IOException ex){
                    formBuilded="<form id='formToSearch'>unexpected error</form>";
                }             
                request.setAttribute("formBuilded", formBuilded);
                oDispatcher=request.getRequestDispatcher("bioproven.jsp");
                oDispatcher.forward(request,response);
                
            }else if(request.getParameter("actionMenuSearch")!=null){
                     HttpSession session = request.getSession();
                     String optionBuilder="";
                    if(session.getAttribute("formSelected")!=null){
                            try{
                                
                                optionBuilder+=formControllerObj.searchFormMenu(); 
                                switch(request.getParameter("actionMenuSearch")){
                                    case "toList":
                                        optionBuilder+=formControllerObj.listForm(request,response,(String) session.getAttribute("formSelected"),ruta);
                                        break;
                                    case "toAdd":
                                         optionBuilder+=formControllerObj.addRowViewForm(request,response,(String) session.getAttribute("formSelected"),ruta);
                                        break;
                                    case "toConsult":
                                        break;
                                    case "toDelete":
                                        break;
                                    case "inform":
                                        break;
                                    default:
                                        break;
                                }
                            }
                            catch(FileNotFoundException notFound){
                                optionBuilder="<form id='formToSearch'>Error to connect with DataBase</form>";
                            }
                            catch(IOException ex){
                                optionBuilder="<form id='formToSearch'>unexpected error</form>";
                            }             
                        request.setAttribute("formBuilded", optionBuilder);
                        oDispatcher=request.getRequestDispatcher("bioproven.jsp");
                        oDispatcher.forward(request,response);
                    }
                    else{
                        response.sendRedirect("bioproven.jsp");
                    }
            }else if (request.getParameter("addRowSubmit")!=null){
                HttpSession session = request.getSession();
                 String result="";
                 if(session.getAttribute("formSelected")!=null){
                     result=formControllerObj.searchFormMenu(); 
                     try{                      
                        if(!formControllerObj.addRow(request,response,(String) session.getAttribute("formSelected"),ruta)){
                             result+="<form class='createError'>You may have left empty fields or entered invalid characters</form>";
                        }
                        else{                          
                            result+="<form class='createCorrecly'>Row has added correctly</form>";
                        }
                     }
                     catch(FileNotFoundException notFound){
                        result="<form class='createError'>Error to connect with DataBase</form>";
                    }
                    catch(IOException ex){
                        result="<form class='createError'>unexpected error</form>";
                    } 
                     request.setAttribute("formBuilded", result);
                     oDispatcher=request.getRequestDispatcher("bioproven.jsp");
                     oDispatcher.forward(request,response);
                 }
            }
            else{
                response.sendRedirect("index.jsp");
            }
           
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
