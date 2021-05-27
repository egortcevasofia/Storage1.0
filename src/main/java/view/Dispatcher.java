package view;

import controller.Controller;
import controller.GoodController;
import controller.ProviderController;
import controller.StoreController;
import parser.RequestParser;
import repository.GoodRepository;
import repository.ProviderRepository;
import repository.StoreRepository;

import java.util.HashMap;
import java.util.Map;


public class Dispatcher {

    private final RequestParser parser;

    private static final Map<String, Controller> map;

    static {
        map = new HashMap<String, Controller>();
        map.put("good", new GoodController(new GoodRepository()));
        map.put("provider", new ProviderController(new ProviderRepository()));
        map.put("store", new StoreController(new StoreRepository()));


        // provider mappings
        // customer mappings
        // store mappings
    }

    // (http get) - get(read) resource (-r) request looks like (good/get/id) or (good/get/type) or (good/get)
    // (http post) - create resource   (-c) request looks like (good/post/name/type/price/quantity/provider)
    // (http put) - update resource    (-u) request looks like (good/put/id/name/type/price/quantity/provider)
    // (http delete) - delete resource (-d) request looks like (good/delete/id)


    // (http get) - get(read) resource (-r) request looks like (provider/get/id) or (provider/get)
    // (http post) - create resource   (-c) request looks like (provider/post/name)
    // (http put) - update resource    (-u) request looks like (provider/put/id/name)
    // (http delete) - delete resource (-d) request looks like (provider/delete/id)


    // (http get) - get(read) resource (-r) request looks like (store/getgoods) or (store/getproviders)
    // (http put) - update resource    (-u) request looks like (store/put/idOfGood)//добавляет товар на склад
    // (http delete) - delete resource (-d) request looks like (store/delete/idOfGood)//удаляет товар со склада

    public Dispatcher(RequestParser parser) {
        this.parser = parser;
    }

    public void changeController(String request) {
        String[] elements = parser.processRequest(request);
        String mapping = elements[0];
        Controller controller = map.get(mapping);
        String response = String.format("mapping: %s\nhttp method: %s\noutput: "
                + controller.mapRequestToMethod(elements), elements[0], elements[1]);
        ConsoleView.printToConsole(response);
    }

}
