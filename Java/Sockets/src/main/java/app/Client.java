package app;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket client = new Socket("localhost", 9797)) {
            try(ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(client.getInputStream())){
                    String response = (String) ois.readObject();
                    if (!response.equals("ready")){
                        System.out.println("Error: not ready");
                        return;
                    }

                    oos.writeObject(5);
                    response = (String) ois.readObject();
                    if (!response.equals("ready to receive messages")) {
                        System.out.println("Error: not ready to receive messages");
                        return;
                    }

                    for (int i=0; i<5; i++) {
                        oos.writeObject(new Message(i,"message"));
                    }

                    response = (String) ois.readObject();
                    if (!response.equals("done")) {
                        System.out.println("Error");
                    }
                }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }
}
