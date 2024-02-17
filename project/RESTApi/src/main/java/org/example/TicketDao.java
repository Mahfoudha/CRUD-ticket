package org.example;
import java.util.List;

public interface TicketDao {
    List<Ticket> getAllTickets();
    Ticket getTicketById(int id);
    void addTicket(Ticket ticket);
    void updateTicket(Ticket ticket);
    void deleteProject(int id);
    void deleteTicket(int id);
}