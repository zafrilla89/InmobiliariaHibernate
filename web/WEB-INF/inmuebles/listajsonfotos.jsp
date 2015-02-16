<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.List"%>
<%@page import="com.izv.hibernate.Fotos"%>
<%
    List lista=(List)request.getAttribute("datos");
    out.print("[ ");
    for(int i=0;i<lista.size();i++){
        Fotos fo=(Fotos)lista.get(i);
        Integer id=fo.getId();
        String nombre=fo.getNombre();
        Integer idinmueble=fo.getIdinmueble();
        String json="{\"id\":\""+id+"\",\"nombre\":\""+nombre+"\",\"idinmueble\":\""+idinmueble+"\"},";
        if(i==lista.size()-1){
            json=json.substring(0,json.length()-1);
        }
         out.print(json);
    }
    out.print(" ]");
%>
