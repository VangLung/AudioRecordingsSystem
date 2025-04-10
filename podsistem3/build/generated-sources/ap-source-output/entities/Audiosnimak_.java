package entities;

import entities.Korisnik;
import entities.Ocena;
import entities.Omiljeni;
import entities.Slusanje;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-02-02T13:51:21")
@StaticMetamodel(Audiosnimak.class)
public class Audiosnimak_ { 

    public static volatile ListAttribute<Audiosnimak, Omiljeni> omiljeniList;
    public static volatile SingularAttribute<Audiosnimak, Date> datumVreme;
    public static volatile SingularAttribute<Audiosnimak, Korisnik> idKor;
    public static volatile ListAttribute<Audiosnimak, Slusanje> slusanjeList;
    public static volatile SingularAttribute<Audiosnimak, Integer> trajanje;
    public static volatile ListAttribute<Audiosnimak, Ocena> ocenaList;
    public static volatile SingularAttribute<Audiosnimak, String> naziv;
    public static volatile SingularAttribute<Audiosnimak, Integer> idAS;

}