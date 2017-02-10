<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.itextpdf.text.Element"%>
<%@page import="com.itextpdf.text.Phrase"%>
<%@page import="com.itextpdf.text.Chunk"%>
<%@page import="com.itextpdf.text.Paragraph"%>
<%@page import="com.itextpdf.text.FontFactory"%>
<%@page import="com.itextpdf.text.Font"%>
<%@page import="com.itextpdf.text.BaseColor"%>
<%@page import="com.itextpdf.text.pdf.PdfPTable"%>
<%@page import="java.io.OutputStream"%>
<%-- 
    Document   : pdf
    Created on : 10-feb-2017, 21:58:54
    Author     : alumne
--%>


<%@page contentType="text/html" pageEncoding="UTF-8" session="true" import="
            com.itextpdf.text.Document,
           com.itextpdf.text.DocumentException, 
           com.itextpdf.text.pdf.PdfWriter"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
        <title></title>
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();
            HashMap<String,Integer>valors= ( HashMap<String,Integer>)sesion.getAttribute("valors");

                response.setContentType("application/pdf");
                OutputStream outa= response.getOutputStream();
                
                try{
                    try{
                        Document documento = new Document();
                        PdfWriter.getInstance(documento,outa).setInitialLeading(20);
                        HttpSession sestion = request.getSession();
                        List<String>arrayrows= (List<String>)sestion.getAttribute("arrayrows");
                        String[] finalArrayRowFinded={};
                        finalArrayRowFinded=arrayrows.toArray(finalArrayRowFinded);
                        String title=(String)sestion.getAttribute("formSelected");
                        String rtitle=title.substring(0,title.length()-4);
                        documento.open();                                
                        documento.add(new Paragraph(rtitle,
				FontFactory.getFont("arial",   
				22,                            
				Font.ITALIC,                   
				BaseColor.BLACK)));    
                        documento.add(new Phrase(Chunk.NEWLINE));
                        documento.add(new Phrase(Chunk.NEWLINE));
                        if(finalArrayRowFinded.length>1){
                                PdfPTable tabla = new PdfPTable(finalArrayRowFinded[0].split(":").length);
                                String[] camps=finalArrayRowFinded[0].split(":");  
                                for(String field:camps){
                                      tabla.addCell(field.split(";")[0]);               
                                }
                                for(int i=1;i<finalArrayRowFinded.length;i++){
                                        for (String field:finalArrayRowFinded[i].split(":")){
                                                 tabla.addCell(field);
                                        }

                                }
                                documento.add(tabla);
                        }else{
                                documento.add(new Paragraph("There are no rows  according this filter",
				FontFactory.getFont("arial",   
				14,                            
				Font.ITALIC,                   
				BaseColor.BLACK))); 
                        }
                        
                        documento.close();       
                               
                    }catch(Exception e){
                        
                    }
                    
                }finally{
                    outa.close();
                }
                
        %>
    </body>
</html>
