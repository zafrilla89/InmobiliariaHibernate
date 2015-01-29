/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izv.modelo;

import com.izv.hibernate.Fotos;
import com.izv.hibernate.HibernateUtil;
import com.izv.hibernate.Inmueble;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author ZAFRA
 */
public class ModeloFoto {
   
       public static List<Fotos> get() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "from Fotos";
        Query q = session.createQuery(hql);
        List<Fotos> pruebas = q.list();
        session.getTransaction().commit();
        session.close();
        return pruebas;
    }
    
    public static void delete(Fotos fo) {
        Session session = HibernateUtil.getSessionFactory().
                openSession();
        session.beginTransaction();
        session.delete(fo);
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
   
       public static void insert(Fotos fo){
        Session session = HibernateUtil.getSessionFactory().
                openSession();
        session.beginTransaction();
        session.save(fo);
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
    
     public static Fotos getobjeto(String id){
        Session session = HibernateUtil.getSessionFactory().
                openSession();
        session.beginTransaction();
        Fotos fo = (Fotos) session.get(Fotos.class,Integer.parseInt(id));
        session.close();
        return fo;
    }
}
