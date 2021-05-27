import parser.RequestParser;
import view.Dispatcher;
import view.View;


public class Main {
    public static void main(String[] args) {
        Dispatcher dispatcher = new Dispatcher(new RequestParser());

        View goodView = new View(dispatcher);


        goodView.handleRequest();
    }

}
