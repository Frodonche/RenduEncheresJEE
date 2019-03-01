/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

/**
 *
 * @author guigu
 */
public class NavBarElement {
    
    private String label;
    private String outcome;
    private NavBarElement subElement;
    
    public NavBarElement(String label, String outcome){
        this.label = label;
        this.outcome = outcome;
    }
    
    public NavBarElement(String label, String outcome, NavBarElement subElement){
        this.label = label;
        this.outcome = outcome;
        this.subElement = subElement;
    }
    
    public String getLabel(){
        return this.label;
    }
    
    public String getOutcome(){
        return this.outcome;
    }
    
    public NavBarElement getSubSelement(){
        return this.subElement;
    }
}
