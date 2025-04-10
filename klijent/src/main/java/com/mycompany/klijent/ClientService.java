/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.klijent;

/**
 *
 * @author Mateja
 */
import java.util.Date;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface ClientService {
    @POST("napraviGrad/{naziv}")
    Call<String> dodajGrad(@Path("naziv") String naziv);
    
    @POST("napraviKategoriju/{naziv}")
    Call<String> dodajKategoriju(@Path("naziv") String naziv);
    
    @POST("napraviPaket/{cena}")
    Call<String> dodajPaket(@Path("cena") int cena);
    
    @POST("napraviKorisnika/{ime}/{prezime}/{email}/{pol}/{idMes}")
    Call<String> dodajKorisnika(@Path("ime") String ime, @Path("prezime") String prezime, @Path("email") String email, @Path("pol") String pol, @Path("idMes") int idMes);
    
    @PATCH("promeniEmailKorisnika/{idKor}/{email}")
    Call<String> promeniEmailKorisnika(@Path("idKor") int idKor, @Path("email") String email);
    
    @PATCH("promeniMestoKorisnika/{idKor}/{idMes}")
    Call<String> promeniMestoKorisnika(@Path("idKor") int idKor, @Path("idMes") int idMes);
    
    @GET("dohvatiSvaMesta")
    Call<String> dohvatiSvaMesta();
    
    @GET("dohvatiSveKorisnike")
    Call<String> dohvatiSveKorisnike();
    
    @POST("napraviAudioSnimak/{naziv}/{trajanje}/{idKor}")
    Call<String> dodajAudioSnimak(@Path("naziv") String naziv, @Path("trajanje") int trajanje, @Path("idKor") int idKor);
    
    @PATCH("promeniNazivAudioSnimka/{idAS}/{naziv}")
    Call<String> promeniNazivAudioSnimka(@Path("idAS") int idAS, @Path("naziv") String naziv);
    
    @POST("dodajKategorijuAudioSnimku/{idAS}/{idKat}")
    Call<String> dodajKategorijuAudioSnimku(@Path("idAS") int idAS, @Path("idKat") int idKat);
    
    @DELETE("izbrisiAudioSnimak/{idAS}/{idKor}")
    Call<String> izbrisiAudioSnimak(@Path("idAS") int idAS, @Path("idKor") int idKor);
    
    @GET("dohvatiSveKategorije")
    Call<String> dohvatiSveKategorije();
    
    @GET("dohvatiSveAudioSnimke") 
    Call<String> dohvatiSveAudioSnimke();
    
    @GET("dohvatiKategorijeAudioSnimka/{idAS}")
    Call<String> dohvatiKategorijeAudioSnimka(@Path("idAS") int idAs);
     
    @PATCH("promeniCenuPaketa/{idPak}/{cena}")
    Call<String> promeniCenuPaketa(@Path("idPak") int idPak, @Path("cena") int cena);
    
    @POST("napraviPretplatu/{idKor}/{idPak}")
    Call<String> dodajPretplatu(@Path("idKor") int idKor,@Path("idPak") int idPak);
    
    @POST("napraviSlusanje/{idKor}/{idAS}/{zapoceto}/{odslusano}")
    Call<String> dodajSlusanje(@Path("idKor") int idKor,@Path("idAS") int idAS, @Path("zapoceto") int zapoceto, @Path("odslusano") int odslusano);
    
    @POST("dodajOmiljeni/{idKor}/{idAS}")    
    Call<String> dodajOmiljeni(@Path("idKor") int idKor,@Path("idAS") int idAS);
    
    @POST("napraviOcenu/{idKor}/{idAS}/{ocena}")
    Call<String> napraviOcenu(@Path("idKor") int idKor,@Path("idAS") int idAS, @Path("ocena") int ocena);
    
    @PATCH("izmeniOcenu/{idKor}/{idAS}/{ocena}")    
    Call<String> izmeniOcenu(@Path("idKor") int idKor,@Path("idAS") int idAS, @Path("ocena") int ocena);
    
    @DELETE("izbrisiOcenu/{idKor}/{idAS}")
    Call<String> izbrisiOcenu(@Path("idKor") int idKor,@Path("idAS") int idAS);
    
    @GET("dohvatiSvePakete")    
    Call<String> dohvatiSvePakete();
    
    @GET("dohvatiPretplateKorisnika/{idKor}")
    Call<String> dohvatiPretplateKorisnika(@Path("idKor") int idKor);
    
    @GET("dohvatiSlusanjaAudioSnimka/{idAS}")
    Call<String> dohvatiSlusanjaAudioSnimka(@Path("idAS") int idAS);
    
    @GET("dohvatiSveOceneAudioSnimka/{idAS}")
    Call<String> dohvatiSveOceneAudioSnimka(@Path("idAS") int idAS);
    
    @GET("dohvatiOmiljeneKorisnika/{idKor}")
    Call<String> dohvatiOmiljeneKorisnika(@Path("idKor") int idKor);
    
    
    
    
   
}
