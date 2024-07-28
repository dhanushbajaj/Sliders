/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package cst8218.baja0012.slider.business;

import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import cst8218.baja0012.slider.entity.Slider;

/**
 * Class extends AbstractFacade and adds program related vales of persistence unit
 * @author Dhanush Bajaj
 */
@Stateless
@LocalBean
public class SliderFacade extends AbstractFacade<Slider> {

     @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SliderFacade() {
        super(Slider.class);
    }
}
