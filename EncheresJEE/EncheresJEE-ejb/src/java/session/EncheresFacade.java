/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Articles;
import javax.annotation.security.PermitAll;
import entity.Encheres;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author laura
 */
@Stateless
public class EncheresFacade extends AbstractFacade<Encheres>{
    
    @PersistenceContext(unitName = "EncheresJEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EncheresFacade() {
        super(Encheres.class);
    }
    
    @PermitAll
    public void removeByIdArticles(Integer idArt){
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Encheres> q = cb.createQuery(Encheres.class);
        Root<Encheres> c = q.from(Encheres.class);
       
        q.select(c).where(cb.equal(c.get("idArticles"), idArt));
        for(Encheres e : em.createQuery(q).getResultList()){
            this.remove(e);
        }
    }
    
}
