/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izv.modelo;

import com.izv.hibernate.HibernateUtil;
import com.izv.hibernate.Inmueble;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author ZAFRA
 */
public class ModeloInmueble {
    
   public static List<Inmueble> get() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "from Inmueble";
        Query q = session.createQuery(hql);
        List<Inmueble> pruebas = q.list();
        session.getTransaction().commit();
        session.close();
        return pruebas;
    }

    public static void delete(String id) {
        Session session = HibernateUtil.getSessionFactory().
                openSession();
        session.beginTransaction();
        Inmueble in = (Inmueble) session.load(Inmueble.class,
                Integer.parseInt(id));
        session.delete(in);
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
   
    public static Integer insert(Inmueble in){
        Session session = HibernateUtil.getSessionFactory().
                openSession();
        session.beginTransaction();
        Integer id=(Integer) session.save(in);
        session.getTransaction().commit();
        session.flush();
        session.close();
        return id;
    }
    
    public static Inmueble getobjeto(String id){
        Session session = HibernateUtil.getSessionFactory().
                openSession();
        session.beginTransaction();
        Inmueble in = (Inmueble) session.get(Inmueble.class,Integer.parseInt(id));
        session.close();
        return in;
    }
    
    public static void update(Inmueble in){
        Session session = HibernateUtil.getSessionFactory().
                openSession();
        session.beginTransaction();
        session.update(in);
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
}