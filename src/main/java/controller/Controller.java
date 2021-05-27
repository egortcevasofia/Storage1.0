package controller;

import java.util.List;

public interface Controller<E> {
    E create(String... elements);
    E readById(int id);
    List<E> readAll();
    List<E> readAllByType(String type);
    void updateById(int id, String... elements);
    void deleteById(int id);
    Object mapRequestToMethod(String... elements);
}
