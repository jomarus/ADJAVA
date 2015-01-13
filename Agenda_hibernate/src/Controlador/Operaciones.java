/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import Modelo.*;

/**
 *
 * @author David
 */
public class Operaciones {
    
     public void altaUsuarios(Usuarios user)
    {
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        session.close();
        JOptionPane.showMessageDialog(null,"Insertado correctamente.");
    }
     
      public void altaNoticias(Noticias noti)
    {
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        session.save(noti);
        tx.commit();
        session.close();
        JOptionPane.showMessageDialog(null,"Insertado correctamente.");
    }
      
       public Usuarios buscaUser(String usuario)
    {
        Usuarios usr;
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        usr=(Usuarios)session.get(Usuarios.class,usuario);
        tx.commit();
        session.close();
       
        return usr;
    }
       
       public DefaultListModel obtenerNoticiasUser(String user){
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from noticias where noticias.usuario=" + user +"");
        List<Noticias> lista = q.list();
        Iterator<Noticias> iter=lista.iterator();
        tx.commit();
        session.close();
        DefaultListModel dlm = new DefaultListModel();
        while(iter.hasNext())
        {
            Noticias noti = (Noticias) iter.next();
           
            dlm.addElement(noti);
        }
        return dlm;
     }
       
        public DefaultListModel mostrarNoticias()
    {
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Noticias");
        List<Noticias> lista = q.list();
        Iterator<Noticias> iter=lista.iterator();
        tx.commit();
        session.close();
        DefaultListModel dlm = new DefaultListModel();
        while(iter.hasNext())
        {
            Noticias noti = (Noticias) iter.next();
            dlm.addElement(noti);
        }
        return dlm;
    }
    
}
