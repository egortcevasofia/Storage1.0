package parser;

public class RequestParser {

    public String[] processRequest(String request) {
        String[] elements = request.split("/");
        for (int i = 0; i < elements.length; i++) {
            elements[i] = elements[i].replace(" ", "");
        }
        //Arrays.stream(elements).forEach(element -> {element = element.replace(" ", "");});
        return elements;
    }
}
