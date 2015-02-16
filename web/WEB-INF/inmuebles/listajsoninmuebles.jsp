
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.List"%>
<%@page import="com.izv.hibernate.Inmueble"%>
<%
    List lista=(List)request.getAttribute("datos");
    out.print("[ ");
    for(int i=0;i<lista.size();i++){
        Inmueble in=(Inmueble)lista.get(i);
        Integer id=in.getId();
        String direccion=in.getDireccion();
        String usuario=in.getUsuario();
        String tipo=in.getTipo();
        BigDecimal precio = in.getPrecio();
        String json="{\"id\":\""+id+"\",\"tipo\":\""+tipo+"\",\"direccion\":\""+direccion+
                "\",\"precio\":\""+precio+"\",\"usuario\":\""+usuario+"\"},";
        if(i==lista.size()-1){
            json=json.substring(0,json.length()-1);
        }
         out.print(json);
    }
    out.print(" ]");
%>
