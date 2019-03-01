/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookies;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tarek
 */
public class CookieGestion {
    
    private static CookieGestion instance;
    
    private CookieGestion() { }
    
    public static CookieGestion getInstance() {
        if (instance == null)
            instance = new CookieGestion();
        return instance;
    }
    
    public void createCookie(String name, String value, int age) {
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequest();
        
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(age);
        cookie.setPath(request.getContextPath());
        
        HttpServletResponse response = (HttpServletResponse) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getResponse();
        response.addCookie(cookie);
    }
    
    public void deleteCookie(String name) {
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequest();
        
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        cookie.setPath(request.getContextPath());
        
        HttpServletResponse response = (HttpServletResponse) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getResponse();
        response.addCookie(cookie);
    }
    
    public Cookie getCookie(String name) {
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequest();
        
        Cookie[] listeCookies = request.getCookies();
        if (listeCookies != null) {
            for(Cookie c : listeCookies) {
                if (c.getName().equals(name)) {
                    return c;
                }
            }
        }
        
        return null;
    }
    
}
