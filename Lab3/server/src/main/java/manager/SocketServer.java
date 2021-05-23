package manager;

import services.CommandsManager;
import services.CommandsManagerImpl;
import services.MovieShop;
import services.MovieShopImpl;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServer {
    private int port;
    private ServerSocket server;
    private Socket socket;
    private Scanner scanner;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private MovieShop service;
    private CommandsManager manager;

    public SocketServer(int port) {
        this.port = port;
    }

    public void work() {
        try {
           connection();
           int command=-1;
           while (!socket.isClosed()){
                   command=objectInputStream.readInt();
                   System.out.println(command);
                   executeCommand(command);
           }
        }
        catch(IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private  void connection() throws IOException {
        server = new ServerSocket(port);
        System.out.println("Waiting for a client...");
        socket = server.accept();
        System.out.println("Client connected");
        scanner = new Scanner(System.in);
        objectInputStream=new ObjectInputStream(socket.getInputStream());
        objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        service=new MovieShopImpl();
        manager=new CommandsManagerImpl(service, objectOutputStream, objectInputStream);
    }
    private void executeCommand(int command) throws IOException, ClassNotFoundException {
        switch (command) {
            case 0 -> manager.sendShopInfo();
            case 1 -> manager.insertGenre();
            case 2 -> manager.insertMovie();
            case 3 -> manager.deleteGenre();
            case 4 -> manager.deleteMovie();
            case 5 -> manager.editMovie();
            case 6 -> manager.sendAmountOfFilmsOCertainGenre();
            case 7 -> manager.sendMovie();
            case 8 -> manager.sendListOfFilms();
            case 9 -> manager.sendListOfGenres();
            default -> {
                socket.close();
                server.close();
            }
        }
    }
}
