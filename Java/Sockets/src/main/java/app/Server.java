package app;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9797)) {
            Socket socket = server.accept();
            System.out.println("Witamy na nowym serwerze minecraft");
            Thread thread = new Thread(new ServerHandler(socket));
            thread.start();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
