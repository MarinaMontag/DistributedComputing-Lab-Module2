package manager;

import services.CommandsManager;
import services.CommandsManagerImpl;
import services.MovieShop;
import services.MovieShopImpl;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientManager {
    private Socket socket;
    private Scanner scanner;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private MovieShop service;
    private CommandsManager manager;
    private int port;
    public ClientManager(int port) throws IOException {
        this.port=port;
    }

    private void connection() throws IOException {
        System.out.println("Connecting to server...");
        socket = new Socket("localhost", port);
        System.out.println("Connected");
        scanner = new Scanner(System.in);
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        service = new MovieShopImpl();
        manager = new CommandsManagerImpl(service, objectOutputStream, objectInputStream);
    }

    public void work() {
        try {
            connection();
            int command = -1;
            while (!socket.isClosed()) {
                manager.displayShop();
                menu();
                command = getCommand();
                executeCommand(command);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    private static void menu() {
        System.out.println("Choose operation: ");
        System.out.println("1 -> Add new genre");
        System.out.println("2 -> Add new movie");
        System.out.println("3 -> Delete genre");
        System.out.println("4 -> Delete movie");
        System.out.println("5 -> Edit movie characteristics");
        System.out.println("6 -> Amount of films of a certain genre");
        System.out.println("7 -> Search movie by name");
        System.out.println("8 -> Show list of films of a certain genre");
        System.out.println("9 -> Show list of genres");
        System.out.println("10 -> EXIT");
        System.out.println();
        System.out.print("Choose: ");
    }

    private int getCommand() {
        int command = scanner.nextInt();
        scanner.nextLine();
        return command;
    }
    private void executeCommand(int command) throws IOException, ClassNotFoundException {
        switch (command) {
            case 1 -> manager.insertGenre();
            case 2 -> manager.insertMovie();
            case 3 -> manager.deleteGenre();
            case 4 -> manager.deleteMovie();
            case 5 -> manager.editMovie();
            case 6 -> manager.showAmountOfFilmsOfCertainGenre();
            case 7 -> manager.showMovie();
            case 8 -> manager.showListOfFilms();
            case 9 -> manager.showListOfGenres();
            default -> socket.close();
        }
    }
}
