package org.example.Repositories;

import oracle.jdbc.proxy.annotation.Pre;
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
                album.setArtista(artista);
                albums.add(album);
            }
        }catch (SQLException e){
            e.printStackTrace();
            logInfo("Error displaying Album");
        }
        return albums;
    }

    @Override
    public void edit(Album album) {
        String sql = "UPDATE ALBUM SET titulo_album = ?, ano_lancamento_album = ?, artista_id = ? WHERE id_album = ?";

        try(Connection conn = DataBaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, album.getTitulo());
            stmt.setInt(2, album.getAnoLancamento());
            stmt.setInt(3, album.getArtista().getId());
            stmt.setInt(4, album.getId());

            int rowsUpdate = stmt.executeUpdate();
            if(rowsUpdate > 0){
                logInfo("Album updated sucessfully!");
            }
        }catch (SQLException e){
            e.printStackTrace();
            logInfo("Error updating album");
        }
    }

    @Override
    public void delete(int id_album) {
        String sql = "DELETE FROM ALBUM WHERE id_album = ?";

        try(Connection conn = DataBaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id_album);
            int rowsDeleted = stmt.executeUpdate();
            if(rowsDeleted > 0){
                logInfo("Album deleted");
            }
        }catch (SQLException e){
            e.printStackTrace();
            logInfo("Error when deleting album");
        }
    }

    @Override
    public Album findById(int id_album) {
        String sql = "SELECT id_album, titulo_album, ano_lancamento_album" +
                ", id AS artista_id, nome AS artista_nome, FROM ALBUM JOIN ARTISTA ON artista_id = id WHERE id_album = ?";
        Album album = new Album();
        try(Connection conn = DataBaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id_album);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                album = new Album();
                album.setId(rs.getInt("id_album"));
                album.setTitulo(rs.getString("titulo_album"));
                album.setAnoLancamento(rs.getInt("ano_lancamento_album"));

                Artista artista = artistaRepositorie.findById(rs.getInt("artista_id"));

                album.setArtista(artista);
                return album;
            }
        }catch (SQLException e){
            e.printStackTrace();
            logInfo("Error when finding by id");
        }
        return album;
    }

    public List<Album> findByYear(int ano){
        List<Album> albums = new ArrayList<>();

        String sql = "SELECT id_album, titulo_album FROM ALBUM WHERE ano_lancamento_album = ?";

        try(Connection conn = DataBaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, ano);
            ResultSet rs = stmt.executeQuery();
        }
        return albums;
    }
}
