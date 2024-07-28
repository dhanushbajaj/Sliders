/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SingletonEjbClass.java to edit this template
 */
package cst8218.baja0012.slider.game;

import cst8218.baja0012.slider.business.SliderFacade;
import cst8218.baja0012.slider.entity.Slider;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import java.util.List;
import jakarta.ejb.LocalBean;

/**
 * Class to add the game aspect
 * @author Dhanush Bajaj
 */
@Singleton
@LocalBean
@Startup
public class SliderGame {

    @Inject
    private SliderFacade sliderFacade;

    private List<Slider> sliders;
/**
 * method to create a new thread which runs the game
 */
    @PostConstruct
    public void go() {
        new Thread(new Runnable() {
            public void run() {
                // the game runs indefinitely
                while (true) {
                    // Update all the sliders and save changes to the database
                    sliders = sliderFacade.findAll();
                    for (Slider slider : sliders) {
                        slider.timeStep();
                        sliderFacade.edit(slider);
                    }
                    // Sleep while waiting to process the next frame of the animation
                    try {
                        // Wake up roughly CHANGE_RATE times per second
                        Thread.sleep((long) (1.0 / Slider.CHANGE_RATE * 1000));
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
