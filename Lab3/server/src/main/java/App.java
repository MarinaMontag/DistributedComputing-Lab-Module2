import manager.SocketServer;

public class App {
    public static void main(String[] args) {
        SocketServer server=new SocketServer(3345);
        server.work();
    }
}
