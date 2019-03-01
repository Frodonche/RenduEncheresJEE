/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author steve
 */
@Entity
@Table(name = "PROMOTIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Promotions.findAll", query = "SELECT p FROM Promotions p")
    , @NamedQuery(name = "Promotions.findById", query = "SELECT p FROM Promotions p WHERE p.id = :id")
    , @NamedQuery(name = "Promotions.findByNom", query = "SELECT p FROM Promotions p WHERE p.nom = :nom")
    , @NamedQuery(name = "Promotions.findByCode", query = "SELECT p FROM Promotions p WHERE p.code = :code")
    , @NamedQuery(name = "Promotions.findByNb", query = "SELECT p FROM Promotions p WHERE p.nb = :nb")})
public class Promotions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 30)
    @Column(name = "NOM")
    private String nom;
    @Size(max = 30)
    @Column(name = "CODE")
    private String code;
    @Column(name = "NB")
    private Integer nb;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "idPromotions")
    //private Collection<Paniers> paniersCollection;
    @JoinColumn(name = "STATUT", referencedColumnName = "STATUT")
    @ManyToOne(optional = false)
    private Statutpromotions statut;

    public Promotions() {
    }

    public Promotions(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getNb() {
        return nb;
    }

    public void setNb(Integer nb) {
        this.nb = nb;
    }

   // @XmlTransient
  //  public Collection<Paniers> getPaniersCollection() {
   //     return paniersCollection;
   // }

   // public void setPaniersCollection(Collection<Paniers> paniersCollection) {
   //     this.paniersCollection = paniersCollection;
   // }

    public Statutpromotions getStatut() {
        return statut;
    }

    public void setStatut(Statutpromotions statut) {
        this.statut = statut;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promotions)) {
            return false;
        }
        Promotions other = (Promotions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Promotions[ id=" + id + " ]";
    }
    
}
