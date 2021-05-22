import javafx.util.Pair;
import model.Genre;
import model.Movie;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientManager {
    private static Socket socket;
    private static Scanner scanner;
    private static ObjectOutputStream objectOutputStream;
    private static ObjectInputStream objectInputStream;
    private static final int COMMAND_TO_DISPLAY_SHOP = 0;
    public static void main(String[] args) {
        try {
           connection();
            int command=-1;
           while(!socket.isClosed()){
               objectOutputStream.writeInt(COMMAND_TO_DISPLAY_SHOP);
               objectOutputStream.flush();
               Pair<ArrayList<Genre>, ArrayList<Movie>>pair=(Pair<ArrayList<Genre>, ArrayList<Movie>>)objectInputStream.readObject();
               System.out.println(pair);
               menu();
               command = scanner.nextInt();
               scanner.nextLine();
               objectOutputStream.writeInt(command);
               objectOutputStream.flush();
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               socket.close();
           }

        }
        catch(IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void connection() throws IOException {
        System.out.println("Connecting to server...");
        socket = new Socket("localhost",3345);
        System.out.println("Connected");
        scanner = new Scanner(System.in);
        objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        objectInputStream=new ObjectInputStream(socket.getInputStream());

    }
    private static void menu(){
        System.out.println("Choose operation: ");
        System.out.println("1 -> Add new genre");
        System.out.println("2 -> Add new movie");
        System.out.println("3 -> Delete genre");
        System.out.println("4 -> Delete movie");
        System.out.println("5 -> Edit genre");
        System.out.println("6 -> Edit movie characteristics");
        System.out.println("7 -> Search genre by id");
        System.out.println("8 -> Search movie by id");
        System.out.println("9 -> Show all genres");
        System.out.println("10 -> Show all movies by genre id");
        System.out.println("11 -> EXIT");
        System.out.println();
        System.out.print("Choose: ");
    }

    private static void makeRequest(int command){
//        switch (command){
//            case
//        }
    }
}
