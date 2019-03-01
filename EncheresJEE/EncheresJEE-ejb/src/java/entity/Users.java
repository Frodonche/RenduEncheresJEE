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
@Table(name = "USERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id")
    , @NamedQuery(name = "Users.findByLogin", query = "SELECT u FROM Users u WHERE u.login = :login")
    , @NamedQuery(name = "Users.findByPass", query = "SELECT u FROM Users u WHERE u.pass = :pass")
    , @NamedQuery(name = "Users.findByNom", query = "SELECT u FROM Users u WHERE u.nom = :nom")
    , @NamedQuery(name = "Users.findByPrenom", query = "SELECT u FROM Users u WHERE u.prenom = :prenom")
    , @NamedQuery(name = "Users.findByAdresse", query = "SELECT u FROM Users u WHERE u.adresse = :adresse")
    , @NamedQuery(name = "Users.findByIdBancaire", query = "SELECT u FROM Users u WHERE u.idBancaire = :idBancaire")
    , @NamedQuery(name = "Users.findByConnecte", query = "SELECT u FROM Users u WHERE u.connecte = :connecte")
    , @NamedQuery(name = "Users.findByNbAbandon", query = "SELECT u FROM Users u WHERE u.nbAbandon = :nbAbandon")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 30)
    @Column(name = "LOGIN")
    private String login;
    @Size(max = 30)
    @Column(name = "PASS")
    private String pass;
    @Size(max = 30)
    @Column(name = "NOM")
    private String nom;
    @Size(max = 30)
    @Column(name = "PRENOM")
    private String prenom;
    @Size(max = 30)
    @Column(name = "ADRESSE")
    private String adresse;
    @Size(max = 30)
    @Column(name = "ID_BANCAIRE")
    private String idBancaire;
    @Column(name = "CONNECTE")
    private boolean connecte;
    @Column(name = "NB_ABANDON")
    private Integer nbAbandon;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsers")
    private Collection<Paniers> paniersCollection;
    @OneToMany(mappedBy = "idBuyUsers")
    private Collection<Articles> articlesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSellUsers")
    private Collection<Articles> articlesCollection1;

    public Users() {
    }

    public Users(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getIdBancaire() {
        return idBancaire;
    }

    public void setIdBancaire(String idBancaire) {
        this.idBancaire = idBancaire;
    }

    public Boolean getConnecte() {
        return connecte;
    }

    public void setConnecte(Boolean connecte) {
        this.connecte = connecte;
    }

    public Integer getNbAbandon() {
        return nbAbandon;
    }

    public void setNbAbandon(Integer nbAbandon) {
        this.nbAbandon = nbAbandon;
    }

    @XmlTransient
    public Collection<Paniers> getPaniersCollection() {
        return paniersCollection;
    }

    public void setPaniersCollection(Collection<Paniers> paniersCollection) {
        this.paniersCollection = paniersCollection;
    }

    @XmlTransient
    public Collection<Articles> getArticlesCollection() {
        return articlesCollection;
    }

    public void setArticlesCollection(Collection<Articles> articlesCollection) {
        this.articlesCollection = articlesCollection;
    }

    @XmlTransient
    public Collection<Articles> getArticlesCollection1() {
        return articlesCollection1;
    }

    public void setArticlesCollection1(Collection<Articles> articlesCollection1) {
        this.articlesCollection1 = articlesCollection1;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + this.id;
    }
    
}
