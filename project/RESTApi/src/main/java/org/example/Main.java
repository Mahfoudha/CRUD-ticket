package org.example;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.ApplicationPath;
@ApplicationPath("/tickets")
public class Main {
    public static void main(String[] args) throws IOException {
        // Définir l'URI de base pour le serveur
        URI baseUri = URI.create("http://localhost:2233");

        // Créer une nouvelle configuration de ressource Jersey
        ResourceConfig config = new ResourceConfig();

        // Enregistrer la classe de ressource principale
        config.register(TicketResource.class);

        // Enregistrer le fournisseur Jackson pour la sérialisation/désérialisation JSON
        config.register(JacksonJsonProvider.class);

        // Créer et démarrer le serveur HTTP Grizzly avec la configuration des ressources Jersey
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);

        // Afficher un message indiquant que l'application Jersey a démarré
        System.out.println("Jersey app started at " + baseUri);

        // Attendre que le serveur soit arrêté
        System.in.read();

        // Arrêter le serveur lorsque l'application est terminée
        server.shutdownNow();
    }
}
