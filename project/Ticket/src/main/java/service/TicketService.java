package service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import model.Ticket;

import java.util.List;

@WebService
public interface TicketService {
    @WebMethod
    void ajouterTicket(Ticket ticket);

    @WebMethod
    void supprimerTicket(int id);

    @WebMethod
    void modifierTicket(Ticket ticket);

    @WebMethod
    List<Ticket> listerTickets();
}
