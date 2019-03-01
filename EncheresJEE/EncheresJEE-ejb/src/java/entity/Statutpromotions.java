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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author steve
 */
@Entity
@Table(name = "STATUTPROMOTIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Statutpromotions.findAll", query = "SELECT s FROM Statutpromotions s")
    , @NamedQuery(name = "Statutpromotions.findById", query = "SELECT s FROM Statutpromotions s WHERE s.id = :id")
    , @NamedQuery(name = "Statutpromotions.findByStatut", query = "SELECT s FROM Statutpromotions s WHERE s.statut = :statut")})
public class Statutpromotions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "ID")
    private int id;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "STATUT")
    private String statut;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statut")
    private Collection<Promotions> promotionsCollection;

    public Statutpromotions() {
    }

    public Statutpromotions(String statut) {
        this.statut = statut;
    }

    public Statutpromotions(String statut, int id) {
        this.statut = statut;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @XmlTransient
    public Collection<Promotions> getPromotionsCollection() {
        return promotionsCollection;
    }

    public void setPromotionsCollection(Collection<Promotions> promotionsCollection) {
        this.promotionsCollection = promotionsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (statut != null ? statut.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Statutpromotions)) {
            return false;
        }
        Statutpromotions other = (Statutpromotions) object;
        if ((this.statut == null && other.statut != null) || (this.statut != null && !this.statut.equals(other.statut))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Statutpromotions[ statut=" + statut + " ]";
    }
    
}
