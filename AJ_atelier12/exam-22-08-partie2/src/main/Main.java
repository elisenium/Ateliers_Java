package main;

import domain.RequestFactoryImpl;

public class Main {

    public static void main(String[] args) {
        Server server = new Server();
        server.setFactory(new RequestFactoryImpl());
        server.listenKeyboard();
    }

}
