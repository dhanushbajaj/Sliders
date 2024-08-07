/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import cst8218.baja0012.slider.entity.Slider;
/**
 *
 * @author Dhanush Bajaj
 */
public class SliderTest {

    private Slider slider;

    @BeforeEach
    public void setUp() {
        slider = new Slider();
    }

    @Test
    public void testInitialValues() {
        assertEquals(Slider.INITIAL_SIZE, slider.getSize());
        assertEquals(0, slider.getX());
        assertEquals(0, slider.getY());
        assertEquals(Slider.INITIAL_SIZE, slider.getCurrentTravel());
        assertEquals(Slider.INITIAL_SIZE, slider.getMaxTravel());
        assertEquals(1, slider.getMovementDirection());
    }

    @Test
    public void testTimeStepWithinMaxTravel() {
        slider.setMaxTravel(100);
        slider.setCurrentTravel(0);
        slider.setMovementDirection(1);
        slider.timeStep();
        assertEquals(5, slider.getCurrentTravel());
        assertEquals(1, slider.getMovementDirection());
    }

    @Test
    public void testTimeStepReachesMaxTravel() {
        slider.setMaxTravel(5);
        slider.setCurrentTravel(5);
        slider.setMovementDirection(1);
        slider.timeStep();
        assertEquals(0, slider.getCurrentTravel());
        assertEquals(-1, slider.getMovementDirection());
    }

    @Test
    public void testDirectionChangeCount() {
        slider.setMaxTravel(5);
        slider.setCurrentTravel(5);
        slider.setMovementDirection(1);
        slider.setDirChangeCount(Slider.MAX_DIR_CHANGES - 1);
        slider.timeStep();
        assertEquals(0, slider.getDirChangeCount());
        assertEquals(Slider.INITIAL_SIZE - Slider.DECAY_RATE, slider.getMaxTravel());
    }

    @Test
    public void testMaxTravelDecay() {
        slider.setMaxTravel(1);
        slider.setCurrentTravel(1);
        slider.setMovementDirection(1);
        slider.setDirChangeCount(Slider.MAX_DIR_CHANGES);
        slider.timeStep();
        assertEquals(Slider.INITIAL_SIZE - Slider.DECAY_RATE, slider.getMaxTravel());
    }

    @Test
    public void testNoMoreTravelAfterDecay() {
        slider.setMaxTravel(0);
        slider.setCurrentTravel(0);
        slider.setMovementDirection(1);
        slider.timeStep();
        assertEquals(0, slider.getCurrentTravel());
        assertEquals(1, slider.getMovementDirection());
    }
}
