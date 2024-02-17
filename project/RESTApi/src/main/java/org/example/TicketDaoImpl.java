package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDaoImpl implements TicketDao {

    private Connection connection;

    public TicketDaoImpl() {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            System.err.println("Error while establishing database connection:");
            e.printStackTrace();
            throw new RuntimeException("Failed to establish database connection", e);
        }
    }

    @Override
    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM ticket";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nomFilm = resultSet.getString("nomFilm");
                String date = resultSet.getString("date");
                String heure = resultSet.getString("heure");
                String salle = resultSet.getString("salle");
                double prix = resultSet.getDouble("prix");
                Ticket ticket = new Ticket(id, nomFilm, date, heure, salle, prix);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public Ticket getTicketById(int id) {
        Ticket ticket = null;
        String query = "SELECT * FROM ticket WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String nomFilm = resultSet.getString("nomFilm");
                    String date = resultSet.getString("date");
                    String heure = resultSet.getString("heure");
                    String salle = resultSet.getString("salle");
                    double prix = resultSet.getDouble("prix");
                    ticket = new Ticket(id, nomFilm, date, heure, salle, prix);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;
    }

    @Override
    public void addTicket(Ticket ticket) {
        String query = "INSERT INTO ticket(nomFilm, date, heure, salle, prix) VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, ticket.getNomFilm());
            preparedStatement.setString(2, ticket.getDate());
            preparedStatement.setString(3, ticket.getHeure());
            preparedStatement.setString(4, ticket.getSalle());
            preparedStatement.setDouble(5, ticket.getPrix());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTicket(Ticket ticket) {
        String query = "UPDATE ticket SET nomFilm = ?, date = ?, heure = ?, salle = ?, prix = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, ticket.getNomFilm());
            preparedStatement.setString(2, ticket.getDate());
            preparedStatement.setString(3, ticket.getHeure());
            preparedStatement.setString(4, ticket.getSalle());
            preparedStatement.setDouble(5, ticket.getPrix());
            preparedStatement.setInt(6, ticket.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProject(int id) {

    }

    @Override
    public void deleteTicket(int id) {
        String query = "DELETE FROM ticket WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
