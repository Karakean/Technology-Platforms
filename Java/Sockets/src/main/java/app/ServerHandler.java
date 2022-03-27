package app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerHandler implements Runnable{
    Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())){
            oos.writeObject("ready");
            Integer n = (Integer) ois.readObject();
            oos.writeObject("ready to receive messages");
            for (int i=0; i<n; i++){
                Message input = (Message) ois.readObject();
                System.out.println(input.getContent());
            }
            oos.writeObject("done");
        }
        catch (IOException | ClassNotFoundException ex){
            System.err.println(ex);
        }
        finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
