/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author laura
 */
@Entity
@Table(name = "ENCHERES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Encheres.findAll", query = "SELECT e FROM Encheres e")
    , @NamedQuery(name = "Encheres.findById", query = "SELECT e FROM Encheres e WHERE e.id = :id")
    , @NamedQuery(name = "Encheres.findByIdUsers", query = "SELECT e FROM Encheres e WHERE e.idUsers = :id")
    , @NamedQuery(name = "Encheres.findByIdArticles", query = "SELECT e FROM Encheres e WHERE e.idArticles = :id")})
public class Encheres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "ID_USERS", referencedColumnName = "ID")
    @OneToOne
    private Users idUsers;
    @JoinColumn(name = "ID_ARTICLES", referencedColumnName = "ID")
    @OneToOne
    private Articles idArticles;
    @Column(name = "VALUE")
    private Integer value;

    public Encheres() {
    }

    public Encheres(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Users getIdUsers() {
        return this.idUsers;
    }
    
    public void setIdUsers(Users id){
        this.idUsers = id;
    }

    public Articles getIdArticles() {
        return this.idArticles;
    }
    
    public void setIdArticles(Articles id){
        this.idArticles = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer v) {
        this.value = v;
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
        if (!(object instanceof Encheres)) {
            return false;
        }
        Encheres other = (Encheres) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Encheres[ id=" + id + " ]";
    }
    
}