
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true" import="
org.jfree.chart.plot.*,
org.jfree.chart.*,
org.jfree.chart.title.TextTitle,
org.jfree.data.general.DefaultPieDataset,
org.jfree.data.general.PieDataset,
org.jfree.data.category.DefaultCategoryDataset,
org.jfree.ui.ApplicationFrame,
org.jfree.ui.RefineryUtilities,
java.io.*,
java.awt.*" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body>
        <%
HttpSession sesion = request.getSession();
 HashMap<String,Integer>valors= ( HashMap<String,Integer>)sesion.getAttribute("valors");


/*Se crea una variable del contenido de la gráfica
de barras*/
DefaultCategoryDataset series = new DefaultCategoryDataset();
/*Ciclo for para depositar los valores de los
seis años tanto para hombres como mujeres*/
Iterator it = valors.keySet().iterator();
        while(it.hasNext()){
            String key = (String) it.next();
            series.setValue(Integer.valueOf(valors.get(key)),
            key,String.valueOf(key));
        }
/*
Generamos la gráfica con los valores depositados
El primer argumento es para el nombre de la gráfica
El segundo para el nombre del eje "x"
El tercero para el nombre del eje "y"
El cuarto para validar si se despliegan las leyendas
El quinto para  validar que se usan tooltips (no se que sean)
El sexto para validar si se jala a alguna URL
*/
JFreeChart grafico = ChartFactory.createBarChart
((String)sesion.getAttribute("fieldSelected"),"","", series,
PlotOrientation.VERTICAL, true,true,false);
//El siguiente código es para desplegar todo en un jpeg
response.setContentType("image/jpeg");
OutputStream salida = response.getOutputStream();
ChartUtilities.writeChartAsPNG(salida,grafico,400,300);
salida.close();
%>
    </body>
</html>
