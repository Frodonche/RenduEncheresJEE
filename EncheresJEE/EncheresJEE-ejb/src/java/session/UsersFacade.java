/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Users;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author steve
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "EncheresJEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
    @PermitAll
    public boolean connecter(String login, String pass){
        Users find = this.findByLogin(login);
        if(find == null)return false;
        if(find.getPass().equals(pass)){
            find.setConnecte(Boolean.TRUE);
            this.getEntityManager().persist(find);
            return true;
        }else{
            return false;
        }
    }
    
    @PermitAll
    public void deconnecter(String login){
        Users find = this.findByLogin(login);
        if(find != null){
            find.setConnecte(false);
            this.getEntityManager().persist(find);
        }
    }
    
    @PermitAll
    public Users findByLogin(String login){
        List<Users> users = this.findAll();
        Users find = null;
        for(Users user : users){
            if(user.getLogin().equals(login)){
                find = user;
            }
        }
        return find;
    }
}
