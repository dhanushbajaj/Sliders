/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cst8218.baja0012.slider.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import jakarta.persistence.Table;

/**
 *Class representing Slider entity
 * 
 * @author Dhanush Bajaj
 */
@Entity
@Table(name = "Slides")
public class Slider implements Serializable {

    public static final int INITIAL_SIZE = 50;
    public static final int TRAVEL_SPEED = 5;
    public static final int MAX_DIR_CHANGES = 10;
    public static final int DECAY_RATE = 1;
    public static final int CHANGE_RATE = 60;
    public static final int X_LIMIT = 800;
    public static final int Y_LIMIT = 600;
    public static final int SIZE_LIMIT = 100;
    public static final int MAX_TRAVEL_LIMIT = 100;

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Min(1)
    @Max(SIZE_LIMIT)
    private Integer size;

    @NotNull
    @Min(0)
    @Max(X_LIMIT)
    private Integer x;

    @NotNull
    @Min(0)
    @Max(Y_LIMIT)
    private Integer y;

    @NotNull
    private Integer currentTravel;

    @NotNull
    @Min(1)
    @Max(MAX_TRAVEL_LIMIT)
    private Integer maxTravel;

    private Integer movementDirection;
    private Integer dirChangeCount;

    // Constructor
    public Slider() {
        this.size = INITIAL_SIZE;
        this.x = 0;
        this.y = 0;
        this.currentTravel = INITIAL_SIZE;
        this.maxTravel = INITIAL_SIZE;
        this.movementDirection = 1; // Initial direction to the right
        this.dirChangeCount = 0;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getCurrentTravel() {
        return currentTravel;
    }

    public void setCurrentTravel(Integer currentTravel) {
        this.currentTravel = currentTravel;
    }

    public Integer getMaxTravel() {
        return maxTravel;
    }

    public void setMaxTravel(Integer maxTravel) {
        this.maxTravel = maxTravel;
    }

    public Integer getMovementDirection() {
        return movementDirection;
    }

    public void setMovementDirection(Integer movementDirection) {
        this.movementDirection = movementDirection;
    }

    public Integer getDirChangeCount() {
        return dirChangeCount;
    }

    public void setDirChangeCount(Integer dirChangeCount) {
        this.dirChangeCount = dirChangeCount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Slider)) {
            return false;
        }
        Slider other = (Slider) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cst8218.baja0012.slider.entity.Slider[ id=" + id + " ]";
    }
    
    /**
     * Method to check if the slider can still travel
     */
    public void timeStep() {
    // Check if the slider is still able to travel
        if (maxTravel > 0) {
        // Update the currentTravel by adding the movement direction multiplied by travel speed
            currentTravel += movementDirection * TRAVEL_SPEED;
        
        // Check if the currentTravel has reached or exceeded the maxTravel distance
            if (Math.abs(currentTravel) >= maxTravel) {
            // Reverse the movement direction
                movementDirection = -movementDirection;
            // Increment the direction change count
                dirChangeCount++;
            
            // Check if the direction has changed too many times
                if (dirChangeCount > MAX_DIR_CHANGES) {
                // Decrease the maxTravel distance by the decay rate
                    maxTravel -= DECAY_RATE;
                // Reset the direction change count
                    dirChangeCount = 0;
                }
            }
        }
    }
}
