package org.example.Entities;

import java.util.ArrayList;
import java.util.List;

public class Album extends _BaseEntitie{

    private String titulo;
    private int anoLancamento;
    private Artista artista;

    private List<Musica> musicas = new ArrayList<>();

    public Album() {
    }

    public Album(String titulo, int anoLancamento, Artista artista) {
        this.titulo = titulo;
        this.anoLancamento = anoLancamento;
        this.artista = artista;
    }

    public Album(int idALbum, String tituloAlbum) {
        super();
        this.id = idALbum;
        this.titulo = tituloAlbum;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    @Override
    public String toString() {
        return "Album{" +
                "titulo='" + titulo + '\'' +
                ", anoLancamento=" + anoLancamento +
                ", artista=" + artista +
                ", musicas=" + musicas +
                ", id=" + id +
                '}';
    }
}
