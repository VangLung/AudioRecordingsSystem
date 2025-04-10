/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem2;

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
import java.util.Date;
import java.time.LocalDate;
import javax.persistence.Query;
import org.eclipse.persistence.config.QueryType;

/**
 *
 * @author Mateja
 */
public class prijemZadataka2 {

    /**
     * @param args the command line arguments
     */
    @Resource(lookup="jms/__defaultConnectionFactory")
    private static ConnectionFactory connectionFactory;
    
    @Resource(lookup="myTopic2Server")
    private static Topic myTopic2Server;
    
    @Resource(lookup="myTopic2Subsystem")
    private static Topic myTopic2Subsystem;
    
    
    String solveTask5(Task t, EntityManagerFactory emf)
    {
        String ret = "uspesno dodata kategorija";
        String naziv = (String) t.getNext();
        Kategorija k = new Kategorija();
        k.setNaziv(naziv);
        
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(k);
        transaction.commit();
        
        System.out.println(ret);
        return ret;
        
    }
    
    String solveTask6(Task t, EntityManagerFactory emf)
    {
        String ret = "uspesno dodak audio snimak";
        String naziv = (String) t.getNext();
        int trajanje = (int) t.getNext();
        int idKor = (int) t.getNext();
        EntityManager em = emf.createEntityManager();
        Korisnik k = em.find(Korisnik.class, idKor);
        if(k == null)
            return "ne postoji korisnik sa datim id";
        
        Audiosnimak as = new Audiosnimak();
        as.setNaziv(naziv);
        as.setTrajanje(trajanje);
        Date now = new Date();
        as.setDatumVreme(now);
        as.setIdKor(k);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(as);
        transaction.commit();
        System.out.println(ret);
        return ret;
    }
    
    String solveTask7(Task t, EntityManagerFactory emf)
    {
        String ret = "uspesno izmenjen naziv audio snimka";
        int idAS = (int) t.getNext();
        String naziv = (String) t.getNext();
        EntityManager em = emf.createEntityManager();
        Audiosnimak as = em.find(Audiosnimak.class, idAS);
        if(as == null)
            return "audio snimak sa datim id ne postoji";
        as.setNaziv(naziv);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(as);
        transaction.commit();  
        System.out.println(ret);
        return ret;
    }
    
    String solveTask8(Task t, EntityManagerFactory emf)
    {
        String ret = "uspesno dodata kategorija";
        int idAS = (int) t.getNext();
        int idKat= (int) t.getNext();
        EntityManager em = emf.createEntityManager();
        Audiosnimak as = em.find(Audiosnimak.class, idAS);
        if(as == null)
            return "audio snimak sa datim id ne postoji";
        
        Kategorija kat = em.find(Kategorija.class, idKat);
        if(kat == null)
            return "kategorija sa datim id ne postoji";
        
        Pripada p = new Pripada();
        p.setIdAS(as);
        p.setIdKat(kat);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(p);
        transaction.commit();  
        System.out.println(ret);
        return ret;
    }
    
    String solveTask17(Task t, EntityManagerFactory emf)
    {
        String ret = "uspesno izbrisan audio snimak";
        int idAS = (int) t.getNext();
        int idKor= (int) t.getNext();
        
        System.out.println(idKor);
        EntityManager em = emf.createEntityManager();
        Audiosnimak as = em.find(Audiosnimak.class, idAS);
        if(as == null)
            return "audio snimak sa datim id ne postoji";
        
        Korisnik kor = em.find(Korisnik.class, idKor);
        if(kor == null)
            return "korisnik sa datim id ne postoji";
        
        if(kor.getIdKor() != as.getIdKor().getIdKor())
            return "zadati korisnik nije vlasnik audio snimka";
        
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(as);
        transaction.commit();  
        System.out.println(ret);
        return ret;
    }
    
    String solveTask20(Task t, EntityManagerFactory emf)
    {
        String ret = "";
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT k FROM Kategorija k");
        List<Kategorija> kategorije = query.getResultList();
        
        for (Kategorija k : kategorije)
            ret += k.toString() + "\n";
        
        System.out.println(ret);
        return ret;
    }
    
    String solveTask21(Task t, EntityManagerFactory emf)
    {
        String ret = "";
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT snimak FROM Audiosnimak snimak");
        List<Audiosnimak> snimci = query.getResultList();
        
        for (Audiosnimak snimak : snimci)
            ret += snimak.toString() + "\n";
        
        System.out.println(snimci.size());
        return ret;
    }
    
    String solveTask22(Task t, EntityManagerFactory emf)
    {
        String ret = "";
        EntityManager em = emf.createEntityManager();
        int idAS = (int) t.getNext();
        Audiosnimak as = em.find(Audiosnimak.class, idAS);
        if(as == null)
            return "audio snimak sa datim id ne postoji";
        Query query = em.createQuery("SELECT k FROM Pripada k WHERE k.idAS = :idAS");
        query.setParameter("idAS", as);
        List<Pripada> pripadanja = query.getResultList();
        
        for(Pripada p : pripadanja)
            ret = ret + p.getIdKat().toString() + "\n";
        
        return ret;
    }
    
    
    
    
    
    public static void main(String[] args) throws JMSException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("podsistem2PU");
        JMSContext context = connectionFactory.createContext();
        JMSConsumer consumer = context.createConsumer(myTopic2Server);
        JMSProducer producer = context.createProducer();
        System.out.println("ceka");
        while (true) {
            ObjectMessage objMsg = (ObjectMessage) consumer.receive();
            Task t = (Task) objMsg.getObject();
            int num = t.getNum();
            System.out.println("primio zadatak " + num);
            TextMessage ret = context.createTextMessage();
            prijemZadataka2 p = new prijemZadataka2();
            if(num == 5)
                ret.setText(p.solveTask5(t,emf));
            if(num == 6)
                ret.setText(p.solveTask6(t,emf));
            
            if(num == 7)
                ret.setText(p.solveTask7(t, emf));
            
            if(num == 8)
                ret.setText(p.solveTask8(t, emf));
            
            if(num == 17)
                ret.setText(p.solveTask17(t, emf));
            
            if(num == 20)
                ret.setText(p.solveTask20(t, emf));
            
            if(num ==21)
                ret.setText(p.solveTask21(t,emf));
            
            if(num == 22)
                ret.setText(p.solveTask22(t, emf));
            
            
            
            producer.send(myTopic2Subsystem, ret);
        }
    
    }
}
