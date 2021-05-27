package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Store implements Serializable {
    private int id;
    private final String name;
    private List<Good> listOFGoods = new ArrayList<>();// хранит товар и его количество, берет инфу из гудрепозитория
    private List<Provider> listOfProviders= new ArrayList<>();// берет инфу из провайдер репозиторий


    public Store(String name) {
        this.name = name;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public List<Good> getListOFGoods() { return listOFGoods; }

    public void setListOFGoods(List<Good> listOFGoods) { this.listOFGoods = listOFGoods; }

    public List<Provider> getListOfProviders() { return listOfProviders; }

    public void setListOfProviders(List<Provider> listOfProviders) { this.listOfProviders = listOfProviders; }
}