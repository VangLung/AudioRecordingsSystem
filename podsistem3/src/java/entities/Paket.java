/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mateja
 */
@Entity
@Table(name = "paket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paket.findAll", query = "SELECT p FROM Paket p"),
    @NamedQuery(name = "Paket.findByIdPak", query = "SELECT p FROM Paket p WHERE p.idPak = :idPak"),
    @NamedQuery(name = "Paket.findByCena", query = "SELECT p FROM Paket p WHERE p.cena = :cena")})
public class Paket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPak")
    private Integer idPak;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cena")
    private int cena;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaketa")
    private List<Pretplata> pretplataList;

    public Paket() {
    }

    public Paket(Integer idPak) {
        this.idPak = idPak;
    }

    public Paket(Integer idPak, int cena) {
        this.idPak = idPak;
        this.cena = cena;
    }

    public Integer getIdPak() {
        return idPak;
    }

    public void setIdPak(Integer idPak) {
        this.idPak = idPak;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    @XmlTransient
    public List<Pretplata> getPretplataList() {
        return pretplataList;
    }

    public void setPretplataList(List<Pretplata> pretplataList) {
        this.pretplataList = pretplataList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPak != null ? idPak.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paket)) {
            return false;
        }
        Paket other = (Paket) object;
        if ((this.idPak == null && other.idPak != null) || (this.idPak != null && !this.idPak.equals(other.idPak))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "paket:" + this.getIdPak() + " cena:" + this.getCena(); 
    }
    
}
