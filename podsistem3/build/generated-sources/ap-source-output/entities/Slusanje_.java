package entities;

import entities.Audiosnimak;
import entities.Korisnik;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-02-02T14:21:27")
@StaticMetamodel(Slusanje.class)
public class Slusanje_ { 

    public static volatile SingularAttribute<Slusanje, Date> datumVreme;
    public static volatile SingularAttribute<Slusanje, Korisnik> idKor;
    public static volatile SingularAttribute<Slusanje, Integer> zapoceto;
    public static volatile SingularAttribute<Slusanje, Integer> odslusano;
    public static volatile SingularAttribute<Slusanje, Integer> idSlus;
    public static volatile SingularAttribute<Slusanje, Audiosnimak> idAS;

}