package entities;

import entities.Audiosnimak;
import entities.Korisnik;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-02-02T14:21:27")
@StaticMetamodel(Omiljeni.class)
public class Omiljeni_ { 

    public static volatile SingularAttribute<Omiljeni, Korisnik> idKor;
    public static volatile SingularAttribute<Omiljeni, Integer> idomiljeni;
    public static volatile SingularAttribute<Omiljeni, Audiosnimak> idAS;

}