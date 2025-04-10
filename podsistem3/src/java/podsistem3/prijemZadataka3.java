/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem3;

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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Mateja
 */
public class prijemZadataka3 {

    /**
     * @param args the command line arguments
     */
    @Resource(lookup="jms/__defaultConnectionFactory")
    private static ConnectionFactory connectionFactory;
    
    @Resource(lookup="myTopic3Server")
    private static Topic myTopic3Server;
    
    @Resource(lookup="myTopic3Subsystem")
    private static Topic myTopic3Subsystem;
    
    
    String solveTask9(Task t, EntityManagerFactory emf)
    {
        String ret = "uspesno dodat novi paket";
        int cena = (int) t.getNext();
        Paket p = new Paket();
        p.setCena(cena);
       
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(p);
        transaction.commit();
        
        System.out.println(ret);
        return ret;
    }
    
    String solveTask10(Task t,EntityManagerFactory emf)
    {
        String ret = "uspesno promenjena cena paketa";
        int idPak = (int) t.getNext();
        int cena = (int) t.getNext();
        EntityManager em = emf.createEntityManager();
        Paket pak = em.find(Paket.class, idPak);
        if(pak == null)
            return "ne postoji paket sa datim id";
        
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        pak.setCena(cena);
        em.merge(pak);
        transaction.commit();
        
        System.out.println(ret);
        return ret;
    }
    
    String solveTask11(Task t, EntityManagerFactory emf)
    {
        String ret = "uspesno dodata pretplata";
        int idKor = (int) t.getNext();
        int idPak = (int) t.getNext();
        EntityManager em = emf.createEntityManager();
        Korisnik korisnik = em.find(Korisnik.class, idKor);
        if(korisnik == null)
            return "korisnik sa zadatim id ne postoji";
        
        Paket paket = em.find(Paket.class, idPak);
        if(paket == null)
            return "paket sa zadatim id ne postoji";
        
        Query query = em.createQuery("SELECT p FROM Pretplata p WHERE p.idKor =:idKor");
        query.setParameter("idKor", korisnik);
        
        List<Pretplata> pretplate = query.getResultList();
        
        for (Pretplata p : pretplate) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(p.getDatumVremePocetka());
            cal.add(Calendar.MONTH, 1); 
            if (!(new Date().after(cal.getTime()))) { 
                return "datom korisniku jos nije istekla pretplata";
            }
        }

        Pretplata p = new Pretplata();
        p.setIdKor(korisnik);
        p.setIdPaketa(paket);
        p.setDatumVremePocetka(new Date());
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(p);
        transaction.commit();
        
        System.out.println(ret);
        return ret;
    }
    
    String solveTask12(Task t, EntityManagerFactory emf)
    {
        String ret = "uspesno dodato slusanje";
        int idKor = (int) t.getNext();
        int idAS = (int) t.getNext();
        int zapoceto = (int) t.getNext();
        int odslusano = (int) t.getNext();
        EntityManager em = emf.createEntityManager();
        Korisnik korisnik = em.find(Korisnik.class, idKor);
        if(korisnik == null)
            return "korisnik sa zadatim id ne postoji";
        
        Audiosnimak audioSnimak = em.find(Audiosnimak.class, idAS);
        if(audioSnimak == null)
            return "audio snimak sa zadatim id ne postoji";
        
        if(audioSnimak.getTrajanje() < odslusano || odslusano < zapoceto)
            return "kraj slusanja nije u validnim granicama";
        
        if(audioSnimak.getTrajanje() < zapoceto || zapoceto < 0)
            return "pocetak slusanja nije u validnim granicama";
        
        Slusanje s = new Slusanje();
        s.setDatumVreme(new Date());
        s.setIdAS(audioSnimak);
        s.setIdKor(korisnik);
        s.setZapoceto(zapoceto);
        s.setOdslusano(odslusano);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(s);
        transaction.commit();
        
        System.out.println(ret);
        return ret;
    }
    
    String solveTask13(Task t, EntityManagerFactory emf)
    {
        String ret = "uspesno dodato u omiljene";
        int idKor = (int) t.getNext();
        int idAS = (int) t.getNext();
        EntityManager em = emf.createEntityManager();
        Korisnik korisnik = em.find(Korisnik.class, idKor);
        if(korisnik == null)
            return "korisnik sa zadatim id ne postoji";
        
        Audiosnimak audioSnimak = em.find(Audiosnimak.class, idAS);
        if(audioSnimak == null)
            return "audio snimak sa zadatim id ne postoji";
        
        
        Query query = em.createQuery("SELECT o FROM Omiljeni o WHERE o.idKor =:idKor AND o.idAS =:idAS");
        query.setParameter("idKor", korisnik);
        query.setParameter("idAS", audioSnimak);
        
        List<Omiljeni> omiljeni = query.getResultList();
        if(!omiljeni.isEmpty())
            return "zadati audio snimak se vec nalazi u listi omiljenih datog korisnika";
        
        Omiljeni omiljen = new Omiljeni();
        omiljen.setIdAS(audioSnimak);
        omiljen.setIdKor(korisnik);
        
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(omiljen);
        transaction.commit();
        
        System.out.println(ret);
        return ret;
    }
    
    String solveTask14(Task t, EntityManagerFactory emf)
    {
        String ret = "uspesno dodata ocena";
        int idKor = (int) t.getNext();
        int idAS = (int) t.getNext();
        int ocenaVrednost = (int) t.getNext();
        EntityManager em = emf.createEntityManager();
        Korisnik korisnik = em.find(Korisnik.class, idKor);
        if(korisnik == null)
            return "korisnik sa zadatim id ne postoji";
        
        Audiosnimak audioSnimak = em.find(Audiosnimak.class, idAS);
        if(audioSnimak == null)
            return "audio snimak sa zadatim id ne postoji";
        
        
        Query query = em.createQuery("SELECT o FROM Ocena o WHERE o.idKor =:idKor AND o.idAs =:idSnimka");
        query.setParameter("idKor", korisnik);
        query.setParameter("idSnimka", audioSnimak);
        
        List<Ocena> ocene = query.getResultList();
        if(!ocene.isEmpty())
            return "ne mozete 2 puta da ocenite isti audio snimak. Mozete da je azurirate.";
        
        if(ocenaVrednost > 10 || ocenaVrednost < 1)
            return "ocena mora biti u intervalu od 1 do 10";
        
        Ocena ocena = new Ocena();
        ocena.setIdAs(audioSnimak);
        ocena.setIdKor(korisnik);
        ocena.setDatumVreme(new Date());
        ocena.setOcena(ocenaVrednost);
        
        
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(ocena);
        transaction.commit();
        
        System.out.println(ret);
        return ret;
    }
    
    String solveTask15(Task t, EntityManagerFactory emf)
    {
        String ret = "uspesno izmenjena ocena";
        int idKor = (int) t.getNext();
        int idAS = (int) t.getNext();
        int ocenaVrednost = (int) t.getNext();
        EntityManager em = emf.createEntityManager();
        Korisnik korisnik = em.find(Korisnik.class, idKor);
        if(korisnik == null)
            return "korisnik sa zadatim id ne postoji";
        
        Audiosnimak audioSnimak = em.find(Audiosnimak.class, idAS);
        if(audioSnimak == null)
            return "audio snimak sa zadatim id ne postoji";
        
        Query query = em.createQuery("SELECT o FROM Ocena o WHERE o.idKor =:idKor AND o.idAs =:idSnimka");
        query.setParameter("idKor", korisnik);
        query.setParameter("idSnimka", audioSnimak);
        
        List<Ocena> ocene = query.getResultList();
        if(ocene.isEmpty())
            return "korisnik nije ocenio zadati audio snimak";
        
        if(ocenaVrednost > 10 || ocenaVrednost < 1)
            return "ocena mora biti u intervalu od 1 do 10";
        
        Ocena ocena = ocene.get(0);
        ocena.setOcena(ocenaVrednost);
        
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(ocena);
        transaction.commit();
        
        System.out.println(ret);
        return ret;
    }
    
    String solveTask16(Task t, EntityManagerFactory emf)
    {
        String ret = "uspesno izbrisana ocena";
        int idKor = (int) t.getNext();
        int idAS = (int) t.getNext();
        EntityManager em = emf.createEntityManager();
        Korisnik korisnik = em.find(Korisnik.class, idKor);
        if(korisnik == null)
            return "korisnik sa zadatim id ne postoji";
        
        Audiosnimak audioSnimak = em.find(Audiosnimak.class, idAS);
        if(audioSnimak == null)
            return "audio snimak sa zadatim id ne postoji";
        
        
        Query query = em.createQuery("SELECT o FROM Ocena o WHERE o.idKor =:idKor AND o.idAs =:idSnimka");
        query.setParameter("idKor", korisnik);
        query.setParameter("idSnimka", audioSnimak);
        
        List<Ocena> ocene = query.getResultList();
        if(ocene.isEmpty())
            return "korisnik nije ocenio dati audio snimak";
        
        
        Ocena ocena = ocene.get(0);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(ocena);
        transaction.commit();
        
        System.out.println(ret);
        return ret;
    }
    
    String solveTask23(Task t, EntityManagerFactory emf)
    {
        String ret = "";
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT P FROM Paket p");
        List<Paket> paketi = q.getResultList();
        for(Paket p : paketi)
            ret = ret + p.toString() + "\n";
        
        System.out.println(ret);
        return ret;
    }
    
    String solveTask24(Task t, EntityManagerFactory emf)
    {
        String ret = "";
        int idKor = (int) t.getNext();
        EntityManager em = emf.createEntityManager();
         Korisnik korisnik = em.find(Korisnik.class, idKor);
        if(korisnik == null)
            return "korisnik sa zadatim id ne postoji";
        
        Query q = em.createQuery("SELECT P FROM Pretplata p WHERE p.idKor=:idKor");
        q.setParameter("idKor", korisnik);
        List<Pretplata> pretplate = q.getResultList();
        for(Pretplata p : pretplate)
            ret = ret + p.toString() + "\n";
        
        System.out.println(ret);
        return ret;
    }
    
    String solveTask25(Task t, EntityManagerFactory emf)
    {
        String ret = "";
        int idAS = (int) t.getNext();
        EntityManager em = emf.createEntityManager();
        
        Audiosnimak audioSnimak = em.find(Audiosnimak.class, idAS);
        if(audioSnimak == null)
            return "audio snimak sa zadatim id ne postoji";
        
        Query q = em.createQuery("SELECT p FROM Slusanje p WHERE p.idAS=:idAS");
        q.setParameter("idAS", audioSnimak);
        List<Slusanje> slusanja = q.getResultList();
        for(Slusanje slusanje : slusanja)
            ret = ret + slusanje.toString() + "\n";
        
        System.out.println(ret);
        return ret;
    }
    
    String solveTask26(Task t, EntityManagerFactory emf)
    {
        String ret = "";
        int idAS = (int) t.getNext();
        EntityManager em = emf.createEntityManager();
        
        Audiosnimak audioSnimak = em.find(Audiosnimak.class, idAS);
        if(audioSnimak == null)
            return "audio snimak sa zadatim id ne postoji";
        
        Query q = em.createQuery("SELECT p FROM Ocena p WHERE p.idAs=:idAS");
        q.setParameter("idAS", audioSnimak);
        List<Ocena> ocene = q.getResultList();
        for(Ocena ocena : ocene)
            ret = ret + ocena.toString() + "\n";
        
        System.out.println(ret);
        return ret;
    }
    
    String solveTask27(Task t, EntityManagerFactory emf)
    {
        String ret = "";
        int idKor = (int) t.getNext();
        EntityManager em = emf.createEntityManager();
         Korisnik korisnik = em.find(Korisnik.class, idKor);
        if(korisnik == null)
            return "korisnik sa zadatim id ne postoji";
        
        Query q = em.createQuery("SELECT p FROM Omiljeni p WHERE p.idKor=:idKor");
        q.setParameter("idKor", korisnik);
        List<Omiljeni> omiljeni = q.getResultList();
        
        for(Omiljeni omiljen : omiljeni)
            ret = ret + omiljen.toString() + "\n";
        
        System.out.println(ret);
        return ret;
    }
    
    
    
    public static void main(String[] args) throws JMSException {
        JMSContext context = connectionFactory.createContext();
        JMSConsumer consumer = context.createConsumer(myTopic3Server);
        JMSProducer producer = context.createProducer();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("podsistem3PU");
        System.out.println("ceka");
        while (true) {
            ObjectMessage objMsg = (ObjectMessage) consumer.receive();
            Task t = (Task) objMsg.getObject();
            int num = t.getNum();
            System.out.println("primio zadatak " + num);
            TextMessage ret = context.createTextMessage();
            prijemZadataka3 p = new prijemZadataka3();
            if(num == 9)
                ret.setText(p.solveTask9(t,emf));
            
            else if(num == 10)
                ret.setText(p.solveTask10(t, emf));
            
            else if(num == 11)
                ret.setText(p.solveTask11(t,emf));
            
            else if(num == 12)
                ret.setText(p.solveTask12(t,emf));
            
            else if(num == 13)
                ret.setText(p.solveTask13(t, emf));
            
            else if(num == 14)
                ret.setText(p.solveTask14(t, emf));
            
            else if(num == 15)
                ret.setText(p.solveTask15(t, emf));
            
            else if(num == 16)
                ret.setText(p.solveTask16(t,emf));
            
            else if(num == 23)
                ret.setText(p.solveTask23(t, emf));
            
            else if(num == 24)
                ret.setText(p.solveTask24(t, emf));
            
            else if(num == 25)
                ret.setText(p.solveTask25(t, emf));
            
            else if(num == 26)
                ret.setText(p.solveTask26(t, emf));
            
            else if(num == 27)
                ret.setText(p.solveTask27(t, emf));

            
            producer.send(myTopic3Subsystem, ret);
        }
    
    }
}
