package service;

import jakarta.xml.ws.Endpoint;
import java.sql.SQLException;

public class TicketServicePublisher {
    public static void main(String[] args) throws SQLException {
        String url = "http://localhost:8080/ticketservice"; // URL où le service sera publié
        TicketServiceImpl ticketService = new TicketServiceImpl();
        Endpoint.publish(url, ticketService);
        System.out.println("TicketService est publié et en cours d'exécution à l'adresse : " + url);
    }
}
