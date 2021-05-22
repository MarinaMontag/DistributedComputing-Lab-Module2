import model.MovieShop;
import service.MovieShopServiceImpl;

public class App {
    public static void main(String[] args) {
        MovieShop shop=MovieShop.getInstance(new MovieShopServiceImpl());
        shop.work();
    }
}
