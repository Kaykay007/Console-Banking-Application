package org.example.Server;

public class Server {
    public static void main(String[] args) {
        ServerHandler serverHandler = new ServerHandler();
        serverHandler.startServer(8080);
    }
}
