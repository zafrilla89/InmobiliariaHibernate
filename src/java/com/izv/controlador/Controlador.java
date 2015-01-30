/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izv.controlador;

import com.izv.hibernate.Fotos;
import com.izv.hibernate.Inmueble;
import com.izv.modelo.ModeloFoto;
import com.izv.modelo.ModeloInmueble;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author 2dam
 */
@WebServlet(name = "Controlador", urlPatterns = {"/control"})
@MultipartConfig

public class Controlador extends HttpServlet {

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
        String destino = "index.html";
        boolean forward = false;
        String target, op, action, view;

        //...
        target = request.getParameter("target");
        op = request.getParameter("op");
        action = request.getParameter("action");
        view = request.getParameter("view");

        if (target.equals("inmueble") && op.equals("select") && action.equals("view")) {
            forward = true;
            destino = "WEB-INF/inmuebles/ver.jsp";
            request.setAttribute("datos", ModeloInmueble.get());
        } else {
            if (target.equals("inmueble") && op.equals("delete") && action.equals("op")) {
                forward = false;
                String id = request.getParameter("id");
                ModeloInmueble.delete(id);
                ArrayList<Fotos> lis = fotosdelinmueble(id);
                for (int x = 0; x < lis.size(); x++) {
                    Fotos fo=lis.get(x);
                    File file = new File(getServletContext().getRealPath("/") + "fotos/"+fo.getNombre());
                    file.delete();
                    ModeloFoto.delete(fo);
                }
                destino = "control?target=inmueble&op=select&action=view";
            } else {
                if (target.equals("inmueble") && op.equals("insert") && action.equals("view")) {
                    forward = true;
                    destino = "WEB-INF/inmuebles/nuevo.jsp";
                } else {
                    if (target.equals("inmueble") && op.equals("insert") && action.equals("op")) {
                        if (esNumero(request.getParameter("precio"))) {
                            forward = false;
                            Inmueble in = new Inmueble();
                            in.setTipo(request.getParameter("tipo"));
                            in.setDireccion(request.getParameter("direccion"));
                            in.setPrecio(new BigDecimal(request.getParameter("precio")));
                            Date horaActual = new Date();
                            in.setFechaalta(horaActual);
                            ModeloInmueble.insert(in);
                            destino = "control?target=inmueble&op=select&action=view";
                        } else {
                            forward = true;
                            request.setAttribute("no", "El precio tiene que ser un numero");
                            destino = "control?target=inmueble&op=insert&action=view";
                        }
                    } else {
                        if (target.equals("inmueble") && op.equals("update") && action.equals("view")) {
                            forward = true;
                            request.setAttribute("inmueble", ModeloInmueble.getobjeto(request.getParameter("id")));
                            destino = "WEB-INF/inmuebles/modificar.jsp";
                        } else {
                            if (target.equals("inmueble") && op.equals("update") && action.equals("op")) {
                                if (esNumero(request.getParameter("precio"))) {
                                    forward = false;
                                    Inmueble in = ModeloInmueble.getobjeto(request.getParameter("id"));
                                    in.setTipo(request.getParameter("tipo"));
                                    in.setDireccion(request.getParameter("direccion"));
                                    in.setPrecio(new BigDecimal(request.getParameter("precio")));
                                    ModeloInmueble.update(in);
                                    destino = "control?target=inmueble&op=select&action=view";
                                } else {
                                    forward = true;
                                    Inmueble in = ModeloInmueble.getobjeto(request.getParameter("id"));
                                    request.setAttribute("inmueble", ModeloInmueble.getobjeto(request.getParameter("id")));
                                    request.setAttribute("no", "El precio tiene que ser un numero");
                                    destino = "control?target=inmueble&op=update&action=view";
                                }
                            } else {
                                if (target.equals("fotos") && op.equals("insert") && action.equals("view")) {
                                    forward = true;
                                    request.setAttribute("id", request.getParameter("id"));
                                    destino = "WEB-INF/inmuebles/subirfotos.jsp";
                                } else {
                                    if (target.equals("fotos") && op.equals("insert") && action.equals("op")) {
                                        forward = false;
                                        String id = request.getParameter("id");
                                        Date d = new Date();
                                        String nombre = "id_" + id + "_" + (d.getYear() + 1900) + "_" + (d.getMonth() + 1) 
                                                + "_" + d.getDate() + "_" + d.getHours() + "_" + d.getMinutes() + "_" + d.getSeconds();
                                        response.setContentType("text/html;charset=UTF-8");
                                        Part archivoPost = request.getPart("archivo");
                                        String carpeta = getServletContext().getRealPath("/") + "fotos/";
                                        InputStream input = archivoPost.getInputStream();
                                        try {
                                            OutputStream out = new FileOutputStream(carpeta + nombre + ".jpg");
                                            byte[] buffer = new byte[2048];
                                            int leido;
                                            try {
                                                while ((leido = input.read(buffer)) != -1) {
                                                    out.write(buffer, 0, leido);
                                                }
                                            } catch (IOException e) {
                                            } finally {
                                                out.close();
                                            }
                                        } catch (IOException e) {
                                        } finally {
                                            input.close();
                                            Fotos fo = new Fotos();
                                            fo.setIdinmueble(Integer.parseInt(id));
                                            fo.setNombre(nombre + ".jpg");
                                            ModeloFoto.insert(fo);
                                        }
                                        destino = "control?target=inmueble&op=select&action=view";
                                    } else {
                                        if (target.equals("fotos") && op.equals("update") && action.equals("view")) {
                                            forward = true;
                                            String id = request.getParameter("id");
                                            ArrayList<Fotos> lis = fotosdelinmueble(id);
                                            request.setAttribute("id", id);
                                            request.setAttribute("fotos", lis);
                                            destino = "WEB-INF/inmuebles/verfotos.jsp";
                                        } else {
                                            if (target.equals("fotos") && op.equals("delete") && action.equals("op")) {
                                                forward = false;
                                                Fotos fo = ModeloFoto.getobjeto(request.getParameter("id"));
                                                ModeloFoto.delete(fo);
                                                File file = new File(getServletContext().getRealPath("/") + "fotos/" + request.getParameter("foto"));
                                                file.delete();
                                                destino = "control?target=fotos&op=update&action=view&id=" + request.getParameter("idinmueble");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (forward) {
            request.getRequestDispatcher(destino).forward(request, response);
        } else {
            response.sendRedirect(destino);
        }
    }

    private ArrayList<Fotos> fotosdelinmueble(String id) {
        List<Fotos> fotos = ModeloFoto.get();
        ArrayList<Fotos> lis = new ArrayList<Fotos>();
        for (int x = 0; x < fotos.size(); x++) {
            Fotos fo = fotos.get(x);
            if (fo.getNombre().indexOf("id_" + id + "_") != -1) {
                lis.add(fo);
            }
        }
        return lis;
    }

    private static boolean esNumero(String cadena) {
        try {
            Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
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
