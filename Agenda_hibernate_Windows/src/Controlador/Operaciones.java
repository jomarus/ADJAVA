
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


//INSERTAR USUARIO
public class Operaciones {
     public void altaUsuarios(Usuarios user)
    {
        //Abrir sesion
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        
        //Ejecutar transaccion sino falla
        Transaction tx = session.beginTransaction();
        
        //INSERT
        session.save(user);
        tx.commit();
        session.close();
        
        //Mostrar mensaje
        JOptionPane.showMessageDialog(null,"Insertado correctamente.");
    }
     
//BUSCAR UN USUARIO     
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
//SELECT
public DefaultListModel mostrarUsuarios()
    {
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Usuarios");
        List<Usuarios> lista = q.list();
        Iterator<Usuarios> iter=lista.iterator();
        tx.commit();
        session.close();
        DefaultListModel dlm = new DefaultListModel();
        while(iter.hasNext())
        {
            Usuarios user = (Usuarios) iter.next();
            dlm.addElement(user);
        }
        return dlm;
    }

//SELECT
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

//SELECT con WHERE
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
}
