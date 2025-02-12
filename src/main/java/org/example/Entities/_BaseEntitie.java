package org.example.Entities;

public class _BaseEntitie {
    public int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "_BaseEntitie{" +
                "id=" + id +
                '}';
    }
}
