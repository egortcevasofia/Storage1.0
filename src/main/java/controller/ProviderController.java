package controller;

import model.Provider;
import repository.ProviderRepository;
import utils.FileWriterUtility;

import java.util.List;

public class ProviderController implements Controller<Provider> {
    private final ProviderRepository providerRepository;


    public ProviderController(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }


    @Override
    public Provider create(String... elements) {
        String name = elements[2];
        return providerRepository.create(new Provider(name));
    }


    @Override
    public Provider readById(int id) {
        Provider provider = providerRepository.readById(id);
        System.out.println(provider);// потом убрать
        System.out.println(provider.getListOfGoodId());// потом убрать
        return provider;
    }

    @Override
    public List<Provider> readAll() {
        return providerRepository.readAll();
    }

    @Override
    public List<Provider> readAllByType(String type) {
        return null;
    }

    @Override
    public void updateById(int id, String... elements) {
        String name = elements[3];
        providerRepository.updateById(id, new Provider(name));
    }

    @Override
    public void deleteById(int id) {
        providerRepository.deliteById(id);

    }

    @Override
    public Object mapRequestToMethod(String... elements) {
        String httpMethod = elements[1];
        switch (httpMethod) {
            case "get":
                if (elements.length == 2) {
                    this.readAll();
                } else if (elements.length == 3) {
                    if (FileWriterUtility.isNumber(elements[2])) {
                        readById(Integer.parseInt(elements[2]));
                    } else {
                        readAllByType(elements[2]);
                    }
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
