import services.MovieShop;
import services.MovieShopImpl;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServer {
    private static ServerSocket server;
    private static Socket socket;
    private static Scanner scanner;
    private static ObjectOutputStream objectOutputStream;
    private static ObjectInputStream objectInputStream;
    private static MovieShop service;
    public static void main(String[] args) {
        try {
           connection(new MovieShopImpl());
           int command=-1;
           while (!socket.isClosed()){
               command=objectInputStream.readInt();
               System.out.println(command);
               executeCommand(command);
           }
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void connection(MovieShop service) throws IOException {
        server = new ServerSocket(3345);
        System.out.println("Waiting for a client...");
        socket = server.accept();
        System.out.println("Client connected");
        scanner = new Scanner(System.in);
        objectInputStream=new ObjectInputStream(socket.getInputStream());
        objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        SocketServer.service=service;
    }
    private static void executeCommand(int command) throws IOException {
        switch (command){
            case 0:
                objectOutputStream.writeObject(service.getShop());
                objectOutputStream.flush();
                break;
            default:
                socket.close();
                server.close();
        }
    }
}
