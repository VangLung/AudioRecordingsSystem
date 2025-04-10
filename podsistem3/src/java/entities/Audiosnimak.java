/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mateja
 */
@Entity
@Table(name = "audiosnimak")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Audiosnimak.findAll", query = "SELECT a FROM Audiosnimak a"),
    @NamedQuery(name = "Audiosnimak.findByIdAS", query = "SELECT a FROM Audiosnimak a WHERE a.idAS = :idAS"),
    @NamedQuery(name = "Audiosnimak.findByNaziv", query = "SELECT a FROM Audiosnimak a WHERE a.naziv = :naziv"),
    @NamedQuery(name = "Audiosnimak.findByTrajanje", query = "SELECT a FROM Audiosnimak a WHERE a.trajanje = :trajanje"),
    @NamedQuery(name = "Audiosnimak.findByDatumVreme", query = "SELECT a FROM Audiosnimak a WHERE a.datumVreme = :datumVreme")})
public class Audiosnimak implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "naziv")
    private String naziv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trajanje")
    private int trajanje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datumVreme")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumVreme;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAS")
    private Integer idAS;
    @JoinColumn(name = "idKor", referencedColumnName = "idKor")
    @ManyToOne(optional = false)
    private Korisnik idKor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAS")
    private List<Omiljeni> omiljeniList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAs")
    private List<Ocena> ocenaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAS")
    private List<Slusanje> slusanjeList;

    public Audiosnimak() {
    }

    public Audiosnimak(Integer idAS) {
        this.idAS = idAS;
    }

    public Audiosnimak(Integer idAS, String naziv, int trajanje, Date datumVreme) {
        this.idAS = idAS;
        this.naziv = naziv;
        this.trajanje = trajanje;
        this.datumVreme = datumVreme;
    }

    public Integer getIdAS() {
        return idAS;
    }

    public void setIdAS(Integer idAS) {
        this.idAS = idAS;
    }


    public Korisnik getIdKor() {
        return idKor;
    }

    public void setIdKor(Korisnik idKor) {
        this.idKor = idKor;
    }

    @XmlTransient
    public List<Omiljeni> getOmiljeniList() {
        return omiljeniList;
    }

    public void setOmiljeniList(List<Omiljeni> omiljeniList) {
        this.omiljeniList = omiljeniList;
    }

    @XmlTransient
    public List<Ocena> getOcenaList() {
        return ocenaList;
    }

    public void setOcenaList(List<Ocena> ocenaList) {
        this.ocenaList = ocenaList;
    }

    @XmlTransient
    public List<Slusanje> getSlusanjeList() {
        return slusanjeList;
    }

    public void setSlusanjeList(List<Slusanje> slusanjeList) {
        this.slusanjeList = slusanjeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAS != null ? idAS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Audiosnimak)) {
            return false;
        }
        Audiosnimak other = (Audiosnimak) object;
        if ((this.idAS == null && other.idAS != null) || (this.idAS != null && !this.idAS.equals(other.idAS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id:" + this.getIdAS() + " naziv:" + this.getNaziv() + " vlasnik:" + this.getIdKor().getIdKor() + " trajanje:" + this.getTrajanje();
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }
    
}
