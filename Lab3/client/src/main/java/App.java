import manager.ClientManager;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        try {
            ClientManager manager=new ClientManager(3345);
            manager.work();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
