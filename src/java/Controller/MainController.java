/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            if(request.getParameter("initBotton")!=null){
                UserController userControllerObj=new UserController();  
                String ruta = getServletContext().getRealPath("/WEB-INF");
                RequestDispatcher oDispatcher;
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
                RequestDispatcher oDispatcher;
                switch(request.getParameter("actionForm")){
                case "logout": 
                        HttpSession session = request.getSession();
                        session.invalidate();
                        response.sendRedirect("index.jsp");
                     break;
                case "createForm":  
                    String formBuilded;
                    FormController formControllerObj = new FormController();
                    formBuilded=formControllerObj.CreateForm();
                    request.setAttribute("formBuilded", formBuilded);
                            oDispatcher=request.getRequestDispatcher("bioproven.jsp");
                            oDispatcher.forward(request,response);
                     break;
                case "searchForm":  
                    
                     break;
                default:
                    break;
                 }

            }else{
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
