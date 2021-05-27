package controller;

import model.Good;
import model.Provider;
import repository.GoodRepository;

import java.util.List;

public class GoodController implements Controller<Good> {
    private final GoodRepository goodRepository;

    public GoodController(GoodRepository goodRepository) {
        this.goodRepository = goodRepository;
    }

    @Override
    public Good create(String... elements) {
        String name = elements[2];
        String type = elements[3];
        double price = Double.parseDouble(elements[4]);
        int quantity = Integer.parseInt(elements[5]);
        int providerId = Integer.parseInt(elements[6]);
        return goodRepository.create(new Good(name, type, price, quantity, new Provider(providerId)));
    }

    @Override
    public Good readById(int id) {
        Good good = goodRepository.readById(id);
        return good;
    }

    @Override
    public List<Good> readAll() {
        return goodRepository.readAll();
    }

    @Override
    public List<Good> readAllByType(String type) { return goodRepository.readAllByType(type); }


    @Override
    public void updateById(int id, String... elements) {
        String name = elements[3];
        String type = elements[4];
        double price = Double.parseDouble(elements[5]);
        int quantity = Integer.parseInt(elements[6]);
        int providerId = Integer.parseInt(elements[7]);

        Good good = new Good(name, type, price, quantity, new Provider(providerId));
        goodRepository.updateById(id, good);
    }

    @Override
    public void deleteById(int id) {
        goodRepository.deleteById(id);
    }

    @Override
    public Object mapRequestToMethod(String... elements) {
        String httpMethod = elements[1];
        switch (httpMethod) {
            case "get":
                if (elements.length == 2) {
                    this.readAll();
                } else if (elements.length == 3) {
                    this.readById(Integer.parseInt(elements[2]));

                } else {
                    throw new RuntimeException();
                }
                break;
            case "post":
                this.create(elements);
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
