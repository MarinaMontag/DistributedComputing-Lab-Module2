import javafx.util.Pair;
import model.Genre;
import model.Movie;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager {
    private static Socket socket;
    private static BufferedReader bufferedReader;
    private static ObjectOutputStream objectOutputStream;
    private static ObjectInputStream objectInputStream;
    private static final int COMMAND_TO_DISPLAY_SHOP = 0;
    public static void main(String[] args) {
        try {
           connection();
           while(!socket.isClosed()){
               objectOutputStream.write(COMMAND_TO_DISPLAY_SHOP);
               objectOutputStream.flush();
              // menu();
           }
           socket.close();
        }
        catch(IOException e) {}
    }
    private static void connection() throws IOException {
        System.out.println("Connecting to server...");
        socket = new Socket("localhost",3345);
        System.out.println("Connected");
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        objectInputStream=new ObjectInputStream(socket.getInputStream());
        objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
    }
}
