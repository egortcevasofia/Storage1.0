package controller;

import model.Store;
import repository.StoreRepository;
import utils.FileWriterUtility;

import java.util.List;

public class StoreController implements Controller{
    private final StoreRepository storeRepository;


    public StoreController(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public void readAllGoods() {
        storeRepository.readAllGoods();
    }
    public void readAllProviders() {
        storeRepository.readAllProviders();
    }

    @Override
    public Object create(String... elements) { return null; }

    @Override
    public Object readById(int id) {
        return null;
    }

    @Override
    public List readAll() {
        return null;
    }

    @Override
    public List readAllByType(String type) {
        return null;
    }

    @Override
    public void updateById(int id, String... elements) {
        storeRepository.addGoodToStore(id);
    }

    @Override
    public void deleteById(int id) {
        storeRepository.deliteGoodFromStoreById(id);
    }



    @Override
    public Object mapRequestToMethod(String... elements) {
        String httpMethod = elements[1];
        switch (httpMethod) {
            case "getgoods":
                this.readAllGoods();
                break;
            case "getproviders":
                this.readAllProviders();
                break;
            case "put": {
                int id = Integer.parseInt(elements[2]);
                this.updateById(id, elements);
                break;
            }
            case "delete": {
                int id = Integer.parseInt(elements[2]);
                this.deleteById(id);
                break;
            }
            default:
                throw new RuntimeException();
        }
        return new Object();
    }
}
