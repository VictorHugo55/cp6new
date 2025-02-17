package org.example.Repositories;

import org.example.Entities._BaseEntitie;

import java.util.ArrayList;

public abstract class ImplRepositorie <T extends _BaseEntitie> implements GenericRepositorie<T> {

    ArrayList<T> list = new ArrayList<>();
    @Override
    public void add(T entitie) {
        list.add(entitie);
    }

    @Override
    public ArrayList<T> display() {
        return list;
    }

    @Override
    public void edit(T entitie) {
        T entitieUpdated = list.stream().filter(x -> x.getId() == entitie.getId()).findFirst().get();
        list.remove(entitieUpdated);
        list.add(entitie);
    }

    @Override
    public void delete(int id) {
        list.remove(id);
    }


    public ImplRepositorie(ArrayList<T> list) {
        this.list = this.list;
    }

}
