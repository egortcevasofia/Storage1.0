package model;

import java.io.Serializable;
import java.util.Objects;

public class Good implements Serializable {
    private int id;
    private String name;
    private String type;
    private double price;
    private int quantity;
    private Provider provider;

    public Good(String name, String type, double price, int quantity, Provider provider) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.provider = provider;
    }

    public Good() {
    }

    public int getId() { return id;}
    public void setId(int id) { this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getType() {return type;}
    public void setType(String type) {this.type = type;}

    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}

    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {this.quantity = quantity;}

    public Provider getProvider() {return provider;}

    public void setProvider(Provider provider) {this.provider = provider;}

    @Override
    public String toString() {
        return id + " "  + name + " "  + type + " " + price + " " + quantity + " " + provider.getProviderId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Good good = (Good) o;
        return id == good.id &&
                Double.compare(good.price, price) == 0 &&
                quantity == good.quantity &&
                Objects.equals(name, good.name) &&
                Objects.equals(type, good.type) &&
                Objects.equals(provider, good.provider);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, price, quantity, provider);///удалить все кроме id
    }
}
