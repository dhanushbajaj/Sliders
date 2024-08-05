/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cst8218.baja0012.appuser.entity;

import java.io.Serializable;
import java.util.HashMap;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Named;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.security.enterprise.identitystore.PasswordHash;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author Dhanush Bajaj
 */
@Entity
@Table(name = "appuser")
@Named
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userid;
    private String password;
    private String groupname;

    // Default constructor
    public AppUser() {
    }

    // Constructor
    public AppUser(String userid, String password, String groupname) {
        this.userid = userid;
        setPassword(password);
        this.groupname = groupname;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return "";
    }

    public void setPassword(String password) {
        if (password != null && !password.isEmpty()) {
            Instance<? extends Pbkdf2PasswordHash> instance = CDI.current().select(Pbkdf2PasswordHash.class);
            PasswordHash passwordHash = instance.get();
            passwordHash.initialize(new HashMap<>()); // Customize parameters as needed
            this.password = passwordHash.generate(password.toCharArray());
        }
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    // Override equals and hashCode methods for proper comparison and usage in collections
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AppUser appUser = (AppUser) obj;
        return id != null && id.equals(appUser.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // Override toString method for better debugging and logging
    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", userid='" + userid + '\'' +
                ", groupname='" + groupname + '\'' +
                '}';
    }
}