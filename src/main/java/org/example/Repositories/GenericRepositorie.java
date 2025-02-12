package org.example.Repositories;

import java.util.ArrayList;

public interface GenericRepositorie <T>{

    void add(T entitie);
    ArrayList<T> exibir();
    void edit(T entitie);
    void delete(int id);

    T findById(int id);
}
