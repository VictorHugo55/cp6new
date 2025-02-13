package org.example.Repositories;

import org.example.Entities.Album;
import org.example.Log.Loggable;

import java.util.ArrayList;

public class AlbumRepositorie implements GenericRepositorie<Album>, Loggable<Album> {
    @Override
    public void add(Album entitie) {

    }

    @Override
    public ArrayList<Album> exibir() {
        return null;
    }

    @Override
    public void edit(Album entitie) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Album findById(int id) {
        return null;
    }
}
