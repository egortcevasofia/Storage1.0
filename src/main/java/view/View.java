package view;



import java.util.Scanner;

public class View {
    private final Dispatcher dispatcher;

    public View(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void handleRequest() {
        Scanner scanner = new Scanner(System.in);

        String request = "";

        while((request = scanner.nextLine()) != null &&
                !(request.equalsIgnoreCase("exit"))) {

            dispatcher.changeController(request);
        }
    }




}
