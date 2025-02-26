package org.example.Repositories;

import org.example.Entities.Album;
import org.example.Entities.Artista;
import org.example.Log.Loggable;
import org.example.infrastructure.DataBaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlbumRepositorie implements GenericRepositorie<Album>, Loggable<Album> {
    private List<Album> albums = new ArrayList<>();

    ArtistaRepositorie artistaRepositorie = new ArtistaRepositorie();

    @Override
    public void add(Album album) {

        String sql = "INSERT INTO Album(titulo_album, ano_lancamento_album, artista_id) VALUES(?, ?, ?)";

        try (Connection conn = DataBaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, album.getTitulo());
            stmt.setInt(2, album.getAnoLancamento());
            stmt.setInt(3, album.getArtista().getId());

            stmt.executeUpdate();
            logInfo("Album registered successfully!");
        }catch (SQLException e){
            e.printStackTrace();
            logInfo("Error registering album");
        }

    }

    @Override
    public ArrayList<Album> display() {
        String sql = "SELECT * FROM Artista";
        ArrayList<Album>albums = new ArrayList<>();
        try(Connection conn = DataBaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()){

            while (rs.next()){
                Album album = new Album();
                album.setId(rs.getInt("id_album"));
                album.setTitulo(rs.getString("titulo_album"));
                album.setAnoLancamento(rs.getInt("ano_lancamento_album"));

                Artista artista = artistaRepositorie.findById(rs.getInt("artista_id"));
            }

        }
        return albums;
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
