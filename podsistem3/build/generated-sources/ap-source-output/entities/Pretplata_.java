package entities;

import entities.Korisnik;
import entities.Paket;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-02-02T13:51:21")
@StaticMetamodel(Pretplata.class)
public class Pretplata_ { 

    public static volatile SingularAttribute<Pretplata, Paket> idPaketa;
    public static volatile SingularAttribute<Pretplata, Korisnik> idKor;
    public static volatile SingularAttribute<Pretplata, Integer> idPret;
    public static volatile SingularAttribute<Pretplata, Date> datumVremePocetka;

}