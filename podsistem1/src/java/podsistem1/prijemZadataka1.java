/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem1;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.jms.Topic;
import paketSlanje.Task;
import entities.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transaction;

/**
 *
 * @author Mateja
 */
public class prijemZadataka1 {

    /**
     * @param args the command line arguments
     */
    @Resource(lookup="jms/__defaultConnectionFactory")
    private static ConnectionFactory connectionFactory;
    
    @Resource(lookup="myTopic1Server")
    private static Topic myTopic1Server;
    
    @Resource(lookup="myTopic1Subsystem")
    private static Topic myTopic1Subsystem;
    
    
    String solveTask1(Task t, EntityManagerFactory emf)
    {
        String ret = "uspesno dodato mesto";
        String naziv = (String) t.getNext();
        Mesto m = new Mesto();
        m.setNaziv(naziv);
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(m);
        transaction.commit();
        
        System.out.println(ret);
        em.close();
        return ret;
    }
    
    String solveTask2(Task t, EntityManagerFactory emf)
    {
        String ret = "uspesno dodat korisnik";
        String ime = (String) t.getNext();
        String prezime = (String) t.getNext();
        String email = (String) t.getNext();
        String pol = (String) t.getNext();
        int idMes = (int) t.getNext();
        EntityManager em = emf.createEntityManager();
        Korisnik k = new Korisnik();
        Mesto m = em.find(Mesto.class, idMes);
        if(m == null)
            return "Ne postoji mesto sa datim id";
        k.setIme(ime);
        k.setPrezime(prezime);
        k.setEmail(email);
        k.setPol(pol);
        k.setIdMes(m);
        
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(k);
        transaction.commit();
        System.out.println(ret);
        em.close();
        return ret;
    }
    
    String solveTask3(Task t, EntityManagerFactory emf)
    {
        String ret = "uspesno izmenje email adresa korisnika";
        int idKor = (int) t.getNext();
        String email = (String) t.getNext();
        EntityManager em = emf.createEntityManager();
        Korisnik k = em.find(Korisnik.class, idKor);
        if(k == null)
            return "ne postoji korisnik sa zadatim id";
        
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        k.setEmail(email);
        em.merge(k);
        transaction.commit();
        
        System.out.println(ret);
        em.close();
        return ret;
    }
    
    String solveTask4(Task t, EntityManagerFactory emf)
    {
        String ret = "uspesno izmenjeno mesto korisnika";
        int idKor = (int) t.getNext();
        int idMes = (int) t.getNext();
        EntityManager em = emf.createEntityManager();
        Korisnik k = em.find(Korisnik.class, idKor);
        if(k == null)
            return "ne postoji korisnik sa zadatim id";
        
        Mesto m = em.find(Mesto.class, idMes);
        if(m == null)
            return "ne postoji mesto sa zadatim id";
        
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        k.setIdMes(m);
        transaction.commit();
        
        System.out.println(ret);
        em.close();
        return ret;
    }
    
    String solveTask18(Task t, EntityManagerFactory emf)
    {
        String ret = "";
        
        EntityManager em = emf.createEntityManager();
        Query selectQuery = em.createQuery("SELECT m FROM Mesto m");
        List<Mesto> lista = selectQuery.getResultList();
        
        for(Mesto m : lista)
            ret = ret + m.toString() + "\n";
        
        em.close();
        return ret;
    }
    
    String solveTask19(Task t, EntityManagerFactory emf)
    {
        String ret = "";
        EntityManager em = emf.createEntityManager();
        Query selectQuery = em.createQuery("SELECT k FROM Korisnik k");
        List<Korisnik> lista = selectQuery.getResultList();
        
        for(Korisnik m : lista)
            ret = ret + m.toString() + "\n";
        
        em.close();
        return ret;
       
    }
    
    public static void main(String[] args) throws JMSException {
        JMSContext context = connectionFactory.createContext();
        JMSConsumer consumer = context.createConsumer(myTopic1Server);
        JMSProducer producer = context.createProducer();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("podsistem1PU");
        System.out.println("ceka");
        while (true) {
            ObjectMessage objMsg = (ObjectMessage) consumer.receive();
            Task t = (Task) objMsg.getObject();
            int num = t.getNum();
            System.out.println("primio zadatak " + num);
            TextMessage ret = context.createTextMessage();
            prijemZadataka1 p = new prijemZadataka1();
            if(num == 1)
                ret.setText(p.solveTask1(t,emf));
            else if(num == 2)
                ret.setText(p.solveTask2(t, emf));
            else if(num == 3)
                ret.setText(p.solveTask3(t,emf));
            else if(num == 4)
                ret.setText(p.solveTask4(t,emf));
            else if(num == 18)
                ret.setText(p.solveTask18(t,emf));
            else if(num == 19)
                ret.setText(p.solveTask19(t,emf));
            
            
            
            producer.send(myTopic1Subsystem, ret);
        }
    
    }
}
