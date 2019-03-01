/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author steve
 */
@Entity
@Table(name = "ARTICLES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Articles.findAll", query = "SELECT a FROM Articles a")
    , @NamedQuery(name = "Articles.findById", query = "SELECT a FROM Articles a WHERE a.id = :id")
    , @NamedQuery(name = "Articles.findByNom", query = "SELECT a FROM Articles a WHERE a.nom = :nom")
    , @NamedQuery(name = "Articles.findByDescription", query = "SELECT a FROM Articles a WHERE a.description = :description")
    , @NamedQuery(name = "Articles.findByPrixInit", query = "SELECT a FROM Articles a WHERE a.prixInit = :prixInit")
    , @NamedQuery(name = "Articles.findByDateFin", query = "SELECT a FROM Articles a WHERE a.dateFin = :dateFin")
    , @NamedQuery(name = "Articles.findByPrixMax", query = "SELECT a FROM Articles a WHERE a.prixMax = :prixMax")
    , @NamedQuery(name = "Articles.findByVisible", query = "SELECT a FROM Articles a WHERE a.visible = :visible")})
public class Articles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 30)
    @Column(name = "NOM")
    private String nom;
    @Size(max = 100)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PRIX_INIT")
    private Integer prixInit;
    @Column(name = "DATE_FIN")
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    @Column(name = "PRIX_MAX")
    private Integer prixMax;
    @Column(name = "VISIBLE")
    private boolean visible;
    @ManyToMany(mappedBy = "articlesCollection")
    private Collection<Paniers> paniersCollection;
    @JoinColumn(name = "NOM_CATEGORIE", referencedColumnName = "NOM")
    @ManyToOne(optional = false)
    private Categories nomCategorie;
    @JoinColumn(name = "ID_BUY_USERS", referencedColumnName = "ID")
    @ManyToOne
    private Users idBuyUsers;
    @JoinColumn(name = "ID_SELL_USERS", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Users idSellUsers;
    @Column(name = "FACTURATION")
    private boolean facturation;
    @Column(name = "LIVRAISON")
    private boolean livraison;

    public Articles() {
    }

    public Articles(Integer id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrixInit() {
        return prixInit;
    }

    public void setPrixInit(Integer prixInit) {
        this.prixInit = prixInit;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getPrixMax() {
        return prixMax;
    }

    public void setPrixMax(Integer prixMax) {
        this.prixMax = prixMax;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    @XmlTransient
    public Collection<Paniers> getPaniersCollection() {
        return paniersCollection;
    }

    public void setPaniersCollection(Collection<Paniers> paniersCollection) {
        this.paniersCollection = paniersCollection;
    }

    public Categories getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(Categories nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public Users getIdBuyUsers() {
        return idBuyUsers;
    }

    public void setIdBuyUsers(Users idBuyUsers) {
        this.idBuyUsers = idBuyUsers;
    }

    public Users getIdSellUsers() {
        return idSellUsers;
    }

    public void setIdSellUsers(Users idSellUsers) {
        this.idSellUsers = idSellUsers;
    }

    public boolean isFacturation() {
        return facturation;
    }

    public void setFacturation(boolean facturation) {
        this.facturation = facturation;
    }

    public boolean isLivraison() {
        return livraison;
    }

    public void setLivraison(boolean livraison) {
        this.livraison = livraison;
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
        if (!(object instanceof Articles)) {
            return false;
        }
        Articles other = (Articles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Articles[ id=" + id + " ]";
    }
    
}
