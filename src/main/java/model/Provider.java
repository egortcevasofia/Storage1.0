package model;

import java.io.Serializable;
import java.util.List;

public class Provider implements Serializable {
    private int providerId;
    private String name;
    private List<Integer> listOfGoodId; //список гудсов которые поставляет провайдер


    public Provider(int providerId) {
        this.providerId = providerId;
    }

    public Provider(String name) {
        this.name = name;
    }

    public Provider(int providerId, String name) {
        this.providerId = providerId;
        this.name = name;
    }

    public Provider() {
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public List<Integer> getListOfGoodId() {
        return listOfGoodId;
    }

    public void setListOfGoodId(List<Integer> listOfGoodId) {
        this.listOfGoodId = listOfGoodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return providerId + " " + name;
    }
}
