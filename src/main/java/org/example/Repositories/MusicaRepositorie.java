package org.example.Repositories;

import org.example.Log.Loggable;
import org.example.Entities.Musica;

import java.util.ArrayList;

public class MusicaRepositorie implements GenericRepositorie<Musica>, Loggable<Musica>  {


    @Override
    public void add(Musica entitie) {

    }

    @Override
    public ArrayList<Musica> exibir() {
        return null;
    }

    @Override
    public void edit(Musica entitie) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Musica findById(int id) {
        return null;
    }
}
