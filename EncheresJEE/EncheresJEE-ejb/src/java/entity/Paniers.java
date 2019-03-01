/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author steve
 */
@Entity
@Table(name = "PANIERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paniers.findAll", query = "SELECT p FROM Paniers p")
    , @NamedQuery(name = "Paniers.findById", query = "SELECT p FROM Paniers p WHERE p.id = :id")
    , @NamedQuery(name = "Paniers.findByTotal", query = "SELECT p FROM Paniers p WHERE p.total = :total")
    , @NamedQuery(name = "Paniers.findByNbArticle", query = "SELECT p FROM Paniers p WHERE p.nbArticle = :nbArticle")
    , @NamedQuery(name = "Paniers.findByValide", query = "SELECT p FROM Paniers p WHERE p.valide = :valide")
    , @NamedQuery(name = "Paniers.findByAdresseFacturation", query = "SELECT p FROM Paniers p WHERE p.adresseFacturation = :adresseFacturation")
    , @NamedQuery(name = "Paniers.findByAdresseLivraison", query = "SELECT p FROM Paniers p WHERE p.adresseLivraison = :adresseLivraison")})
public class Paniers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TOTAL")
    private Integer total;
    @Column(name = "NB_ARTICLE")
    private Integer nbArticle;
    @Column(name = "VALIDE")
    private Boolean valide;
    @Size(max = 100)
    @Column(name = "ADRESSE_FACTURATION")
    private String adresseFacturation;
    @Size(max = 100)
    @Column(name = "ADRESSE_LIVRAISON")
    private String adresseLivraison;
    @JoinTable(name = "PANIERS_ARTICLES", joinColumns = {
        @JoinColumn(name = "IDPANIERS", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "IDARTICLES", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Articles> articlesCollection;
    @JoinColumn(name = "ID_PROMOTIONS", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Promotions idPromotions;
    @JoinColumn(name = "ID_USERS", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Users idUsers;

    public Paniers() {
    }

    public Paniers(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getNbArticle() {
        return nbArticle;
    }

    public void setNbArticle(Integer nbArticle) {
        this.nbArticle = nbArticle;
    }

    public Boolean getValide() {
        return valide;
    }

    public void setValide(Boolean valide) {
        this.valide = valide;
    }

    public String getAdresseFacturation() {
        return adresseFacturation;
    }

    public void setAdresseFacturation(String adresseFacturation) {
        this.adresseFacturation = adresseFacturation;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    @XmlTransient
    public Collection<Articles> getArticlesCollection() {
        return articlesCollection;
    }

    public void setArticlesCollection(Collection<Articles> articlesCollection) {
        this.articlesCollection = articlesCollection;
    }

    public Promotions getIdPromotions() {
        return idPromotions;
    }

    public void setIdPromotions(Promotions idPromotions) {
        this.idPromotions = idPromotions;
    }

    public Users getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(Users idUsers) {
        this.idUsers = idUsers;
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
        if (!(object instanceof Paniers)) {
            return false;
        }
        Paniers other = (Paniers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Paniers[ id=" + id + " ]";
    }
    
}
