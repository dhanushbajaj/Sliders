package cst8218.baja0012.slider.service;

import cst8218.baja0012.slider.entity.Slider;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
/**
 * Class to do the RESTful HTTP
 * @author Dhanush Bajaj
 */
@Stateless
@Path("sliders")
public class SliderFacadeREST extends AbstractFacade<Slider> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SliderFacadeREST() {
        super(Slider.class);
    }

    /**
     * Method to create a slider from an http reponse
     * @param entity
     * @return 
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createSlider(Slider entity) {
        if (entity.getId() == null) {
            super.create(entity);
            return Response.status(Response.Status.CREATED).entity(entity).build();
        } else {
            Slider existingSlider = super.find(entity.getId());
            if (existingSlider != null) {
                updateNonNullAttributes(existingSlider, entity);
                super.edit(existingSlider);
                return Response.ok(existingSlider).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
    }
/**
 * Method to edit a slider according to the http request
 * @param id
 * @param entity
 * @return 
 */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response editSlider(@PathParam("id") Long id, Slider entity) {
        Slider existingSlider = super.find(id);
        if (existingSlider == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else if (!entity.getId().equals(id)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            updateNonNullAttributes(existingSlider, entity);
            super.edit(existingSlider);
            return Response.ok(existingSlider).build();
        }
    }

    /**
     * Method to delete a slider as requested in the http response
     * @param id
     * @return 
     */
    @DELETE
    @Path("{id}")
    public Response removeSlider(@PathParam("id") Long id) {
        Slider entity = super.find(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        super.remove(entity);
        return Response.noContent().build();
    }

    /**
     * Method to find the slider as requested by the http request
     * @param id
     * @return 
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findSlider(@PathParam("id") Long id) {
        Slider entity = super.find(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(entity).build();
    }

    /**
     * Method to return a list of all sliders
     * @return 
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Slider> findAllSliders() {
        return super.findAll();
    }

    /**
     * Method to find a range of sliders
     * @param from
     * @param to
     * @return 
     */
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Slider> findSliderRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    /**
     * method to get a count of number of sliders
     * @return 
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response countSliders() {
        return Response.ok(String.valueOf(super.count())).build();
    }

    @POST
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response postOnSpecificId(@PathParam("id") Long id, Slider entity) {
        Slider existingSlider = super.find(id);
        if (existingSlider == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else if (!entity.getId().equals(id)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            updateNonNullAttributes(existingSlider, entity);
            super.edit(existingSlider);
            return Response.ok(existingSlider).build();
        }
    }

    private void updateNonNullAttributes(Slider existingSlider, Slider newSlider) {
        if (newSlider.getSize() != null) {
            existingSlider.setSize(newSlider.getSize());
        }
        if (newSlider.getX() != null) {
            existingSlider.setX(newSlider.getX());
        }
        if (newSlider.getY() != null) {
            existingSlider.setY(newSlider.getY());
        }
        if (newSlider.getCurrentTravel() != null) {
            existingSlider.setCurrentTravel(newSlider.getCurrentTravel());
        }
        if (newSlider.getMaxTravel() != null) {
            existingSlider.setMaxTravel(newSlider.getMaxTravel());
        }
        if (newSlider.getMovementDirection() != null) {
            existingSlider.setMovementDirection(newSlider.getMovementDirection());
        }
        if (newSlider.getDirChangeCount() != null) {
            existingSlider.setDirChangeCount(newSlider.getDirChangeCount());
        }
    }
}
