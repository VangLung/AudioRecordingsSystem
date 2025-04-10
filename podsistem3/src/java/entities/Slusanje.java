/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mateja
 */
@Entity
@Table(name = "slusanje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Slusanje.findAll", query = "SELECT s FROM Slusanje s"),
    @NamedQuery(name = "Slusanje.findByIdSlus", query = "SELECT s FROM Slusanje s WHERE s.idSlus = :idSlus"),
    @NamedQuery(name = "Slusanje.findByDatumVreme", query = "SELECT s FROM Slusanje s WHERE s.datumVreme = :datumVreme"),
    @NamedQuery(name = "Slusanje.findByZapoceto", query = "SELECT s FROM Slusanje s WHERE s.zapoceto = :zapoceto"),
    @NamedQuery(name = "Slusanje.findByOdslusano", query = "SELECT s FROM Slusanje s WHERE s.odslusano = :odslusano")})
public class Slusanje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSlus")
    private Integer idSlus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datumVreme")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumVreme;
    @Basic(optional = false)
    @NotNull
    @Column(name = "zapoceto")
    private int zapoceto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "odslusano")
    private int odslusano;
    @JoinColumn(name = "idAS", referencedColumnName = "idAS")
    @ManyToOne(optional = false)
    private Audiosnimak idAS;
    @JoinColumn(name = "idKor", referencedColumnName = "idKor")
    @ManyToOne(optional = false)
    private Korisnik idKor;

    public Slusanje() {
    }

    public Slusanje(Integer idSlus) {
        this.idSlus = idSlus;
    }

    public Slusanje(Integer idSlus, Date datumVreme, int zapoceto, int odslusano) {
        this.idSlus = idSlus;
        this.datumVreme = datumVreme;
        this.zapoceto = zapoceto;
        this.odslusano = odslusano;
    }

    public Integer getIdSlus() {
        return idSlus;
    }

    public void setIdSlus(Integer idSlus) {
        this.idSlus = idSlus;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public int getZapoceto() {
        return zapoceto;
    }

    public void setZapoceto(int zapoceto) {
        this.zapoceto = zapoceto;
    }

    public int getOdslusano() {
        return odslusano;
    }

    public void setOdslusano(int odslusano) {
        this.odslusano = odslusano;
    }

    public Audiosnimak getIdAS() {
        return idAS;
    }

    public void setIdAS(Audiosnimak idAS) {
        this.idAS = idAS;
    }

    public Korisnik getIdKor() {
        return idKor;
    }

    public void setIdKor(Korisnik idKor) {
        this.idKor = idKor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSlus != null ? idSlus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Slusanje)) {
            return false;
        }
        Slusanje other = (Slusanje) object;
        if ((this.idSlus == null && other.idSlus != null) || (this.idSlus != null && !this.idSlus.equals(other.idSlus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "slusanje:" + this.getIdSlus() + " korisnik:" + this.getIdKor().getEmail() + " snimak:" + this.getIdAS().getNaziv() + " od:" + this.getZapoceto() +" do:" + this.getOdslusano();
    }
    
}
