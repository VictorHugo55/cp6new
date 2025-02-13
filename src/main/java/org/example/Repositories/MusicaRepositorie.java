package org.example.Repositories;

import org.example.Entities.Album;
import org.example.Log.Loggable;
import org.example.Entities.Musica;
import org.example.infrastructure.DataBaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MusicaRepositorie implements GenericRepositorie<Musica>, Loggable<Musica>  {

    private List<Musica> musicas = new ArrayList<>();
    private AlbumRepositorie albumRepositorie = new AlbumRepositorie();

    public MusicaRepositorie() {
        this.musicas = new ArrayList<>();
    }

    @Override
    public void add(Musica musica) {
        String sql = "INSERT INTO MUSICA (titulo_musica, duracao, album_id) VALUES (?, ? ,?)";

        try(Connection conn = DataBaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, musica.getTitulo());
            stmt.setDouble(2, musica.getDuracao());
            stmt.setInt(3, musica.getAlbum().getId());

            stmt.executeUpdate();
            logInfo("Inserted song successfully");

        }catch (SQLException e){
            e.printStackTrace();
            logInfo("Error insert song!");
        }

    }

    @Override
    public ArrayList<Musica> exibir() {
        String sql = "SELECT * FROM MUSICA";

        ArrayList<Musica> musicas = new ArrayList<>();

        try(Connection conn = DataBaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

            while(rs.next()){
                Musica musica = new Musica();
                musica.setId(rs.getInt("id_musica"));
                musica.setTitulo(rs.getString("titulo_musica"));
                musica.setDuracao(rs.getDouble("duracao"));

                Album album = albumRepositorie.findById(rs.getInt("album_id"));
                musica.setAlbum(album);
                musicas.add(musica);
            }
        }catch (SQLException e){
            e.printStackTrace();
            logInfo("Error displaying Songs");
        }
        return musicas;
    }

    @Override
    public void edit(Musica musica) {
        String sql = "UPDATE MUSICA SET titulo = ?, duracao =?, album_id= ? WHERE id = ?";

        try(Connection conn = DataBaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, musica.getTitulo());
            stmt.setDouble(2, musica.getDuracao());
            stmt.setInt(3, musica.getAlbum().getId());
            stmt.setInt(4, musica.getId());

            int rowsUpdated = stmt.executeUpdate();
            if(rowsUpdated > 0){
                logInfo("song updated successfully!");
            }
        }catch (SQLException e){
            e.printStackTrace();
            logInfo("Error updating music");
        }
    }

    @Override
    public void delete(int id_musica) {
        String sql = "DELETE FROM MUSICA WHERE id_musica = ?";

        try(Connection conn = DataBaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id_musica);
            int rowsDeleted = stmt.executeUpdate();
            if(rowsDeleted > 0){
                logInfo("Song deleated");
            }
        }catch (SQLException e){
            e.printStackTrace();
            logInfo("Error when deleting Music");
        }

    }

    @Override
    public Musica findById(int id_musica) {
        String sql = "SELECT id_musica, titulo_musica, ducacao, id_album as album_id, titulo_album as album_nome FROM MUSICA JOIN ALBUM on album_id WHERE id_muisca=?";
        Musica musica = null;

        try(Connection conn = DataBaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1,id_musica);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                musica = new Musica();
                musica.setId(rs.getInt("id_musica"));
                musica.setTitulo(rs.getString("titulo_musica"));
                musica.setDuracao(rs.getDouble("duracao"));

                Album album = albumRepositorie.findById(rs.getInt("album_id"));

                musica.setAlbum(album);
                return musica;
            }
        }catch (SQLException e){
            e.printStackTrace();
            logInfo("Error when finding by id");
        }
        return musica;
    }
}
