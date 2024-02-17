package service;

import jakarta.jws.WebService;
import model.Ticket;
import dao.TicketDAO;

import java.sql.SQLException;
import java.util.List;

@WebService(endpointInterface = "service.TicketService")
public class TicketServiceImpl implements TicketService {
    private TicketDAO ticketDAO;

    public TicketServiceImpl() throws SQLException {
        this.ticketDAO = new TicketDAO(); // Vous devez implémenter votre TicketDAO
    }

    @Override
    public void ajouterTicket(Ticket ticket) {
        ticketDAO.ajouterTicket(ticket);
    }

    @Override
    public void supprimerTicket(int id) {
        ticketDAO.supprimerTicket(id);
    }

    @Override
    public void modifierTicket(Ticket ticket) {
        ticketDAO.modifierTicket(ticket);
    }

    @Override
    public List<Ticket> listerTickets() {
        return ticketDAO.listerTickets();
    }




}
