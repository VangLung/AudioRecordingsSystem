/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import paketSlanje.Task;

/**
 *
 * @author Mateja
 */

@Path("services")
public class Services {
    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup="myTopic1Server")
    private Topic myTopic1Server;
    
    @Resource(lookup="myTopic1Subsystem")
    private Topic myTopic1Subsystem;
    
    @Resource(lookup="myTopic2Server")
    private Topic myTopic2Server;
    
    @Resource(lookup="myTopic2Subsystem")
    private Topic myTopic2Subsystem;
    
    @Resource(lookup="myTopic3Server")
    private Topic myTopic3Server;
    
    @Resource(lookup="myTopic3Subsystem")
    private Topic myTopic3Subsystem;

    @PersistenceContext
    private EntityManager em;
    
    
    
   
    
    
    @Path("test")
    @GET
    public Response ping(){
        return Response
                .ok("ping")
                .build();
    }
    
    String sendTaskSubsystem(int taskNum, List<Object> l, int sysNum) throws JMSException
    {
        Topic serverTopics[] = {myTopic1Server, myTopic2Server, myTopic3Server};
        Topic subsystemTopics[] = {myTopic1Subsystem, myTopic2Subsystem, myTopic3Subsystem};
        
        
        Topic serverTopic = serverTopics[sysNum - 1];
        Topic subsystemTopic = subsystemTopics[sysNum - 1];
        
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        ObjectMessage objMsg = context.createObjectMessage();
        Task task1 = new Task(l, taskNum);
        objMsg.setObject(task1);
        producer.send(serverTopic, objMsg);
            
        JMSConsumer consumer = context.createConsumer(subsystemTopic);
        TextMessage txtMsg = (TextMessage) consumer.receive();
        return txtMsg.getText();
    }
    
    //task 1
    @Path("napraviGrad/{naziv}")
    @POST
    public Response napraviGrad(@PathParam("naziv") String naziv) throws JMSException
    {
        int taskNum = 1;        
        List<Object> lista = new ArrayList<>();
        lista.add(naziv);
        return Response.ok(sendTaskSubsystem(taskNum,lista,1)).build();      
    }
    
    //task 2
    @Path("napraviKorisnika/{ime}/{prezime}/{email}/{pol}/{idMes}")
    @POST
    public Response napraviKorisnika(@PathParam("ime") String ime, @PathParam("prezime") String prezime, @PathParam("email") String email, @PathParam("pol") String pol, @PathParam("idMes") int idMes) throws JMSException
    {
        int taskNum = 2;        
        List<Object> lista = new ArrayList<>();
        lista.add(ime);
        lista.add(prezime);
        lista.add(email);
        lista.add(pol);
        lista.add(idMes);
        return Response.ok(sendTaskSubsystem(taskNum,lista,1)).build();      
    }
    
    //task 3
    @Path("promeniEmailKorisnika/{idKor}/{email}")
    @PATCH
    public Response promeniEmailKorisnika(@PathParam("idKor") int idKor, @PathParam("email") String email) throws JMSException
    {
        int taskNum = 3;
        List<Object> lista = new ArrayList<>();
        lista.add(idKor);
        lista.add(email);
        return Response.ok(sendTaskSubsystem(taskNum, lista, 1)).build();
    }
    
    //task 4
    @Path("promeniMestoKorisnika/{idKor}/{idMes}")
    @PATCH
    public Response promeniMestoKorisnika(@PathParam("idKor") int idKor, @PathParam("idMes") int idMes) throws JMSException
    {
        int taskNum = 4;
        List<Object> lista = new ArrayList<>();
        lista.add(idKor);
        lista.add(idMes);
        return Response.ok(sendTaskSubsystem(taskNum, lista, 1)).build();
    }
    
    //task 5
    @Path("napraviKategoriju/{naziv}")
    @POST
    public Response napraviKategoriju(@PathParam("naziv") String naziv) throws JMSException
    {
        int taskNum = 5;
        List<Object> lista = new ArrayList<>();
        lista.add(naziv);
        return Response.ok(sendTaskSubsystem(taskNum,lista,2)).build();
    }
    
    //task 6
    @Path("napraviAudioSnimak/{naziv}/{trajanje}/{idKor}")
    @POST
    public Response napraviAudioSnimak(@PathParam("naziv") String naziv, @PathParam("trajanje") int trajanje, @PathParam("idKor") int idKor) throws JMSException
    {
        int taskNum = 6;
        List<Object> lista = new ArrayList<>();
        lista.add(naziv);
        lista.add(trajanje);
        lista.add(idKor);
        return Response.ok(sendTaskSubsystem(taskNum, lista, 2)).build();
    }
    
    //task 7
    @Path("promeniNazivAudioSnimka/{idAS}/{naziv}")
    @PATCH
    public Response promeniNazivAudioSnimka(@PathParam("idAS") int idAS, @PathParam("naziv") String naziv) throws JMSException
    {
        int taskNum = 7;
        List<Object> lista = new ArrayList<>();
        lista.add(idAS);
        lista.add(naziv);
        return Response.ok(sendTaskSubsystem(taskNum, lista, 2)).build();
    }
    
    //task 8
    @Path("dodajKategorijuAudioSnimku/{idAS}/{idKat}")
    @POST
    public Response dodajKategorijuAudioSnimku(@PathParam("idAS") int idAS, @PathParam("idKat") int idKat) throws JMSException
    {
        int taskNum = 8;
        List<Object> lista = new ArrayList<>();
        lista.add(idAS);
        lista.add(idKat);
        return Response.ok(sendTaskSubsystem(taskNum, lista, 2)).build();
    }
    
    //task 9
    @Path("napraviPaket/{cena}")
    @POST
    public Response napraviPaket(@PathParam("cena") int cena) throws JMSException
    {
        int taskNum = 9;
        List<Object> lista = new ArrayList<>();
        lista.add(cena);
        return Response.ok(sendTaskSubsystem(taskNum,lista,3)).build();
    }
    
    //task 10
    @Path("promeniCenuPaketa/{idPak}/{cena}")
    @PATCH
    public Response promeniCenuPaketa(@PathParam("idPak") int idPak,@PathParam("cena") int cena) throws JMSException
    {
        int taskNum = 10;
        List<Object> lista = new ArrayList<>();
        lista.add(idPak);
        lista.add(cena);
        return Response.ok(sendTaskSubsystem(taskNum, lista, 3)).build();
    }
    
    //task 11
    @Path("napraviPretplatu/{idKor}/{idPak}")
    @POST
    public Response napraviPretplatu(@PathParam("idKor") int idKor,@PathParam("idPak") int idPak) throws JMSException
    {
        int taskNum = 11;
        List<Object> lista = new ArrayList<>();
        lista.add(idKor);
        lista.add(idPak);
        return Response.ok(sendTaskSubsystem(taskNum, lista, 3)).build();
    }
    
    //task 12
    @Path("napraviSlusanje/{idKor}/{idAS}/{zapoceto}/{odslusano}")
    @POST
    public Response napraviSlusanje(@PathParam("idKor") int idKor,@PathParam("idAS") int idAS, @PathParam("zapoceto") int zapoceto, @PathParam("odslusano") int odslusano) throws JMSException
    {
        int taskNum = 12;
        List<Object> lista = new ArrayList<>();
        lista.add(idKor);
        lista.add(idAS);
        lista.add(zapoceto);
        lista.add(odslusano);
        return Response.ok(sendTaskSubsystem(taskNum, lista, 3)).build();
    }
    
    //task 13
    @Path("dodajOmiljeni/{idKor}/{idAS}")
    @POST
    public Response dodajOmiljeni(@PathParam("idKor") int idKor,@PathParam("idAS") int idAS) throws JMSException
    {
        int taskNum = 13;
        List<Object> lista = new ArrayList<>();
        lista.add(idKor);
        lista.add(idAS);
        return Response.ok(sendTaskSubsystem(taskNum, lista, 3)).build();
    }
    
    //task 14
    @Path("napraviOcenu/{idKor}/{idAS}/{ocena}")
    @POST
    public Response napraviOcenu(@PathParam("idKor") int idKor,@PathParam("idAS") int idAS, @PathParam("ocena") int ocena) throws JMSException
    {
        int taskNum = 14;
        List<Object> lista = new ArrayList<>();
        lista.add(idKor);
        lista.add(idAS);
        lista.add(ocena);
        return Response.ok(sendTaskSubsystem(taskNum, lista, 3)).build();
    }
    
    //task15
    @Path("izmeniOcenu/{idKor}/{idAS}/{ocena}")
    @PATCH
    public Response izmeniOcenu(@PathParam("idKor") int idKor,@PathParam("idAS") int idAS, @PathParam("ocena") int ocena) throws JMSException
    {
        int taskNum = 15;
        List<Object> lista = new ArrayList<>();
        lista.add(idKor);
        lista.add(idAS);
        lista.add(ocena);
        
        
        return Response.ok(sendTaskSubsystem(taskNum, lista, 3)).build();
    }
    
    //task16
    @Path("izbrisiOcenu/{idKor}/{idAS}")
    @DELETE
    public Response izbrisiOcenu(@PathParam("idKor") int idKor,@PathParam("idAS") int idAS) throws JMSException
    {
        int taskNum = 16;
        List<Object> lista = new ArrayList<>();
        lista.add(idKor);
        lista.add(idAS);
        return Response.ok(sendTaskSubsystem(taskNum, lista, 3)).build();
    }
    
    //task 17
    @Path("izbrisiAudioSnimak/{idAS}/{idKor}")
    @DELETE
    public Response izbrisiAudioSnimak(@PathParam("idAS") int idAS, @PathParam("idKor") int idKor) throws JMSException
    {
        int taskNum = 17;
        List<Object> lista = new ArrayList<>();
        lista.add(idAS);
        lista.add(idKor);
        return Response.ok(sendTaskSubsystem(taskNum, lista, 2)).build();
    }
    
    //task 18
    @Path("dohvatiSvaMesta")
    @GET
    public Response dohvatiSvaMesta() throws JMSException
    {
        int taskNum = 18;
        return Response.ok(sendTaskSubsystem(taskNum, null, 1)).build();
    }
    
    //task 19
    @Path("dohvatiSveKorisnike")
    @GET
    public Response dohvatiSveKorisnike() throws JMSException
    {
        int taskNum = 19;
        return Response.ok(sendTaskSubsystem(taskNum, null, 1)).build();
    }
    
    //task 20
    @Path("dohvatiSveKategorije")
    @GET
    public Response dohvatiSveKategorije() throws JMSException
    {
        int taskNum = 20;
        return Response.ok(sendTaskSubsystem(taskNum, null, 2)).build();
    }
    
    //task 21
    @Path("dohvatiSveAudioSnimke") 
    @GET
    public Response dohvatiSveAudioSnimke() throws JMSException
    {
        int taskNum = 21;
        return Response.ok(sendTaskSubsystem(taskNum, null, 2)).build();
    }
    
    
    //task 22
    @Path("dohvatiKategorijeAudioSnimka/{idAS}")
    @GET
    public Response dohvatiKategorijeAudioSnimka(@PathParam("idAS") int idAS) throws JMSException
    {
        int taskNum = 22;
        List<Object> lista = new ArrayList<>();
        lista.add(idAS);
        return Response.ok(sendTaskSubsystem(taskNum, lista, 2)).build();
    }
    
    //task 23
    @Path("dohvatiSvePakete") 
    @GET
    public Response dohvatiSvePakete() throws JMSException
    {
        int taskNum = 23;
        return Response.ok(sendTaskSubsystem(taskNum, null, 3)).build();
    }
    
    //task 24
    @Path("dohvatiPretplateKorisnika/{idKor}")
    @GET
    public Response dohvatiPretplateKorisnika(@PathParam("idKor") int idKor) throws JMSException
    {
        int taskNum = 24;
        List<Object> lista = new ArrayList<>();
        lista.add(idKor);
        return Response.ok(sendTaskSubsystem(taskNum, lista, 3)).build();
    }
    
    //task 25
    @Path("dohvatiSlusanjaAudioSnimka/{idAS}")
    @GET
    public Response dohvatiSlusanjaAudioSnimka(@PathParam("idAS") int idAS) throws JMSException
    {
        int taskNum = 25;
        List<Object> lista = new ArrayList<>();
        lista.add(idAS);
        return Response.ok(sendTaskSubsystem(taskNum, lista, 3)).build();
    }
    
    //task 26
    @Path("dohvatiSveOceneAudioSnimka/{idAS}")
    @GET
    public Response dohvatiSveOceneAudioSnimka(@PathParam("idAS") int idAS) throws JMSException
    {
        int taskNum = 26;
        List<Object> lista = new ArrayList<>();
        lista.add(idAS);
        return Response.ok(sendTaskSubsystem(taskNum, lista, 3)).build();
    }
    
    //task 27
    @Path("dohvatiOmiljeneKorisnika/{idKor}")
    @GET
    public Response dohvatiOmiljeneKorisnika(@PathParam("idKor") int idKor) throws JMSException
    {
        int taskNum = 27;
        List<Object> lista = new ArrayList<>();
        lista.add(idKor);
        return Response.ok(sendTaskSubsystem(taskNum, lista, 3)).build();
    }
    
    
    
    
    
    
    
}
