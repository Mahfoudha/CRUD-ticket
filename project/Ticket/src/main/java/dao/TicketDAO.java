package dao;

import util.DbConnection;
import model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    private Connection connection;

    public TicketDAO() throws SQLException {
        connection = DbConnection.getInstance().getConnection();
    }

    public void ajouterTicket(Ticket ticket) {
        String query = "INSERT INTO tickets (nom_film, date, heure, salle, prix) VALUES (?, ?, ?, ?, ?)";
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

    public void supprimerTicket(int id) {
        String query = "DELETE FROM tickets WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierTicket(Ticket ticket) {
        String query = "UPDATE tickets SET nom_film = ?, date = ?, heure = ?, salle = ?, prix = ? WHERE id = ?";
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

    public List<Ticket> listerTickets() {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM tickets";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getInt("id"));
                ticket.setNomFilm(resultSet.getString("nom_film"));
                ticket.setDate(resultSet.getString("date"));
                ticket.setHeure(resultSet.getString("heure"));
                ticket.setSalle(resultSet.getString("salle"));
                ticket.setPrix(resultSet.getDouble("prix"));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }
}
