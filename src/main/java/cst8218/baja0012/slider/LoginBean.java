/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package cst8218.baja0012.slider;

import jakarta.annotation.ManagedBean;
import jakarta.faces.context.FacesContext;
import java.io.IOException;

/**
 *
 * @author Dhanush Bajaj
 */

@ManagedBean
public class LoginBean {

     private String username;
    private String password;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("j_security_check");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
