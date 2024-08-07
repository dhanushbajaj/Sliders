/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package cst8218.baja0012.slider;

import jakarta.annotation.ManagedBean;
import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import jakarta.faces.context.FacesContext;
import java.io.IOException;
/**
 *
 * @author Dhanush Bajaj
 */

@ManagedBean
public class SessionManager {

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        try {
            context.getExternalContext().redirect("login.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
