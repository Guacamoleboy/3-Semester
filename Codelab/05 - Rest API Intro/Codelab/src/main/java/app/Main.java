package app;

import app.server.Server;

public class Main {

    // Attributes

    // ____________________________________________________________

    public static void main(String[] args) {

        // Server start @7070
        Server server = new Server();
        server.start(7070);

    }

}