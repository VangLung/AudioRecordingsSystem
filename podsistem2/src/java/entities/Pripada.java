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
@Table(name = "pripada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pripada.findAll", query = "SELECT p FROM Pripada p"),
    @NamedQuery(name = "Pripada.findByIdpripada", query = "SELECT p FROM Pripada p WHERE p.idpripada = :idpripada")})
public class Pripada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpripada")
    private Integer idpripada;
    @JoinColumn(name = "idAS", referencedColumnName = "idAS")
    @ManyToOne(optional = false)
    private Audiosnimak idAS;
    @JoinColumn(name = "idKat", referencedColumnName = "idKat")
    @ManyToOne(optional = false)
    private Kategorija idKat;

    public Pripada() {
    }

    public Pripada(Integer idpripada) {
        this.idpripada = idpripada;
    }

    public Integer getIdpripada() {
        return idpripada;
    }

    public void setIdpripada(Integer idpripada) {
        this.idpripada = idpripada;
    }

    public Audiosnimak getIdAS() {
        return idAS;
    }

    public void setIdAS(Audiosnimak idAS) {
        this.idAS = idAS;
    }

    public Kategorija getIdKat() {
        return idKat;
    }

    public void setIdKat(Kategorija idKat) {
        this.idKat = idKat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpripada != null ? idpripada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pripada)) {
            return false;
        }
        Pripada other = (Pripada) object;
        if ((this.idpripada == null && other.idpripada != null) || (this.idpripada != null && !this.idpripada.equals(other.idpripada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Pripada[ idpripada=" + idpripada + " ]";
    }
    
}
