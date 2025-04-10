
package com.mycompany.klijent;


import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import sun.print.resources.serviceui;

public class myClient {
    
    public static void callURL(Call<String> call) throws InterruptedException
    {
      
        call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) 
                        System.out.println("Odgovor sa servera:\n" + response.body());
                     else 
                        System.out.println("Greška: " + response.code());    
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    System.out.println("Greška u povezivanju: " + t.getMessage());
                }
            });
        
        Thread.sleep(2000);
        
    }
    
    public static int idKor = -1;
    
    public static void task1(Scanner scanner, ClientService service) throws InterruptedException
    {
        System.out.println("unesite naziv grada");
            String naziv = scanner.next();
            Call<String> call = service.dodajGrad(naziv);
            callURL(call);
    }
    
    public static void task2(Scanner scanner, ClientService service) throws InterruptedException
    {
        System.out.println("unesite ime korisnika");
        String ime = scanner.next();
        System.out.println("unesite prezime korisnika");
        String prezime = scanner.next();
        System.out.println("unesite email korisnika");
        String email = scanner.next();
        System.out.println("unesite pol korisnika");
        String pol = scanner.next();
        System.out.println("unesite id mesta iz kog dolazi");
        int mesto = scanner.nextInt();
        Call<String> call = service.dodajKorisnika(ime, prezime, email, pol, mesto);
        callURL(call);
    }
    
    public static void task3(Scanner scanner, ClientService service) throws InterruptedException
    {
        
        
        System.out.println("unesite novi email korisnika");
        String email = scanner.next();
        Call<String> call = service.promeniEmailKorisnika(idKor, email);
        callURL(call);
    }
    
    public static void task4(Scanner scanner, ClientService service) throws InterruptedException
    {
        
        System.out.println("unesite id novog mesta");
        int idMes = scanner.nextInt();
        Call<String> call = service.promeniMestoKorisnika(idKor, idMes);
        callURL(call);
    }
    
    public static void task5(Scanner scanner, ClientService service) throws InterruptedException
    {
        System.out.println("unesite naziv kategorije");
            String naziv = scanner.next();
            Call<String> call = service.dodajKategoriju(naziv);
            callURL(call);
    }
    
    public static void task6(Scanner scanner, ClientService service) throws InterruptedException
    {
        System.out.println("unesite naziv audio snimak");
        String naziv = scanner.next();
        System.out.println("unesite trajanje u sekundama");
        int trajanje = scanner.nextInt();
        
        
        Call<String> call = service.dodajAudioSnimak(naziv, trajanje, idKor);
        callURL(call);
    }
    
    public static void task7(Scanner scanner, ClientService service) throws InterruptedException
    {
        System.out.println("unesite id audio snimka");
        int idAS = scanner.nextInt();
        System.out.println("unesite nov naziv audio snimka");
        String naziv = scanner.next();
        Call<String> call = service.promeniNazivAudioSnimka(idAS, naziv);
        callURL(call);
    }
    
     public static void task8(Scanner scanner, ClientService service) throws InterruptedException
    {
        System.out.println("unesite id audio snimka");
        int idAS = scanner.nextInt();
        System.out.println("unesite id kategorije");
        int idKat = scanner.nextInt();
        Call<String> call = service.dodajKategorijuAudioSnimku(idAS, idKat);
        callURL(call);
    }
    
    public static void task9(Scanner scanner, ClientService service) throws InterruptedException
    {
        System.out.println("unesite cenu paketa");
            int cena = scanner.nextInt();
            Call<String> call = service.dodajPaket(cena);
            callURL(call);
    }
    
    public static void task10(Scanner scanner, ClientService service) throws InterruptedException
    {
        System.out.println("unesite id paketa ciju cenu zelite da izmenite");
        int idPak = scanner.nextInt();
        System.out.println("unesite novu cenu paketa");
        int cena = scanner.nextInt();
        
        Call<String> call = service.promeniCenuPaketa(idPak, cena);
        callURL(call);
    }
    
    public static void task11(Scanner scanner, ClientService service) throws InterruptedException
    {
        
        System.out.println("unesite id paketa na koji zelite da ga pretplatite");
        int idPak = scanner.nextInt();
        Call<String> call = service.dodajPretplatu(idKor, idPak);
        callURL(call);
    }
    
    public static void task12(Scanner scanner, ClientService service) throws InterruptedException
    {
        
        System.out.println("unesite id audio snimka koji se slusa");
        int idAS = scanner.nextInt();
        System.out.println("unesite pocetak slusanja (u sekundama)");
        int zapoceto = scanner.nextInt();
        System.out.println("unesite kraj slusanja");
        int zavrseno = scanner.nextInt();
        Call<String> call = service.dodajSlusanje(idKor, idAS, zapoceto, zavrseno);
        callURL(call);
    }
    
    public static void task13(Scanner scanner, ClientService service) throws InterruptedException
    {
        
        System.out.println("unesite id audio snimka koji zelite da ubacite u omiljeni");
        int idAS = scanner.nextInt();
        
        Call<String> call = service.dodajOmiljeni(idKor, idAS);
        callURL(call);
    }
    
    public static void task14(Scanner scanner, ClientService service) throws InterruptedException
    {
        
        System.out.println("unesite id audio snimka koji zelite da ocenite");
        int idAS = scanner.nextInt();
        System.out.println("unesite ocenu koju zelite da mu date");
        int ocena = scanner.nextInt();
        
        Call<String> call = service.napraviOcenu(idKor, idAS, ocena);
        callURL(call);
    }
    
    public static void task15(Scanner scanner, ClientService service) throws InterruptedException
    {
        
        System.out.println("unesite id audio snimka za koji zelite da izmenite ocenu");
        int idAS = scanner.nextInt();
        System.out.println("unesite novu ocenu koju zelite da mu date");
        int ocena = scanner.nextInt();
        
        Call<String> call = service.izmeniOcenu(idKor, idAS, ocena);
        callURL(call);
    }
    
    public static void task16(Scanner scanner, ClientService service) throws InterruptedException
    {
        
        System.out.println("unesite id audio snimka za koji zelite da izbrisete ocenu");
        int idAS = scanner.nextInt();
        
        Call<String> call = service.izbrisiOcenu(idKor, idAS);
        callURL(call);
    }
    
    
    
     public static void task17(Scanner scanner, ClientService service) throws InterruptedException
    {
        System.out.println("unesite id audio snimka");
        int idAS = scanner.nextInt();
        
        Call<String> call = service.izbrisiAudioSnimak(idAS, idKor);
        callURL(call);
    }
    
    
    public static void task18(Scanner scanner, ClientService service) throws InterruptedException
    {
        Call<String> call = service.dohvatiSvaMesta();
        callURL(call);
    }
    
    public static void task19(Scanner scanner, ClientService service) throws InterruptedException
    {
        Call<String> call = service.dohvatiSveKorisnike();
        callURL(call);
    }
    
    public static void task20(Scanner scanner, ClientService service) throws InterruptedException
    {
        Call<String> call = service.dohvatiSveKategorije();
        callURL(call);
    }
    
    public static void task21(Scanner scanner, ClientService service) throws InterruptedException
    {
        Call<String> call = service.dohvatiSveAudioSnimke();
        callURL(call);
    }
    
    public static void task22(Scanner scanner, ClientService service) throws InterruptedException
    {
        System.out.println("unesite id audio snimka za koji zelite da dohvatite kategorije");
        int idAS = scanner.nextInt();
        Call<String> call = service.dohvatiKategorijeAudioSnimka(idAS);
        callURL(call);
    }
      
    public static void task23(Scanner scanner, ClientService service) throws InterruptedException
    {
        Call<String> call = service.dohvatiSvePakete();
        callURL(call);
    }
     
    public static void task24(Scanner scanner, ClientService service) throws InterruptedException
    {
        Call<String> call = service.dohvatiPretplateKorisnika(idKor);
        callURL(call);
    }
     
    public static void task25(Scanner scanner, ClientService service) throws InterruptedException
    {
        System.out.println("unesite id audio snimka za koji zelite da dohvatite slusanja");
        int idAS = scanner.nextInt();
        Call<String> call = service.dohvatiSlusanjaAudioSnimka(idAS);
        callURL(call);
    }
    
    public static void task26(Scanner scanner, ClientService service) throws InterruptedException
    {
        System.out.println("unesite id audio snimka za koji zelite da dohvatite ocene");
        int idAS = scanner.nextInt();
        Call<String> call = service.dohvatiSveOceneAudioSnimka(idAS);
        callURL(call);
    }
    
    public static void task27(Scanner scanner, ClientService service) throws InterruptedException
    {
        Call<String> call = service.dohvatiOmiljeneKorisnika(idKor);
        callURL(call);
    }
    

    public static void main(String[] args) throws InterruptedException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/server/resources/services/")  
                .addConverterFactory(ScalarsConverterFactory.create())  // Scalar converter za string odgovore
                .build();

        
        ClientService service = retrofit.create(ClientService.class);
        Scanner scanner = new Scanner(System.in);

        System.out.println("unesite vas korisnicki id");
        idKor = scanner.nextInt();
        
        while(true)
        {
            System.out.println("Unesite sifru komande koja zelite da bude izvrsena ");
            int izbor = scanner.nextInt();
            
            if(izbor == -1)
                break;

            else if (izbor == 1) 
                task1(scanner, service);
            
            else if (izbor == 2)
                task2(scanner, service);
            
            else if (izbor == 3)
                task3(scanner, service);
            
            else if(izbor == 4)
                task4(scanner, service);
            
            else if (izbor == 5)
                task5(scanner, service);
            
            else if (izbor == 6)
                task6(scanner, service);
            
            else if (izbor == 7)
                task7(scanner,service);
            
            else if (izbor == 8)
                task8(scanner,service);
           
            else if(izbor == 9)
                task9(scanner, service);
            
            else if(izbor == 10)
                task10(scanner, service);
            
            else if(izbor == 11)
                task11(scanner, service);
            
            else if(izbor == 12)
                task12(scanner, service);
            
            else if(izbor == 13)
                task13(scanner, service);
            
            else if(izbor == 14)
                task14(scanner, service);
            
            else if(izbor == 15)
                task15(scanner, service);
            
            else if(izbor == 16)
                task16(scanner, service);
            
            else if(izbor == 17)
                task17(scanner, service);
            
            else if(izbor == 18)
                task18(scanner, service);
            
            else if(izbor == 19)
                task19(scanner, service);
            
            else if(izbor == 20)
                task20(scanner, service);
            
            else if(izbor == 21)
                task21(scanner, service);
            
            else if(izbor == 22)
                task22(scanner, service);
            
            else if(izbor == 23)
                task23(scanner, service);
            
            else if(izbor == 24)
                task24(scanner, service);
            
            else if(izbor == 25)
                task25(scanner, service);
            
            else if(izbor == 26)
                task26(scanner, service);
            
            else if(izbor == 27)
                task27(scanner, service);
            
            
            
            
        }
        

        
        
            
        
        scanner.close();
        System.exit(0);
    }
}