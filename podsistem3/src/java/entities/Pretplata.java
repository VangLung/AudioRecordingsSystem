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
@Table(name = "pretplata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pretplata.findAll", query = "SELECT p FROM Pretplata p"),
    @NamedQuery(name = "Pretplata.findByIdPret", query = "SELECT p FROM Pretplata p WHERE p.idPret = :idPret"),
    @NamedQuery(name = "Pretplata.findByDatumVremePocetka", query = "SELECT p FROM Pretplata p WHERE p.datumVremePocetka = :datumVremePocetka")})
public class Pretplata implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPret")
    private Integer idPret;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datumVremePocetka")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumVremePocetka;
    @JoinColumn(name = "idKor", referencedColumnName = "idKor")
    @ManyToOne(optional = false)
    private Korisnik idKor;
    @JoinColumn(name = "idPaketa", referencedColumnName = "idPak")
    @ManyToOne(optional = false)
    private Paket idPaketa;

    public Pretplata() {
    }

    public Pretplata(Integer idPret) {
        this.idPret = idPret;
    }

    public Pretplata(Integer idPret, Date datumVremePocetka) {
        this.idPret = idPret;
        this.datumVremePocetka = datumVremePocetka;
    }

    public Integer getIdPret() {
        return idPret;
    }

    public void setIdPret(Integer idPret) {
        this.idPret = idPret;
    }

    public Date getDatumVremePocetka() {
        return datumVremePocetka;
    }

    public void setDatumVremePocetka(Date datumVremePocetka) {
        this.datumVremePocetka = datumVremePocetka;
    }

    public Korisnik getIdKor() {
        return idKor;
    }

    public void setIdKor(Korisnik idKor) {
        this.idKor = idKor;
    }

    public Paket getIdPaketa() {
        return idPaketa;
    }

    public void setIdPaketa(Paket idPaketa) {
        this.idPaketa = idPaketa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPret != null ? idPret.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pretplata)) {
            return false;
        }
        Pretplata other = (Pretplata) object;
        if ((this.idPret == null && other.idPret != null) || (this.idPret != null && !this.idPret.equals(other.idPret))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pretplata id:" + this.getIdPret() + " korisnik:" + this.getIdKor().getIdKor() + " paket:" + this.getIdPaketa() + " pocetak:" + this.getDatumVremePocetka();
    }
    
}
