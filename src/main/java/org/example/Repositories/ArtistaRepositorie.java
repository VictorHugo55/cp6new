package org.example.Repositories;

import org.example.Entities.Artista;
import org.example.Log.Loggable;

import java.util.ArrayList;

public class ArtistaRepositorie implements GenericRepositorie<Artista>, Loggable<Artista> {
    @Override
    public void add(Artista artista) {
        String sql = "INSERT INTO Artista(Nome"

    }

    @Override
    public ArrayList<Artista> exibir() {
        return null;
    }

    @Override
    public void edit(Artista entitie) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Artista findById(int id) {
        return null;
    }
}
