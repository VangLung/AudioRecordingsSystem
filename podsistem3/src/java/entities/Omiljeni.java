/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mateja
 */
@Entity
@Table(name = "omiljeni")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Omiljeni.findAll", query = "SELECT o FROM Omiljeni o"),
    @NamedQuery(name = "Omiljeni.findByIdomiljeni", query = "SELECT o FROM Omiljeni o WHERE o.idomiljeni = :idomiljeni")})
public class Omiljeni implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idomiljeni")
    private Integer idomiljeni;
    @JoinColumn(name = "idAS", referencedColumnName = "idAS")
    @ManyToOne(optional = false)
    private Audiosnimak idAS;
    @JoinColumn(name = "idKor", referencedColumnName = "idKor")
    @ManyToOne(optional = false)
    private Korisnik idKor;

    public Omiljeni() {
    }

    public Omiljeni(Integer idomiljeni) {
        this.idomiljeni = idomiljeni;
    }

    public Integer getIdomiljeni() {
        return idomiljeni;
    }

    public void setIdomiljeni(Integer idomiljeni) {
        this.idomiljeni = idomiljeni;
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
        hash += (idomiljeni != null ? idomiljeni.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Omiljeni)) {
            return false;
        }
        Omiljeni other = (Omiljeni) object;
        if ((this.idomiljeni == null && other.idomiljeni != null) || (this.idomiljeni != null && !this.idomiljeni.equals(other.idomiljeni))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Audio snimak:" + this.getIdAS().getNaziv() + " korisnik:" + this.getIdKor().getEmail();
    }
    
}
