package org.example;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/tickets")
public class TicketResource {

    private TicketDao ticketDao;

    public TicketResource() {
        ticketDao = new TicketDaoImpl();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ticket> getAllTickets() {
        return ticketDao.getAllTickets();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ticket getTicketById(@PathParam("id") int id) {
        return ticketDao.getTicketById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addTicket(Ticket ticket) {
        ticketDao.addTicket(ticket);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateTicket(@PathParam("id") int id, Ticket ticket) {
        ticket.setId(id);
        ticketDao.updateTicket(ticket);
    }

    @DELETE
    @Path("/{id}")
    public void deleteTicket(@PathParam("id") int id) {
        ticketDao.deleteTicket(id);
    }
}
