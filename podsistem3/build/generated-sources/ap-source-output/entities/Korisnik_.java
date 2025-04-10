package entities;

import entities.Audiosnimak;
import entities.Mesto;
import entities.Ocena;
import entities.Omiljeni;
import entities.Pretplata;
import entities.Slusanje;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-02-02T10:12:38")
@StaticMetamodel(Korisnik.class)
public class Korisnik_ { 

    public static volatile SingularAttribute<Korisnik, String> ime;
    public static volatile SingularAttribute<Korisnik, String> prezime;
    public static volatile ListAttribute<Korisnik, Omiljeni> omiljeniList;
    public static volatile SingularAttribute<Korisnik, Integer> idKor;
    public static volatile ListAttribute<Korisnik, Audiosnimak> audiosnimakList;
    public static volatile ListAttribute<Korisnik, Pretplata> pretplataList;
    public static volatile ListAttribute<Korisnik, Slusanje> slusanjeList;
    public static volatile SingularAttribute<Korisnik, Mesto> idMes;
    public static volatile ListAttribute<Korisnik, Ocena> ocenaList;
    public static volatile SingularAttribute<Korisnik, String> pol;
    public static volatile SingularAttribute<Korisnik, String> email;

}