package entities;

import entities.Korisnik;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-01-28T12:07:14")
@StaticMetamodel(Audiosnimak.class)
public class Audiosnimak_ { 

    public static volatile SingularAttribute<Audiosnimak, Date> datumVreme;
    public static volatile SingularAttribute<Audiosnimak, Korisnik> idKor;
    public static volatile SingularAttribute<Audiosnimak, String> trajanje;
    public static volatile SingularAttribute<Audiosnimak, String> naziv;
    public static volatile SingularAttribute<Audiosnimak, Integer> idAS;

}